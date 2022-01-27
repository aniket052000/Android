package com.example.e_mobile.productRetro;

import com.example.e_mobile.productRetro.MerchantEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductEntity implements Serializable {
    private String productId;
    private String productName;
    private String categoryName;
    private List<MerchantEntity> merchantList;
    private String image;
    private Long orderCount;
    private String description;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

    public ProductEntity(String productId, String productName, String categoryName, Long orderCount, String attribute1, String attribute2, String attribute3, String attribute4, String attribute5,  List<MerchantEntity> merchantList,  String description, String image ) {
        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
        this.merchantList = merchantList;
        this.image = image;
        this.orderCount = orderCount;
        this.description = description;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

    public List<MerchantEntity> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<MerchantEntity> merchantList) {
        this.merchantList = merchantList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
