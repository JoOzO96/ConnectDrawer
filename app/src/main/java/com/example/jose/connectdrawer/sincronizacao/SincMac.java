package com.example.jose.connectdrawer.sincronizacao;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.util.Patterns;

import com.example.jose.connectdrawer.login.LoginService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SincMac {

    public String iniciasinc(Context context) {

        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit();
        LoginService loginService = retrofit.create(LoginService.class);
        final String[] ip = {""};
        //PEGA O MAC DO APARELHO

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String macAddress = info.getMacAddress();

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts();

        Gson gson = new Gson();
        if (accounts.length > 0) {
            macAddress += accounts[0].name;
        }
        final Call<String> requestCliente = loginService.retornaMac(macAddress);
//        Log.e("TEST", response.toString());
//        requestCliente.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                ip[0] = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("TEST", t.toString());
//            }
//        });
        final Response<String>[] response = new Response[]{null};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        response[0] = requestCliente.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            while (response[0] == null){
                Log.e("NULL", "RESPOSTA NULL");
            }
            ip[0] = response[0].body();
            thread.interrupt();
        return ip[0];
    }

}
