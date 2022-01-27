package com.example.e_mobile.cartRetro;

public class CartEntity {
    private String id;
    private String userEmail;
    private Double grandTotal;
    private CartProductEntity cartProductEntity;

    public CartEntity(String id, String userEmail, Double grandTotal, CartProductEntity cartProductEntity) {
        this.id = id;
        this.userEmail = userEmail;
        this.grandTotal = grandTotal;
        this.cartProductEntity = cartProductEntity;
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

    public CartProductEntity getCartProductEntity() {
        return cartProductEntity;
    }

    public void setCartProductEntity(CartProductEntity cartProductEntity) {
        this.cartProductEntity = cartProductEntity;
    }
}
