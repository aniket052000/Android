package com.example.e_mobile.orderhistoryRetro;

import java.util.List;

public class OrderItemsEntity {
    private String orderId;
    private String userEmail;
    List<ProductsOrdersList> productsList;
    private Double grandTotal;


    public OrderItemsEntity(String orderId, String userEmail) {
        this.orderId = orderId;
        this.userEmail = userEmail;
    }

    public OrderItemsEntity(String orderId, String userEmail, List<ProductsOrdersList> productsList, Double grandTotal) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.productsList = productsList;
        this.grandTotal = grandTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<ProductsOrdersList> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductsOrdersList> productsList) {
        this.productsList = productsList;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }
}
