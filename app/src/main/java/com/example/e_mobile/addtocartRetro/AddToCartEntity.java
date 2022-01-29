package com.example.e_mobile.addtocartRetro;

import java.io.Serializable;

public class AddToCartEntity implements Serializable {
    private String userEmail;
    private String productId;
    private int quantity;
    private String merchantId;

    public AddToCartEntity(String userEmail, String productId, int quantity, String merchantId) {
        this.userEmail = userEmail;
        this.productId = productId;
        this.quantity = quantity;
        this.merchantId = merchantId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
}
