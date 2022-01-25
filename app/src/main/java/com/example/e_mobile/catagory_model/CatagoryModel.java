package com.example.e_mobile.catagory_model;

public class CatagoryModel {
    private String name;

    private int price;
    private String imgUrl;

    public CatagoryModel(String name, String imgUrl, int price) {
        this.name = name;

        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
