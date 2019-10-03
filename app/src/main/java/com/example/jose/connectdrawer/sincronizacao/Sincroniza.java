package com.example.jose.connectdrawer.sincronizacao;

import android.content.Context;
import android.os.Handler;

import com.example.jose.connectdrawer.Emitente.EmiteConfigura;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;

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
        SincParcelas sincParcelas = new SincParcelas();
        SincIcms sincIcms = new SincIcms();
        SincClienteAnimais sincClienteAnimais = new SincClienteAnimais();
        SincEmiteConfigura sincEmiteConfigura = new SincEmiteConfigura();

        final SincMac sincMac = new SincMac();
        SincEmitente sincEmitente = new SincEmitente();
        String ip = null;
//        Handler handler = Sessao.getHandler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
        Sessao.iniciaProgress();
        Sessao.colocaTexto("Verificando dados do IP.");
//            }
//        });
        ip = sincMac.iniciasinc(context);
        if (ip == null) {
            Sessao.terminaProgress();
            MostraToast mostraToast = new MostraToast();
            mostraToast.mostraToastLong(context, "ERRO AO OBTER O IP");
        } else {
//            Boolean retorno = sincCliente.iniciaAsinc(context, ip);

//            sincPedido.iniciaSinc(context, ip);

            sincEmitente.iniciaAsinc(context, ip);
            sincEmiteConfigura.iniciaAsinc(context, ip);
            sincCliente.iniciaAsinc(context, ip);
            sincIcms.iniciaAsinc(context, ip);
            sincFormaPagamento.iniciaASinc(context, ip);
            sincVendedor.iniciaASinc(context, ip);
            sincProduto.iniciaASinc(context, ip);
            sincCidade.iniciaAsinc(context, ip);
            sincCliente.iniciaenvio(context, ip);
            sincPedido.iniciaenvio(context, ip);
//            sincPedidoProduto.iniciaenvio(context, ip);
            sincParcelas.iniciaenvio(context, ip);
            EmiteConfigura emiteConfigura = new EmiteConfigura().retornaEmiteConfiguraObjeto(context, 1L);
            if (emiteConfigura.getEmpresapet()){
                sincClienteAnimais.iniciaAsinc(context, ip);
            }
            Sessao.terminaProgress();

        }
    }


}


