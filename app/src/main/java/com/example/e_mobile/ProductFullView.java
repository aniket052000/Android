package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.e_mobile.RetrofitInterfaces.CartInterface;
import com.example.e_mobile.RetrofitInterfaces.ProductFullViewInterface;
import com.example.e_mobile.builder.BuilderCart;
import com.example.e_mobile.builder.BuilderProductView;
import com.example.e_mobile.cartRetro.CartEntity;
import com.example.e_mobile.cartRetro.CartProductEntity;
import com.example.e_mobile.cart_adapter.CartAdapter;
import com.example.e_mobile.loginRetro.Login;
import com.example.e_mobile.productRetro.ProductEntity;

import java.util.List;

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
    Button addtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_view);
        String productId=getIntent().getStringExtra("productId");
        String merchantId=getIntent().getStringExtra("merchantId");

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

        addtocart=findViewById(R.id.PFVAddtocart);

        addtocart.setOnClickListener(view -> {
//            loginAPI(email.getText().toString(),pwd.getText().toString());
            SharedPreferences sharedPreferences=getSharedPreferences("com.example.inkedpages", Context.MODE_PRIVATE);

            String email=sharedPreferences.getString("email","Default");
            Double price = Double.parseDouble(sharedPreferences.getString("price", "1"));
            int quantity = Integer.parseInt(sharedPreferences.getString("quantity", "1"));
            Double grandTotal = Double.parseDouble(sharedPreferences.getString("grandTotal", "1"));




            cartApi(email , grandTotal, productId, quantity, merchantId, price);
        });

    }

    private void cartApi(String email, Double grandTotal , String productId, int quantity, String merchantId, Double price){

        CartProductEntity cartProductEntity = new CartProductEntity(productId, quantity, merchantId, price);
        CartEntity cartEntity = new CartEntity(productId, email, grandTotal, cartProductEntity);

        Retrofit retrofit = BuilderCart.getInstance();
        CartInterface cartInterface = retrofit.create(CartInterface.class);


        Call<java.lang.Void> cartInterfaceCall = cartInterface.postLog(cartEntity);
        cartInterfaceCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ProductFullView.this,"Hello Boys Chai Pilo",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void getProductFullView(String productId){

        Retrofit retrofit= BuilderProductView.getInstance();
        ProductFullViewInterface productFullViewInterface=retrofit.create(ProductFullViewInterface.class);
        Call<ProductEntity> productEntityCall=productFullViewInterface.postLog(productId);

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

                SharedPreferences sharedPreferences=getSharedPreferences("com.example.inkedpages", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor=sharedPreferences.edit();


                editor.putString("quantity", "1");
                editor.putString("merchantId", "0");
                editor.putString("price", String.valueOf(productEntityList.getMerchantList().get(0).getPrice()));
                editor.putString("grandTotal", String.valueOf(productEntityList.getMerchantList().get(0).getPrice()));

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