package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_mobile.loginRetro.Login;
import com.example.e_mobile.signupRetro.SignUp;

public class AfterSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
        findViewById(R.id.gotosignuppage).setOnClickListener(view -> {
            startActivity(new Intent(this,SignUp.class));
        });
        findViewById(R.id.Gotologinpage).setOnClickListener(view -> {
            startActivity(new Intent(this,Login.class));
        });
        findViewById(R.id.gotohomepage).setOnClickListener(view -> {
              startActivity(new Intent(this,Dummy.class));
        });

    }
}