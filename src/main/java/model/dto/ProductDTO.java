package model.dto;

public class ProductDTO {
    private int productId;
    private String productName;
    private String sellerName;
    private int price;
    private String categoryName;

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public int getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // Setters
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
