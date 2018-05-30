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
//                .baseUrl("http://177.92.186.84:15101/ConnectServices/")
                .baseUrl("http://192.168.0.199:8080/ConnectServices/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    public String retornaSring(String tipo){
        String url = "";
        if (tipo.equals("cidade")){
//          url = "http://177.92.186.84:15101/ConnectServices/recebeCidade";
            url = "http://192.168.0.199:8080/ConnectServices/recebeCidade";
        }else if (tipo.equals("cliente")){
//            url = "http://177.92.186.84:15101/ConnectServices/recebeCliente";
            url = "http://192.168.0.199:8080/ConnectServices/recebeCliente";
        }
        else if (tipo.equals("pedido")){
//          url = "http://177.92.186.84:15101/ConnectServices/recebePedido";
            url = "http://092.168.0.199:8080/ConnectServices/recebePedido";
        }

        return url;
    }


}