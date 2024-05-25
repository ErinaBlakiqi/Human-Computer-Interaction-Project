package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.dto.AdminProductDTO;
import model.dto.SellItemDto;
import services.SellItemService;

import java.util.List; // Add this import statement

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

        // Populate typeComboBox with data
        typeComboBox.setItems(FXCollections.observableArrayList("Type1", "Type2", "Type3")); // Adjust this to your actual data

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
                newItem.setCategoryId(typeComboBox.getSelectionModel().getSelectedIndex() + 1);
                newItem.setSellerId(currentUserId); // Set the current user ID

                sellItemService.addItem(newItem);
                loadProducts(); // Reload the table to include the new item
                clearInputFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price and Quantity must be numbers.");
        } catch (Exception e) {
            showAlert("Error", "An error occurred while adding the item.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            if (isInputValid()) {
                SellItemDto selectedItem = productTableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    selectedItem.setProductName(productNameField.getText());
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));
                    selectedItem.setQuantity(Integer.parseInt(quantityField.getText()));
                    selectedItem.setCategoryId(typeComboBox.getSelectionModel().getSelectedIndex() + 1);

                    sellItemService.updateItem(selectedItem);
                    loadProducts(); // Reload the table to reflect the updated item
                    clearInputFields();
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price and Quantity must be numbers.");
        } catch (Exception e) {
            showAlert("Error", "An error occurred while updating the item.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        SellItemDto selectedItem = productTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            sellItemService.deleteItem(selectedItem.getProductId());
            loadProducts(); // Reload the table to remove the deleted item
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

    private void loadProducts() {
        productList.clear();
        List<SellItemDto> items = sellItemService.getItemsByUserId(currentUserId);
        if (items != null) {
            productList.addAll(items);
        }
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        loadProducts(); // Reload products for the new user ID
    }

    public void setProductDetails(AdminProductDTO productDTO) {
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
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
