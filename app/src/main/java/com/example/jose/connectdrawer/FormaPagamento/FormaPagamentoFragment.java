package com.example.jose.connectdrawer.FormaPagamento;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormaPagamentoFragment extends Fragment {

    private ListView listFormaPagamento;

    public FormaPagamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forma_pagamento, container, false);


        Cursor cursor = FormaPagamento.retornaFormaPagamento(getContext());
        List<FormaPagamento> formaPagamentoList = new ArrayList<>();
        cursor.moveToFirst();
        listFormaPagamento = (ListView) view.findViewById(R.id.listFormaPagamento);
        if (cursor.getCount() > 0) {
            for (Long cont = 0L; cursor.getCount() != cont; cont++) {
                FormaPagamento formaPagamentoListar = new FormaPagamento();

                formaPagamentoListar.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
                formaPagamentoListar.setPagamento(cursor.getString(cursor.getColumnIndex("pagamento")));

                formaPagamentoList.add(formaPagamentoListar);
                try {
                    cursor.moveToNext();
                } catch (IllegalStateException i) {
                }
            }
        }
        ArrayAdapter<FormaPagamento> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, formaPagamentoList);
        listFormaPagamento.setAdapter(adapter);
        listFormaPagamento.setEmptyView(view.findViewById(R.id.semdados));
        return view;
    }

}
