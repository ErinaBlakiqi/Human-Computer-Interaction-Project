package controllers;

import application.Navigator;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Order;
import model.dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CartService;
import services.DBConnector;
import services.OrderService;
import services.ProductService;
import model.Cart;
import utils.SessionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductController {

    @FXML
    private TableView<Cart> tableView_Products;

    @FXML
    private TableColumn<Cart, Integer> col_Price_Products;

    @FXML
    private TableColumn<Cart, String> col_Product_Products;

    @FXML
    private TableColumn<Cart, Integer> col_Quantity_Products;

    @FXML
    private TableColumn<ProductDTO, String> colAction_products;

    @FXML
    private TableColumn<ProductDTO, String> colCategory_products;

    @FXML
    private TableColumn<ProductDTO, Integer> colPrice_products;

    @FXML
    private TableColumn<ProductDTO, String> colProductName_products;

    @FXML
    private TableColumn<ProductDTO, String> colSeller_products;

    @FXML
    private TableView<ProductDTO> tableProductsPage;

    @FXML
    private TextField fieldProductSearch;

    @FXML
    private Label txtTotal_Products;

    @FXML
    private ChoiceBox<String> listPayment_Products;

    @FXML
    private Button btnOrder_Products;

    @FXML
    private Button btnRemove_Products;

    @FXML
    private Label statusLabel;

    @FXML
    private Label userLabel;

    private int timeRemaining = 900;
    private Timeline timeline;

    private ProductService productService;
    private CartService cartService;
    private OrderService orderService;

    private int currentUserId = SessionManager.getCurrentUserId(); // This should be dynamically set based on the logged-in user

    public ProductController() throws SQLException {
        Connection connection = DBConnector.getConnection();
        productService = new ProductService();
        cartService = new CartService();
        orderService = new OrderService();
    }

    @FXML
    public void initialize() {
        startCountdown();
        String fullName = SessionManager.getLoggedInUser().getUsername();
        userLabel.setText(fullName);
        colProductName_products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSeller_products.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        colPrice_products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory_products.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        col_Product_Products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_Quantity_Products.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_Price_Products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAction_products.setCellFactory(param -> new TableCell<>() {
            private final Button buyButton = new Button("Add");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    buyButton.setOnAction(event -> {
                        ProductDTO product = getTableView().getItems().get(getIndex());
                        addToCart(product);
                    });
                    setGraphic(buyButton);
                    setText(null);
                }
            }
        });

        listPayment_Products.setItems(FXCollections.observableArrayList("OnDelivery", "CreditCard"));
        listPayment_Products.getSelectionModel().selectFirst();

        loadProducts();
        loadCartItems();
    }

    private void startCountdown() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemaining--;
            statusLabel.setText("Active for " + timeRemaining + " seconds" );

            if (timeRemaining <= 0) {
                Navigator.navigate(Navigator.SIGNIN_PAGE);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void loadCartItems() {
        try {
            List<Cart> cartItems = cartService.getCartItemsByUserId(currentUserId);
            ObservableList<Cart> cartObservableList = FXCollections.observableArrayList(cartItems);
            tableView_Products.setItems(cartObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            showFeedback("Error loading cart items.");
        }
    }

    private void loadProducts() {
        try {
            List<ProductDTO> products = productService.getAllProductDTOs();
            ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
            tableProductsPage.setItems(productObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleKeyPress(KeyEvent event) {
        onActionSearch();
    }

    @FXML
    private void onActionSearch() {
        String searchQuery = fieldProductSearch.getText();
        try {
            List<ProductDTO> products = productService.searchProductDTOsByName(searchQuery);
            ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
            tableProductsPage.setItems(productObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addToCart(ProductDTO product) {
        try {
            Cart cart = cartService.getCartByUserIdAndProductId(currentUserId, product.getProductId());
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(currentUserId);
                cart.setProductId(product.getProductId());
                cart.setQuantity(1);
                cartService.addCart(cart);
            } else {
                cart.setQuantity(cart.getQuantity() + 1);
                cartService.updateCart(cart);
            }
            updateTotal();
            loadCartItems();  // Load cart items into the TableView after adding a product
        } catch (SQLException e) {
            e.printStackTrace();
            showFeedback("Error adding product to cart.");
        }
    }

    private void updateTotal() {
        try {
            int total = cartService.getTotalPriceByUserId(currentUserId);
            txtTotal_Products.setText(total + "$");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showFeedback(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onActionOrder() {
        try {
            String paymentMethod = listPayment_Products.getValue();
            List<Cart> cartItems = cartService.getCartItemsByUserId(currentUserId);

            for (Cart cartItem : cartItems) {
                Order order = new Order();
                order.setProductId(cartItem.getProductId());
                order.setBuyerId(currentUserId);
                order.setQuantity(cartItem.getQuantity());
                order.setTotalPrice(cartItem.getQuantity() * cartItem.getPrice());
                order.setOrderStatus("Pending");
                order.setPaymentMethod(paymentMethod);
                orderService.addOrder(order);
            }

            // Clear the cart after placing the order
            cartService.clearCartByUserId(currentUserId);
            loadCartItems(); // Reload the cart items to reflect the empty cart
            updateTotal();   // Update the total price display

            showFeedback("Order placed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showFeedback("Error placing order.");
        }
    }

    @FXML
    private void onActionRemove() {
        Cart selectedCart = tableView_Products.getSelectionModel().getSelectedItem();
        if (selectedCart != null) {
            try {
                cartService.removeCartByUserIdAndProductId(selectedCart.getUserId(), selectedCart.getProductId());
                loadCartItems();
                updateTotal();
                showFeedback("Item removed from cart.");
            } catch (SQLException e) {
                e.printStackTrace();
                showFeedback("Error removing item from cart.");
            }
        } else {
            showFeedback("No item selected.");
        }
    }

    @FXML
    private void navigateToSell(ActionEvent actionEvent) {
        Navigator.navigate(Navigator.SELL_PAGE);
    }

    @FXML
    private void navigateToProfile(ActionEvent actionEvent) {
        Navigator.navigate(Navigator.USER_PAGE);
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        Navigator.navigate(Navigator.SIGNIN_PAGE);
    }

    @FXML
    public void handleChange(ActionEvent actionEvent) {
    }

    @FXML
    private void handleHelp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HelpPopUp.fxml"));
            Parent root = loader.load();

            HelpPopUpController controller = loader.getController();
            controller.setHelpText("This is the Admin Products page.\n\n" +
                    "You can perform the following actions:\n" +
                    "- **Add a new product**: Fill in the product details and click 'Add'.\n" +
                    "- **Edit a product**: Click the 'Edit' button next to the product you want to edit.\n" +
                    "- **Delete a product**: Click the 'Delete' button next to the product you want to delete.\n" +
                    "- **Search for products**: Use the search bar to filter products by name.\n" +
                    "- **Navigate to other pages**: Use the navigation buttons on the left.\n" +
                    "\nUse F1 to open this help dialog.");

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


