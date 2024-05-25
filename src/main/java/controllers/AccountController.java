package controllers;

import application.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AccountController {

    @FXML
    private Button adminButton;

    @FXML
    private Label bioLabel;

    @FXML
    private AreaChart<?, ?> boughtProductsChartProfile;

    @FXML
    private CategoryAxis boughtXAxis;

    @FXML
    private NumberAxis boughtYAxis;

    @FXML
    private Label contactLabel;

    @FXML
    private TableColumn<?, ?> dateBoughtColumn;

    @FXML
    private TableColumn<?, ?> dateSoldColumn;

    @FXML
    private Button editAccountButton;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<?, ?> itemBoughtColumn;

    @FXML
    private TableColumn<?, ?> itemSoldColumn;

    @FXML
    private TableView<?> lastItemsBoughtTableProfile;

    @FXML
    private TableView<?> lastItemsSoldTableProfile;

    @FXML
    private Label locationLabel;

    @FXML
    private TableColumn<?, ?> priceBoughtColumn;

    @FXML
    private TableColumn<?, ?> priceSoldColumn;

    @FXML
    private Button productsButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button sellButton;

    @FXML
    private Button signoutButton;

    @FXML
    private AreaChart<?, ?> soldProductsChartProfile;

    @FXML
    private CategoryAxis soldXAxis;

    @FXML
    private NumberAxis soldYAxis;

    @FXML
    private Button userButton;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;

    @FXML
    void handleAdmin(MouseEvent event) {
        Navigator.navigate(event, Navigator.ADMIN_PAGE);
    }

    @FXML
    void handleEditAccount(ActionEvent event) {
        Navigator.navigate(event, Navigator.EDIT_PAGE);
    }

    @FXML
    void handleProducts(MouseEvent event) {
        Navigator.navigate(event, Navigator.PRODUCTS_PAGE);
    }

    @FXML
    void handleSell(MouseEvent event) {
        Navigator.navigate(event, Navigator.SELL_PAGE);
    }

    @FXML
    void handleSignOut(MouseEvent event) {
        Navigator.navigate(event, Navigator.SIGNIN_PAGE);
    }

    @FXML
    void handleUser(MouseEvent event) {
        Navigator.navigate(event, Navigator.USER_PAGE);
    }

    public void handleChangePassword(ActionEvent actionEvent) {
    }
}
