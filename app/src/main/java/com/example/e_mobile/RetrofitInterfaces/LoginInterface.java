package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.loginRetro.LoginEntity;
import com.example.e_mobile.signupRetro.Respentity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    @POST("/user/signin")
    Call<Respentity> postLog(@Body LoginEntity loginEntity);
}