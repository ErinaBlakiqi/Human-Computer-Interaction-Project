package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.SellItemDto;
import services.SellItemService;

public class SellItemEditController {

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    private SellItemService sellItemService;
    private SellItemDto product;

    public void setProductDetails(SellItemDto product, SellItemService sellItemService) {
        this.product = product;
        this.sellItemService = sellItemService;
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getQuantity()));
    }

    @FXML
    private void handleSave() {
        product.setPrice(Double.parseDouble(priceField.getText()));
        product.setQuantity(Integer.parseInt(quantityField.getText()));

        try {
            sellItemService.updateItem(product);
            Stage stage = (Stage) priceField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
