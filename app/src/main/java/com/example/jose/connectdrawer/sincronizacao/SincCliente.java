package com.example.jose.connectdrawer.sincronizacao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteService;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jose on 22/05/2017.
 */

public class SincCliente extends Activity {


    public void iniciaSinc(Context context) {
        final Context context1;
        context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit();

        ClienteService clienteService = retrofit.create(ClienteService.class);
        Call<List<Cliente>> requestCliente = clienteService.listCliente();
        requestCliente.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                List<Cliente> loginList = response.body();

                Cliente cliInsere = new Cliente();
                for (int cli = 0; loginList.size() != cli + 1; cli++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = cliInsere.retornaClienteFiltradoCursor(context1, loginList.get(cli).getCodigo());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR
                        Cliente cliente = new Cliente();
                        cliente.setCodigo(loginList.get(cli).getCodigo());
                        cliente.setNomeCliente(loginList.get(cli).getNomeCliente());
                        cliente.setCpf(loginList.get(cli).getCpf());
                        cliente.setDataNasc(loginList.get(cli).getDataNasc());
                        cliente.setEndereco(loginList.get(cli).getEndereco());
                        cliente.setPosicao(loginList.get(cli).getPosicao());
                        cliente.setPai(loginList.get(cli).getPai());
                        cliente.setMae(loginList.get(cli).getMae());
                        cliente.setBairro(loginList.get(cli).getBairro());
                        cliente.setCep(loginList.get(cli).getCep());
                        cliente.setIdentidade(loginList.get(cli).getIdentidade());
                        cliente.setTrabalho(loginList.get(cli).getTrabalho());
                        cliente.setTelefone(loginList.get(cli).getTelefone());
                        cliente.setFonetrab(loginList.get(cli).getFonetrab());
                        cliente.setCgc(loginList.get(cli).getCgc());
                        cliente.setIncest(loginList.get(cli).getIncest());
                        cliente.setEnderecotrab(loginList.get(cli).getEnderecotrab());
                        cliente.setCodProfissao(loginList.get(cli).getCodProfissao());
                        cliente.setCodCidade(loginList.get(cli).getCodCidade());
                        cliente.setResponsavel(loginList.get(cli).getResponsavel());
                        cliente.setFone(loginList.get(cli).getFone());
                        cliente.setObs(loginList.get(cli).getObs());
                        cliente.setNume(loginList.get(cli).getNume());
                        cliente.setEmail(loginList.get(cli).getEmail());
                        cliente.setPessoaAuto(loginList.get(cli).getPessoaAuto());
                        cliente.setLimiteCredito(loginList.get(cli).getLimiteCredito());
                        cliente.setPessoaAuto1(loginList.get(cli).getPessoaAuto1());
                        cliente.setLimiteCredito1(loginList.get(cli).getLimiteCredito1());
                        cliente.setPessoaAuto2(loginList.get(cli).getPessoaAuto2());
                        cliente.setLimiteCredito2(loginList.get(cli).getLimiteCredito2());
                        cliente.setLimitePessoal(loginList.get(cli).getLimitePessoal());
                        cliente.setTipoCliente(loginList.get(cli).getTipoCliente());
                        cliente.setCodVendedor(loginList.get(cli).getCodVendedor());
                        cliente.setSimples(loginList.get(cli).getSimples());
                        cliente.setCelular(loginList.get(cli).getCelular());
                        cliente.setFisJu(loginList.get(cli).getFisJu());
                        cliente.setConjuge(loginList.get(cli).getConjuge());
                        cliente.setFreteCli(loginList.get(cli).getFreteCli());
                        cliente.setAntecipacao(loginList.get(cli).getAntecipacao());
                        cliente.setEtiquetas(loginList.get(cli).getEtiquetas());
                        cliente.setSistema(loginList.get(cli).getSistema());
                        cliente.setVmanu(loginList.get(cli).getVmanu());
                        cliente.setRecibo(loginList.get(cli).getRecibo());
                        cliente.setCodigoPgto(loginList.get(cli).getCodigoPgto());
                        cliente.setCodRepresentante(loginList.get(cli).getCodRepresentante());
                        cliente.setDataCadastro(loginList.get(cli).getDataCadastro());
                        cliente.setDataAlteracao(loginList.get(cli).getDataAlteracao());
                        cliente.setLiberaLimite(loginList.get(cli).getLiberaLimite());
                        cliente.setFantasia(loginList.get(cli).getFantasia());
                        cliente.setContatoCobranca(loginList.get(cli).getContatoCobranca());
                        cliente.setInativo(loginList.get(cli).getInativo());
                        cliente.setClienteTipo(loginList.get(cli).getClienteTipo());
                        cliente.setDiaCobranca(loginList.get(cli).getDiaCobranca());
                        cliente.setDiaParaVencimento(loginList.get(cli).getDiaParaVencimento());


                        ///
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //

                        boolean status = cliInsere.insereDados(
                                context1,
                                cliente.getCodigo(),
                                cliente.getNomeCliente(),
                                cliente.getCpf(),
                                cliente.getDataNasc(),
                                cliente.getEndereco(),
                                cliente.getPosicao(),
                                cliente.getPai(),
                                cliente.getMae(),
                                cliente.getBairro(),
                                cliente.getCep(),
                                cliente.getIdentidade(),
                                cliente.getTrabalho(),
                                cliente.getFonetrab(),
                                cliente.getFonetrab(),
                                cliente.getCgc(),
                                cliente.getIncest(),
                                cliente.getEnderecotrab(),
                                cliente.getCodProfissao(),
                                cliente.getCodCidade(),
                                cliente.getResponsavel(),
                                cliente.getFone(),
                                cliente.getObs(),
                                cliente.getNume(),
                                cliente.getEmail(),
                                cliente.getPessoaAuto(),
                                cliente.getLimiteCredito(),
                                cliente.getPessoaAuto1(),
                                cliente.getLimiteCredito1(),
                                cliente.getPessoaAuto2(),
                                cliente.getLimiteCredito2(),
                                cliente.getLimitePessoal(),
                                cliente.getClienteTipo(),
                                cliente.getCodVendedor(),
                                cliente.getSimples(),
                                cliente.getCelular(),
                                cliente.getFisJu(),
                                cliente.getConjuge(),
                                cliente.getFreteCli(),
                                cliente.getAntecipacao(),
                                cliente.getEtiquetas(),
                                cliente.getSistema(),
                                cliente.getVmanu(),
                                cliente.getRecibo(),
                                cliente.getCodigoPgto(),
                                cliente.getCodRepresentante(),
                                cliente.getDataCadastro(),
                                cliente.getDataAlteracao(),
                                cliente.getLiberaLimite(),
                                cliente.getFantasia(),
                                cliente.getContatoCobranca(),
                                cliente.getInativo(),
                                cliente.getClienteTipo(),
                                cliente.getDiaCobranca(),
                                cliente.getDiaParaVencimento()
                        );
                        cursor.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e("DEU ERRO Sinc", t.toString());
            }
        });
    }
}
