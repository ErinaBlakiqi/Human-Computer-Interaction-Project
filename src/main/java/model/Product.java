package model;

public class Product {
    private int productId;
    private String productName;
    private String description;
    private String category;
    private int quantity;
    private double price;
    private String status;

    public Product(int productId, String productName, String description, String category, int quantity, double price, String status) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public Product(String productName, String description, String category, int quantity, double price, String status) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
