package com.example.jose.connectdrawer.sincronizacao;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeService;

import java.io.IOException;

/**
 * Created by Jose on 26/05/2017.
 */

public class Sincroniza {

    public void iniciaSincronizacao(Context context) {

        ProgressDialog dialog = new ProgressDialog(context);
        SincCliente sincCliente = new SincCliente();
        SincCidade sincCidade = new SincCidade();
        dialog.setMessage("Aguarde... Sincronizando Clientes");
        sincCliente.iniciaSinc(context);
        dialog.setMessage("Aguarde... Sincronizando Cidades");
        sincCidade.iniciaSinc(context);
        try {
            sincCidade.iniciaEnvio(context);
            dialog.dismiss();
        } catch (IOException e) {
            dialog.dismiss();
            e.printStackTrace();
        }
    }
}
