package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by Jose on 11/07/2017.
 */

public class SincPedidoProduto {

    public void iniciaSinc(Context context){
        final Context context1 = context;
        RetRetrofit retRetrofit = new RetRetrofit();
        //SETA O RETROFIT COM OS DADOS QUE A CLASSE RETORNOU, PARA O SISTEMA
        Retrofit retrofit = retRetrofit.retornaRetrofit();
    }
}
