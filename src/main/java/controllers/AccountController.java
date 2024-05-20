package controllers;

import application.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class AccountController {
    @FXML
    private Button adminButton;

    @FXML
    private Button homeButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button productsButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private Rectangle rectId;

    @FXML
    private Button sellButton;

    @FXML
    private Button signoutButton;

    @FXML
    private Button userButton;

    @FXML
    private Label welcomeLabel;
    // Buttons
    @FXML private Button sellItemButton;
    @FXML private Button cartButton;
    @FXML private Button editAccountButton;

    // Labels
    @FXML private Label userLabel;
    @FXML private Label usernameLabel;
    @FXML private Label locationLabel;
    @FXML private Label bioLabel;
    @FXML private Label recentlyListedLabel;
    @FXML private Label collectionsLabel;

    // ImageViews
    @FXML private ImageView userImageView;

    // Containers
    @FXML private HBox itemsContainer;
    @FXML private HBox sellsContainer;

    // ScrollPanes
    @FXML private ScrollPane itemsScrollPane;
    @FXML private ScrollPane collectionsScrollPane;

    @FXML
    void handleAdmin(MouseEvent event) {
        Navigator.navigate(event, Navigator.ADMIN_PAGE);
    }

    @FXML
    void handleHome(MouseEvent event) {
        Navigator.navigate(event, Navigator.HOME_PAGE);
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
    // Event handlers for the UI interactions
    @FXML private void handleSellItem() {
    }
    @FXML private void handleEditAccount() {
    }
    @FXML
    private void initialize() {
    }
}
