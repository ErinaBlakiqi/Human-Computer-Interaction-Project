package model.dto;

public class AdminProductDTO {
    private int productId;
    private String productName;
    private String sellerName;
    private double price;
    private int quantity;
    private String categoryName;
    private String status;

    // Constructor
    public ProductDTO(int productId, String productName, String sellerName, double price, int quantity, String categoryName, String status) {
        this.productId = productId;
        this.productName = productName;
        this.sellerName = sellerName;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

}

