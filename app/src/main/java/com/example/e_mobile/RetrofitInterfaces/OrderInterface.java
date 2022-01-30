package com.example.e_mobile.RetrofitInterfaces;

import com.example.e_mobile.addtocartRetro.AddToCartEntity;
import com.example.e_mobile.cartRetro.CartRecieveEntity;
import com.example.e_mobile.orderhistory.OrderHistoryModel;
import com.example.e_mobile.orderhistory.OrderListItem;
import com.example.e_mobile.orderhistoryRetro.OrderHistoryEntity;
import com.example.e_mobile.orderhistoryRetro.OrderItemsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderInterface {

    @GET("order/getAll/{email}")
    Call<OrderHistoryModel> postLogRecieve(@Path("email") String email);

    @GET("order/get")
    Call<OrderItemsEntity> postLogRecieveProductList(@Body OrderItemsEntity orderItemsEntity);

}
