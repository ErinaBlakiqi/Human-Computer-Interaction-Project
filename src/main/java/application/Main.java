package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the stage in the Navigator
        Navigator.setStage(primaryStage);

        // Load the FXML file using the correct path
        Parent root = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
