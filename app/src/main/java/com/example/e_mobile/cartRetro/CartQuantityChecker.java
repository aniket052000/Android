package com.example.e_mobile.cartRetro;

import java.io.Serializable;

public class CartQuantityChecker implements Serializable {

    private String productId;
    private String merchantId;

    public CartQuantityChecker(String productId, String merchantId) {
        this.productId = productId;
        this.merchantId = merchantId;
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
