package com.example.e_mobile.cartRetro;

public class CartProductEntity {
    private String productId;
    private int quantity;
    private String merchantId;
    private double price;

    public CartProductEntity(String productId, int quantity, String merchantId, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.merchantId = merchantId;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
