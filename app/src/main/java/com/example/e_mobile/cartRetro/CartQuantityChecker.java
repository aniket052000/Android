package com.example.e_mobile.cartRetro;

import java.io.Serializable;

public class CartQuantityChecker implements Serializable {

    private String productId;
    private String merchantId;
    private int quantity;

    public CartQuantityChecker(String productId, String merchantId, int quantity) {
        this.productId = productId;
        this.merchantId = merchantId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
