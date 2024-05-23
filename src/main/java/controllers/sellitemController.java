package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Product;

public class sellitemController {
    @FXML
    private TextField productNameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, String> quantityColumn;

    @FXML
    private TableColumn<Product, String> priceColumn;

    private ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Binding columns with model properties
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

        // Set the items of the table
        productTable.setItems(products);
    }

    @FXML
    private void handleAddItem() {
        // Get data from input fields
        String productName = productNameField.getText();
        String quantity = quantityField.getText();
        String price = priceField.getText();

        // Create a new product
        Product newProduct = new Product(productName, quantity, price);

        // Add the new product to the list
        products.add(newProduct);

        // Clear input fields
        clearFields();
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Check if it's a double-click
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Populate the text fields with the information of the selected product
                productNameField.setText(selectedProduct.getProductName());
                quantityField.setText(selectedProduct.getQuantity());
                priceField.setText(selectedProduct.getPrice());
            }
        }
    }

    @FXML
    private void handleUpdateItem() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Update the selected product with the new values
            selectedProduct.setProductName(productNameField.getText());
            selectedProduct.setQuantity(quantityField.getText());
            selectedProduct.setPrice(priceField.getText());

            // Refresh the table
            productTable.refresh();
        }
    }

    @FXML
    private void handleDeleteItem() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Remove the selected product from the list
            products.remove(selectedProduct);
        }
    }

    private void clearFields() {
        productNameField.clear();
        quantityField.clear();
        priceField.clear();
    }
}
