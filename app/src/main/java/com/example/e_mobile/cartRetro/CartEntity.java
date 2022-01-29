package com.example.e_mobile.cartRetro;

import java.util.List;

public class CartEntity {

    private String id;
    private String userEmail;
    private Double grandTotal;
    private CartProducts cartProducts;


    //For Send
    public CartEntity(String userEmail, CartProducts cartProducts) {
        this.userEmail = userEmail;
        this.cartProducts = cartProducts;
    }

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

    public CartProducts getCartProductEntity() {
        return cartProducts;
    }

    public void setCartProductEntity(CartProducts cartProducts) {
        this.cartProducts = cartProducts;
    }
}
