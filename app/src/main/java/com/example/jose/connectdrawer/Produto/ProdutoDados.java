package com.example.jose.connectdrawer.Produto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jose.connectdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutoDados extends Fragment {


    public ProdutoDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produto_dados, container, false);


        final Bundle bundle = this.getArguments();
        String codiigoProduto = bundle.getString("codigo");




        return view;
    }

}
