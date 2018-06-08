package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jose.connectdrawer.Produto.Produto;
import com.example.jose.connectdrawer.Produto.ProdutoService;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jose on 31/07/2017.
 */

public class SincProduto {

    public void iniciaSinc(final Context context, String ip) {


        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);


        ProdutoService produtoService = retrofit.create(ProdutoService.class);
        Call<List<Produto>> requestProduto = produtoService.listaProduto();
        requestProduto.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                List<Produto> produtoList = response.body();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                Produto produto = new Produto();
                produto.removeProdutos(context);
                for (int pro = 0; produtoList.size() != pro; pro++) {
                    Banco myDb = new Banco(context);
                    SQLiteDatabase db = myDb.getReadableDatabase();
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR
                    produto = new Produto();
                    Cursor cursor = produto.retornaProdutoFiltradaCursorSincro(db, produtoList.get(pro).getCodproduto());

                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS DADOS QUE VIERAM DO SERVIDOR
                        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));

                        produto = new Produto();
                        for (int i = 0; fieldListClasse.size() != i; i++) {
                            if (fieldListClasse.get(i).getName().toLowerCase().equals("$change") ||
                                    fieldListClasse.get(i).getName().toLowerCase().equals("serialversionuid")) {
                            } else {
                                Object valorCampo = getSetDinamico.retornaValorCampo(fieldListClasse.get(i), produtoList.get(pro));
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

            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });
    }
}
