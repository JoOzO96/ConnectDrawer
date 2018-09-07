package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.FormaPagamento.FormaPagamentoService;
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
 * Created by Jose on 23/06/2017.
 */

public class SincFormaPagamento {

    public void iniciaSinc(Context context, String ip) {
        final Context context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);

        FormaPagamentoService formaPagamentoService = retrofit.create(FormaPagamentoService.class);
        Call<List<FormaPagamento>> requestFormaPagamento = formaPagamentoService.listFormaPagamento();

        requestFormaPagamento.enqueue(new Callback<List<FormaPagamento>>() {
            @Override
            public void onResponse(Call<List<FormaPagamento>> call, Response<List<FormaPagamento>> response) {
                List<FormaPagamento> formaPagamentoList = response.body();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                FormaPagamento formaPagamento = new FormaPagamento();
                List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(FormaPagamento.class.getDeclaredFields()));
                for (int i = 0; formaPagamentoList.size() != i; i++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = formaPagamento.retornaFormaPagamentoFiltradaCursor(context1, formaPagamentoList.get(i).getCodigo());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR

                        FormaPagamento formaPagamento1 = new FormaPagamento();
                        for (int j = 0; fieldListClasse.size() != j; j++) {
                            if (fieldListClasse.get(j).getName().toLowerCase().equals("$change") ||
                                    fieldListClasse.get(j).getName().toLowerCase().equals("serialversionuid")) {
                            } else {
                                String tipo = getSetDinamico.retornaTipoCampo(fieldListClasse.get(j));
                                String nomecampo = "";
                                nomecampo = fieldListClasse.get(j).getName().toLowerCase();
                                Object valorCampo = getSetDinamico.retornaValorCampo(fieldListClasse.get(j), formaPagamentoList.get(i));
                                if (valorCampo != null) {
                                    Object teste;
                                    teste = getSetDinamico.insereField(fieldListClasse.get(j), formaPagamento1, valorCampo);
                                    formaPagamento1 = (FormaPagamento) teste;
                                }
                            }
                        }

                        ///
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //

                        boolean retorno = formaPagamento.cadastraFormaPagamentoSinc(context1, formaPagamento1);

                        cursor.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FormaPagamento>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });

    }


}
