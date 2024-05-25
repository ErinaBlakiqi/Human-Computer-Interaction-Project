package application;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Navigator{

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
    public final static String PRODUCTS_PAGE= "views/products.fxml";
    public final static String SELL_PAGE= "views/sellitem.fxml";
    public final static String USER_PAGE= "views/account2.fxml";

    public final static String ADMIN_PAGE= "views/AdminDashboard.fxml";

    public final static String SIGNIN_PAGE="com.example.shitjeblerjeonline/SignIn.fxml";

    public final static String SIGNUP_PAGE="com.example.shitjeblerjeonline/SignUp.fxml";
    public final static String EDIT_PAGE="views/editAccount.fxml";
    public final static String CHANGEPASSWORD_PAGE="com.example.shitblerjeonline/ChangePassword.fxml";




    public static void navigate(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Navigator.class.getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void navigate(Event event, String page){
//        Node node=(Node)event.getSource();
//        Stage stage=(Stage)node.getScene().getWindow();
//        navigate(stage,page);
//    }
}
