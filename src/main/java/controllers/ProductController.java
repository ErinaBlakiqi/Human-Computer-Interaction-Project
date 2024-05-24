package controllers;

import javafx.scene.control.TableCell;
import model.dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.ProductRepository;
import services.ProductService;
import repository.CartRepository;
import model.Cart;

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

    private ProductService productService;
    private CartRepository cartRepository;

    private int currentUserId = 1; // This should be dynamically set based on the logged-in user

    public ProductController() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
            productService = new ProductService(new ProductRepository(connection));
            cartRepository = new CartRepository(connection);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
