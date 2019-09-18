package com.example.jose.connectdrawer.uteis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.os.Handler;
import android.text.StaticLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.main.ConnectMain;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sessao {
    private static Thread thread;
    private static Handler handler;
    private static ProgressBar progressBar;
    private static TextView textoSinc;
    private static List<Cidade> listaCidade;
    // Variável estática que conterá a instancia da classe
    private static Sessao instance;
    private static Context contextSalvo;
    private static ProgressDialog progressDialog;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {
    }

    public static Double retornaFormatado(Double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Double.parseDouble(decimalFormat.format(numero).toString().replace(",","."));
    }


    public static List<Cidade> retornaListaCidade() {

        if (listaCidade == null) {
            listaCidade = new ArrayList<>();
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            Cidade cidade = new Cidade();
            Cursor cursorCidade = cidade.retornaCidade(Sessao.contextSalvo);
            List<Field> fieldListCidade = new ArrayList<>(Arrays.asList(cidade.getClass().getDeclaredFields()));
            for (int i = 0; i < fieldListCidade.size(); i++) {
                if (fieldListCidade.get(i).getName().toLowerCase().equals("nomecidade") ||
                        fieldListCidade.get(i).getName().toLowerCase().equals("codcidade") ||
                        fieldListCidade.get(i).getName().toLowerCase().equals("uf") ||
                        fieldListCidade.get(i).getName().toLowerCase().equals("codnacionalcidade")) {
                } else {
                    fieldListCidade.remove(i);
                    i = -1;
                }
            }
            int posicao = 0;
            if (cursorCidade.getCount() > 0) {
                cursorCidade.moveToFirst();
                for (int j = 0; cursorCidade.getCount() != j; j++) {
                    Cidade cidade1 = new Cidade();
                    for (int f = 0; fieldListCidade.size() != f; f++) {

                        String tipo = getSetDinamico.retornaTipoCampo(fieldListCidade.get(f));
                        String nomeCampo = fieldListCidade.get(f).getName().toLowerCase();
                        Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorCidade);
                        if (retorno != null) {
                            Object teste = getSetDinamico.insereField(fieldListCidade.get(f), cidade1, retorno);
                            cidade1 = (Cidade) teste;
                        }


                    }
                    cursorCidade.moveToNext();
                    listaCidade.add(cidade1);
                }
            }
            return listaCidade;
        } else {
            return listaCidade;
        }

    }


    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
    }

    public static void setTextView(TextView texto) {
        textoSinc = texto;
    }

    public static TextView getTextView() {
        return textoSinc;
    }

    public static void setHandler(Handler h) {
        handler = h;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static void setProgressBar(ProgressBar p) {
        progressBar = p;
    }

    public static ProgressBar getProgressBar() {
        return progressBar;
    }

    public static void colocaTexto(final String texto) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textoSinc.setText(texto);
            }
        });

    }

    public static ProgressDialog getProgress() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(contextSalvo);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        return progressDialog;
    }

    public static ProgressDialog getProgress(String titulo) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(contextSalvo);
            progressDialog.setTitle(titulo);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
        return progressDialog;
    }

    public static void removeProgress() {
        progressDialog = null;
    }

    public static void setaContext(Context context) {
        contextSalvo = context;
    }

    public static Context retornaContext() {
        return contextSalvo;
    }

    public static void setInstance(Sessao instance) {
        Sessao.instance = instance;
    }

    public static boolean colocaDebug() {
         boolean isDebuggable = android.os.Debug.isDebuggerConnected();
        return isDebuggable;
    }

    public static int retornaDebug() {
        boolean isDebuggable = android.os.Debug.isDebuggerConnected();
        if (isDebuggable){
            return 0;
        }else{
            return 4;
        }
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }


    public static void colocaTextoProgress(String mensagem) {
        if (mensagem.length() > 28) {
            progressDialog.setMessage(mensagem.substring(0, 28));
        } else {
            progressDialog.setMessage(mensagem);
        }
    }

    public static void terminaProgress() {
        Intent intent = new Intent(contextSalvo, ConnectMain.class);
        contextSalvo.startActivity(intent);
    }

    public static void iniciaProgress() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }



/*
    Pode criar outros métodos que precise aqui, como getters e setters.
    */
}
