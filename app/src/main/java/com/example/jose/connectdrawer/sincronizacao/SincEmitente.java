package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.example.jose.connectdrawer.Emitente.Emitente;
import com.example.jose.connectdrawer.Emitente.EmitenteService;
import com.example.jose.connectdrawer.uteis.DateDeserializer;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SincEmitente {
    public boolean iniciaAsinc(final Context context, final String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        Emitente emitente = new Emitente();
        final Mac mac = new Mac();

        EmitenteService emitenteService = retrofit.create(EmitenteService.class);
        final Call<Emitente> requestEmitente = emitenteService.emitente("1");
        final Response<Emitente>[] response = new Response[]{null};
        List<Field> listaCampos = new ArrayList<>(Arrays.asList(emitente.getClass().getDeclaredFields()));
        Sessao.colocaTexto("Consultando dados. (Emitente)");
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();
        Date dataInicio = new Date();
        final String[] conteudo = {null};

        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response[0] = requestEmitente.execute();
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

            if (thread.isAlive()) {
                thread.interrupt();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastLong(Sessao.retornaContext(),
                        "Erro ao consultar emitente.");
            }

            if (response[0].body() != null) {
                emitente =  response[0].body();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (emitente != null) {
            iniciaSinc(context, emitente);
            return true;
        } else {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(Sessao.retornaContext(),
                    "Erro ao consultar dados do emitente.");

            return false;
        }
    }

    public void iniciaSinc(final Context context, final Emitente emitente) {
        Handler handler = Sessao.getHandler();
        final Context context1;
        RetRetrofit retRetrofit = new RetRetrofit();

        final GetSetDinamico getSetDinamico = new GetSetDinamico();

        final List<Field> fieldList = new ArrayList<>(Arrays.asList(emitente.getClass().getDeclaredFields()));
        for (int i = fieldList.size() - 1; 0 != i; i--) {
            if (fieldList.get(i).getName().toLowerCase().equals("$change") ||
                    fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                fieldList.remove(i);
            }
        }

//        Cursor cursor = emitente.retornaEmitenteFiltradoCursor(context, emitente.getCodigoemitente());

        boolean status = emitente.cadastraEmitente(context, emitente);

    }


}
