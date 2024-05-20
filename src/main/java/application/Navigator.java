package application;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Navigator{
    public final static String HOME_PAGE="home.fxml";
    public final static String PRODUCTS_PAGE="products.fxml";
    public final static String SELL_PAGE="sellitem.fxml";
    public final static String USER_PAGE="account2.fxml";
    public final static String ADMIN_PAGE="admin.fxml";
    public final static String SIGNIN_PAGE="SignIn.fxml";
    public static void navigate(Stage stage, String page) {
        FXMLLoader loader=new FXMLLoader(
                Navigator.class.getResource(page)
        );
        try{
            Scene scene=new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void navigate(Event event, String page){
        Node node=(Node)event.getSource();
        Stage stage=(Stage)node.getScene().getWindow();
        navigate(stage,page);
    }
}
