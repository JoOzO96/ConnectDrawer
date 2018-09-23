package com.example.jose.connectdrawer.sincronizacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.FormaPagamento.FormaPagamentoService;
import com.example.jose.connectdrawer.cliente.Cliente;
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
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jose on 23/06/2017.
 */

public class SincFormaPagamento {

    public void iniciaSinc(List<FormaPagamento> formaPagamentoList) {
//        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
//        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
//
//        FormaPagamentoService formaPagamentoService = retrofit.create(FormaPagamentoService.class);
//        Call<List<FormaPagamento>> requestFormaPagamento = formaPagamentoService.listFormaPagamento();

//        requestFormaPagamento.enqueue(new Callback<List<FormaPagamento>>() {
//            @Override
//            public void onResponse(Call<List<FormaPagamento>> call, Response<List<FormaPagamento>> response) {
//        List<FormaPagamento> formaPagamentoList = response.body();
        Sessao.colocaTexto("Consultando dados da Forma de Pagamento");
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        FormaPagamento formaPagamento = new FormaPagamento();
        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(FormaPagamento.class.getDeclaredFields()));
        for (int i = 0; formaPagamentoList.size() != i; i++) {
            //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

            Cursor cursor = formaPagamento.retornaFormaPagamentoFiltradaCursor(Sessao.retornaContext(), formaPagamentoList.get(i).getCodigo());
//            progressDialog.setMessage("Cadastro de Forma de Pagamento " + (i + 1) + " de " + formaPagamentoList.size());
            Sessao.colocaTexto("Cadastrando Forma de Pagamento. " + (i + 1) + " de " + formaPagamentoList.size());
            if (cursor.getCount() > 0) {
                cursor.close();
            } else {
                //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR

                formaPagamento = new FormaPagamento();
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
                            teste = getSetDinamico.insereField(fieldListClasse.get(j), formaPagamento, valorCampo);
                            formaPagamento = (FormaPagamento) teste;
                        }
                    }
                }

                ///
                //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                //
                //
                //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                //

                boolean retorno = formaPagamento.cadastraFormaPagamentoSinc(Sessao.retornaContext(), formaPagamento);

                cursor.close();
            }
        }
    }

//            @Override
//            public void onFailure(Call<List<FormaPagamento>> call, Throwable t) {
//                Log.e("DEU ERRO Sinc", t.toString());
//            }
//        });


    public void iniciaASinc(Context context, String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
//        SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        Date dataInicio = new Date();
        FormaPagamentoService formaPagamentoService = retrofit.create(FormaPagamentoService.class);
        final Call<List<FormaPagamento>> requestFormaPagamento = formaPagamentoService.listFormaPagamento();

        final Response<List<FormaPagamento>>[] response = new Response[]{null};

        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response[0] = requestFormaPagamento.execute();
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

        List<FormaPagamento> listaFormaPagamento = null;
        if (response[0].body() != null) {
            listaFormaPagamento = new ArrayList<>(response[0].body());
        }


        thread.interrupt();
        if (listaFormaPagamento != null){
            iniciaSinc(listaFormaPagamento);
        }else{
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "Erro ao consultar dados da forma de pagamento.");
        }

    }

}
