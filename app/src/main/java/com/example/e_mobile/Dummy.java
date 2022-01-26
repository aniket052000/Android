package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dummy extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

//        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Home home = new Home();
        Cart cartobj = new Cart();
        Profile profileobj = new Profile();
        setCurrentFragment(home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            boolean userLoggedIn=false;
            switch (id) {
                case R.id.cart:
                    if(userLoggedIn)
                    {
                        setCurrentFragment(cartobj);
                    }
                    else {
                        Intent i = new Intent(Dummy.this, SignupOrLogin.class);
                        startActivity(i);

                    }

                    break;

                case R.id.person:
                    if(userLoggedIn) {
                        setCurrentFragment(profileobj);
                    }
                    else{
                        Intent i = new Intent(Dummy.this, SignupOrLogin.class);
                        startActivity(i);
                    }
                    break;

                default:
                    setCurrentFragment(home);
                    break;
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;

    }
    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}