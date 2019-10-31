package com.example.jose.connectdrawer.sincronizacao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteService;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jose on 22/05/2017.
 */

public class SincCliente extends Activity {


    public void iniciaSinc(final Context context, final List<Cliente> requestCliente) {
        Handler handler = Sessao.getHandler();
        final Context context1;
//        context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();

        final GetSetDinamico getSetDinamico = new GetSetDinamico();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
//        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);

//        ClienteService clienteService = retrofit.create(ClienteService.class);
//        Call<List<Cliente>> requestCliente = clienteService.listCliente();

//        requestCliente.enqueue(new Callback<List<Cliente>>() {
//            @Override
//            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {

//                List<Cliente> loginList = response.body();
////        Date dataInicioInsercao = null;
//        ProgressDialog progressDialog = Sessao.getProgress();
//        Sessao.colocaTextoProgress("Cadastro de Cliente. 0 de " + requestCliente.size());
        final Cliente cliInsere = new Cliente();
        final List<Field> fieldList = new ArrayList<>(Arrays.asList(cliInsere.getClass().getDeclaredFields()));
        for (int i = fieldList.size() - 1; 0 != i; i--) {
            if (fieldList.get(i).getName().toLowerCase().equals("$change") || fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                fieldList.remove(i);
//                i--;
            }
        }
//        Date inicioCadastro = new Date();
//        Date fimCadastro = new Date();
//        Long tempoDemora = 0L;
//        Long media = 0L;
//        Long maiorDemora = 0L;
        String clienteMaisDemorado = "";
        for (int cli = 0; requestCliente.size() != cli; cli++) {
//            inicioCadastro = new Date();
            //TESTE SE O CODIGO JA ESTA NO BANCO DO CELULAR, SE NAO ESTIVER ELE IRA CADASTRAR
//            dataInicioInsercao = new Date();
            Cursor cursor = cliInsere.retornaClienteFiltradoCursor(context, requestCliente.get(cli).getCodigo());
            Cliente cliente = new Cliente();
            final int finalCli = cli;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Sessao.colocaTexto("Cadastro de Cliente. " + (finalCli + 1) + " de " + requestCliente.size());
                }
            });
            if (cursor.getCount() > 0) {
                Cliente clienteConsulta = new Cliente();
//                for (int f = 0; fieldList.size() != f; f++) {
//
////                            String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
//                    String nomeCampo = fieldList.get(f).getName();
//                    Object valorCampo = getSetDinamico.retornaValorCampo(fieldList.get(f), requestCliente.get(cli));
//                    Object teste = getSetDinamico.insereField(fieldList.get(f), cliente, valorCampo);
//                    cliente = (Cliente) teste;
//
//                }

//                clienteConsulta = clienteConsulta.retornaClienteObjetoAtualizar(context, cursor.getLong(cursor.getColumnIndex("codigo")));
//                String clienteS = new Gson().toJson(cliente);
//                String clienteConsultaS = new Gson().toJson(clienteConsulta);

                cursor.close();


            } else {
                cursor.close();
                //PEGA OS CODIGOS QUE VIERAM DO SERVIDOR


//                for (int f = 0; fieldList.size() != f; f++) {
//
////                            String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
//                    String nomeCampo = fieldList.get(f).getName();
//                    Object valorCampo = getSetDinamico.retornaValorCampo(fieldList.get(f), requestCliente.get(cli));
//                    Object teste = getSetDinamico.insereField(fieldList.get(f), cliente, valorCampo);
//                    cliente = (Cliente) teste;
//
//                }
                cursor.moveToNext();
//                cliente.setCodigo(requestCliente.get(cli).getCodigo());
//                cliente.setNomecliente(requestCliente.get(cli).getNomecliente());
//                cliente.setCpf(requestCliente.get(cli).getCpf());
//                cliente.setDatanasc(requestCliente.get(cli).getDatanasc());
//                cliente.setEndereco(requestCliente.get(cli).getEndereco());
//                cliente.setPosicao(requestCliente.get(cli).getPosicao());
//                cliente.setPai(requestCliente.get(cli).getPai());
//                cliente.setMae(requestCliente.get(cli).getMae());
//                cliente.setBairro(requestCliente.get(cli).getBairro());
//                cliente.setCep(requestCliente.get(cli).getCep());
//                cliente.setIdentidade(requestCliente.get(cli).getIdentidade());
//                cliente.setTrabalho(requestCliente.get(cli).getTrabalho());
//                cliente.setTelefone(requestCliente.get(cli).getTelefone());
//                cliente.setFonetrab(requestCliente.get(cli).getFonetrab());
//                cliente.setCgc(requestCliente.get(cli).getCgc());
//                cliente.setIncest(requestCliente.get(cli).getIncest());
//                cliente.setEnderecotrab(requestCliente.get(cli).getEnderecotrab());
//                cliente.setCodprofissao(requestCliente.get(cli).getCodprofissao());
//                cliente.setCodcidade(requestCliente.get(cli).getCodcidade());
//                cliente.setResponsavel(requestCliente.get(cli).getResponsavel());
//                cliente.setFone(requestCliente.get(cli).getFone());
//                cliente.setObs(requestCliente.get(cli).getObs());
//                cliente.setNume(requestCliente.get(cli).getNume());
//                cliente.setEmail(requestCliente.get(cli).getEmail());
//                cliente.setPessoaauto(requestCliente.get(cli).getPessoaauto());
//                cliente.setLimitecredito(requestCliente.get(cli).getLimitecredito());
//                cliente.setPessoaauto1(requestCliente.get(cli).getPessoaauto1());
//                cliente.setLimitecredito1(requestCliente.get(cli).getLimitecredito1());
//                cliente.setPessoaauto2(requestCliente.get(cli).getPessoaauto2());
//                cliente.setLimitecredito2(requestCliente.get(cli).getLimitecredito2());
//                cliente.setLimitepessoal(requestCliente.get(cli).getLimitepessoal());
//                cliente.setTipocliente(requestCliente.get(cli).getTipocliente());
//                cliente.setCodvendedor(requestCliente.get(cli).getCodvendedor());
//                cliente.setSimples(requestCliente.get(cli).getSimples());
//                cliente.setCelular(requestCliente.get(cli).getCelular());
//                cliente.setFisju(requestCliente.get(cli).getFisju());
//                cliente.setConjuge(requestCliente.get(cli).getConjuge());
//                cliente.setFretecli(requestCliente.get(cli).getFretecli());
//                cliente.setAntecipacao(requestCliente.get(cli).getAntecipacao());
//                cliente.setEtiquetas(requestCliente.get(cli).getEtiquetas());
//                cliente.setSistema(requestCliente.get(cli).getSistema());
//                cliente.setVmanu(requestCliente.get(cli).getVmanu());
//                cliente.setRecibo(requestCliente.get(cli).getRecibo());
//                cliente.setCodigopgto(requestCliente.get(cli).getCodigopgto());
//                cliente.setCodrepresentante(requestCliente.get(cli).getCodrepresentante());
//                cliente.setDatacadastro(requestCliente.get(cli).getDatacadastro());
//                cliente.setDataalteracao(requestCliente.get(cli).getDataalteracao());
//                cliente.setLiberalimite(requestCliente.get(cli).getLiberalimite());
//                cliente.setFantasia(requestCliente.get(cli).getFantasia());
//                cliente.setContatocobranca(requestCliente.get(cli).getContatocobranca());
//                cliente.setInativo(requestCliente.get(cli).getInativo());
//                cliente.setClientetipo(requestCliente.get(cli).getClientetipo());
//                cliente.setDiacobranca(requestCliente.get(cli).getDiacobranca());
//                cliente.setDiaparavencimento(requestCliente.get(cli).getDiaparavencimento());


                ///
                //TESTA SE OS DADOS CONTEM ALGO NULO E SETA PARA BRANCO OU FALSO
                //
                //
                //INSERE NO BANCO DE DADOS DO ANDROID OS DADOS QUE VIERAM DO SERVIDOR
                //

                boolean status = cliInsere.cadastraCliente(
                        context, requestCliente.get(cli)
                );


            }

