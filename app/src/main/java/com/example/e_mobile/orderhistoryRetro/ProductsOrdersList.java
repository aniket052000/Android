package com.example.e_mobile.orderhistoryRetro;

import java.io.Serializable;

public class ProductsOrdersList implements Serializable {
    private String productId;
    private String productName;
    private String merchantId;
    private int quantity;
    private String image;
    private String usp;
    private Double price;
    private Double productRating;
    private Double total;

    public ProductsOrdersList() {
    }

    public ProductsOrdersList(String productId, String productName, String merchantId, int quantity, String image, String usp, Double price, Double productRating, Double total) {
        this.productId = productId;
        this.productName = productName;
        this.merchantId = merchantId;
        this.quantity = quantity;
        this.image = image;
        this.usp = usp;
        this.price = price;
        this.productRating = productRating;
        this.total = total;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsp() {
        return usp;
    }

    public void setUsp(String usp) {
        this.usp = usp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getProductRating() {
        return productRating;
    }

    public void setProductRating(Double productRating) {
        this.productRating = productRating;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
