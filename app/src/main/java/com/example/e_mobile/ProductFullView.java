package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.e_mobile.RetrofitInterfaces.ProductFullViewInterface;
import com.example.e_mobile.builder.BuilderProductView;
import com.example.e_mobile.productRetro.MerchantEntity;
import com.example.e_mobile.productRetro.ProductEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductFullView extends AppCompatActivity {
    TextView productName;
    ImageView image;
    TextView description;
    TextView attribute1;
    TextView attribute2;
    TextView attribute3;
    TextView attribute4;
    TextView attribute5;
    TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_view);
        String productId=getIntent().getStringExtra("productId");

        getProductFullView(productId);
        productName=findViewById(R.id.Proname);
        image=findViewById(R.id.ProImg);
        description=findViewById(R.id.prodes);
        attribute1=findViewById(R.id.proattribute1);
        attribute2=findViewById(R.id.proattribute2);
        attribute3=findViewById(R.id.proattribute3);
        attribute4=findViewById(R.id.proattribute4);
        attribute5=findViewById(R.id.proattribute5);
        price=findViewById(R.id.Proprice);
    }
    private void getProductFullView(String productId){

        Retrofit retrofit= BuilderProductView.getInstance();
        ProductFullViewInterface productFullViewInterface=retrofit.create(ProductFullViewInterface.class);
        Call<ProductEntity> productEntityCall=productFullViewInterface.postLog("2");
        productEntityCall.enqueue(new Callback<ProductEntity>() {
            @Override
            public void onResponse(Call<ProductEntity> call, Response<ProductEntity> response) {
                Log.d("AAAAusdushudhushds", "BBBBBBBBBB");

                ProductEntity productEntityList=response.body();
                productName.setText(productEntityList.getProductName());
                description.setText(productEntityList.getDescription());
                attribute1.setText(productEntityList.getAttribute1());
                attribute2.setText(productEntityList.getAttribute2());
                attribute3.setText(productEntityList.getAttribute3());
                attribute4.setText(productEntityList.getAttribute4());
                attribute5.setText(productEntityList.getAttribute5());
                price.setText(String.valueOf(productEntityList.getMerchantList().get(0).getPrice()));
                Glide.with(image.getContext()).load(productEntityList.getImage()).placeholder(R.drawable.ic_baseline_person).into(image);
                Toast.makeText(ProductFullView.this,response.body().getProductId(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<ProductEntity> call, Throwable t) {
                Log.d("AVC",t.getMessage());
                Toast.makeText(ProductFullView.this,"Not able to fetch product",Toast.LENGTH_SHORT).show();
            }
        });
    }
}