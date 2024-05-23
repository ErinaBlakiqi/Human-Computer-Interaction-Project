package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import services.ProductService;

public class AdminProductsController {
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> typeColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField quantityField;

    private ProductService productService = new ProductService();
    private ObservableList<Product> productList;

    @FXML
    public void initialize() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productList = FXCollections.observableArrayList(productService.getAllProducts());
        productsTable.setItems(productList);

        typeComboBox.setItems(FXCollections.observableArrayList("Type1", "Type2", "Type3")); // Example types
    }

    @FXML
    private void handleHome(ActionEvent event) {
        // Navigate to Home
    }

    @FXML
    private void handleProducts(ActionEvent event) {
        // Already on Products
    }

    @FXML
    private void handleOrders(ActionEvent event) {
        // Navigate to Orders
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        // Handle Sign Out
    }

    @FXML
    private void handleAddItem(ActionEvent event) {
        // Add item logic
        String productName = productNameField.getText();
        String type = typeComboBox.getValue();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());

        Product product = new Product(productName, type, quantity, price);
        productService.addProduct(product);
        productList.add(product);
    }

    @FXML
    private void handleUpdateItem(ActionEvent event) {
        // Update item logic
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setProductName(productNameField.getText());
            selectedProduct.setType(typeComboBox.getValue());
            selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            productService.updateProduct(selectedProduct);
            productsTable.refresh();
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        // Clear fields
        productIdField.clear();
        productNameField.clear();
        priceField.clear();
        typeComboBox.setValue(null);
        quantityField.clear();
    }

    @FXML
    private void handleDeleteItem(ActionEvent event) {
        // Delete item logic
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productService.deleteProduct(selectedProduct.getId());
            productList.remove(selectedProduct);
        }
    }

    @FXML
    private void handleImportImage(ActionEvent event) {
        // Import image logic
    }
}
