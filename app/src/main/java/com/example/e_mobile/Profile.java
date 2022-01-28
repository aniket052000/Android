package com.example.e_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.e_mobile.signupRetro.SignUp;


public class Profile extends Fragment {

    public Profile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        v.findViewById(R.id.PAccount).setOnClickListener(view -> {
            startActivity(new Intent(getContext(), Account.class));
        });


        v.findViewById(R.id.POrderHistory).setOnClickListener(view -> {
            startActivity(new Intent(getContext(), OrderHistory.class));
        });


        v.findViewById(R.id.PLogOut).setOnClickListener(view -> {
            startActivity(new Intent(getContext(), Logout.class));
        });





        return v;
    }
}