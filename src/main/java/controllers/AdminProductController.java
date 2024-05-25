package controllers;

import model.dto.AdminProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.AdminProductService;
import application.Navigator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdminProductController {

    @FXML
    private TableView<AdminProductDTO> tableProductsPage;

    @FXML
    private TableColumn<AdminProductDTO, String> colProductName_products;

    @FXML
    private TableColumn<AdminProductDTO, String> colSeller_products;

    @FXML
    private TableColumn<AdminProductDTO, Double> colPrice_products;

    @FXML
    private TableColumn<AdminProductDTO, Integer> colQuantity_products;

    @FXML
    private TableColumn<AdminProductDTO, String> colCategory_products;

    @FXML
    private TableColumn<AdminProductDTO, String> colStatus_products;

    @FXML
    private TableColumn<AdminProductDTO, Void> colAction_products;

    @FXML
    private TextField fieldProductSearch;

    @FXML
    private Button btnSearch;

    private AdminProductService productService;

    public AdminProductController() {
        // Initialize the productService
        this.productService = new AdminProductService(); // Adjust this if you're using dependency injection
    }

    @FXML
    public void initialize() {
        colProductName_products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSeller_products.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        colPrice_products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity_products.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCategory_products.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colStatus_products.setCellValueFactory(new PropertyValueFactory<>("status"));

        colAction_products.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox pane = new HBox(editButton, deleteButton);

            {
                deleteButton.setOnAction(event -> {
                    AdminProductDTO product = getTableView().getItems().get(getIndex());
                    productService.deleteProduct(product.getProductId());
                    getTableView().getItems().remove(product);
                });

                editButton.setOnAction(event -> {
                    AdminProductDTO product = getTableView().getItems().get(getIndex());
                    // Redirect to the sell page with the product details
                    redirectToSellPage(product);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });

        btnSearch.setOnAction(this::onActionSearch);

        loadProducts();
    }

    private void loadProducts() {
        List<AdminProductDTO> products = productService.getAllProducts();
        ObservableList<AdminProductDTO> productList = FXCollections.observableArrayList(products);
        tableProductsPage.setItems(productList);
    }

    @FXML
    private void onActionSearch(ActionEvent event) {
        String searchText = fieldProductSearch.getText().toLowerCase();
        List<AdminProductDTO> products = productService.getAllProducts();
        List<AdminProductDTO> filteredProducts = products.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(searchText) ||
                        product.getSellerName().toLowerCase().contains(searchText) ||
                        product.getCategoryName().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        ObservableList<AdminProductDTO> productList = FXCollections.observableArrayList(filteredProducts);
        tableProductsPage.setItems(productList);
    }

    private void redirectToSellPage(AdminProductDTO productDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/sellitem.fxml"));
            Parent root = loader.load();

            // Pass the product details to the sell page controller
            SellItemController controller = loader.getController();
            controller.setProductDetails(productDTO); // Make sure this method exists in SellItemController

            Stage stage = (Stage) tableProductsPage.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToDashboard(ActionEvent actionEvent) {
        // Implement navigation logic
    }

    public void goToProducts(ActionEvent actionEvent) {
        // Implement navigation logic
    }

    public void goToSell(ActionEvent actionEvent) {
        // Implement navigation logic
    }

    public void goToProfile(ActionEvent actionEvent) {
        // Implement navigation logic
    }
}
