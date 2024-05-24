package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AdminProductController {

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> colAction_products;

    @FXML
    private TableColumn<?, ?> colCategory_products;

    @FXML
    private TableColumn<?, ?> colPrice_products;

    @FXML
    private TableColumn<?, ?> colProductName_products;

    @FXML
    private TableColumn<?, ?> colSeller_products;

    @FXML
    private TextField fieldProductSearch;

    @FXML
    private Pane pane_Products;

    @FXML
    private ImageView profileImage;

    @FXML
    private TableView<?> tableProductsPage;

    @FXML
    void onActionSearch(ActionEvent event) {

    }

}
