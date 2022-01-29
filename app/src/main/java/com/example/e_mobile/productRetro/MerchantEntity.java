package com.example.e_mobile.productRetro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MerchantEntity implements Serializable {


    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("merchantName")
    private String merchantName;
    @SerializedName("usp")
    private String usp;
    @SerializedName("stock")
    private Long stock;
    @SerializedName("price")
    private double price;
    @SerializedName("productRating")
    private double productRating;

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

    public String getUsp() {
        return usp;
    }

    public void setUsp(String usp) {
        this.usp = usp;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }
}
