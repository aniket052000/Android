package com.example.e_mobile.productRetro;

import com.example.e_mobile.productRetro.MerchantEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductEntity {
    @SerializedName("productID")
    private String productID;

    @SerializedName("grandTotal")
    private double grandTotal;

    @SerializedName("productName")
    private String productName;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("description")
    private String description;

    @SerializedName("orderCount")
    private Long orderCount;

    @SerializedName("attribute1")
    private String attribute1;

    @SerializedName("attribute2")
    private String attribute2;

    @SerializedName("attribute3")
    private String attribute3;

    @SerializedName("attribute4")
    private String attribute4;

    @SerializedName("attribute5")
    private String attribute5;

    @SerializedName("merchantList")
    private List<MerchantEntity> merchantEntityList;

    @SerializedName("usp")
    private String usp;

    @SerializedName("imageUrl")
    private String imageUrl;


    public ProductEntity(String productID, double grandTotal, String productName, String categoryName, Long orderCount, String attribute1, String attribute2, String attribute3, String attribute4, String attribute5, List<MerchantEntity> merchantEntityList, String usp, String description, String imageUrl) {
        this.productID = productID;
        this.grandTotal = grandTotal;
        this.productName = productName;
        this.categoryName = categoryName;
        this.orderCount = orderCount;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.merchantEntityList = merchantEntityList;
        this.usp = usp;
        this.description = description;
        this.imageUrl = imageUrl;
    }

//    public ProductModel(String productID, double grandTotal) {
//        this.productID = productID;
//        this.grandTotal = grandTotal;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public List<MerchantEntity> getMerchantList() {
        return merchantEntityList;
    }

    public void setMerchantList(List<MerchantEntity> merchantEntityList) {
        this.merchantEntityList = merchantEntityList;
    }

    public String getUsp() {
        return usp;
    }

    public void setUsp(String usp) {
        this.usp = usp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
}
