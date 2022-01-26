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


        Button login=findViewById(R.id.Gotologinpage);
        Button signup=findViewById(R.id.gotosignuppage);
        Button skip=findViewById(R.id.gotohomepage);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AfterSplash.this, Login.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AfterSplash.this, SignUp.class);
                startActivity(i);
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AfterSplash.this,Dummy.class);
                startActivity(i);
            }
        });

    }
}