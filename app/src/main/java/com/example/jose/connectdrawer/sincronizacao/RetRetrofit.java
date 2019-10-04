package com.example.jose.connectdrawer.sincronizacao;

import com.example.jose.connectdrawer.uteis.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jose on 26/05/2017.
 */

public class RetRetrofit {

    public Retrofit retornaRetrofit(String ip) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = null;
        if (ip.equals("") || ip == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://177.92.186.84:15101/api/")
//                    .baseUrl("http://192.168.0.199:8080/ConnectServices/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        } else {
//            if (ip.substring(0, 3).equals("192")) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + ip + "/api/")
//                    .baseUrl("http://" + ip + ":8080/ConnectServices/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
//            } else {
//                retrofit = new Retrofit.Builder()
//                        .baseUrl("http://" + ip + ":15101/ConnectServices/")
////                    .baseUrl("http://" + ip + ":8080/ConnectServices/")
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .build();
//            }
        }

        return retrofit;
    }

    public Retrofit retornaRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .create();
        Retrofit retrofit = null;
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://177.92.186.84:15101/api/")
//                .baseUrl("http://192.168.56.1:45455/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public String retornaSring(String tipo, String ip) {
        String url = "";
        if (ip.equals("") || ip == null) {
            if (tipo.equals("cidade")) {
                url = "http://" + ip + "/api/cidade";
//                url = "http://192.168.0.199:8080/ConnectServices/recebeCidade";
            } else if (tipo.equals("cliente")) {
                url = "http://" + ip + "/api/Cliente";
//                url = "http://192.168.0.199:8080/ConnectServices/recebeCliente";
            } else if (tipo.equals("parcelas")) {
                url = "http://" + ip + "/api/parcelas";
//                url = "http://192.168.0.199:8080/ConnectServices/recebePedido";
            } else if (tipo.equals("pedido")) {
                url = "http://" + ip + "/api/Pedido";
//                url = "http://192.168.0.199:8080/ConnectServices/recebePedido";
            } else if (tipo.equals("pedidoproduto")) {
                url = "http://" + ip + "/api/PedidoProduto";
//                url = "http://192.168.0.199:8080/ConnectServices/recebePedido";
            }else if (tipo.equals("clienteanimais")) {
                url = "http://" + ip + "/api/clienteanimais";
//                url = "http://192.168.0.199:8080/ConnectServices/recebePedido";
            }
        } else {
            if (tipo.equals("cidade")) {
                url = "http://" + ip + "/api/Cidade";
//                url = "http://" + ip + ":8080/ConnectServices/recebeCidade";
            } else if (tipo.equals("parcelas")) {
                url = "http://" + ip + "/api/parcelas";
//                url = "http://192.168.0.199:8080/ConnectServices/recebePedido";
            } else if (tipo.equals("cliente")) {
                url = "http://" + ip + "/api/Cliente";
//                url = "http://" + ip + ":8080/ConnectServices/recebeCliente";
            } else if (tipo.equals("pedido")) {
                url = "http://" + ip + "/api/Pedido";
//                url = "http://" + ip + ":8080/ConnectServices/recebePedido";
            } else if (tipo.equals("pedidoproduto")) {
                url = "http://" + ip + "/api/PedidoProduto";
//                url = "http://" + ip + ":8080/ConnectServices/recebePedido";
            }else if (tipo.equals("clienteanimais")) {
                url = "http://" + ip + "/api/clienteanimais";
//                url = "http://192.168.0.199:8080/ConnectServices/recebePedido";
            }
        }


        return url;
    }


}