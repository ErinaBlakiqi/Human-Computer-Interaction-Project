package controllers;

import application.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.dto.LoginUserDto;
import services.UserService;
import utils.SessionManager;

import java.io.IOException;

public class SignInController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private CheckBox showpassword;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label loginMessageLabel;

    @FXML
    void handleCreateAccountClick(MouseEvent me) throws IOException {
        Navigator.navigate(Navigator.SIGNUP_PAGE);
    }

    @FXML
    public void initialize() {
        showpassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                txtPassword.setText(pwdPassword.getText());
                txtPassword.setVisible(true);
                pwdPassword.setVisible(false);
            } else {
                pwdPassword.setText(txtPassword.getText());
                pwdPassword.setVisible(true);
                txtPassword.setVisible(false);
            }
        });
    }

    @FXML
    public void onPressLogIn(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            String username = txtUsername.getText();
            String password = pwdPassword.getText();

            LoginUserDto loginUserDto = new LoginUserDto(username, password);
            boolean success = UserService.login(loginUserDto);

            if (success) {
                if (SessionManager.isAdmin()) {
                    Navigator.navigate(Navigator.ADMIN_DASHBOARD_PAGE);
                } else {
                    Navigator.navigate(Navigator.PRODUCTS_PAGE);
                }
                showAlert(Alert.AlertType.INFORMATION, "Login Successful!", "Welcome " + username);
            } else {
                loginMessageLabel.setText("Invalid username or password.");
            }
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = txtUsername.getText();
        String password = pwdPassword.getText();

        LoginUserDto loginUserDto = new LoginUserDto(username, password);
        boolean success = UserService.login(loginUserDto);

        if (success) {
            if (SessionManager.isAdmin()) {
                Navigator.navigate(Navigator.ADMIN_DASHBOARD_PAGE);
            } else {
                Navigator.navigate(Navigator.PRODUCTS_PAGE);
            }
            showAlert(Alert.AlertType.INFORMATION, "Login Successful!", "Welcome " + username);
        } else {
            loginMessageLabel.setText("Invalid username or password.");
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleChange(ActionEvent actionEvent) {
    }
}
