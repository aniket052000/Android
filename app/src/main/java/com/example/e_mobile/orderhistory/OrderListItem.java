package com.example.e_mobile.orderhistory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderListItem{

	@SerializedName("productsList")
	private List<ProductsListItem> productsList;

	@SerializedName("orderId")
	private String orderId;

	@SerializedName("grandTotal")
	private double grandTotal;

	@SerializedName("userEmail")
	private String userEmail;

	public void setProductsList(List<ProductsListItem> productsList){
		this.productsList = productsList;
	}

	public List<ProductsListItem> getProductsList(){
		return productsList;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setGrandTotal(double grandTotal){
		this.grandTotal = grandTotal;
	}

	public double getGrandTotal(){
		return grandTotal;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}
}