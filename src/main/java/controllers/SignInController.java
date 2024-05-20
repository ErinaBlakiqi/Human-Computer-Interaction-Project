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
    public void handleCancelClick(ActionEvent event) {

        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCreateAccountClick(MouseEvent event) {

    }

    @FXML
    void handleLoginClick(ActionEvent event) {

        loginMessageLabel.setText("blla blla");

    }

    public void validateLogin(){
        
    }

}

