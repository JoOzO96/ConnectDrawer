package com.example.jose.connectdrawer.sincronizacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.jose.connectdrawer.uteis.MostraToast;

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
        SincMac sincMac = new SincMac();
        String ip = null;
        ip = sincMac.iniciasinc(context);
        if (ip == null) {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "ERRO AO OBTER O IP");
        }else {
            sincCliente.iniciaSinc(context, ip);
            sincCidade.iniciaSinc(context, ip);
//        sincPedido.iniciaSinc(context);
            sincFormaPagamento.iniciaSinc(context, ip);
            sincVendedor.iniciaSinc(context, ip);
            sincProduto.iniciaSinc(context, ip);
            try {
                sincCidade.iniciaEnvio(context, ip);
                sincCliente.iniciaenvio(context, ip);
                sincPedido.iniciaenvio(context, ip);
                dialog.dismiss();
            } catch (IOException e) {
                dialog.dismiss();
                e.printStackTrace();
            }
        }
    }
}
