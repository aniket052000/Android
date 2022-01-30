package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.e_mobile.loginRetro.Login;
import com.example.e_mobile.signupRetro.SignUp;

public class SignupOrLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_or_login);
        Button loginpage=findViewById(R.id.Gotologinpage1);
        Button signuppage=findViewById(R.id.gotosignuppage1);

        loginpage.setOnClickListener(view -> {
            Intent i=new Intent(SignupOrLogin.this, Login.class);
            startActivity(i);
        });
        signuppage.setOnClickListener(view -> {
            Intent i=new Intent(SignupOrLogin.this, SignUp.class);
            startActivity(i);
        });

    }
}