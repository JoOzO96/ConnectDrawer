package com.example.jose.connectdrawer.A7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jose.connectdrawer.R;

public class A7Dados extends Fragment {
    Bluetooth impressaoA7 = new Bluetooth();
    Button botao;
    public A7Dados() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_a7_dados, container, false);
        botao = view.findViewById(R.id.botaoImprimir);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                impressaoA7.imprimeA7(getContext());

            }
        });
        return  view;

    }

}
