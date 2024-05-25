package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.dto.AdminProductDTO;
import services.AdminProductService;
import application.Navigator;

import java.sql.SQLException;
import java.util.List;

public class AdminProductsController {

    @FXML
    private TableView<AdminProductDTO> tableProductsPage;

    @FXML
    private TableColumn<AdminProductDTO, String> colProductName_products;

    @FXML
    private TableColumn<AdminProductDTO, String> colSeller_products;

    @FXML
    private TableColumn<AdminProductDTO, Integer> colPrice_products;

    @FXML
    private TableColumn<AdminProductDTO, String> colCategory_products;

    @FXML
    private TableColumn<AdminProductDTO, Void> colAction_products;

    @FXML
    private TextField fieldProductSearch;

    private AdminProductService adminProductService;

    public void initialize() throws SQLException {
        adminProductService = new AdminProductService();

        // Set up cell value factories
        colProductName_products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSeller_products.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        colPrice_products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory_products.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        // Set up action buttons in table column
        colAction_products.setCellFactory(createActionCellFactory());

        // Load all products
        loadProducts();
    }

    private Callback<TableColumn<AdminProductDTO, Void>, TableCell<AdminProductDTO, Void>> createActionCellFactory() {
        return new Callback<TableColumn<AdminProductDTO, Void>, TableCell<AdminProductDTO, Void>>() {
            @Override
            public TableCell<AdminProductDTO, Void> call(final TableColumn<AdminProductDTO, Void> param) {
                final TableCell<AdminProductDTO, Void> cell = new TableCell<AdminProductDTO, Void>() {

                    private final Button btnEdit = new Button("Edit");
                    private final Button btnDelete = new Button("Delete");

                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            AdminProductDTO product = getTableView().getItems().get(getIndex());
                            handleEditProduct(product);
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            AdminProductDTO product = getTableView().getItems().get(getIndex());
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

    private void handleEditProduct(AdminProductDTO product) {
        // Implement edit product functionality
        System.out.println("Edit product: " + product.getProductName());
    }

    private void handleDeleteProduct(AdminProductDTO product) {
        try {
            adminProductService.deleteProduct(product.getProductId());
            tableProductsPage.getItems().remove(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProducts() {
        try {
            tableProductsPage.getItems().setAll(adminProductService.getAllAdminProductDTOs());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onActionSearch(ActionEvent actionEvent) {
        String searchQuery = fieldProductSearch.getText();
        try {
            List<AdminProductDTO> filteredProducts = adminProductService.searchAdminProductDTOsByName(searchQuery);
            tableProductsPage.getItems().setAll(filteredProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        Navigator.navigate(Navigator.ADMIN_DASHBOARD_PAGE);
    }

    @FXML
    private void navigateToSell(ActionEvent actionEvent) {
        Navigator.navigate(Navigator.SELL_PAGE);
    }

    @FXML
    private void navigateToProfile(ActionEvent actionEvent) {
        Navigator.navigate(Navigator.USER_PAGE);
    }
}
