package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.e_mobile.RetrofitInterfaces.CartInterface;
import com.example.e_mobile.RetrofitInterfaces.ProductFullViewInterface;
import com.example.e_mobile.addtocartRetro.AddToCartEntity;
import com.example.e_mobile.builder.BuilderCart;
import com.example.e_mobile.builder.BuilderProduct;
import com.example.e_mobile.builder.BuilderProductView;
import com.example.e_mobile.cartRetro.CartEntity;
import com.example.e_mobile.cartRetro.CartProducts;
import com.example.e_mobile.productRetro.MerchantEntity;
import com.example.e_mobile.productRetro.ProductEntity;

import java.util.ArrayList;
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
    Button addtocart;
    String merchantId;
    public Integer number;
    String email;
    Button buynow;
    Button gotocart;
    String merchantName;
    //Button gotocart = findViewById(R.id.gotocart);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_view);
        String productId = getIntent().getStringExtra("productId");
        productName = findViewById(R.id.Proname);
        image = findViewById(R.id.ProImg);
        description = findViewById(R.id.prodes);
        attribute1 = findViewById(R.id.proattribute1);
        attribute2 = findViewById(R.id.proattribute2);
        attribute3 = findViewById(R.id.proattribute3);
        attribute4 = findViewById(R.id.proattribute4);
        attribute5 = findViewById(R.id.proattribute5);
        //price=findViewById(R.id.Proprice);
        //getProductFullView(productId);
        ProductFullApi(productId);
        addtocart = findViewById(R.id.PFVAddtocart);
        buynow = findViewById(R.id.buynow);
        gotocart=findViewById(R.id.gotocart);

        addtocart.setOnClickListener(view -> {
//            loginAPI(email.getText().toString(),pwd.getText().toString());
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
            email = sharedPreferences.getString("email", "Default");

            int quantity=1;
            AddToCart(email, productId, quantity, merchantId, merchantName);
            Log.d("Hellllllllllllooooo", "1SliderAPI");


        });
        gotocart.setOnClickListener(view -> {
            Intent i=new Intent(ProductFullView.this,CartActivity.class);
            startActivity(i);
        });


        buynow.setOnClickListener(view ->{

            // Logic of display Only that product into cart

        });
    }
    public void ProductFullApi(String productId) {

        Retrofit retrofit = BuilderProduct.getInstance();
        Call<ProductEntity> productCall = retrofit.create(ProductFullViewInterface.class).postLog(productId);
        Log.d("Hellllllllllllooooo", "1SliderAPI");
        productCall.enqueue(new Callback<ProductEntity>() {
            @Override
            public void onResponse(Call<ProductEntity> call, Response<ProductEntity> response) {

//                productEntity[0] = response.body();
                productName.setText(response.body().getProductName());
                List<String> spinnerList = new ArrayList<>();
                Spinner spinnerMerchantProduct = findViewById(R.id.merchant_list);
                List<MerchantEntity> merchantEntity = response.body().getMerchantList();

                for (MerchantEntity merchantEntityobj : merchantEntity) {

                    String spinnerDetails = "Merchant :- " + merchantEntityobj.getMerchantName() + "Price :- " + merchantEntityobj.getPrice();
                    spinnerList.add(spinnerDetails);
                }


                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(ProductFullView.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
                productName.setText(response.body().getProductName());
                description.setText(response.body().getDescription());
                attribute1.setText(response.body().getAttribute1());
                attribute2.setText(response.body().getAttribute2());
                attribute3.setText(response.body().getAttribute3());
                attribute4.setText(response.body().getAttribute4());
                attribute5.setText(response.body().getAttribute5());
                //price.setText(String.valueOf(productEntityList.getMerchantList().get(0).getPrice()));
                spinnerMerchantProduct.setAdapter(stringArrayAdapter);
                spinnerMerchantProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ProductFullView.this.number =  position;
                        Log.d("AAAAAAAAAAAAAAAAA",String.valueOf(merchantEntity.get(position).getMerchantId()));
                        //Integer in = number;
                        merchantId=merchantEntity.get(position).getMerchantId();
                        Toast.makeText(ProductFullView.this,String.valueOf(merchantEntity.get(position).getMerchantId()), Toast.LENGTH_SHORT).show();

                        merchantName=merchantEntity.get(position).getMerchantName();
                        TextView merchantprice=findViewById(R.id.proprice);
                        merchantprice.setText(merchantEntity.get(position).getPrice()+"");
                    }

                    public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                    }

                });

                Glide.with(image.getContext()).load(response.body().getImage()).placeholder(R.drawable.ic_baseline_person).into(image);
            }

            @Override
            public void onFailure(Call<ProductEntity> call, Throwable t) {
                Toast.makeText(ProductFullView.this, "Everything is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddToCart(String email, String productId , int quantity, String merchantId, String merchatName){

        AddToCartEntity addToCartEntity = new AddToCartEntity(email, productId, quantity, merchantId);

        Retrofit retrofit = BuilderCart.getInstance();
        CartInterface cartInterface = retrofit.create(CartInterface.class);
        Call<java.lang.Void> cartInterfaceCall = cartInterface.postLog(addToCartEntity);
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
        String p = getIntent().getStringExtra("categoryName");
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
                //price.setText(String.valueOf(productEntityList.getMerchantList().get(0).getPrice()));
                Glide.with(image.getContext()).load(productEntityList.getImage()).placeholder(R.drawable.ic_baseline_person).into(image);
                SharedPreferences sharedPreferences=getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
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