package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Account extends AppCompatActivity {

    TextView tv_name;
    TextView tv_email;
    TextView tv_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
//
//        SharedPreferences sharedPreferences=getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
//        String email=sharedPreferences.getString("email","Default");
//        String username=sharedPreferences.getString("email","Default");
//        String address=sharedPreferences.getString("email","Default");
//        tv_name=findViewById(R.id.Name);
//        tv_email=findViewById(R.id.Phone);
//        tv_address=findViewById(R.id.Address);
//
//        tv_name.setText(username);
//        tv_email.setText(email);
//        tv_address.setText(address);
    }
}