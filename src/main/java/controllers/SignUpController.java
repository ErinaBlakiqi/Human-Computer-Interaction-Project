package controllers;

import application.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.UserDto;
import services.UserService;

public class SignUpController {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdComfirmPassword;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void handleSignUp(ActionEvent event) {
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = pwdPassword.getText();
        String confirmPassword = pwdComfirmPassword.getText();

        // Create UserDto with default role as "user"
        UserDto userDto = new UserDto(firstName, lastName, username, email, password, confirmPassword);
        boolean success = UserService.signUp(userDto);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "Welcome " + username);
            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.close();
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Passwords do not match or an error occurred.");
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        Navigator.navigate(event, Navigator.SIGNIN_PAGE);
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
}
