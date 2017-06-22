package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.Pedido.PedidoService;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeDados;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteService;
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
 * Created by Jose on 21/06/2017.
 */

public class SincPedido {
    public void iniciaSinc(Context context){
        final Context context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit();

        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Pedido>> requestPedido = pedidoService.listPedido();

        requestPedido.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                List<Pedido> pedidoList= response.body();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                Pedido pedido = new Pedido();
                for (int ped = 0; pedidoList.size() != ped; ped++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = pedido.retornaPedidoFiltradaCursor(context1, pedidoList.get(ped).getPedido());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR
                        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
                        Pedido pedido1 = new Pedido();
                        for (int i=0;fieldListClasse.size()!=i;i++){
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
//                        cidade1.setCep(cidadeList.get(cli).getCep());
//                        cidade1.setCodnacionalcidade(cidadeList.get(cli).getCodnacionalcidade());
//                        cidade1.setPais(cidadeList.get(cli).getCodnacionalpais());
//                        cidade1.setCodnacionaluf(cidadeList.get(cli).getCodnacionaluf());
//                        cidade1.setNomecidade(cidadeList.get(cli).getNomecidade());
//                        cidade1.setPais(cidadeList.get(cli).getPais());
//                        cidade1.setUf(cidadeList.get(cli).getUf());
//                        cidade1.setCodcidade(cidadeList.get(cli).getCodcidade());

                        ///
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //

                        boolean retorno = pedido.cadastraPedido(context1, pedido1);

                        cursor.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });
    }

}
