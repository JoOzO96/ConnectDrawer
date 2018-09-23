package com.example.jose.connectdrawer.uteis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jose.connectdrawer.main.ConnectMain;

public class Sessao {
    private static Thread thread;
    private static Handler handler;
    private static ProgressBar progressBar;
    private static TextView textoSinc;
    // Variável estática que conterá a instancia da classe
    private static Sessao instance;
    private static Context contextSalvo;
    private static ProgressDialog progressDialog;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {
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
