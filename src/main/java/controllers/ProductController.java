package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ProductController {

    @FXML
    private Button btnPay_Products;

    @FXML
    private Button btnRemove_Products;

    @FXML
    private TableColumn<?, ?> col_Price_Products;

    @FXML
    private TableColumn<?, ?> col_Product_Products;

    @FXML
    private TableColumn<?, ?> col_Quantity_Products;

    @FXML
    private ChoiceBox<?> listPayment_Products;

    @FXML
    private Pane pane_Products;

    @FXML
    private ImageView profileImage;

    @FXML
    private ScrollPane scrollPane_Products;

    @FXML
    private TableView<?> tableView_Products;

    @FXML
    private TextField txtAmount_Products;

    @FXML
    private Label txtTotal_Products;



}
