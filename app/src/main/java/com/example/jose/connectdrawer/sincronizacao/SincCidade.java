package com.example.jose.connectdrawer.sincronizacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeService;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;

import java.io.IOException;
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
 * Created by Jose on 25/05/2017.
 */

public class SincCidade {

    public void iniciaSinc(final Context context, List<Cidade> cidadeList) {
        final Context context1;
        context1 = context;

        //CLASSE QUE MANTEM OS DADOS DO RETROFIR
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
//        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
//
//        CidadeService cidadeService = retrofit.create(CidadeService.class);
//        Call<List<Cidade>> requestCidade = cidadeService.listCidade();
//        requestCidade.enqueue(new Callback<List<Cidade>>() {
//            @Override
//            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
//                List<Cidade> cidadeList = response.body();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        Cidade cidade = new Cidade();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(cidade.getClass().getDeclaredFields()));
        for (int i = 0; fieldList.size() != i; i++) {
            if (fieldList.get(i).getName().equals("$change") || fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                fieldList.remove(i);
                i = 0;
            }
        }
        ProgressDialog progressDialog = Sessao.getProgress();
//        progressDialog.setMessage("Cadastro de Cidade   0 de " + cidadeList.size());
        Cursor cursor = null;
        Object valorCampo = null;
        Object teste = null;
//        String tipo = null;
        String nomeCampo = null;
        for (int cid = 0; cidadeList.size() != cid; cid++) {
            //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR
            if (cidadeList.get(cid).getCodcidade() == null){

            }else {
                cursor = cidade.retornaCidadeFiltradaCursor(context1, cidadeList.get(cid).getCodcidade());
                Sessao.colocaTexto("Cadastro de Cidade   " + (cid + 1) + " de " + cidadeList.size());
//            progressDialog.setMessage("Cadastro de Cidade   " + (cid + 1) + " de " + cidadeList.size());
                if (cursor.getCount() > 0) {
                    cursor.close();
                } else {
                    //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR

                    cidade = new Cidade();

                    for (int f = 0; fieldList.size() != f; f++) {

//                    tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                        nomeCampo = fieldList.get(f).getName();

                        if (nomeCampo.equals("UF")) {
                            nomeCampo = "uf";
                        }
                        valorCampo = getSetDinamico.retornaValorCampo(fieldList.get(f), cidadeList.get(cid));
                        teste = getSetDinamico.insereField(fieldList.get(f), cidade, valorCampo);
                        cidade = (Cidade) teste;

                    }
                    teste = null;
                    valorCampo = null;
//                        aqui tenho a cidade completa;
//                        cidade1.setCep(cidadeList.get(cid).getCep());
//                        cidade1.setCodnacionalcidade(cidadeList.get(cid).getCodnacionalcidade());
//                        cidade1.setPais(cidadeList.get(cid).getCodnacionalpais());
//                        cidade1.setCodnacionaluf(cidadeList.get(cid).getCodnacionaluf());
//                        cidade1.setNomecidade(cidadeList.get(cid).getNomecidade());
//                        cidade1.setPais(cidadeList.get(cid).getPais());
//                        cidade1.setUf(cidadeList.get(cid).getUf());
//                        cidade1.setCodcidade(cidadeList.get(cid).getCodcidade());
                    cursor.moveToNext();
                    ///
                    //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                    //
                    //
                    //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                    //
                    boolean retorno = cidade.cadastraCidade(context, cidade);
//                    cidade = null;
                    cursor.close();
                }
            }
        }
//            }
//
//            @Override
//            public void onFailure(Call<List<Cidade>> call, Throwable t) {
//                Log.e("DEU ERRO Sinc", t.toString());
//            }
//        });
    }

    public boolean iniciaAsinc(Context context, String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        Cidade cidade = new Cidade();
        CidadeService cidadeService = retrofit.create(CidadeService.class);
        final Call<List<Cidade>> requestCidade = cidadeService.listCidade();
        final Response<List<Cidade>>[] response = new Response[]{null};
        List<Cidade> listaCidade = null;
//        List<Field> listaCampos = new ArrayList<>(Arrays.asList(cidade.getClass().getDeclaredFields()));
//        ProgressDialog progressDialog = Sessao.getProgress();
//        progressDialog.setMessage("Consultando dados das cidades");
        Sessao.colocaTexto("Consultando dados das cidades");
        final Date dataInicio = new Date();
//        try {
//            response[0] = requestCidade.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response[0] = requestCidade.execute();
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

        if (thread.isAlive()){
            thread.interrupt();
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar cidades.");
        }



//        while (response[0] == null) {
////                Log.e("NULL", "RESPOSTA NULL");
////            limitaResposta ++;
////                Log.e("OI", " " + (dataInicio.getTime() - System.currentTimeMillis()) + " ");
//            if ((dataInicio.getTime() - System.currentTimeMillis()) <= -300000) {
//                break;
//            }else{
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

//        if (thread.getState() == Thread.State.TERMINATED) {
        if (response[0] != null) {
            listaCidade = new ArrayList<>(response[0].body());
        }

//        for (int i = 0 ; listaCampos.size() != i ; i++){
//            if (listaCampos.get(i).getName().toUpperCase().equals(""));
//        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


//            thread.interrupt();
        if (listaCidade != null) {
            iniciaSinc(context, listaCidade);
            return true;
        } else {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "Erro ao consultar dados do cliente.");
            return false;
        }
    }


