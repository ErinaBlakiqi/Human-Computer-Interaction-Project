package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private CheckBox showpassword;

    @FXML
    private TextField txtUsername;

    @FXML
    public void initialize() {
        pwdPassword.setVisible(false);

        // Bind the visibility of txtPassword and pwdPassword to the showpassword checkbox
        showpassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                pwdPassword.setText(pwdPassword.getText());
                pwdPassword.setVisible(true);
                pwdPassword.setVisible(false);
            } else {
                pwdPassword.setText(pwdPassword.getText());
                pwdPassword.setVisible(true);
                pwdPassword.setVisible(false);
            }
        });
    }

    @FXML
    public void handleCancelClick(ActionEvent event) {

        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCreateAccountClick(MouseEvent event) {

    }

    @FXML
    void handleLoginClick(ActionEvent event) {

        if (txtUsername.getText().isBlank()==false && pwdPassword.getText().isBlank()==false) {
            validateSignIn();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    void validateSignIn(){

        String username = txtUsername.getText();
        String password = pwdPassword.isVisible() ? pwdPassword.getText() : pwdPassword.getText();

        if ("user".equals(username) && "password".equals(password)) {
            loginMessageLabel.setText("Login successful.");
        } else {
            loginMessageLabel.setText("Invalid username or password.");
        }
    }

}

