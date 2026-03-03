package com.example.e_mobile.recommended_model;

import java.io.Serializable;

public class Recommended_Model implements Serializable, Comparable<Recommended_Model> {

    private String productId;
    private String name;
    private double originalPrice;
    private double discountPercentage;
    private String imageUrl;
    private double rating;
    private int totalReviews;
    private boolean inStock;
    private double recommendationScore;

    public Recommended_Model(String productId,
                             String name,
                             double originalPrice,
                             double discountPercentage,
                             String imageUrl,
                             double rating,
                             int totalReviews,
                             boolean inStock) {

        this.productId = productId;
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.totalReviews = totalReviews;
        this.inStock = inStock;

        calculateRecommendationScore();
    }

    // 🔥 BUSINESS LOGIC METHODS

    public double getFinalPrice() {
        return originalPrice - (originalPrice * discountPercentage / 100);
    }

    public boolean isHighlyRated() {
        return rating >= 4.5;
    }

    public boolean isTrending() {
        return totalReviews > 200;
    }

    public boolean isAvailable() {
        return inStock;
    }

    private void calculateRecommendationScore() {
        this.recommendationScore =
                (rating * 2) +
                (discountPercentage * 0.5) +
                (totalReviews * 0.01);
    }

    public double getRecommendationScore() {
        return recommendationScore;
    }

    @Override
    public int compareTo(Recommended_Model other) {
        return Double.compare(other.recommendationScore, this.recommendationScore);
    }

    // 🔹 Getters

    public String getProductId() { return productId; }

    public String getName() { return name; }

    public double getOriginalPrice() { return originalPrice; }

    public double getDiscountPercentage() { return discountPercentage; }

    public String getImageUrl() { return imageUrl; }

    public double getRating() { return rating; }

    public int getTotalReviews() { return totalReviews; }

    public boolean isInStock() { return inStock; }
}
