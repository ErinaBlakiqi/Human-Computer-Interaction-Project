package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.AdminProductDTO;
import services.AdminProductService;

public class EditProductController {

    @FXML
    private TextField productNameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField categoryField;

    private AdminProductService adminProductService;
    private AdminProductDTO product;

    public void setProductDetails(AdminProductDTO product, AdminProductService adminProductService) {
        this.product = product;
        this.adminProductService = adminProductService;
        productNameField.setText(product.getProductName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getQuantity()));
        categoryField.setText(product.getCategoryName());
    }

    @FXML
    private void handleSave() {
        product.setProductName(productNameField.getText());
        product.setPrice(Integer.parseInt(priceField.getText()));
        product.setQuantity(Integer.parseInt(quantityField.getText()));
        product.setCategoryName(categoryField.getText());

        // Assuming category name is mapped to category id
        product.setCategoryId(getCategoryIdFromName(categoryField.getText()));

        try {
            adminProductService.updateProduct(product);
            // Close the pop-up window
            Stage stage = (Stage) productNameField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getCategoryIdFromName(String categoryName) {
        // Implement this method to return categoryId based on categoryName
        // This is a placeholder and should be replaced with actual logic to fetch categoryId
        return 1; // Replace with actual implementation
    }
}
