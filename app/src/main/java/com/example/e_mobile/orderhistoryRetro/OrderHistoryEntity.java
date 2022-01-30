package com.example.e_mobile.orderhistoryRetro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderHistoryEntity implements Serializable {
    @SerializedName("userEmail")
    private String userEmail;
    @SerializedName("orderList")
    private List<OrderItemsEntity> orderList;

    public OrderHistoryEntity(String userEmail, List<OrderItemsEntity> orderList) {
        this.userEmail = userEmail;
        this.orderList = orderList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<OrderItemsEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderItemsEntity> orderList) {
        this.orderList = orderList;
    }
}
