package com.example.e_mobile.signupRetro;

import com.google.gson.annotations.SerializedName;

public class SignupEntity{

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	@SerializedName("address")
	private String address;

	@SerializedName("name")
	private String name;

	public SignupEntity(String name, String email, String password, String address) {
		this.password = password;
		this.address = address;
		this.name = name;
		this.email = email;
	}



	public String getPassword(){
		return password;
	}

	public String getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}
}