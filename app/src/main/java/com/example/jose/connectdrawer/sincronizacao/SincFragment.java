package com.example.jose.connectdrawer.sincronizacao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SincFragment extends Fragment {
    private ListView listSincronizacao;

    public SincFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sinc, container, false);
//        return inflater.inflate(R.layout.fragment_sinc, container, false);
        listSincronizacao = (ListView) view.findViewById(R.id.listSincronizacao);
        List<String> listString = new ArrayList<>();
        listSincronizacao.setEmptyView(view.findViewById(R.id.semdados));




        return view;
    }

}
