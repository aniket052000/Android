package com.example.e_mobile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_mobile.cart_adapter.CartAdapter;
import com.example.e_mobile.cart_model.CartModel;
import com.example.e_mobile.orderHistory_adapter.OrderHistoryAdapter;
import com.example.e_mobile.orderHistory_model.OrderHistoryModel;

import java.util.ArrayList;
import java.util.List;


public class OrderHistory extends AppCompatActivity implements OrderHistoryAdapter.OrderHistoryDataInterface{


    public OrderHistory() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_history);
        List<OrderHistoryModel> orderHistoryModels=new ArrayList<>();
        generateUserData(orderHistoryModels);
        RecyclerView recyclerView=findViewById(R.id.orderhistoryrecycler);
        OrderHistoryAdapter orderHistoryAdapter=new OrderHistoryAdapter(orderHistoryModels,  OrderHistory.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(orderHistoryAdapter);

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_order_history, container, false);
//    }
    private void generateUserData(List<OrderHistoryModel> orderHistoryModelList) {

        orderHistoryModelList.add(new OrderHistoryModel("Employee 1", 100));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 2", 101));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 3", 102));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 4", 103));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 5", 104));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 6", 105));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 7", 106));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 8", 107));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 9", 108));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 10", 109));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 11", 110));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 12", 111));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 13", 112));
        orderHistoryModelList.add(new OrderHistoryModel("Employee 14", 113));
    }

    @Override
    public void onUserClick(OrderHistoryModel orderHistoryModel) {

    }
}