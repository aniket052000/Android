package com.example.e_mobile.orderhistory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryModel{

	@SerializedName("orderList")
	private List<OrderListItem> orderList;

	@SerializedName("userEmail")
	private String userEmail;

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
}