package controllers;

import javafx.scene.control.TableCell;
import model.dto.AdminProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import services.AdminProductService;

import java.util.List;

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

    private AdminProductService productService;

    public AdminProductController(AdminProductService productService) {
        this.productService = productService;
    }

    @FXML
    public void initialize() {
        colProductName_products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSeller_products.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        colPrice_products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity_products.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCategory_products.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colStatus_products.setCellValueFactory(new PropertyValueFactory<>("status"));

        List<AdminProductDTO> products = productService.getAllProducts();
        ObservableList<AdminProductDTO> productList = FXCollections.observableArrayList(products);
        tableProductsPage.setItems(productList);

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
    }

    @FXML
    void onActionSearch(ActionEvent event) {
        // Implement search logic
    }
}
