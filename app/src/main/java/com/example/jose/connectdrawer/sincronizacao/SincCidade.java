package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeService;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void iniciaSinc(final Context context) {
        final Context context1;
        context1 = context;

        //CLASSE QUE MANTEM OS DADOS DO RETROFIR
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit();

        CidadeService cidadeService = retrofit.create(CidadeService.class);
        Call<List<Cidade>> requestCidade = cidadeService.listCidade();
        requestCidade.enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                List<Cidade> cidadeList = response.body();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                Cidade cidade = new Cidade();
                for (int cid = 0; cidadeList.size() != cid; cid++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = cidade.retornaCidadeFiltradaCursor(context1, cidadeList.get(cid).getCodcidade());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR

                        Cidade cidade1 = new Cidade();
                        List<Field> fieldList = new ArrayList<>(Arrays.asList(cidade1.getClass().getDeclaredFields()));

                        for (int f = 0; fieldList.size() != f; f++) {

                            String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                            String nomeCampo = fieldList.get(f).getName();
                            if (nomeCampo.equals("$change") || nomeCampo.equals("serialVersionUID")) {

                            } else {
                                if (nomeCampo.equals("UF")) {
                                    nomeCampo = "uf";
                                }
                                Object valorCampo = getSetDinamico.retornaValorCampo(fieldList.get(f), cidadeList.get(cid));
                                Object teste = getSetDinamico.insereField(fieldList.get(f), cidade1, valorCampo);
                                cidade1 = (Cidade) teste;
                            }
                        }
                        cursor.moveToNext();
                        //aqui tenho a cidade completa;
//                        cidade1.setCep(cidadeList.get(cli).getCep());
//                        cidade1.setCodNacionalCidade(cidadeList.get(cli).getCodNacionalCidade());
//                        cidade1.setPais(cidadeList.get(cli).getCodNacionalPais());
//                        cidade1.setCodNacionalUf(cidadeList.get(cli).getCodNacionalUf());
//                        cidade1.setNomeCidade(cidadeList.get(cli).getNomeCidade());
//                        cidade1.setPais(cidadeList.get(cli).getPais());
//                        cidade1.setUF(cidadeList.get(cli).getUF());
//                        cidade1.setCodCidade(cidadeList.get(cli).getCodCidade());

                        ///
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //

                        boolean retorno = cidade.insereDados(context, cidade1);

                        cursor.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });
    }

    public void iniciaEnvio(Context context) throws IOException {
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
            String url = "http://192.168.0.106:8080/ConnectServices/recebeCidade";
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
    }


}
