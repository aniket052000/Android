package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.e_mobile.RetrofitInterfaces.CartInterface;
import com.example.e_mobile.builder.BuilderCart;
import com.example.e_mobile.builder.BuilderProduct;
import com.example.e_mobile.cartRetro.CartProducts;
import com.example.e_mobile.cartRetro.CartRecieveEntity;
import com.example.e_mobile.cart_model.CartModel;
import com.example.e_mobile.cart_recieve_adapter.CartRecieveAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements CartRecieveAdapter.CartDataInterface{


    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartProductApi();
        Log.d("ABBBBBBBBBBBBBBBBBBBBBB", "Hello ....... ");

    }


    public void CartProductApi() {

        Retrofit retrofit = BuilderCart.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.inkedpages", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "Default");

        Call<CartRecieveEntity> productListCall = retrofit.create(CartInterface.class).postLogRecieve(email);

        Log.d("Hellllllllllllooooo", "1SliderAPI");

        productListCall.enqueue(new Callback<CartRecieveEntity>() {
            @Override
            public void onResponse(Call<CartRecieveEntity> call, Response<CartRecieveEntity> response) {

                RecyclerView recyclerView = findViewById(R.id.CartRecycler);
                CartRecieveAdapter cartRecieveAdapter = new CartRecieveAdapter(response.body().getCartProductList(),  CartActivity.this);
               // Toast.makeText(CartActivity.this,response.body().getUserEmail(), Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(cartRecieveAdapter);

                Toast.makeText(getApplicationContext(), response.body().getCartProductList().get(0).getProductName(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(CartActivity.this,response.body().getUserEmail(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(Product.this, "Everything is correct", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CartRecieveEntity> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Everything is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onUserClick(CartProducts cartProducts) {

    }
}