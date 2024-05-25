package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import services.AdminOrderService;
import application.Navigator;

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

    private AdminOrderService adminOrderService = new AdminOrderService();

    @FXML
    public void initialize() {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        buyerIdColumn.setCellValueFactory(new PropertyValueFactory<>("buyerId"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        ordersTable.setItems(getOrders());
    }

    private ObservableList<Order> getOrders() {
        return FXCollections.observableArrayList(adminOrderService.getAllOrders());
    }

    @FXML
    private void handleHome(ActionEvent event) {
        //Navigator.loadScene(event, "/views/AdminDashboard.fxml");
    }

    @FXML
    private void handleProducts(ActionEvent event) {
        //Navigator.loadScene(event, "/views/AdminProducts.fxml");
    }

    @FXML
    private void handleOrders(ActionEvent event) {
        // Already on Orders
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        //Navigator.loadScene(event, "/views/SignIn.fxml");
    }
}
