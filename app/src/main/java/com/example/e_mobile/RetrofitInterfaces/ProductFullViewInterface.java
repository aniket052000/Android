package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.productRetro.ProductEntity;
import com.example.e_mobile.signupRetro.Respentity;
import com.example.e_mobile.signupRetro.SignupEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductFullViewInterface {
    @GET("/product/{id}")
    Call<ProductEntity> postLog(@Path("id") String  productID);
}
