package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import model.dto.UpdateUserDto;
import services.UserService;
import utils.SessionManager;
import application.Navigator;

public class UserController {

    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmNewPasswordField;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void handleChangePassword(ActionEvent event) {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmNewPassword = confirmNewPasswordField.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "New passwords do not match.");
            return;
        }

        // Fetch the logged-in user from the session
        User user = SessionManager.getLoggedInUser();
        if (user == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "User not found.");
            return;
        }

        // Verify the current password
        if (!UserService.verifyPassword(user, currentPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Current password is incorrect.");
            return;
        }

        // Create UpdateUserDto
        UpdateUserDto updateUserDto = new UpdateUserDto(user.getId(), newPassword);

        boolean success = UserService.updatePassword(updateUserDto);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully.");
            // Optionally clear the fields
            currentPasswordField.clear();
            newPasswordField.clear();
            confirmNewPasswordField.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password.");
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        Navigator.navigate(event, Navigator.SIGNIN_PAGE);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
