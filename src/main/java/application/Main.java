package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the default locale to English
        Locale.setDefault(new Locale("en"));
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());

        // Load the FXML file with the resource bundle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SignIn.fxml"), bundle);
        Parent root = loader.load();

        // Set the primary stage
        Navigator.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}