//
//            fimCadastro = new Date();
//
//            tempoDemora = fimCadastro.getTime() - inicioCadastro.getTime();
//            media += tempoDemora;
//            if (tempoDemora > maiorDemora){
//                maiorDemora = tempoDemora;
//                clienteMaisDemorado = cliente.getNomecliente();
//            }

        }


//        Log.e("TESTECLIENTE", "MEDIA:" + media / requestCliente.size() + " MAIS LENTO:" + maiorDemora + " NOME: " + clienteMaisDemorado);


//            @Override
//            public void onFailure(Call<List<Cliente>> call, Throwable t) {

//            }
//        });
    }


    public boolean iniciaAsinc(final Context context, final String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        Cliente cliInsere = new Cliente();
        final Mac mac = new Mac();

        ClienteService clienteService = retrofit.create(ClienteService.class);
        final Call<List<Cliente>> requestCliente = clienteService.listCliente(mac.retornaMac(context));
        final Response<List<Cliente>>[] response = new Response[]{null};
        List<Field> listaCampos = new ArrayList<>(Arrays.asList(cliInsere.getClass().getDeclaredFields()));

        Sessao.colocaTexto("Consultando dados. (Cliente)");

        Date dataInicio = new Date();
        final String[] conteudo = {null};

        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response[0] = requestCliente.execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

        List<Cliente> listaCliente = null;
        try {
            thread.join(120000);

            if (thread.isAlive()) {
                thread.interrupt();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar vendedor.");
            }

            if (response[0].body() != null) {
                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

                listaCliente = new ArrayList<>(response[0].body());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (listaCliente != null) {
            iniciaSinc(context, listaCliente);
            return true;
        } else {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar dados do cliente.");

            return false;
        }


    }


    public void iniciaenvio(Context context, String ip) {
        Cliente cliente = new Cliente();
        List<Cliente> clienteList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
        Cursor cursor = cliente.retornaClienteAlteradaAndroid(context, "cadastroandroid");
        Sessao.colocaTexto("Verificando clientes novos.");
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
                Sessao.colocaTexto("Enviando os dados de clientes." + (i + 1) + " de " + cursor.getCount());
                cursor.moveToNext();
            }
        }

        RetRetrofit retRetrofit = new RetRetrofit();
        if (clienteList.size() > 0) {
            Gson gson = new Gson();
            String gsonRetorno = gson.toJson(clienteList);
            Log.e("JSON", gsonRetorno);
            EnviaJson enviaJson = new EnviaJson();
            String url = retRetrofit.retornaSring("cliente", ip);
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
                    if (controleCodigoList.get(i).getCodigoBanco() == 0) {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro: " + controleCodigoList.get(i).getMensagem());
                    } else {
                        cliente.alteraPedidoCliente(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                        cliente.alteraCodCliente(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                        cliente.removeClienteAlteradaAndroid(context, "cadastroandroid");
                    }

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
            String url = retRetrofit.retornaSring("cliente", ip);
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
            ControleCodigo conversao[] = gson.fromJson(retornoEnvio, ControleCodigo[].class);
            List<ControleCodigo> controleCodigoList = new ArrayList<>(Arrays.asList(conversao));
            cliente = new Cliente();
            for (int i = 0; controleCodigoList.size() != i; i++) {
                if (controleCodigoList.get(i).getCodigoBanco() == 0) {
                    MostraToast mostraToast = new MostraToast();
                    mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro: " + controleCodigoList.get(i).getMensagem());
                } else {
                    cliente.removeClienteAlteradaAndroid(context, "alteradoandroid");
                }

            }

        }
    }

}
