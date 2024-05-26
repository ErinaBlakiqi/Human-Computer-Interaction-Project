package controllers;

import application.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Order;
import services.AdminOrderService;

import java.io.IOException;
import java.util.List;

public class AdminOrderController {

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Integer> orderIdColumn;

    @FXML
    private TableColumn<Order, Integer> buyerIdColumn;

    @FXML
    private TableColumn<Order, Integer> productIdColumn;

    @FXML
    private TableColumn<Order, Integer> quantityColumn;

    @FXML
    private TableColumn<Order, Integer> totalPriceColumn;

    @FXML
    private TableColumn<Order, String> orderStatusColumn;

    @FXML
    private TableColumn<Order, String> createdAtColumn;

    private AdminOrderService adminOrderService;

    @FXML
    public void initialize() {
        adminOrderService = new AdminOrderService();
        loadOrders();
    }

    private void loadOrders() {
        List<Order> orders = adminOrderService.getAllOrders();
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList(orders);

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        buyerIdColumn.setCellValueFactory(new PropertyValueFactory<>("buyerId"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        ordersTable.setItems(orderObservableList);
    }

    @FXML
    private void handleDashboard(ActionEvent event) {
        Navigator.navigate(Navigator.ADMIN_DASHBOARD_PAGE);
    }

    @FXML
    private void handleProducts(ActionEvent event) {
        Navigator.navigate(Navigator.ADMIN_PRODUCTS_PAGE);
    }

    @FXML
    private void handleOrders(ActionEvent event) {
        Navigator.navigate(Navigator.ADMIN_ORDERS_PAGE);
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        Navigator.navigate(Navigator.SIGNIN_PAGE);
    }

    public void handleChange(ActionEvent actionEvent) {
    }

    @FXML
    private void handleHelp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HelpPopUp.fxml"));
            Parent root = loader.load();

            HelpPopUpController controller = loader.getController();
            controller.setHelpText("This is the Admin Products page.\n\n" +
                    "You can perform the following actions:\n" +
                    "- **Add a new product**: Fill in the product details and click 'Add'.\n" +
                    "- **Edit a product**: Click the 'Edit' button next to the product you want to edit.\n" +
                    "- **Delete a product**: Click the 'Delete' button next to the product you want to delete.\n" +
                    "- **Search for products**: Use the search bar to filter products by name.\n" +
                    "- **Navigate to other pages**: Use the navigation buttons on the left.\n" +
                    "\nUse F1 to open this help dialog.");

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
