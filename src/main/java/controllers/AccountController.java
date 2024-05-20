package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccountController {

    // Buttons
    @FXML private Button sellItemButton;
    @FXML private Button cartButton;
    @FXML private Button editAccountButton;

    // Labels
    @FXML private Label profileLabel;
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

    // Event handlers for the UI interactions
    @FXML private void handleSellItem() {
    }

    @FXML private void handleCart() {
    }

    @FXML private void handleEditAccount() {
    }
    @FXML
    private void initialize() {
    }
}
