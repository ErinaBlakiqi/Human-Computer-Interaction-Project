package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ProductController {

    @FXML
    private Button btnOrder_Products;

    @FXML
    private Button btnRemove_Products;

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
    private TableColumn<?, ?> col_Price_Products;

    @FXML
    private TableColumn<?, ?> col_Product_Products;

    @FXML
    private TableColumn<?, ?> col_Quantity_Products;

    @FXML
    private TextField fieldProductSearch;

    @FXML
    private ChoiceBox<?> listPayment_Products;

    @FXML
    private Pane pane_Products;

    @FXML
    private ImageView profileImage;

    @FXML
    private TableView<?> tableCart;

    @FXML
    private TableView<?> tableProducts;

    @FXML
    private Label txtTotal_Products;

    @FXML
    void onActionOrder(ActionEvent event) {

    }

    @FXML
    void onActionRemove(ActionEvent event) {

    }

    @FXML
    void onActionSearch(ActionEvent event) {

    }

    @FXML
    void onClickPayment(MouseEvent event) {

    }

}
