package com.example.jose.connectdrawer.cliente;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteFragment extends Fragment {

    private ListView listaCliente;

    public ClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        listaCliente = (ListView) view.findViewById(R.id.listCliente);

        this.setHasOptionsMenu(true);

        final Cliente cliente = new Cliente();
        Cursor cursor = cliente.retornaCliente(getContext());
        List<Cliente> clienteList = new ArrayList<>();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 0L; cursor.getCount() != cont; cont++) {
                Cliente clienteListar = new Cliente();
                clienteListar.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
                clienteListar.setNomecliente(cursor.getString(cursor.getColumnIndex("nomecliente")));

                clienteList.add(clienteListar);
                cursor.moveToNext();

            }
            ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, clienteList);
            listaCliente.setAdapter(adapter);
            listaCliente.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            Cliente cliente1 = (Cliente) listaCliente.getItemAtPosition(position);
                            ClienteDados clienteDados = new ClienteDados();
                            Bundle bundle = new Bundle();
                            bundle.putLong("codigo", cliente1.getCodigo());
                            clienteDados.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
                        }
                    }
            );
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.cliente_opcoes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
        if (id == R.id.cliente_opcoes) {
            ClienteDados clienteDados = new ClienteDados();
            Bundle bundle = new Bundle();
            bundle.putLong("codigo", -1L);
            clienteDados.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
