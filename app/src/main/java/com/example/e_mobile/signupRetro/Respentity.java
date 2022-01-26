package com.example.e_mobile.signupRetro;

import com.google.gson.annotations.SerializedName;

public class Respentity{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}