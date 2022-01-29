package com.example.e_mobile.cartRetro;

public class CartProductEntity {
    private String productId;
    private int quantity;
    private String merchantId;
    private String merchantName;

    public CartProductEntity(String productId, int quantity, String merchantId, String merchantName) {
        this.productId = productId;
        this.quantity = quantity;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
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
