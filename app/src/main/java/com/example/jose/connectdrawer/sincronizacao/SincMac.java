package com.example.jose.connectdrawer.sincronizacao;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.util.Patterns;

import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.login.LoginService;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SincMac {
    public String iniciasinc(Context context) {
        Date dataInicio = new Date();
        Long limitaResposta;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit();
        LoginService loginService = retrofit.create(LoginService.class);
        String ip = "";
        Mac mac = new Mac();
        //PEGA O MAC DO APARELHO

//        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = manager.getConnectionInfo();
//        String macAddress = info.getMacAddress();

        Gson gson = new Gson();
        final Call<String> requestMac = loginService.retornaMac(mac.retornaMac(context));
        final Response<String>[] response = new Response[]{null};
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
//        final Response<String>[] response = new Response[]{null};
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        response[0] = requestCliente.execute();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            thread.setPriority(Thread.MAX_PRIORITY);
//            thread.start();
//        try {
//            thread.join(120000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        limitaResposta = 0L;
//            while (response[0] == null){
////                Log.e("NULL", "RESPOSTA NULL");
//                limitaResposta ++;
////                Log.e("OI", " " + (dataInicio.getTime() - System.currentTimeMillis()) + " ");
//                if ((dataInicio.getTime() - System.currentTimeMillis()) <= -30000 ){
//                    return null;
//                }
//
//            }
//            ip[0] = response[0].body();
//            thread.interrupt();



        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response[0] = requestMac.execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        try {
            thread.join(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (thread.isAlive()){
            thread.interrupt();
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar forma de pagamento.");
        }
        if (response[0] != null){
            ip = response[0].body();
        }


        return ip;
    }

}
