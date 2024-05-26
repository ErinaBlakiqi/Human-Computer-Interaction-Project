package controllers;

import application.Navigator;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import model.User;
import model.dto.DailyChartDto;
import services.ProfileService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dto.ProfileDto;
import model.dto.ProfileOrderDto;
import utils.SessionManager;

import java.io.IOException;
import java.util.List;

public class AccountController {

    @FXML
    private Label bioLabel;

    @FXML
    private AreaChart<String, Number> boughtProductsChartProfile;

    @FXML
    private CategoryAxis boughtXAxis;

    @FXML
    private NumberAxis boughtYAxis;

    @FXML
    private Button btnChangeGjuhen;

    @FXML
    private Button btnHelp;

    @FXML
    private Button btnProducts;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSell;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Label contactEmailLabel;

    @FXML
    private Label contactNumberLabel;

    @FXML
    private TableColumn<ProfileOrderDto, String> dateBoughtColumn;

    @FXML
    private Button editAccountButton;

    @FXML
    private TableColumn<ProfileOrderDto, String> itemBoughtColumn;

    @FXML
    private TableView<ProfileOrderDto> lastItemsBoughtTableProfile;

    @FXML
    private Label locationLabel;

    @FXML
    private TableColumn<ProfileOrderDto, Integer> priceBoughtColumn;

    @FXML
    private Label usernameLabel;

    private ProfileService profileService = new ProfileService();

    public void setProfileData(ProfileDto profile) {
        if (profile != null) {
            usernameLabel.setText(profile.getUserName() != null ? profile.getUserName() : "");
            locationLabel.setText(profile.getLocation() != null ? profile.getLocation() : "");
            contactNumberLabel.setText(profile.getContactNumber() != null ? profile.getContactNumber() : "");
            contactEmailLabel.setText(profile.getContactEmail() != null ? profile.getContactEmail() : "");
            bioLabel.setText(profile.getBio() != null ? profile.getBio() : "");
        }
    }
    @FXML
    void handleChange(ActionEvent event) {

    }

    @FXML
    void handleChangePassword(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ChangePassword.fxml"));
            Parent changePasswordRoot = fxmlLoader.load();

            Stage changePasswordStage = new Stage();
            changePasswordStage.setScene(new Scene(changePasswordRoot));

            // Set the new window as a modal dialog
            changePasswordStage.initModality(Modality.WINDOW_MODAL);

            // Set the owner of the new window
            Stage primaryStage = (Stage) changePasswordButton.getScene().getWindow();
            changePasswordStage.initOwner(primaryStage);

            // Show the new window and wait until it is closed
            changePasswordStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleEditAccount(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/editAccount.fxml"));
            Parent editAccountRoot = fxmlLoader.load();

            editAccountController controller = fxmlLoader.getController();
            int userId = getCurrentUserId();
            System.out.println("Passing userId to editAccountController: " + userId);  // Debug print

            ProfileDto profile = profileService.fetchProfileData(userId);
            if (profile != null) {
                controller.setUserId(userId);
                controller.setProfileData(profile);
            } else {
                System.err.println("Profile data not found for user ID: " + userId);
            }

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

            // Refresh the profile data after the edit window is closed
            refreshProfileData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleHelp(ActionEvent event) {

    }

    @FXML
    void handleSignOut(ActionEvent event) {
        Navigator.navigate("/views/SignIn.fxml");
    }

    @FXML
    void navigateToProducts(ActionEvent event) {
        Navigator.navigate("/views/products.fxml");
    }

    @FXML
    void navigateToSell(ActionEvent event) {
        Navigator.navigate("/views/sellitem.fxml");
    }
    @FXML
    public void initialize() {
        dateBoughtColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        itemBoughtColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
        priceBoughtColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        try {
            int userId = getCurrentUserId();
            ProfileDto profile = profileService.fetchProfileData(userId);
            if (profile != null) {
                setProfileData(profile);
            } else {
                showAlert("Profile Data Not Found", "No profile data found for the current user.");
            }

            List<DailyChartDto> boughtData = profileService.fetchBoughtProductsData(userId);
            List<ProfileOrderDto> lastBoughtItems = profileService.fetchLastBoughtItems(userId);

            populateChart(boughtProductsChartProfile, boughtData);
            populateBoughtItemsTable(lastItemsBoughtTableProfile, lastBoughtItems);

        } catch (IllegalStateException e) {
            showAlert("User Not Logged In", "No user is currently logged in.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private int getCurrentUserId() {
        SessionManager sessionManager = new SessionManager();
        User currentUser = sessionManager.getLoggedInUser();
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
            System.out.println("Adding data to chart: " + item.getDay() + " -> " + item.getCount());
            series.getData().add(new XYChart.Data<>(item.getDay(), item.getCount()));
        }
        chart.getData().add(series);
    }
    private void populateBoughtItemsTable(TableView<ProfileOrderDto> table, List<ProfileOrderDto> data) {
        System.out.println("Populating table with data: "+data);
        table.getItems().clear();
        table.getItems().addAll(data);
    }
    private void refreshProfileData() {
        try {
            int userId = getCurrentUserId();
            ProfileDto profile = profileService.fetchProfileData(userId);
            if (profile != null) {
                setProfileData(profile);
            } else {
                showAlert("Profile Data Not Found", "No profile data found for the current user.");
            }

            List<DailyChartDto> boughtData = profileService.fetchBoughtProductsData(userId);

            populateChart(boughtProductsChartProfile, boughtData);

        } catch (IllegalStateException e) {
            showAlert("User Not Logged In", "No user is currently logged in.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }
}
