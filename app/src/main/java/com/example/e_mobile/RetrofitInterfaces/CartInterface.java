package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.addtocartRetro.AddToCartEntity;
import com.example.e_mobile.cartRetro.CartEntity;
import com.example.e_mobile.cartRetro.CartQuantityChecker;
import com.example.e_mobile.cartRetro.CartRecieveEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface CartInterface {


//        @POST("/cart/add")
//        Call<Void> postLog(@Body CartEntity cartEntity);

        @POST("/cart/add")
        Call<Void> postLog(@Body AddToCartEntity addToCartEntity);

        @GET("/cart/get/{mail}")
        Call<CartRecieveEntity> postLogRecieve(@Path("mail") String mail);


        @GET("/cart/stock")
        Call<Long> postLogGetQty(@Body CartQuantityChecker cartQuantityChecker);

        @POST("/cart/execute/{mail}")
        Call<Void> postLogPlace(@Path("mail") String mail);




}
