package controllers;

import application.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.dto.SellItemDto;
import services.SellItemService;

import java.util.List;

public class SellItemController {
    @FXML
    private TextField productNameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TableView<SellItemDto> productTableView;
    @FXML
    private TableColumn<SellItemDto, String> productNameColumn;
    @FXML
    private TableColumn<SellItemDto, String> typeColumn;
    @FXML
    private TableColumn<SellItemDto, Integer> quantityColumn;
    @FXML
    private TableColumn<SellItemDto, Double> priceColumn;
    @FXML
    private Button homeButton;
    @FXML
    private Button productsButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button userButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button signoutButton;

    private final ObservableList<SellItemDto> productList = FXCollections.observableArrayList();
    private final SellItemService sellItemService = new SellItemService();
    private int currentUserId; // Store the current user ID

    @FXML
    public void initialize() {
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty()); // Assuming type is stored in status
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        productTableView.setItems(productList);
        productTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                onEdit();
            }
        });

        // Populate typeComboBox with actual categories
        typeComboBox.setItems(FXCollections.observableArrayList(
                "Electronics",
                "Fashion",
                "Home & Garden",
                "Sports & Outdoors",
                "Toys & Games",
                "Health & Beauty",
                "Automotive",
                "Books",
                "Music",
                "Movies & TV",
                "Groceries",
                "Pets",
                "Office Supplies"
        ));

        // Log in a user programmatically for testing
        setCurrentUserId(1); // Replace with a valid user ID from your database

        loadProducts();
    }

    @FXML
    private void handleAddItem() {
        try {
            if (isInputValid()) {
                SellItemDto newItem = new SellItemDto();
                newItem.setProductName(productNameField.getText());
                newItem.setPrice(Double.parseDouble(priceField.getText()));
                newItem.setQuantity(Integer.parseInt(quantityField.getText()));
                newItem.setCategoryId(typeComboBox.getSelectionModel().getSelectedIndex() + 1); // Ensure this matches your category IDs
                newItem.setSellerId(currentUserId); // Set the current user ID

                sellItemService.addItem(newItem);
                loadProducts(); // Reload the table to include the new item
                clearInputFields();
                showAlert("Success", "Product added successfully.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price and Quantity must be numbers.");
        } catch (Exception e) {
            showAlert("Database Error", "Unable to add product: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleProducts() {
        Navigator.navigate("/views/products.fxml");
    }

    @FXML
    void handleSell() {
        Navigator.navigate("/views/sellitem.fxml");
    }

    @FXML
    void handleSignOut() {
        Navigator.navigate("/views/SignIn.fxml");
    }

    @FXML
    void handleUser() {
        Navigator.navigate("/views/account2.fxml");
    }

    @FXML
    void handleAdmin() {
        Navigator.navigate("/views/AdminDashboard.fxml");
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        loadProducts(); // Reload products for the new user ID
    }

    private void loadProducts() {
        productList.clear();
        List<SellItemDto> items = sellItemService.getItemsByUserId(currentUserId);
        if (items != null) {
            productList.addAll(items);
        }
    }

    private void onEdit() {
        if (productTableView.getSelectionModel().getSelectedItem() != null) {
            SellItemDto selectedItem = productTableView.getSelectionModel().getSelectedItem();
            productNameField.setText(selectedItem.getProductName());
            priceField.setText(String.valueOf(selectedItem.getPrice()));
            quantityField.setText(String.valueOf(selectedItem.getQuantity()));
            typeComboBox.getSelectionModel().select(selectedItem.getCategoryId() - 1);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (productNameField.getText() == null || productNameField.getText().isEmpty()) {
            errorMessage += "No valid product name!\n";
        }
        if (priceField.getText() == null || priceField.getText().isEmpty()) {
            errorMessage += "No valid price!\n";
        } else {
            try {
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be a number)!\n";
            }
        }
        if (quantityField.getText() == null || quantityField.getText().isEmpty()) {
            errorMessage += "No valid quantity!\n";
        } else {
            try {
                Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid quantity (must be a number)!\n";
            }
        }

        if (typeComboBox.getSelectionModel().getSelectedIndex() < 0) {
            errorMessage += "No valid type selected!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert("Invalid Fields", errorMessage);
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearInputFields() {
        productNameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        typeComboBox.getSelectionModel().clearSelection();
    }
}
