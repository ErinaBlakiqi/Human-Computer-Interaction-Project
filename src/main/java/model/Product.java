package model;

public class Product {
    private int productId;
    private String productName;
    private int sellerId;
    private int price;
    private int quantity;
    private int categoryId;
    private String status;

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
