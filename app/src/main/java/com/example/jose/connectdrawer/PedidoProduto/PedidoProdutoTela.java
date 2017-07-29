package com.example.jose.connectdrawer.PedidoProduto;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 30/06/2017.
 */

public class PedidoProdutoTela extends DialogFragment {
    private Button btCancelar;
    private Button btSalvar;
    private EditText txQuantidade;
    private EditText txValorUnitario;
    private EditText txValorTotal;
    private Spinner spproduto;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.popuppedidoproduto, container);

        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);
        txQuantidade = (EditText) view.findViewById(R.id.txQuantidade);
        txValorUnitario = (EditText) view.findViewById(R.id.txValorUnitario);
        txValorTotal = (EditText) view.findViewById(R.id.txValorTotal);

        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        GetSetDinamico getSetDinamico = new GetSetDinamico();

        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoProdutoTela.class.getDeclaredFields()));

        //RETORNA O PRODUTO FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        Long codigoProduto = bundle.getLong("codigo");

        if (codigoProduto > 0){
            PedidoProduto pedidoProduto = new PedidoProduto();
            Cursor cursorPedidoProduto = pedidoProduto.retornaItemPedido(getContext(), codigoProduto);
            if (cursorPedidoProduto.getCount() > 0){
                cursorPedidoProduto.moveToFirst();
                //CONSEGUIU BUSCAR OS DADOS DO PRODUTO NO PEDIDO
                Double quantidade = 0D;
                Double valorunitario = 0D;
                Double valortotal = 0D;
                quantidade = cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("quantidade"));
                valorunitario = cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("valorunitario"));
                valortotal = quantidade * valorunitario;
                txQuantidade.setText("" + quantidade);
                txValorUnitario.setText("" + valorunitario);
                txValorTotal.setText("" + valortotal);
                cursorPedidoProduto.close();
            }else{
//                MostraToast mostraToast = new MostraToast();
//                mostraToast.mostraToastShort(getContext(), "Erro ao tentar localizar produto.");
//                btCancelar.performClick();
            }
        }


        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}
