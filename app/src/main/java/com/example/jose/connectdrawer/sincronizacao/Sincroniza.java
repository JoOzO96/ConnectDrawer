package com.example.jose.connectdrawer.sincronizacao;

import android.app.ProgressDialog;
import android.content.Context;

import java.io.IOException;

/**
 * Created by Jose on 26/05/2017.
 */

public class Sincroniza {

    public void iniciaSincronizacao(Context context) {

        ProgressDialog dialog = new ProgressDialog(context);
        SincCliente sincCliente = new SincCliente();
        SincCidade sincCidade = new SincCidade();
        SincPedido sincPedido = new SincPedido();
        SincPedidoProduto sincPedidoProduto = new SincPedidoProduto();
        SincVendedor sincVendedor = new SincVendedor();
        SincProduto sincProduto = new SincProduto();
        SincFormaPagamento sincFormaPagamento = new SincFormaPagamento();
        sincCliente.iniciaSinc(context);
        sincCidade.iniciaSinc(context);
//        sincPedido.iniciaSinc(context);
        sincFormaPagamento.iniciaSinc(context);
        sincVendedor.iniciaSinc(context);
        sincProduto.iniciaSinc(context);
        try {
            sincCidade.iniciaEnvio(context);
            sincCliente.iniciaenvio(context);
            sincPedido.iniciaenvio(context);
            dialog.dismiss();
        } catch (IOException e) {
            dialog.dismiss();
            e.printStackTrace();
        }
    }
}
