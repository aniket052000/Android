package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.productRetro.ProductEntity;
import com.example.e_mobile.signupRetro.Respentity;
import com.example.e_mobile.signupRetro.SignupEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SliderInterface {
    @POST("/product/cat/{name}")
    Call<List<ProductEntity> >postLog(@Path("name") String catName);
}
