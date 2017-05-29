package com.example.jose.connectdrawer.sincronizacao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jose on 26/05/2017.
 */

public class RetRetrofit {

    public Retrofit retornaRetrofit(){
        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8080/ConnectServices/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}