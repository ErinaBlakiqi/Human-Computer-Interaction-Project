package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.dto.EditProfileDto;
import services.ProfileService;

public class editAccountController {

    @FXML
    private TextField addressField;

    @FXML
    private TextArea bioField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField contactEmailField;

    private ProfileService profileService = new ProfileService();
    // Dynamic user and profile IDs
    private int currentUserId;
    private int currentProfileId;

    public void setUserId(int userId) {
        this.currentUserId = userId;
    }

    public void setProfileId(int profileId) {
        this.currentProfileId = profileId;
    }

    @FXML
    void handleEditAccountSave(ActionEvent event) {
        try {
            // Retrieve data from input fields
            String username = usernameField.getText();
            String address = addressField.getText();
            String contactNumber = contactNumberField.getText();
            String bio = bioField.getText();
            String contactEmail = contactEmailField.getText();

            // Validate input data
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }
            if (!contactNumber.matches("\\+?[0-9]*")) {
                throw new IllegalArgumentException("Invalid contact number format");
            }
            if (!contactEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            // Create DTO for editing profile
            EditProfileDto editProfileDto = new EditProfileDto(currentUserId, username, address, contactNumber, bio, contactEmail);
            // Call the service to update the profile
            boolean isUpdated = profileService.updateProfile(editProfileDto);

            // Provide feedback to user
            if (isUpdated) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Profile updated successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update profile.");
            }
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred.");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
