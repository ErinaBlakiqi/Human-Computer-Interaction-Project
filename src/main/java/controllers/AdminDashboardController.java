package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dto.DailyRevenueDto;
import services.AdminService;
import application.Navigator;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private AreaChart<String, Number> dailyIncomeChart;
    @FXML
    private BarChart<String, Number> monthlyIncomeChart;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignOut;

    private AdminService adminService = new AdminService();

    @FXML
    public void initialize() {
        updateDashboard();
    }

    private void updateDashboard() {
        int totalOrders = adminService.getTotalOrders();
        int pendingOrders = adminService.getPendingOrders();
        int completedOrders = adminService.getCompletedOrders();
        double totalRevenue = adminService.getTotalRevenue();
        double dailyRevenue = adminService.getDailyRevenue();

        totalOrdersLabel.setText("Total Orders: " + totalOrders);
        pendingOrdersLabel.setText("Pending Orders: " + pendingOrders);
        completedOrdersLabel.setText("Completed Orders: " + completedOrders);
        totalRevenueLabel.setText("Total Revenue: $" + String.format("%.2f", totalRevenue));
        dailyRevenueLabel.setText("Daily Revenue: $" + String.format("%.2f", dailyRevenue));

        updateDailyIncomeChart();
        updateMonthlyIncomeChart();
    }


    private void updateDailyIncomeChart() {
        List<DailyRevenueDto> dailyRevenues = adminService.getDailyRevenueData();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (DailyRevenueDto dailyRevenue : dailyRevenues) {
            series.getData().add(new XYChart.Data<>(dailyRevenue.getDate().toString(), dailyRevenue.getRevenue()));
        }
        dailyIncomeChart.getData().clear();
        dailyIncomeChart.getData().add(series);
    }

    private void updateMonthlyIncomeChart() {
        List<DailyRevenueDto> monthlyRevenues = adminService.getMonthlyRevenueData();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (DailyRevenueDto monthlyRevenue : monthlyRevenues) {
            series.getData().add(new XYChart.Data<>(monthlyRevenue.getDate().toString(), monthlyRevenue.getRevenue()));
        }
        monthlyIncomeChart.getData().clear();
        monthlyIncomeChart.getData().add(series);
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
}
