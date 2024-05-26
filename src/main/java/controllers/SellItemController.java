package controllers;

import application.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.dto.SellItemDto;
import services.SellItemService;

import java.io.IOException;
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
    private TableColumn<SellItemDto, Void> actionColumn; // Add action column
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

        // Add action buttons in the table column
        actionColumn.setCellFactory(createActionCellFactory());

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
        Navigator.navigate(Navigator.PRODUCTS_PAGE);
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

    private Callback<TableColumn<SellItemDto, Void>, TableCell<SellItemDto, Void>> createActionCellFactory() {
        return new Callback<TableColumn<SellItemDto, Void>, TableCell<SellItemDto, Void>>() {
            @Override
            public TableCell<SellItemDto, Void> call(final TableColumn<SellItemDto, Void> param) {
                final TableCell<SellItemDto, Void> cell = new TableCell<SellItemDto, Void>() {

                    private final Button btnEdit = new Button("Edit");
                    private final Button btnDelete = new Button("Delete");

                    {
                        btnEdit.setOnAction(event -> {
                            SellItemDto product = getTableView().getItems().get(getIndex());
                            handleEditProduct(product);
                        });
                        btnDelete.setOnAction(event -> {
                            SellItemDto product = getTableView().getItems().get(getIndex());
                            handleDeleteProduct(product);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttons = new HBox(btnEdit, btnDelete);
                            setGraphic(buttons);
                        }
                    }
                };
                return cell;
            }
        };
    }

    private void handleEditProduct(SellItemDto product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SellItemEdit.fxml"));
            Parent root = loader.load();

            SellItemEditController controller = loader.getController();
            controller.setProductDetails(product, sellItemService);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Product");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteProduct(SellItemDto product) {
        try {
            System.out.println("Attempting to delete product with ID: " + product.getProductId());
            sellItemService.deleteItem(product.getProductId());
            productTableView.getItems().remove(product);
            System.out.println("Product deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Unable to delete product: " + e.getMessage());
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

    public void handleProfile(ActionEvent actionEvent) {
        Navigator.navigate(Navigator.USER_PAGE);
    }

    @FXML
    public void handleChange(ActionEvent actionEvent) {
    }

    @FXML
    public void handleHelp(ActionEvent actionEvent) {
    }
}
