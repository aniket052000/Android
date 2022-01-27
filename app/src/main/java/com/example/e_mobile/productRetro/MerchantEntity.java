package com.example.e_mobile.productRetro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MerchantEntity implements Serializable {


    @SerializedName("id")
    private Long merchantId;

    @SerializedName("stock")
    private String stock;

    @SerializedName("price")
    private double price;

    @SerializedName("rating")
    private double rating;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
