package com.example.jose.connectdrawer.ConsultaSefaz;

import android.content.Context;

import com.example.jose.connectdrawer.sincronizacao.EnviaJson;
import com.example.jose.connectdrawer.sincronizacao.RetRetrofit;
import com.example.jose.connectdrawer.sincronizacao.SincMac;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConsultaSefaz {
    String ip;

    public List<InfCadastro> buscaDados(Context context, String cnpj){

        RetRetrofit retRetrofit = new RetRetrofit();

        EnviaJson enviaJson = new EnviaJson();
        InfCadastro infCadastro = new InfCadastro();
        SincMac sincMac = new SincMac();
        ip = sincMac.iniciasinc(context);
        final Response<List<InfCadastro>>[] response = new Response[]{null};

        if (ip != null) {
            Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
            if (cnpj.length() == 14 || cnpj.length() == 11) {

                    InfCadastroService infCadastroService = retrofit.create(InfCadastroService.class);
                    final Call<List<InfCadastro>> requestInfCadastro = infCadastroService.listInfCadastro(cnpj);

                    Thread thread = new Thread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        response[0] = requestInfCadastro.execute();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    );
                    thread.setPriority(Thread.MAX_PRIORITY);
                    thread.start();

                    List<InfCadastro> listaInfCadastro = null;
                    try {
                        thread.join(120000);

                        if (thread.isAlive()) {
                            thread.interrupt();
                            MostraToast mostraToast = new MostraToast();
                            mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar vendedor.");
                        }

                        if (response[0].body() != null) {
                            Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

                            listaInfCadastro = new ArrayList<>(response[0].body());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                return listaInfCadastro;
            } else {
                return null;
            }
        }else{
            return null;
        }
    }


}
