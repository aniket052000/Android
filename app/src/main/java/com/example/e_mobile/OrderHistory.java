package com.example.e_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import com.example.e_mobile.RetrofitInterfaces.OrderInterface;
import com.example.e_mobile.builder.BuilderCart;
import com.example.e_mobile.cartRetro.CartRecieveEntity;
import com.example.e_mobile.orderHistory_adapter.OrderHistoryItemsAdapter;
import com.example.e_mobile.orderHistory_model.OrderHistoryModel;
import com.example.e_mobile.orderhistoryRetro.OrderHistoryEntity;
import com.example.e_mobile.orderhistoryRetro.OrderItemsEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class OrderHistory extends AppCompatActivity implements OrderHistoryItemsAdapter.OrderHistoryItemsInterface{

    String email;

    public OrderHistory() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_history);



        OrderHistoryDetailsApi();

    }

    public void OrderHistoryDetailsApi() {


        Retrofit retrofit = BuilderCart.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "Default");

        Call<OrderHistoryEntity> orderHistoryEntityCall = retrofit.create(OrderInterface.class).postLogRecieve(email);

        Log.d("Hellllllllllllooooo", "1SliderAPI");

        orderHistoryEntityCall.enqueue(new Callback<OrderHistoryEntity>() {
            @Override
            public void onResponse(Call<OrderHistoryEntity> call, Response<OrderHistoryEntity> response) {

                RecyclerView recyclerView = findViewById(R.id.cartrecycler);
                OrderHistoryItemsAdapter orderHistoryItemsAdapter = new OrderHistoryItemsAdapter(response.body().getOrderList(),  OrderHistory.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(orderHistoryItemsAdapter);

                //Toast.makeText(getApplicationContext(), response.body().getCa.get(0).getProductName(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(CartActivity.this,response.body().getUserEmail(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(Product.this, "Everything is correct", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OrderHistoryEntity> call, Throwable t) {
                Toast.makeText(OrderHistory.this, "Everything is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onUserClick(OrderItemsEntity orderItemsEntity) {

        Toast.makeText(this, "Image Clicked for" + orderItemsEntity.getOrderId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OrderHistoryProducts.class);
        intent.putExtra("orderId",orderItemsEntity.getOrderId());
        startActivity(intent);

    }
}