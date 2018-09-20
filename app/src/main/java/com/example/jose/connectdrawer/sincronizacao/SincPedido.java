package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.Pedido.PedidoService;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jose on 21/06/2017.
 */

public class SincPedido {
    public void iniciaSinc(Context context, String ip) {
        final Context context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);

        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Pedido>> requestPedido = pedidoService.listPedido();
        final Banco myDb = new Banco(context);
        final SQLiteDatabase db = myDb.getReadableDatabase();
        requestPedido.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                List<Pedido> pedidoList = response.body();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                Pedido pedido = new Pedido();
                PedidoProduto pedidoProduto = new PedidoProduto();
                Long codPedido = 0L;
                for (int ped = 0; pedidoList.size() != ped; ped++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = pedido.retornaPedidoFiltradaCursor(context1, pedidoList.get(ped).getPedido());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR
                        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
                        Pedido pedido1 = new Pedido();
                        for (int i = 0; fieldListClasse.size() != i; i++) {
                            if (fieldListClasse.get(i).getName().toLowerCase().equals("$change") ||
                                    fieldListClasse.get(i).getName().toLowerCase().equals("serialversionuid")) {
                            } else {
                                String tipo = getSetDinamico.retornaTipoCampo(fieldListClasse.get(i));
                                String nomecampo = "";
                                nomecampo = fieldListClasse.get(i).getName().toLowerCase();
                                Object valorCampo = getSetDinamico.retornaValorCampo(fieldListClasse.get(i), pedidoList.get(ped));
                                if (valorCampo != null) {
                                    Object teste;
                                    teste = getSetDinamico.insereField(fieldListClasse.get(i), pedido1, valorCampo);
                                    pedido1 = (Pedido) teste;
                                }
                            }
                        }
                        //
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //
                        codPedido = pedido1.getPedido();
                        boolean retorno = pedido.cadastraPedido(context1, pedido1);

                        cursor.close();
                    }

                    //CADASTRA OS ITENS DO PEDIDO

                    cursor = pedidoProduto.retornaItensPedido(context1, codPedido);

                    if (cursor.getCount() > 0) {
                        if (cursor.getCount() != pedidoList.get(ped).getItensPedido().size()) {
                            cursor.close();
                        }
                    } else {
                        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));
                        pedidoProduto = new PedidoProduto();
                        for (int itensPed = 0; pedidoList.get(ped).getItensPedido().size() != itensPed; itensPed++) {
                            for (int i = 0; fieldListClasse.size() != i; i++) {
                                if (fieldListClasse.get(i).getName().toLowerCase().equals("$change") ||
                                        fieldListClasse.get(i).getName().toLowerCase().equals("serialversionuid")) {
                                } else {
                                    String tipo = getSetDinamico.retornaTipoCampo(fieldListClasse.get(i));
                                    String nomecampo = "";
                                    nomecampo = fieldListClasse.get(i).getName().toLowerCase();
                                    Object valorCampo = getSetDinamico.retornaValorCampo(fieldListClasse.get(i), pedidoList.get(ped).getItensPedido().get(itensPed));
                                    if (valorCampo != null) {
                                        Object teste;
                                        teste = getSetDinamico.insereField(fieldListClasse.get(i), pedidoProduto, valorCampo);
                                        pedidoProduto = (PedidoProduto) teste;
                                    }
                                }
                            }
                            boolean status = pedidoProduto.cadastraPedidoProduto(db, pedidoProduto);
                        }
                        cursor.close();
                    }

                }
                myDb.close();
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });
    }


    public void iniciaenvio(Context context, String ip) {
        Pedido pedido = new Pedido();
        List<Pedido> pedidoList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<Field> fieldListPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
        Cursor cursor = pedido.retornaPedidoAlteradaAndroid(context, "cadastroAndroid");
        Sessao.colocaTextoProgress("Verificando pedidos novos.");
        if (cursor.getCount() > 0) {
            for (long i = 0L; cursor.getCount() != i; i++) {
                pedido = new Pedido();
                for (int ped = 0; fieldListPedido.size() != ped; ped++) {
                    if (fieldListPedido.get(ped).getName().toLowerCase().equals("$change") ||
                            fieldListPedido.get(ped).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListPedido.get(ped));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListPedido.get(ped).getName(), cursor);
                        Object pedidoRetorno = getSetDinamico.insereField(fieldListPedido.get(ped), pedido, retornoCursor);
                        pedido = (Pedido) pedidoRetorno;
                    }
                }
                pedidoList.add(pedido);
                Sessao.colocaTextoProgress("Enviando os dados de pedidos." + (i+1) + " de " + cursor.getCount());
//                Log.e("PGTO", "" + pedido.getPgto());
                cursor.moveToNext();
            }
        }
        if (pedidoList.size() > 0) {
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(pedidoList);
            Log.i("JSONPEDIDO", gsonRetorno);
            EnviaJson enviaJson = new EnviaJson();
            RetRetrofit retRetrofit = new RetRetrofit();
            String url = retRetrofit.retornaSring("pedido", ip);
            List<ControleCodigo> retorno = null;
            String retornoEnvio = "";
            Date dataInicio = new Date();
            try {
                enviaJson.execute(gsonRetorno, url);
                while (retornoEnvio == "") {
                    retornoEnvio = enviaJson.get();
                    if ((dataInicio.getTime() - System.currentTimeMillis()) <= -30000 ){
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
                pedido = new Pedido();
                for (int i = 0; controleCodigoList.size() != i; i++) {
                    pedido.alteraCodPedido(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                    pedido.alteraCodPedidoProduto(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                    pedido.removePedidoAlteradaAndroid(context, "cadastroandroid");
                    SincPedidoProduto sincPedidoProduto = new SincPedidoProduto();
                    sincPedidoProduto.iniciaenvio(context, ip);
                }

            }
        }
    }
}
