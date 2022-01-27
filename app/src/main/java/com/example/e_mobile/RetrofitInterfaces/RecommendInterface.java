package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.recommendRetro.RecommendEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecommendInterface {
    @GET("/product/recommends")
    Call<List<RecommendEntity>> postlog();
}
