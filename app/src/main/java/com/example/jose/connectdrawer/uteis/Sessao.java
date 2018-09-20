package com.example.jose.connectdrawer.uteis;

import android.app.ProgressDialog;
import android.content.Context;

public class Sessao {
    // Variável estática que conterá a instancia da classe
    private static Sessao instance;
    private static Context contextSalvo;
    private static ProgressDialog progressDialog;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {}

    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
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
            progressDialog.show();
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


    public static void colocaTextoProgress(String mensagem){
        ProgressDialog progressDialog = Sessao.getProgress();
        if (mensagem.length() > 29 ){
            mensagem.substring(0,29);
        }
        if (progressDialog.isShowing()){
            progressDialog.setMessage(mensagem);
        }else{
            progressDialog.setMessage(mensagem);
//            progressDialog.show();
        }
    }

    public static void terminaProgress(){
        progressDialog.dismiss();
        progressDialog = null;
    }


/*
    Pode criar outros métodos que precise aqui, como getters e setters.
    */
}
