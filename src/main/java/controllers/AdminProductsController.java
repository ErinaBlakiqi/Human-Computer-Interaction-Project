package controllers;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.dto.AdminProductDTO;
import model.filter.Filter;
import model.filter.ProductFilter;
import services.AdminProductService;
import application.Navigator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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

    @FXML
    private Button btnHelp;


    private AdminProductService adminProductService;
    private ObservableList<AdminProductDTO> masterData = FXCollections.observableArrayList();
    private Filter<AdminProductDTO> productFilter = new ProductFilter();

    public AdminProductsController() throws SQLException {
        adminProductService = new AdminProductService();
    }

    public void initialize() throws SQLException {
        // Set up cell value factories
        colProductName_products.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSeller_products.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        colPrice_products.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory_products.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        // Set up action buttons in table column
        colAction_products.setCellFactory(createActionCellFactory());

        // Load all products
        loadProducts();

        // Add listener to the search field
        fieldProductSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<AdminProductDTO> filteredList = productFilter.filter(masterData, newValue);
            tableProductsPage.setItems(filteredList);
            colAction_products.setCellFactory(createActionCellFactory()); // Re-apply cell factory
        });

        // Set up key event filter to detect F1 key press on the parent node
        tableProductsPage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.F1) {
                        handleHelp();
                        event.consume();
                    }
                });
            }
        });
    }

    @FXML
    private void onActionSearch(ActionEvent actionEvent) {
        String searchQuery = fieldProductSearch.getText();
        tableProductsPage.setItems(productFilter.filter(masterData, searchQuery));
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditProduct.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the product details to it
            EditProductController controller = loader.getController();
            controller.setProductDetails(product, adminProductService);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Product");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Reload the products after the pop-up is closed
            loadProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            List<AdminProductDTO> products = adminProductService.getAllAdminProductDTOs();
            masterData.setAll(products);
            tableProductsPage.setItems(masterData);
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

    public void navigateToOrders(ActionEvent actionEvent) {Navigator.navigate(Navigator.ADMIN_ORDERS_PAGE);
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        Navigator.navigate(Navigator.SIGNIN_PAGE);
    }


    @FXML
    private void handleHelp() {
        try {
            Locale locale = new Locale("en", "US"); // Or retrieve current locale
            ResourceBundle bundle = ResourceBundle.getBundle("help", locale);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HelpPopUp.fxml"), bundle);
            Parent root = loader.load();

            HelpPopUpController controller = loader.getController();
            controller.setHelpText(bundle.getString("help_text"));

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleChange(ActionEvent actionEvent) {
    }
}
