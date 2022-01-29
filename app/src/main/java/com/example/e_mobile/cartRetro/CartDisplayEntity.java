package com.example.e_mobile.cartRetro;

import java.io.Serializable;
import java.util.List;

public class CartDisplayEntity implements Serializable {
    private String id;
    private String userEmail;
    private Double grandTotal;
    private List<CartProducts> cartProductsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }
}
