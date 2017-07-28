package com.example.jose.connectdrawer.PedidoProduto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;

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

        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        GetSetDinamico getSetDinamico = new GetSetDinamico();

        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoProdutoTela.class.getDeclaredFields()));

        //RETORNA O PRODUTO FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        String codigoProduto = bundle.getString("codigo");

        if (!codigoProduto.equals("")){

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
