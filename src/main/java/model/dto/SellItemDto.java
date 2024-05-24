package model.dto;

import javafx.beans.property.*;

public class SellItemDto {
    private final IntegerProperty productId = new SimpleIntegerProperty();
    private final StringProperty productName = new SimpleStringProperty();
    private final IntegerProperty sellerId = new SimpleIntegerProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final IntegerProperty categoryId = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();

    // Getters and setters using JavaFX properties
    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public int getSellerId() {
        return sellerId.get();
    }

    public void setSellerId(int sellerId) {
        this.sellerId.set(sellerId);
    }

    public IntegerProperty sellerIdProperty() {
        return sellerId;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public IntegerProperty categoryIdProperty() {
        return categoryId;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
