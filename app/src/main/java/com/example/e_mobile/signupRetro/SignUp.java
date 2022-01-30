package com.example.e_mobile.signupRetro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_mobile.Dummy;
import com.example.e_mobile.R;
import com.example.e_mobile.RetrofitInterfaces.SignupInterface;

import com.example.e_mobile.builder.BuilderSignup;
import com.example.e_mobile.loginRetro.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {

    private EditText name, email, password, cpassword, address;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.SUPName);
        email = findViewById(R.id.SUPEmail);
        cpassword = findViewById(R.id.SUPConfirm);
        password = findViewById(R.id.SUPPassword);
        address = findViewById(R.id.SUPAddress);
        signup = findViewById(R.id.SUPbutton);

        signup.setOnClickListener(view -> {
            if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty() && name.getText().toString().isEmpty() &&
                    cpassword.getText().toString().isEmpty() && address.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password.getText().toString().equals(cpassword.getText().toString())){
                Toast.makeText(SignUp.this, "Enter same password", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("do",email.getText().toString());
            signupAPI(name.getText().toString(), email.getText().toString(), password.getText().toString(), address.getText().toString());
        });

    }

    public void signupAPI(String name, String email, String password, String address) {

        SharedPreferences sharedPreferences=getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("username",name);
        editor.putString("address",address);
        editor.apply();







        Retrofit retrofit = BuilderSignup.getInstance();
        SignupEntity signupEntity = new SignupEntity(name, email, password, address);
        SignupInterface signupInterface = retrofit.create(SignupInterface.class);
        Call<Respentity> signupEntityCall = signupInterface.postLog(signupEntity);
        signupEntityCall.enqueue(new Callback<Respentity>() {
            @Override
            public void onResponse(Call<Respentity> call, Response<Respentity> response) {
                if(response.body()==null){
                    Toast.makeText(SignUp.this, "User mail is already registered", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(SignUp.this, "Signin Successful", Toast.LENGTH_SHORT).show();
//                Toast.makeText(SignUp.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Dummy.class));
            }

            @Override
            public void onFailure(Call<Respentity> call, Throwable t) {
                Toast.makeText(SignUp.this, "User mail is already registered", Toast.LENGTH_SHORT).show();
//                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}