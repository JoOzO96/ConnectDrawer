package com.example.jose.connectdrawer.FormaPagamento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jose.connectdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormaPagamentoFragment extends Fragment {


    public FormaPagamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forma_pagamento, container, false);
        return view;
    }

}
