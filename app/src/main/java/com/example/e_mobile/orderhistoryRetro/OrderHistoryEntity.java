package com.example.e_mobile.orderhistoryRetro;

import java.util.List;

public class OrderHistoryEntity {
    private String userEmail;
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
