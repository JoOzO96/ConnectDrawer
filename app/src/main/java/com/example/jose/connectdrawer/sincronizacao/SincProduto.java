package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jose.connectdrawer.Produto.Produto;
import com.example.jose.connectdrawer.Produto.ProdutoService;
import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.Vendedor.VendedorService;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
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
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jose on 31/07/2017.
 */

public class SincProduto {

    public void iniciaSinc(List<Produto> listaProduto, Context context) {


//        RetRetrofit retRetrofit = new RetRetrofit();
//        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
//        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
//
//
//        ProdutoService produtoService = retrofit.create(ProdutoService.class);
//        Call<List<Produto>> requestProduto = produtoService.listaProduto();
//        requestProduto.enqueue(new Callback<List<Produto>>() {
//            @Override
//            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
//                List<Produto> produtoList = response.body();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        Produto produto = new Produto();
        produto.removeProdutos(Sessao.retornaContext());
        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));
        for (int pro = 0; listaProduto.size() != pro; pro++) {
            Banco myDb = new Banco(Sessao.retornaContext());
            SQLiteDatabase db = myDb.getReadableDatabase();
            //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR
            produto = new Produto();
            Cursor cursor = produto.retornaProdutoFiltradaCursorSincro(db, listaProduto.get(pro).getCodproduto());
            Sessao.colocaTexto("Cadastro de produtos.   " + (pro + 1) + " de " + listaProduto.size());
            if (cursor.getCount() > 0) {
                cursor.close();
            } else {
                //PEGA OS DADOS QUE VIERAM DO SERVIDOR


                produto = new Produto();
                for (int i = 0; fieldListClasse.size() != i; i++) {
                    if (fieldListClasse.get(i).getName().toLowerCase().equals("$change") ||
                            fieldListClasse.get(i).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        Object valorCampo = getSetDinamico.retornaValorCampo(fieldListClasse.get(i), listaProduto.get(pro));
                        if (valorCampo != null) {
                            Object produtoRetorno;
                            produtoRetorno = getSetDinamico.insereField(fieldListClasse.get(i), produto, valorCampo);
                            produto = (Produto) produtoRetorno;
                        }
                    }
                }
                boolean retorno = produto.cadastraProdutoSincro(db, produto);

                cursor.close();

            }
            db.close();
        }

//            }
//
//            @Override
//            public void onFailure(Call<List<Produto>> call, Throwable t) {
//                Log.e("DEU ERRO Sinc", t.toString());
//            }
//        });
    }


    public void iniciaASinc(Context context, String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        Date dataInicio = new Date();
        Long controlaWhile = 0L;
        Sessao.colocaTexto("Consultando dados dos produtos.");

        ProdutoService produtoService = retrofit.create(ProdutoService.class);
        final Call<List<Produto>> requestProduto = produtoService.listaProduto();
        final Response<List<Produto>>[] response = new Response[]{null};
        final String[] teste = {""};
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {

                             response[0] = requestProduto.execute();
                             while (response[0].isSuccessful()){
                                 break;
                             }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        thread.setName("ConsultaProduto");
        List<Produto> listaProduto = null;
        try {

            thread.join();

            if (thread.isAlive()) {
                thread.interrupt();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar dados do produto.");
            }

            if (response[0].isSuccessful()) {
                listaProduto = new ArrayList<>(response[0].body());
            }else {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        thread.interrupt();
        if (listaProduto != null) {
            iniciaSinc(listaProduto, context);
        } else {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "Erro ao consultar dados do vendedor.");
        }
    }
}
