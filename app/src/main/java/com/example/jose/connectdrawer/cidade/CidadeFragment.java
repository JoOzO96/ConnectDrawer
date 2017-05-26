package com.example.jose.connectdrawer.cidade;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteDados;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CidadeFragment extends Fragment {

    private ListView listCidade;


    public CidadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cidade, container, false);
        listCidade = (ListView) view.findViewById(R.id.listCidade);
        this.setHasOptionsMenu(true);

        Cidade cidade = new Cidade();

        Cursor cursor = cidade.retornaCidade(getContext());
        List<Cidade> cidadeList = new ArrayList<>();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 1L; cursor.getCount() != cont; cont++) {
                Cidade cidade1 = new Cidade();
                cidade1.setCodCidade(cursor.getLong(cursor.getColumnIndex("codCidade")));
                cidade1.setNomeCidade(cursor.getString(cursor.getColumnIndex("nomeCidade")));
                cidade1.setUF(cursor.getString(cursor.getColumnIndex("uf")));

                cidadeList.add(cidade1);
                cursor.moveToNext();
            }
            ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cidadeList);
            listCidade.setAdapter(adapter);
            listCidade.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            Cidade cidade1= (Cidade) listCidade.getItemAtPosition(position);
                            ClienteDados clienteDados = new ClienteDados();
                            Bundle bundle = new Bundle();
                            bundle.putLong("codigo", cidade1.getCodCidade());
                            clienteDados.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
                        }
                    }
            );

        }


        return view;
    }

}
