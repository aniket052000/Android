package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.signupRetro.Respentity;
import com.example.e_mobile.signupRetro.SignupEntity;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupInterface {

    @POST("/user/signup")
    Call<Respentity> postLog(@Body SignupEntity signupEntity);

}
