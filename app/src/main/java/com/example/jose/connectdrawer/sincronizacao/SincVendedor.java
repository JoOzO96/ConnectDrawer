package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.Vendedor.VendedorService;
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

public class SincVendedor {

    public void iniciaSinc(Context context, String ip) {
        final Context context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);

        VendedorService vendedorService = retrofit.create(VendedorService.class);
        Call<List<Vendedor>> requestVendedor = vendedorService.listVendedor();

        requestVendedor.enqueue(new Callback<List<Vendedor>>() {
            @Override
            public void onResponse(Call<List<Vendedor>> call, Response<List<Vendedor>> response) {
                List<Vendedor> vendedorList = response.body();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                Vendedor vendedor = new Vendedor();
                List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(Vendedor.class.getDeclaredFields()));
                for (int i = 0; vendedorList.size() != i; i++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = vendedor.retornaVendedorFiltradaCursor(context1, vendedorList.get(i).getCodvendedor());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR

                        Vendedor vendedor1 = new Vendedor();
                        for (int j = 0; fieldListClasse.size() != j; j++) {
                            if (fieldListClasse.get(j).getName().toLowerCase().equals("$change") ||
                                    fieldListClasse.get(j).getName().toLowerCase().equals("serialversionuid")) {
                            } else {
                                String tipo = getSetDinamico.retornaTipoCampo(fieldListClasse.get(j));
                                String nomecampo = "";
                                nomecampo = fieldListClasse.get(j).getName().toLowerCase();
                                Object valorCampo = getSetDinamico.retornaValorCampo(fieldListClasse.get(j), vendedorList.get(i));
                                if (valorCampo != null) {
                                    Object teste;
                                    teste = getSetDinamico.insereField(fieldListClasse.get(j), vendedor1, valorCampo);
                                    vendedor1 = (Vendedor) teste;
                                }
                            }
                        }

                        ///
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //

                        boolean retorno = vendedor.cadastraVendedorSinc(context1, vendedor1);

                        cursor.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Vendedor>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });

    }


}
