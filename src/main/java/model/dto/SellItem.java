package model.dto;

public class SellItem {
    private int productId;
    private String productName;
    private int sellerId;
    private int price;
    private int quantity;
    private int categoryId;
    private String status;

    public SellItem(int productId, String productName, int sellerId, int price, int quantity, int categoryId, String status) {
        this.productId = productId;
        this.productName = productName;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
    }

    // Default constructor
    public SellItem() {}

    // Getters and Setters
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    @Override
    public String toString() {
        return "SellItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", sellerId=" + sellerId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryId=" + categoryId +
                ", status='" + status + '\'' +
                '}';
    }
}
