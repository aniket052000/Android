package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Button btYes=findViewById(R.id.btYes);
        Button btNo=findViewById(R.id.btNo);
        btYes.setOnClickListener(view -> {
            SharedPreferences sharedPreferences=getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            Intent i=new Intent(Logout.this,AfterSplash.class);
            startActivity(i);

        });

        btNo.setOnClickListener(view -> {
            Intent i=new Intent(Logout.this,Dummy.class);
            startActivity(i);
        });
    }
}