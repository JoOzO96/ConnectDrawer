package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;

import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeService;

/**
 * Created by Jose on 26/05/2017.
 */

public class Sincroniza {

    public void iniciaSincronizacao(Context context){
        SincCliente sincCliente = new SincCliente();
        SincCidade sincCidade = new SincCidade();
        sincCliente.iniciaSinc(context);
        sincCidade.iniciaSinc(context);
    }
}
