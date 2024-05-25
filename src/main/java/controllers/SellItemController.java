package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.dto.AdminProductDTO;
import model.dto.SellItemDto;
import services.SellItemService;

    class SellItemController {
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

        loadProducts();
    }

    @FXML
    private void handleAddItem() {
        SellItemDto newItem = new SellItemDto();
        newItem.setProductName(productNameField.getText());
        newItem.setPrice(Double.parseDouble(priceField.getText()));
        newItem.setQuantity(Integer.parseInt(quantityField.getText()));
        newItem.setCategoryId(typeComboBox.getSelectionModel().getSelectedIndex() + 1);

        sellItemService.addItem(newItem);
        loadProducts();
    }

    @FXML
    private void handleUpdate() {
        SellItemDto selectedItem = productTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem.setProductName(productNameField.getText());
            selectedItem.setPrice(Double.parseDouble(priceField.getText()));
            selectedItem.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedItem.setCategoryId(typeComboBox.getSelectionModel().getSelectedIndex() + 1);

            sellItemService.updateItem(selectedItem);
            loadProducts();
        }
    }

    @FXML
    private void handleDelete() {
        SellItemDto selectedItem = productTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            sellItemService.deleteItem(selectedItem.getProductId());
            loadProducts();
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
        productList.addAll(sellItemService.getAllItems());
    }

    public void setProductDetails(AdminProductDTO productDTO) {
    }
}
