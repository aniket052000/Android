package com.example.e_mobile.cartRetro;

public class CartProducts {
    private String productId;
    private int quantity;
    private String merchantId;
    private String merchantName;
    private String image;
    private String productName;
    private double price;
    private double subTotal;

    //Receive for cart fragment
    public CartProducts(String productId, int quantity, String merchantId, String merchantName, String image, String productName, double price, double subTotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.image = image;
        this.productName = productName;
        this.price = price;
        this.subTotal = subTotal;
    }

    // Send
    public CartProducts(String productId, int quantity, String merchantId, String merchantName) {
        this.productId = productId;
        this.quantity = quantity;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
