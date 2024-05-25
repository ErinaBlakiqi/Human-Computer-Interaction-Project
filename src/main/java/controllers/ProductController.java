package controllers;

import javafx.animation.PauseTransition;
import javafx.scene.control.*;
import javafx.util.Duration;
import model.Order;
import model.dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.ProductRepository;
import services.ProductService;
import repository.CartRepository;
import model.Cart;
import repository.OrderRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
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
    private Label lblFeedback;

    private ProductService productService;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    private int currentUserId = 1; // This should be dynamically set based on the logged-in user

    public ProductController() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
            productService = new ProductService(new ProductRepository(connection));
            cartRepository = new CartRepository(connection);
            orderRepository = new OrderRepository(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        colProductName_products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSeller_products.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        colPrice_products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory_products.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colAction_products.setCellFactory(param -> new TableCell<>() {
            private final Button buyButton = new Button("Buy");

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
            Cart cart = cartRepository.getCartByUserIdAndProductId(currentUserId, product.getProductId());
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(currentUserId);
                cart.setProductId(product.getProductId());
                cart.setQuantity(1);
                cartRepository.addCart(cart);
            } else {
                cart.setQuantity(cart.getQuantity() + 1);
                cartRepository.updateCart(cart);
            }
            showFeedback("Product added to cart.");
            updateTotal();
        } catch (SQLException e) {
            e.printStackTrace();
            showFeedback("Error adding product to cart.");
        }
    }

    private void updateTotal() {
        try {
            int total = cartRepository.getTotalPriceByUserId(currentUserId);
            txtTotal_Products.setText(total + "$");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showFeedback(String message) {
        lblFeedback.setText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> lblFeedback.setText(""));
        pause.play();
    }

    @FXML
    private void onActionOrder() {
        try {
            String paymentMethod = listPayment_Products.getValue();
            List<Cart> cartItems = cartRepository.getCartItemsByUserId(currentUserId);

            for (Cart cartItem : cartItems) {
                Order order = new Order();
                order.setProductId(cartItem.getProductId());
                order.setBuyerId(currentUserId);
                order.setTotalPrice(cartItem.getQuantity() * cartItem.getPrice());
                order.setOrderStatus("Pending");
                order.setPaymentMethod(paymentMethod);
                orderRepository.addOrder(order);
            }

            cartRepository.clearCartByUserId(currentUserId);
            showFeedback("Order placed successfully.");
            updateTotal();
        } catch (SQLException e) {
            e.printStackTrace();
            showFeedback("Error placing order.");
        }
    }

    @FXML
    private void onActionRemove() {
        ProductDTO selectedProduct = tableProductsPage.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                cartRepository.removeCartByUserIdAndProductId(currentUserId, selectedProduct.getProductId());
                showFeedback("Product removed from cart.");
                updateTotal();
            } catch (SQLException e) {
                e.printStackTrace();
                showFeedback("Error removing product from cart.");
            }
        } else {
            showFeedback("No product selected.");
        }
    }
}
