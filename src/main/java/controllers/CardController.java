package controllers;

import data.productData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    @FXML
    private Button btnAddProduct;

    @FXML
    private Spinner<?> countProduct;

    @FXML
    private ImageView imgProductView;

    @FXML
    private AnchorPane paneCard;

    @FXML
    private Label txtProductName;

    @FXML
    private Label txtProductPrice;
    private productData prodData;
    private Image image;
    public void setData(productData prodData){
        this.prodData = prodData;

        txtProductName.setText(prodData.getProductName());
        txtProductPrice.setText(String.valueOf(prodData.getPrice()));
        String path = "File:" + prodData.getImage();
        image = new Image(path, 168, 123, false, true);
        imgProductView.setImage(image);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
