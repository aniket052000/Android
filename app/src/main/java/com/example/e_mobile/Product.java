package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


import com.example.e_mobile.product_adapter.ProductAdapter;
import com.example.e_mobile.productRetro.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity implements ProductAdapter.ProductDataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        List<ProductEntity> productEntities =new ArrayList<>();
//        generateUserData(productEntities);


//        List<ProductModel> productModelList = new ArrayList<>();
//        generateProductData(productModelList, "all");

        //Log.d("ABBBBBBBBBBBBBBBBBBBBBB", "Hello ....... "+ p);

        RecyclerView recyclerView=findViewById(R.id.productrecycler);
        ProductAdapter productAdapter =new ProductAdapter(productEntities,  Product.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(productAdapter);



    }



    @Override
    public void onUserClick(ProductEntity productEntity) {
//        SharedPreferences sharedPreferences=getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("productID",productEntity.getProductId());

        Toast.makeText(this, "Image Clicked for" + productEntity.getProductId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProductFullView.class);
        String p = productEntity.getProductId();
        intent.putExtra("productId",productEntity.getProductId());
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("product", productEntity);
        //intent.putExtra("merchantId", productEntity.getMerchantList().get(0).getMerchantId());
        startActivity(intent);
    }
}