package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Navigator {

    public static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public final static String PRODUCTS_PAGE = "/views/products.fxml";
    public final static String SELL_PAGE = "/views/sellitem.fxml";
    public final static String USER_PAGE = "/views/account2.fxml";
    public final static String ADMIN_DASHBOARD_PAGE = "/views/AdminDashboard.fxml";
    public final static String ADMIN_PRODUCTS_PAGE = "/views/AdminProducts.fxml";
    public final static String ADMIN_ORDERS_PAGE = "/views/AdminOrder.fxml";
    public final static String SIGNIN_PAGE = "/views/SignIn.fxml";
    public final static String SIGNUP_PAGE = "/views/SignUp.fxml";
    public final static String EDIT_PAGE = "/views/editAccount.fxml";
    public final static String CHANGEPASSWORD_PAGE = "/views/ChangePassword.fxml";

    public static void navigate(String fxmlFile) {
        if (stage == null) {
            throw new IllegalStateException("Stage is not set. Call Navigator.setStage() before navigating.");
        }
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxmlFile), bundle);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeLanguage(Locale locale, String currentFxmlFile) {
        Locale.setDefault(locale);
        navigate(currentFxmlFile); // Reload the current page to apply the language change
    }
}
