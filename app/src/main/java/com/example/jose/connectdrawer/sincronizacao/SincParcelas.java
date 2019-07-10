package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.Parcelas.Parcelas;
import com.example.jose.connectdrawer.uteis.DateDeserializer;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SincParcelas {
    public void iniciaenvio(Context context, String ip) {
        Parcelas parcelas = new Parcelas();
        List<Parcelas> parcelasList = new ArrayList<>();
        List<Parcelas> parcelasListFinal = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<Field> fieldListPedido = new ArrayList<>(Arrays.asList(Parcelas.class.getDeclaredFields()));
        Cursor cursor = parcelas.retornaParcelaAlteradaAndroid(context, "cadastroandroid");
        Sessao.colocaTexto("Verificando parcelas novos.");
        if (cursor.getCount() > 0) {
            for (long i = 0L; cursor.getCount() != i; i++) {
                parcelasList.clear();
                parcelasList = parcelas.retornaListaDeParcelas(context, Long.parseLong(cursor.getString(cursor.getColumnIndex("codpedido"))));

                parcelasListFinal.addAll(parcelasList);


                Sessao.colocaTexto("Enviando os dados das parcelas." + (i + 1) + " de " + cursor.getCount());
//                Log.e("PGTO", "" + pedido.getPgto());
                cursor.moveToNext();
            }
        }
        if (parcelasListFinal.size() > 0) {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Gson gson = gsonBuilder.create();
            String gsonRetorno = gson.toJson(parcelasListFinal);
            Log.i("JSONPARCELAS", gsonRetorno);
            EnviaJson enviaJson = new EnviaJson();
            RetRetrofit retRetrofit = new RetRetrofit();
            String url = retRetrofit.retornaSring("parcelas", ip);
            List<ControleCodigo> retorno = null;
            String retornoEnvio = "";
            Date dataInicio = new Date();
            try {
                enviaJson.execute(gsonRetorno, url);
                while (retornoEnvio == "") {
                    retornoEnvio = enviaJson.get();
                    if ((dataInicio.getTime() - System.currentTimeMillis()) <= -30000 || retornoEnvio != "") {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (retornoEnvio != null && retornoEnvio != "") {

                ControleCodigo conversao[] = gson.fromJson(retornoEnvio, ControleCodigo[].class);
                List<ControleCodigo> controleCodigoList = new ArrayList<>(Arrays.asList(conversao));
                parcelas = new Parcelas();
                for (int i = 0; controleCodigoList.size() != i; i++) {
                    parcelas.removeParcelaAlteradaAndroid(context, "cadastroandroid");
//                    pedido.alteraCodPedido(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
//                    pedido.alteraCodPedidoProduto(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
//                    pedido.alteraParcelas(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
//                    pedido.removePedidoAlteradaAndroid(context, "cadastroandroid");
//                    SincPedidoProduto sincPedidoProduto = new SincPedidoProduto();
//                    sincPedidoProduto.iniciaenvio(context, ip);
                }

            }
        }
    }
}
