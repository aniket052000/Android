package com.example.e_mobile.loginRetro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_mobile.Dummy;
import com.example.e_mobile.R;
import com.example.e_mobile.RetrofitInterfaces.LoginInterface;
import com.example.e_mobile.builder.BuilderSignup;
import com.example.e_mobile.signupRetro.Respentity;
import com.example.e_mobile.signupRetro.SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    private EditText email,pwd;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.SINEmail);
        pwd=findViewById(R.id.SINPassword);
        signin=findViewById(R.id.SINbutton);

        signin.setOnClickListener(view -> {
            if(email.getText().toString().isEmpty() && pwd.getText().toString().isEmpty()){
                Toast.makeText(Login.this,"Please enter valid details",Toast.LENGTH_SHORT).show();
                return;
            }
            loginAPI(email.getText().toString(),pwd.getText().toString());
        });
        findViewById(R.id.SINSignup).setOnClickListener(view -> {
            startActivity(new Intent(this, SignUp.class));
        });

    }

    public void loginAPI(String email,String pwd){
        SharedPreferences sharedPreferences=getSharedPreferences("com.example.inkedpages", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);

        Retrofit retrofit= BuilderSignup.getInstance();

        LoginEntity loginEntity=new LoginEntity(email,pwd);

        LoginInterface loginInterface = retrofit.create(LoginInterface.class);

        Call<Respentity> loginEntityCall= loginInterface.postLog(loginEntity);
        loginEntityCall.enqueue(new Callback<Respentity>() {
            @Override
            public void onResponse(Call<Respentity> call, Response<Respentity> response) {
                if(response.body()==null){
                    Toast.makeText(Login.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Dummy.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Respentity> call, Throwable t) {
                Toast.makeText(Login.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        });

    }
}