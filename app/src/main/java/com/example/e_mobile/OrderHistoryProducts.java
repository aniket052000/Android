package com.example.e_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.e_mobile.RetrofitInterfaces.OrderInterface;
import com.example.e_mobile.builder.BuilderCart;
import com.example.e_mobile.orderHistory_adapter.OrderHistoryItemsAdapter;
import com.example.e_mobile.orderHistory_adapter.OrderHistoryProductsAdapter;
import com.example.e_mobile.orderhistoryRetro.OrderHistoryEntity;
import com.example.e_mobile.orderhistoryRetro.OrderItemsEntity;
import com.example.e_mobile.orderhistoryRetro.ProductsOrdersList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OrderHistoryProducts extends AppCompatActivity implements OrderHistoryProductsAdapter.OrderHistoryProductsDataInterface{

    String email;
    String orderId;

    public OrderHistoryProducts() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_products);

        OrderHistoryProductsDetailsApi();

    }

    public void OrderHistoryProductsDetailsApi() {


        Retrofit retrofit = BuilderCart.getInstance();
        orderId=getIntent().getStringExtra("orderId");

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "Default");

        OrderItemsEntity orderItemsEntity = new OrderItemsEntity(orderId, email);
        Call<OrderItemsEntity> orderHistoryEntityCall = retrofit.create(OrderInterface.class).postLogRecieveProductList(orderItemsEntity);

        Log.d("Hellllllllllllooooo", "1SliderAPI");

        orderHistoryEntityCall.enqueue(new Callback<OrderItemsEntity>() {
            @Override
            public void onResponse(Call<OrderItemsEntity> call, Response<OrderItemsEntity> response) {

                RecyclerView recyclerView = findViewById(R.id.orderhistoryorderrecycler);
                OrderHistoryProductsAdapter orderHistoryProductsAdapter = new OrderHistoryProductsAdapter(response.body().getProductsList(),  OrderHistoryProducts.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(orderHistoryProductsAdapter);

                //Toast.makeText(getApplicationContext(), response.body().getCa.get(0).getProductName(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(CartActivity.this,response.body().getUserEmail(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(Product.this, "Everything is correct", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OrderItemsEntity> call, Throwable t) {
                Toast.makeText(OrderHistoryProducts.this, "Everything is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onUserClick(ProductsOrdersList productsOrdersList) {


    }
}