package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Product;
import services.ProductService;

import java.io.IOException;

public class AdminProductsController {

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> statusColumn;

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> statusComboBox;

    private ProductService productService = new ProductService();
    private ObservableList<Product> productList;

    @FXML
    public void initialize() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        productList = FXCollections.observableArrayList(productService.getAllProducts());
        productsTable.setItems(productList);

        // Load categories dynamically from the database
        ObservableList<String> categories = FXCollections.observableArrayList(productService.getAllCategories());
        categoryComboBox.setItems(categories);

        statusComboBox.setItems(FXCollections.observableArrayList("Active", "Sold Out"));
    }

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminDashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleProducts(ActionEvent event) {
        // Already on Products
    }

    @FXML
    private void handleOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminOrders.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        // Handle Sign Out
    }

    @FXML
    private void handleAddItem(ActionEvent event) {
        String productName = productNameField.getText();
        String description = descriptionField.getText();
        String category = categoryComboBox.getValue();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        String status = statusComboBox.getValue();

        Product product = new Product(0, productName, description, category, quantity, price, status);
        productService.addProduct(product);
        productList.add(product);
    }

    @FXML
    private void handleUpdateItem(ActionEvent event) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setProductName(productNameField.getText());
            selectedProduct.setDescription(descriptionField.getText());
            selectedProduct.setCategory(categoryComboBox.getValue());
            selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setStatus(statusComboBox.getValue());
            productService.updateProduct(selectedProduct);
            productsTable.refresh();
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        productIdField.clear();
        productNameField.clear();
        descriptionField.clear();
        categoryComboBox.setValue(null);
        priceField.clear();
        quantityField.clear();
        statusComboBox.setValue(null);
    }

    @FXML
    private void handleDeleteItem(ActionEvent event) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productService.deleteProduct(selectedProduct.getProductId());
            productList.remove(selectedProduct);
        }
    }

    @FXML
    private void handleImportImage(ActionEvent event) {
        // Import image logic
    }
}
