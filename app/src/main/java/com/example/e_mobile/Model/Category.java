package com.example.e_mobile.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class Category implements Serializable {


    @SerializedName("id")
    private BigInteger id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;


    public Category(BigInteger id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
