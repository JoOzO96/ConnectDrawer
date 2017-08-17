package com.example.jose.connectdrawer.Produto;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutoFragment extends Fragment {
    private ListView listaProduto;

    public ProdutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto, container, false);
        listaProduto = (ListView) view.findViewById(R.id.listProduto);

        this.setHasOptionsMenu(true);

        Produto produto = new Produto();

        Cursor cursor = produto.retornaProduto(getContext());
        List<Produto> produtoList = new ArrayList<>();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 0L; cursor.getCount() != cont; cont++) {

                produto = new Produto();
                produto.setCodproduto(cursor.getString(cursor.getColumnIndex("codproduto")));
                produto.setMercadoria(cursor.getString(cursor.getColumnIndex("mercadoria")));
                cursor.moveToNext();
                produtoList.add(produto);
            }

            cursor.close();
            ArrayAdapter<Produto> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, produtoList);
            listaProduto.setAdapter(adapter);

//            listaProduto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }


        return view;

    }

}
