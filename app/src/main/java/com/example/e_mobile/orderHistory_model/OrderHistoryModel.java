package com.example.e_mobile.orderHistory_model;

public class OrderHistoryModel {
    private String productID;
    private double grandTotal;

    public OrderHistoryModel(String productID, double grandTotal) {
        this.productID = productID;
        this.grandTotal = grandTotal;
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
