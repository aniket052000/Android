package com.example.e_mobile.builder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuilderOrder {

    private static Retrofit instance;

    public BuilderOrder() {
    }
    public static Retrofit getInstance(){
        if(instance==null){
            synchronized (BuilderCart.class){
                if(instance==null){
                    instance=new Retrofit.Builder().baseUrl("http://10.177.1.232:8086/").
                            addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }
}

