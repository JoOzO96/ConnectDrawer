package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Retrofit;

/**
 * Created by Jose on 11/07/2017.
 */

public class SincPedidoProduto {

    public void iniciaSinc(Context context, String ip) {
        final Context context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
    }


    public void iniciaenvio(Context context, String ip, Long pedido) {
        try {
            PedidoProduto pedidoProduto = new PedidoProduto();
            List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            List<Field> fieldListPedido = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));
//            pedidoProduto.colocaPedidoProdutoAlteradaAndroid(context, "cadastroandroid");
            pedidoProdutoList = pedidoProduto.retornaPedidoProdutoObjeto(context,pedido);

            if (pedidoProdutoList.size() > 0) {
                Gson gson = new Gson();
                String gsonRetorno = gson.toJson(pedidoProdutoList);
                Log.i("JSONPEDIDOPRODUTO", gsonRetorno);
                EnviaJson enviaJson = new EnviaJson();
//            String url = "http://177.92.186.84:15101/ConnectServices/recebePedidoProduto";
                RetRetrofit retRetrofit = new RetRetrofit();
                String url = retRetrofit.retornaSring("pedidoproduto", ip);
                List<ControleCodigo> retorno = null;
                String retornoEnvio = "";
                try {
                    enviaJson.execute(gsonRetorno, url);
                    retornoEnvio = enviaJson.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (retornoEnvio != null && retornoEnvio != "") {

                    ControleCodigo conversao[] = gson.fromJson(retornoEnvio, ControleCodigo[].class);
                    if (conversao != null) {
                        List<ControleCodigo> controleCodigoList = new ArrayList<>(Arrays.asList(conversao));

                        pedidoProduto = new PedidoProduto();
                        for (int i = 0; controleCodigoList.size() != i; i++) {

                        }
                        pedidoProduto.removePedidoProdutoAlteradaAndroid(context, "cadastroandroid");
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
