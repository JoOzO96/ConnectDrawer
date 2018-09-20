package com.example.jose.connectdrawer.sincronizacao;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;

import java.io.IOException;

/**
 * Created by Jose on 26/05/2017.
 */

public class Sincroniza {

    public void iniciaSincronizacao(final Context context) {
        final SincCliente sincCliente = new SincCliente();
        final SincCidade sincCidade = new SincCidade();
        SincPedido sincPedido = new SincPedido();
        SincPedidoProduto sincPedidoProduto = new SincPedidoProduto();
        SincVendedor sincVendedor = new SincVendedor();
        SincProduto sincProduto = new SincProduto();
        SincFormaPagamento sincFormaPagamento = new SincFormaPagamento();
        final SincMac sincMac = new SincMac();
        String ip = null;
        ip = sincMac.iniciasinc(context);
        if (ip == null) {
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "ERRO AO OBTER O IP");
        } else {
            Boolean retorno = sincCliente.iniciaAsinc(context, ip);

//            sincPedido.iniciaSinc(context, ip);
            sincFormaPagamento.iniciaASinc(context, ip);
            sincVendedor.iniciaASinc(context, ip);
            sincProduto.iniciaASinc(context, ip);
            sincCidade.iniciaAsinc(context, ip);
            sincCliente.iniciaenvio(context, ip);
            sincPedido.iniciaenvio(context, ip);
            sincPedidoProduto.iniciaenvio(context,ip);
            Sessao.terminaProgress();
        }
    }


}


