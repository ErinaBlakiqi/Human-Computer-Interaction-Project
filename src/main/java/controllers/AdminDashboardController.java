package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.OrderService;
import services.RevenueService;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Label totalOrdersLabel;
    @FXML
    private Label pendingOrdersLabel;
    @FXML
    private Label completedOrdersLabel;
    @FXML
    private Label totalRevenueLabel;
    @FXML
    private Label dailyRevenueLabel;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignOut;

    private OrderService orderService = new OrderService();
    private RevenueService revenueService = new RevenueService();

    @FXML
    public void initialize() {
        updateDashboard();
    }

    private void updateDashboard() {
        int totalOrders = orderService.getTotalOrders();
        int pendingOrders = orderService.getPendingOrders();
        int completedOrders = orderService.getCompletedOrders();
        double totalRevenue = revenueService.getTotalRevenue();
        double dailyRevenue = revenueService.getDailyRevenue();

        totalOrdersLabel.setText("Total Orders: " + totalOrders);
        pendingOrdersLabel.setText("Pending Orders: " + pendingOrders);
        completedOrdersLabel.setText("Completed Orders: " + completedOrders);
        totalRevenueLabel.setText("Total Revenue: $" + String.format("%.2f", totalRevenue));
        dailyRevenueLabel.setText("Daily Revenue: $" + String.format("%.2f", dailyRevenue));
    }

    @FXML
    private void handleDashboard(ActionEvent event) throws IOException {
        // This button is for navigating to the dashboard itself, so you might want to refresh the dashboard
        loadPage("/views/AdminHome.fxml", event);
    }

    @FXML
    private void handleProducts(ActionEvent event) throws IOException {
        loadPage("/views/AdminProducts.fxml", event);
    }

    @FXML
    private void handleOrders(ActionEvent event) throws IOException {
        loadPage("/views/AdminOrders.fxml", event);
    }

    @FXML
    private void handleSignOut(ActionEvent event) throws IOException {
        // Navigate to the login or sign-out screen
        loadPage("/views/Login.fxml", event);
    }

    private void loadPage(String page, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
