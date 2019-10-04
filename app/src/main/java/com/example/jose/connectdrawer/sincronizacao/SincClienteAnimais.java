package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.cliente.ClienteAnimais;
import com.example.jose.connectdrawer.cliente.ClienteAnimaisService;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SincClienteAnimais {

    public void iniciaSinc(final Context context, final List<ClienteAnimais> requestClienteAnimais) {

        Handler handler = Sessao.getHandler();
        final Context context1;
        RetRetrofit retRetrofit = new RetRetrofit();

        final GetSetDinamico getSetDinamico = new GetSetDinamico();
        final ClienteAnimais clienteAnimais = new ClienteAnimais();
        final List<Field> fieldList = new ArrayList<>(Arrays.asList(clienteAnimais.getClass().getDeclaredFields()));
        for (int i = fieldList.size() - 1; 0 != i; i--) {
            if (fieldList.get(i).getName().toLowerCase().equals("$change") || fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                fieldList.remove(i);

            }
        }

        for (int cli = 0; requestClienteAnimais.size() != cli; cli++) {
            final int finalCli = cli;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Sessao.colocaTexto("Cadastro dos animais. " + (finalCli + 1) + " de " + requestClienteAnimais.size());
                }
            });

            boolean status = clienteAnimais.cadastraClienteAnimais(
                    context, requestClienteAnimais.get(cli)
            );

        }
    }

    public boolean iniciaAsinc(final Context context, final String ip) {
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
        ClienteAnimais clienteAnimais = new ClienteAnimais();
        final Mac mac = new Mac();
//        clienteAnimais.removeAnimais(Sessao.retornaContext());
        ClienteAnimaisService clienteAnimaisService = retrofit.create(ClienteAnimaisService.class);
        final Call<List<ClienteAnimais>> requestCliente = clienteAnimaisService.listClienteAnimais();
        final Response<List<ClienteAnimais>>[] response = new Response[]{null};
        List<Field> listaCampos = new ArrayList<>(Arrays.asList(clienteAnimais.getClass().getDeclaredFields()));
        Sessao.colocaTexto("Consultando dados. (Animais)");
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

        List<ClienteAnimais> clienteAnimaisList = null;
        try {
            thread.join(120000);

            if (thread.isAlive()) {
                thread.interrupt();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar animais.");
            }

            if (response[0].body() != null) {
                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

                clienteAnimaisList = new ArrayList<>(response[0].body());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (clienteAnimaisList != null) {
            iniciaSinc(context, clienteAnimaisList);
            return true;
        } else {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar dados dos animais.");

            return false;
        }


    }


    public void iniciaenvio(Context context, String ip) {
        ClienteAnimais clienteAnimais = new ClienteAnimais();
        List<ClienteAnimais> clienteAnimaisList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(ClienteAnimais.class.getDeclaredFields()));
        Cursor cursor = clienteAnimais.retornaAnimalAlteradaAndroid(context, "cadastroandroid");
        Sessao.colocaTexto("Verificando animais novos.");
        if (cursor.getCount() > 0) {
            for (long i = 0L; cursor.getCount() != i; i++) {
                clienteAnimais = new ClienteAnimais();
                for (int cid = 0; fieldListCliente.size() != cid; cid++) {
                    if (fieldListCliente.get(cid).getName().toLowerCase().equals("$change") ||
                            fieldListCliente.get(cid).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(cid));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListCliente.get(cid).getName(), cursor);
                        Object clienteRetorno = getSetDinamico.insereField(fieldListCliente.get(cid), clienteAnimais, retornoCursor);
                        clienteAnimais = (ClienteAnimais) clienteRetorno;
                    }
                }
                clienteAnimaisList.add(clienteAnimais);
                Sessao.colocaTexto("Enviando os dados dos animais." + (i + 1) + " de " + cursor.getCount());
                cursor.moveToNext();
            }
        }

        RetRetrofit retRetrofit = new RetRetrofit();
        if (clienteAnimaisList.size() > 0) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create();
            String gsonRetorno = gson.toJson(clienteAnimaisList);
            EnviaJson enviaJson = new EnviaJson();
            String url = retRetrofit.retornaSring("clienteanimais", ip);
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
                clienteAnimais = new ClienteAnimais();
                for (int i = 0; controleCodigoList.size() != i; i++) {
                    if (controleCodigoList.get(i).getCodigoBanco() == 0) {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro: " + controleCodigoList.get(i).getMensagem());
                    } else {
                        clienteAnimais.alteraIdclienteanimal(context, controleCodigoList.get(i).getCodigoAndroid(), controleCodigoList.get(i).getCodigoBanco());
                        clienteAnimais.removeAnimalAlteradaAndroid(context, "cadastroandroid");
                    }

                }
            }
        }

        //
        //
        //    INICIA O ENVIO DOS CLIENTES QUE FORAM ATUALIZADOS NO ANDORID
        //
        //


        clienteAnimais = new ClienteAnimais();
        clienteAnimaisList = new ArrayList<>();
        getSetDinamico = new GetSetDinamico();
        fieldListCliente = new ArrayList<>(Arrays.asList(ClienteAnimais.class.getDeclaredFields()));
        cursor = clienteAnimais.retornaAnimalAlteradaAndroid(context, "alteradoandroid");

        if (cursor.getCount() > 0) {
            for (long i = 0L; cursor.getCount() != i; i++) {
                clienteAnimais = new ClienteAnimais();
                for (int cid = 0; fieldListCliente.size() != cid; cid++) {
                    if (fieldListCliente.get(cid).getName().toLowerCase().equals("$change") ||
                            fieldListCliente.get(cid).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(cid));
                        Object retornoCursor = getSetDinamico.retornaValorCursor(tipo, fieldListCliente.get(cid).getName(), cursor);
                        Object clienteRetorno = getSetDinamico.insereField(fieldListCliente.get(cid), clienteAnimais, retornoCursor);
                        clienteAnimais = (ClienteAnimais) clienteRetorno;
                    }
                }
                clienteAnimaisList.add(clienteAnimais);

                cursor.moveToNext();
            }
        }
        if (clienteAnimaisList.size() > 0) {
            Gson gson  = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create();
            String gsonRetorno = gson.toJson(clienteAnimaisList);
            EnviaJson enviaJson = new EnviaJson();
            String url = retRetrofit.retornaSring("clienteanimais", ip);
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
            clienteAnimais = new ClienteAnimais();
            for (int i = 0; controleCodigoList.size() != i; i++) {
                if (controleCodigoList.get(i).getCodigoBanco() == 0) {
                    MostraToast mostraToast = new MostraToast();
                    mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro: " + controleCodigoList.get(i).getMensagem());
                } else {
                    clienteAnimais.removeAnimalAlteradaAndroid(context, "alteradoandroid");
                }

            }

        }
    }


}
