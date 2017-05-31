package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Call<List<Cidade>> requestCidade= cidadeService.listCidade();
        requestCidade.enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                List<Cidade> cidadeList = response.body();

                Cidade cidade = new Cidade();
                for (int cli = 0; cidadeList.size() != cli; cli++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = cidade.retornaCidadeFiltradaCursor(context1, cidadeList.get(cli).getCodCidade());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR

                        Cidade cidade1 = new Cidade();
                        cidade1.setCep(cidadeList.get(cli).getCep());
                        cidade1.setCodNacionalCidade(cidadeList.get(cli).getCodNacionalCidade());
                        cidade1.setPais(cidadeList.get(cli).getCodNacionalPais());
                        cidade1.setCodNacionalUf(cidadeList.get(cli).getCodNacionalUf());
                        cidade1.setNomeCidade(cidadeList.get(cli).getNomeCidade());
                        cidade1.setPais(cidadeList.get(cli).getPais());
                        cidade1.setUF(cidadeList.get(cli).getUF());
                        cidade1.setCodCidade(cidadeList.get(cli).getCodCidade());

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
        Cursor cursor = cidade.retornaCidadeAlteradaAndroid(context, "cadastroAndroid");
        if (cursor.getCount() > 0){
            for (long i = 0L ; cursor.getCount() != i; i++){
                Cidade cidade1 = new Cidade();
                cidade1.setCodCidade(cursor.getLong(cursor.getColumnIndex("codCidade")));
                cidade1.setNomeCidade(cursor.getString(cursor.getColumnIndex("nomeCidade")));
                cidade1.setUF(cursor.getString(cursor.getColumnIndex("uf")));
                cidade1.setCodNacionalUf(cursor.getString(cursor.getColumnIndex("codNacionalUf")));
                cidade1.setCodNacionalCidade(cursor.getString(cursor.getColumnIndex("codNacionalCidade")));
                cidade1.setPais(cursor.getString(cursor.getColumnIndex("pais")));
                cidade1.setCodNacionalPais(cursor.getString(cursor.getColumnIndex("codNacionalPais")));
                cidade1.setCep(cursor.getString(cursor.getColumnIndex("cep")).replace("-",""));
                cidadeList.add(cidade1);
                cursor.moveToNext();
            }
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(cidadeList);
            EnviaJson enviaJson = new EnviaJson();
            enviaJson.execute(gsonRetorno);
        }
    }
}
