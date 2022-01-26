package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.e_mobile.product_model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

//        List<ProductModel> productModels=new ArrayList<>();
//        generateUserData(productModels);


        ImageView img=findViewById(R.id.ProImg);


        TextView name=findViewById(R.id.Proname);
        name.setText("lays");
        TextView des=findViewById(R.id.prodes);
        des.setText("Lay's Classic Potato Chips, 1 Ounce (Pack of 104)");
        TextView a1=findViewById(R.id.proattribute1);
        a1.setText("tasty");
        TextView a2=findViewById(R.id.proattribute2);
        a2.setText("tasty");
        TextView a3=findViewById(R.id.proattribute3);
        a3.setText("tasty");
        TextView a4=findViewById(R.id.proattribute4);
        a4.setText("tasty");
        TextView a5=findViewById(R.id.proattribute5);
        a5.setText("tasty");



    }

//    private void generateUserData(List<ProductModel> productModelList){
//        productModelList.add(new ProductModel("https://m.media-amazon.com/images/I/81vJyb43URL._SL1500_.jpg","lays",20,"Lay's Classic Potato Chips, 1 Ounce (Pack of 104)","tasty","tasty","tasty","tasty","tasty"));
//    }
}