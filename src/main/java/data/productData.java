package data;


import java.sql.Date;

public class productData {

    private int ProductId;
    private int SellerId;
    private String productName;
    private String description;
    private int CategoryId;
    private Double price;
    private String status;
    private String image;
    private Date date;
    private int quantity;

    public productData(int productId, int sellerId, String productName, String description, int categoryId, Double price, String status, String image, Date date, int quantity) {
        ProductId = productId;
        SellerId = sellerId;
        this.productName = productName;
        this.description = description;
        CategoryId = categoryId;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
        this.quantity = quantity;
    }

    public productData(int ProductId, String productName,
                       int quantity, Double price, String image, Date date){
        this.ProductId = ProductId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.date = date;
        this.quantity = quantity;
    }

    public int getProductId() {
        return ProductId;
    }

    public int getSellerId() {
        return SellerId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }
}