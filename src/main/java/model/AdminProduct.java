package model;

public class AdminProduct {
    private int productId;
    private String productName;
    private int sellerId;
    private double price;
    private int quantity;
    private int categoryId;
    private String status;

    // Constructor including productId
    public AdminProduct(int productId, String productName, int sellerId, double price, int quantity, int categoryId, String status) {
        this.productId = productId;
        this.productName = productName;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
    }

    // Constructor without productId
    public AdminProduct(String productName, int sellerId, double price, int quantity, int categoryId, String status) {
        this.productName = productName;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
    }

    // Getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
