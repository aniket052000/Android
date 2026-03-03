package com.example.e_mobile.orderhistory;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderHistoryModel implements Serializable {

    @SerializedName("orderList")
    private List<OrderListItem> orderList;

    @SerializedName("userEmail")
    private String userEmail;

    // 🔹 Basic Getters & Setters

    public void setOrderList(List<OrderListItem> orderList){
        this.orderList = orderList;
    }

    public List<OrderListItem> getOrderList(){
        return orderList;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserEmail(){
        return userEmail;
    }

    // 🔥 BUSINESS LOGIC METHODS

    public int getTotalOrders() {
        return orderList == null ? 0 : orderList.size();
    }

    public boolean hasOrders() {
        return orderList != null && !orderList.isEmpty();
    }

    public double getTotalSpent() {
        if (orderList == null) return 0.0;

        double total = 0.0;
        for (OrderListItem item : orderList) {
            total += item.getOrderAmount();  // assuming OrderListItem has getOrderAmount()
        }
        return total;
    }

    public OrderListItem getMostRecentOrder() {
        if (orderList == null || orderList.isEmpty()) {
            return null;
        }

        return Collections.max(orderList,
                Comparator.comparing(OrderListItem::getOrderDate)); // assuming getOrderDate()
    }

    public boolean isHighValueCustomer() {
        return getTotalSpent() > 50000; // threshold logic
    }

    public boolean isValidUserEmail() {
        return userEmail != null && userEmail.contains("@");
    }
}
