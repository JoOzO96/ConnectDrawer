package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;

import com.example.jose.connectdrawer.Icms.Icms;
import com.example.jose.connectdrawer.Icms.IcmsService;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeService;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SincIcms {
    public void iniciaSinc(final Context context, List<Icms> icmsList) {
        final Context context1;
        context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();

        GetSetDinamico getSetDinamico = new GetSetDinamico();
        Icms icms = new Icms();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(icms.getClass().getDeclaredFields()));
        for (int i = 0; fieldList.size() != i; i++) {
            if (fieldList.get(i).getName().equals("$change") || fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                fieldList.remove(i);
                i = 0;
            }
        }
        Cursor cursor = null;
        Object valorCampo = null;
        Object teste = null;
        String nomeCampo = null;
        for (int i = 0; icmsList.size() != i; i++) {
            if (icmsList.get(i).getCodicms() == null) {
            } else {
                cursor = icms.retornaIcmsFiltradaCursor(context1, icmsList.get(i).getCodicms());
                Sessao.colocaTexto("Cadastro de Cidade   " + (i + 1) + " de " + icmsList.size());
                if (cursor.getCount() > 0) {
                    cursor.close();
                } else {
                    icms = new Icms();
                    for (int f = 0; fieldList.size() != f; f++) {
                        valorCampo = getSetDinamico.retornaValorCampo(fieldList.get(f), icmsList.get(i));
                        teste = getSetDinamico.insereField(fieldList.get(f), icms, valorCampo);
                        icms = (Icms) teste;

                    }
                    cursor.moveToNext();

                    boolean retorno = icms.cadastraIcms(context, icms);

                    cursor.close();
                }
            }
        }
    }

    public boolean iniciaAsinc(Context context, String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        Icms icms    = new Icms();
        IcmsService icmsService = retrofit.create(IcmsService.class);
        final Call<List<Icms>> requestIcms = icmsService.listIcms();
        final Response<List<Icms>>[] response = new Response[]{null};
        List<Icms> listaIcms = null;
        Sessao.colocaTexto("Consultando dados do ICMS");
        final Date dataInicio = new Date();
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response[0] = requestIcms.execute();
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
                mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar do ICMS.");
            }
            if (response[0] != null) {
                listaIcms = new ArrayList<>(response[0].body());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listaIcms != null) {
            iniciaSinc(context, listaIcms);
            return true;
        } else {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "Erro ao consultar dados do ICMS.");
            return false;
        }
    }
}
