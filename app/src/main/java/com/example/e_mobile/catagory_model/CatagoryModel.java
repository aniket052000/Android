package com.example.e_mobile.catagory_model;

import java.io.Serializable;

public class CatagoryModel implements Serializable {

    private String categoryId;
    private String name;
    private String imgUrl;
    private int basePrice;

    // 🔥 New Fields
    private int productCount;
    private double discountPercentage;
    private boolean isFeatured;
    private boolean isActive;

    public CatagoryModel(String categoryId,
                         String name,
                         String imgUrl,
                         int basePrice,
                         int productCount,
                         double discountPercentage,
                         boolean isFeatured,
                         boolean isActive) {

        this.categoryId = categoryId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.basePrice = basePrice;
        this.productCount = productCount;
        this.discountPercentage = discountPercentage;
        this.isFeatured = isFeatured;
        this.isActive = isActive;
    }

    // 🔥 BUSINESS LOGIC METHODS

    public double getDiscountedPrice() {
        return basePrice - (basePrice * discountPercentage / 100);
    }

    public boolean hasDiscount() {
        return discountPercentage > 0;
    }

    public boolean isPopularCategory() {
        return productCount > 50;
    }

    public boolean isCategoryActive() {
        return isActive;
    }

    public String getDisplayTitle() {
        return name + " (" + productCount + " items)";
    }

    public String getFormattedPrice() {
        return "₹" + getDiscountedPrice();
    }

    // 🔹 Getters

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public boolean isActive() {
        return isActive;
    }
}
