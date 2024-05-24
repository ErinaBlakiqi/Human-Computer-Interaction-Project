package controllers;

import application.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import model.dto.DailyChartDto;
import model.dto.ProfileDto;
import repository.ProfileRepository;
import utils.SessionManager;

import java.io.IOException;
import java.util.List;

public class AccountController {

    @FXML
    private Button adminButton;

    @FXML
    private Label bioLabel;

    @FXML
    private AreaChart<String, Number> boughtProductsChartProfile;

    @FXML
    private CategoryAxis boughtXAxis;

    @FXML
    private NumberAxis boughtYAxis;

    @FXML
    private Label contactLabel;

    @FXML
    private TableColumn<?, ?> dateBoughtColumn;

    @FXML
    private TableColumn<?, ?> dateSoldColumn;

    @FXML
    private Button editAccountButton;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<?, ?> itemBoughtColumn;

    @FXML
    private TableColumn<?, ?> itemSoldColumn;

    @FXML
    private TableView<?> lastItemsBoughtTableProfile;

    @FXML
    private TableView<?> lastItemsSoldTableProfile;

    @FXML
    private Label locationLabel;

    @FXML
    private TableColumn<?, ?> priceBoughtColumn;

    @FXML
    private TableColumn<?, ?> priceSoldColumn;

    @FXML
    private Button productsButton;

    @FXML
    private Button sellButton;

    @FXML
    private Button signoutButton;

    @FXML
    private AreaChart<String, Number> soldProductsChartProfile;

    @FXML
    private CategoryAxis soldXAxis;

    @FXML
    private NumberAxis soldYAxis;

    @FXML
    private Button userButton;

    @FXML
    private Label usernameLabel;

    public void setProfileData(ProfileDto profile) {
        usernameLabel.setText(profile.getUserName());
        locationLabel.setText(profile.getLocation());
        contactLabel.setText(profile.getContactEmail() + "\n" + profile.getContactNumber());
        bioLabel.setText(profile.getBio());
    }
    @FXML
    void handleAdmin(MouseEvent event) {
        Navigator.navigate("/views/AdminDashboard.fxml");
    }

    @FXML
    void handleEditAccount(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/editAccount.fxml"));
            Parent editAccountRoot = fxmlLoader.load();

            Stage editAccountStage = new Stage();
            editAccountStage.setTitle("Edit Account");
            editAccountStage.setScene(new Scene(editAccountRoot));

            // Set the new window as a modal dialog
            editAccountStage.initModality(Modality.WINDOW_MODAL);

            // Set the owner of the new window
            Stage primaryStage = (Stage) editAccountButton.getScene().getWindow();
            editAccountStage.initOwner(primaryStage);

            // Show the new window and wait until it is closed
            editAccountStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleProducts(MouseEvent event) {
        Navigator.navigate("/views/products.fxml");
    }

    @FXML
    void handleSell(MouseEvent event) {
        Navigator.navigate("/views/sellitem.fxml");
    }

    @FXML
    void handleSignOut(MouseEvent event) {
        Navigator.navigate("/views/SignIn.fxml");
    }

    @FXML
    void handleUser(MouseEvent event) {
        Navigator.navigate("/views/account2.fxml");
    }

    public void handleChangePassword(ActionEvent actionEvent) {
        Navigator.navigate("/views/ChangePassword.fxml");
    }
    @FXML
    public void initialize() {
        try {
            int userId = getCurrentUserId();
            ProfileRepository profileService = null;
            ProfileDto profile = profileService.fetchProfileData(userId);
            if (profile != null) {
                setProfileData(profile);
            } else {
                showAlert("Profile Data Not Found", "No profile data found for the current user.");
            }

            List<DailyChartDto> boughtData = profileService.fetchBoughtProductsData(userId);
            List<DailyChartDto> soldData = profileService.fetchSoldProductsData(userId);

            populateChart(boughtProductsChartProfile, boughtData);
            populateChart(soldProductsChartProfile, soldData);

        } catch (IllegalStateException e) {
            showAlert("User Not Logged In", "No user is currently logged in.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private int getCurrentUserId() {
        SessionManager SessionManager = new SessionManager();
        User currentUser = SessionManager.getLoggedInUser();
        if (currentUser != null) {
            return currentUser.getId();
        } else {
            throw new IllegalStateException("No user is currently logged in.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void populateChart(AreaChart<String, Number> chart, List<DailyChartDto> data) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (DailyChartDto item : data) {
            series.getData().add(new XYChart.Data<>(item.getDay(), item.getCount()));
        }
        chart.getData().add(series);
    }
}
