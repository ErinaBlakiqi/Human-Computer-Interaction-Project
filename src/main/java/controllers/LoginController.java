package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {


    @FXML
    private Button cancleButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("urln e fotos");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("urln e fotos");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }




    public void loginButtonOnAction(ActionEvent event){

        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){

           validateLogin();

        }else{

            loginMessageLabel.setText("Please enter username and password");

        }


    }
    public void CancleButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancleButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){

    }
}