//        return true;
//    }

    public void iniciaEnvio(Context context, String ip) throws IOException {

        //
        //
        //
        //           PARTE DAS CIDADES NOVAS QUE FORAM INSERIDAS NO ANDROID
        //
        //
        //

        Cidade cidade = new Cidade();
        List<Cidade> cidadeList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<Field> fieldListCidade = new ArrayList<>(Arrays.asList(Cidade.class.getDeclaredFields()));
        Cursor cursor = cidade.retornaCidadeAlteradaAndroid(context, "cadastroAndroid");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (long i = 0L; cursor.getCount() != i; i++) {
                Cidade cidade1 = new Cidade();
                for (int cid = 0; fieldListCidade.size() != cid; cid++) {
                    if (fieldListCidade.get(cid).getName().toLowerCase().equals("$change") ||
                            fieldListCidade.get(cid).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCidade.get(cid));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListCidade.get(cid).getName(), cursor);
                        if (fieldListCidade.get(cid).getName().toLowerCase().equals("cep")) {
                            retornoCursor = String.valueOf(retornoCursor).replace("-", "");
                        }
                        Object cidadeRetorno = getSetDinamico.insereField(fieldListCidade.get(cid), cidade1, retornoCursor);
                        cidade1 = (Cidade) cidadeRetorno;
                    }
                }
                cidadeList.add(cidade1);

                cursor.moveToNext();
            }
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(cidadeList);
            EnviaJson enviaJson = new EnviaJson();
            RetRetrofit retRetrofit = new RetRetrofit();
            String url = retRetrofit.retornaSring("cidade", ip);
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
            if (retornoEnvio != null) {
                ControleCodigo conversao[] = gson.fromJson(retornoEnvio, ControleCodigo[].class);
                List<ControleCodigo> controleCodigoList = new ArrayList<>(Arrays.asList(conversao));
                Cliente cliente = new Cliente();
                for (int i = 0; controleCodigoList.size() != i; i++) {
                    cliente.alteraCidadeCliente(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                    cidade.alteraCodCidade(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                    cidade.removeCidadeAlteradaAndroid(context, "cadastroAndroid");
                }
            }

        }


        //
        //
        //
        //           PARTE DAS CIDADES QUE FORAM ATUALIZADAS NO ANDROID
        //
        //
        //


        cidade = new Cidade();
        cidadeList = new ArrayList<>();
        getSetDinamico = new GetSetDinamico();
        fieldListCidade = new ArrayList<>(Arrays.asList(Cidade.class.getDeclaredFields()));
        cursor = cidade.retornaCidadeAlteradaAndroid(context, "alteradoandroid");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (long i = 0L; cursor.getCount() != i; i++) {
                Cidade cidade1 = new Cidade();
                for (int cid = 0; fieldListCidade.size() != cid; cid++) {
                    if (fieldListCidade.get(cid).getName().toLowerCase().equals("$change") ||
                            fieldListCidade.get(cid).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCidade.get(cid));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListCidade.get(cid).getName(), cursor);
                        if (fieldListCidade.get(cid).getName().toLowerCase().equals("cep")) {
                            retornoCursor = String.valueOf(retornoCursor).replace("-", "");
                        }
                        Object cidadeRetorno = getSetDinamico.insereField(fieldListCidade.get(cid), cidade1, retornoCursor);
                        cidade1 = (Cidade) cidadeRetorno;
                    }
                }
                cidadeList.add(cidade1);

                cursor.moveToNext();
            }
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(cidadeList);
//            Log.i("JSON", gsonRetorno);
            EnviaJson enviaJson = new EnviaJson();
            String url = "http://177.92.186.84:15101/ConnectServices/recebeCidadeAtualizada";
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
//            if (retornoEnvio != null) {
//                ControleCodigo conversao[] = gson.fromJson(retornoEnvio, ControleCodigo[].class);
//                List<ControleCodigo> controleCodigoList = new ArrayList<>(Arrays.asList(conversao));
//                Cliente cliente = new Cliente();
//                for (int i = 0; controleCodigoList.size() != i; i++) {
//                    cliente.alteraCidadeCliente(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
//                    cidade.alteraCodCidade(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
//                    cidade.removeCidadeAlteradaAndroid(context, "alteradoandroid");
//                }
//            }

        }

    }


}
