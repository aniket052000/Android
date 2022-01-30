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
import com.example.e_mobile.builder.BuilderOrder;
import com.example.e_mobile.cartRetro.CartRecieveEntity;
import com.example.e_mobile.orderHistory_adapter.OrderHistoryItemsAdapter;

import com.example.e_mobile.orderhistory.OrderHistoryModel;
import com.example.e_mobile.orderhistory.OrderListItem;
import com.example.e_mobile.orderhistory.ProductsListItem;
import com.example.e_mobile.orderhistoryRetro.OrderHistoryEntity;
import com.example.e_mobile.orderhistoryRetro.OrderItemsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class OrderHistory extends AppCompatActivity implements OrderHistoryItemsAdapter.OrderListItemInterface{

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
        Retrofit retrofit = BuilderOrder.getInstance();
//        OrderInterface orderInterface=retrofit.create(OrderInterface.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "Default");


        Call<OrderHistoryModel> orderHistoryModelCall =retrofit.create(OrderInterface.class).postLogRecieve(email);
//        OrderItemsEntity orderItemsEntity=new OrderItemsEntity();
//        Call<List<OrderItemsEntity>> orderHistoryEntityCall =orderInterface.postLogRecieveProductList(orderItemsEntity);
        orderHistoryModelCall.enqueue(new Callback<OrderHistoryModel>() {
            @Override
            public void onResponse(Call<OrderHistoryModel> call, Response<OrderHistoryModel> response) {
          //      if(response.body()!=null) {


                    RecyclerView recyclerView = findViewById(R.id.orderhistoryrecycler);
                    OrderHistoryItemsAdapter orderHistoryItemsAdapter = new OrderHistoryItemsAdapter(response.body().getOrderList(), OrderHistory.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    recyclerView.setAdapter(orderHistoryItemsAdapter);

            //    }
//              //  else
//                {
//                    Toast.makeText(OrderHistory.this,"Not able to fetch history", Toast.LENGTH_SHORT).show();
//                }

                //Toast.makeText(getApplicationContext(), response.body().getCa.get(0).getProductName(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(CartActivity.this,response.body().getUserEmail(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(Product.this, "Everything is correct", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OrderHistoryModel> call, Throwable t) {
                Toast.makeText(OrderHistory.this, "Everything is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onUserClick(ProductsListItem productsListItem) {

//        Toast.makeText(this, "Image Clicked for" + productsListItem.getQuantity(), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, OrderHistoryProducts.class);
//        intent.putExtra("orderId",productsListItem.);
//        startActivity(intent);

    }

    @Override
    public void onUserClick(List<OrderListItem> orderListItem) {

    }
}