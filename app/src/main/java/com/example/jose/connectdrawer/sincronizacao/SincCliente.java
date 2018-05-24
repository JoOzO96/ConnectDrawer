package com.example.jose.connectdrawer.sincronizacao;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteService;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.google.gson.Gson;

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
                for (int cli = 0; loginList.size() != cli; cli++) {
                    //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR

                    Cursor cursor = cliInsere.retornaClienteFiltradoCursor(context1, loginList.get(cli).getCodigo());
                    if (cursor.getCount() > 0) {
                        cursor.close();
                    } else {
                        //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR
                        Cliente cliente = new Cliente();
                        cliente.setCodigo(loginList.get(cli).getCodigo());
                        cliente.setNomecliente(loginList.get(cli).getNomecliente());
                        cliente.setCpf(loginList.get(cli).getCpf());
                        cliente.setDatanasc(loginList.get(cli).getDatanasc());
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
                        cliente.setCodprofissao(loginList.get(cli).getCodprofissao());
                        cliente.setCodcidade(loginList.get(cli).getCodcidade());
                        cliente.setResponsavel(loginList.get(cli).getResponsavel());
                        cliente.setFone(loginList.get(cli).getFone());
                        cliente.setObs(loginList.get(cli).getObs());
                        cliente.setNume(loginList.get(cli).getNume());
                        cliente.setEmail(loginList.get(cli).getEmail());
                        cliente.setPessoaauto(loginList.get(cli).getPessoaauto());
                        cliente.setLimitecredito(loginList.get(cli).getLimitecredito());
                        cliente.setPessoaauto1(loginList.get(cli).getPessoaauto1());
                        cliente.setLimitecredito1(loginList.get(cli).getLimitecredito1());
                        cliente.setPessoaauto2(loginList.get(cli).getPessoaauto2());
                        cliente.setLimitecredito2(loginList.get(cli).getLimitecredito2());
                        cliente.setLimitepessoal(loginList.get(cli).getLimitepessoal());
                        cliente.setTipocliente(loginList.get(cli).getTipocliente());
                        cliente.setCodvendedor(loginList.get(cli).getCodvendedor());
                        cliente.setSimples(loginList.get(cli).getSimples());
                        cliente.setCelular(loginList.get(cli).getCelular());
                        cliente.setFisju(loginList.get(cli).getFisju());
                        cliente.setConjuge(loginList.get(cli).getConjuge());
                        cliente.setFretecli(loginList.get(cli).getFretecli());
                        cliente.setAntecipacao(loginList.get(cli).getAntecipacao());
                        cliente.setEtiquetas(loginList.get(cli).getEtiquetas());
                        cliente.setSistema(loginList.get(cli).getSistema());
                        cliente.setVmanu(loginList.get(cli).getVmanu());
                        cliente.setRecibo(loginList.get(cli).getRecibo());
                        cliente.setCodigopgto(loginList.get(cli).getCodigopgto());
                        cliente.setCodrepresentante(loginList.get(cli).getCodrepresentante());
                        cliente.setDatacadastro(loginList.get(cli).getDatacadastro());
                        cliente.setDataalteracao(loginList.get(cli).getDataalteracao());
                        cliente.setLiberalimite(loginList.get(cli).getLiberalimite());
                        cliente.setFantasia(loginList.get(cli).getFantasia());
                        cliente.setContatocobranca(loginList.get(cli).getContatocobranca());
                        cliente.setInativo(loginList.get(cli).getInativo());
                        cliente.setClientetipo(loginList.get(cli).getClientetipo());
                        cliente.setDiacobranca(loginList.get(cli).getDiacobranca());
                        cliente.setDiaparavencimento(loginList.get(cli).getDiaparavencimento());


                        ///
                        //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                        //
                        //
                        //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                        //

                        boolean status = cliInsere.cadastraCliente(
                                context1, cliente
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


    public void iniciaenvio(Context context) {
        Cliente cliente = new Cliente();
        List<Cliente> clienteList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
        Cursor cursor = cliente.retornaClienteAlteradaAndroid(context, "cadastroandroid");

        if (cursor.getCount() > 0) {
            for (long i = 0L; cursor.getCount() != i; i++) {
                cliente = new Cliente();
                for (int cid = 0; fieldListCliente.size() != cid; cid++) {
                    if (fieldListCliente.get(cid).getName().toLowerCase().equals("$change") ||
                            fieldListCliente.get(cid).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(cid));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListCliente.get(cid).getName(), cursor);
                        Object clienteRetorno = getSetDinamico.insereField(fieldListCliente.get(cid), cliente, retornoCursor);
                        cliente = (Cliente) clienteRetorno;
                    }
                }
                clienteList.add(cliente);

                cursor.moveToNext();
            }
        }
        if (clienteList.size() > 0) {
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(clienteList);
            EnviaJson enviaJson = new EnviaJson();
//            String url = "http://177.92.186.84:15101/ConnectServices/recebeCliente";
            String url = "http://192.168.0.199:8080/ConnectServices/recebeCliente";
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
                cliente = new Cliente();
                for (int i = 0; controleCodigoList.size() != i; i++) {
                    cliente.alteraPedidoCliente(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                    cliente.alteraCodCliente(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                    cliente.removeClienteAlteradaAndroid(context, "cadastroandroid");
                }
            }
        }


        //
        //
        //    INICIA O ENVIO DOS CLIENTES QUE FORAM ATUALIZADOS NO ANDORID
        //
        //


        cliente = new Cliente();
        clienteList = new ArrayList<>();
        getSetDinamico = new GetSetDinamico();
        fieldListCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
        cursor = cliente.retornaClienteAlteradaAndroid(context, "alteradoandroid");

        if (cursor.getCount() > 0) {
            for (long i = 0L; cursor.getCount() != i; i++) {
                cliente = new Cliente();
                for (int cid = 0; fieldListCliente.size() != cid; cid++) {
                    if (fieldListCliente.get(cid).getName().toLowerCase().equals("$change") ||
                            fieldListCliente.get(cid).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(cid));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListCliente.get(cid).getName(), cursor);
                        Object clienteRetorno = getSetDinamico.insereField(fieldListCliente.get(cid), cliente, retornoCursor);
                        cliente = (Cliente) clienteRetorno;
                    }
                }
                clienteList.add(cliente);

                cursor.moveToNext();
            }
        }
        if (clienteList.size() > 0) {
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(clienteList);
            EnviaJson enviaJson = new EnviaJson();
            String url = "http://177.92.186.84:15101/ConnectServices/recebeClienteAtualizado";
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
            cliente.removeClienteAlteradaAndroid(context, "alteradoandroid");
        }
    }

}
