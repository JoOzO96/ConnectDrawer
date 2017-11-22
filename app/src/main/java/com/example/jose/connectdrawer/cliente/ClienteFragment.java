package com.example.jose.connectdrawer.cliente;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeFragment;
import com.example.jose.connectdrawer.uteis.MostraToast;

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
        final Cursor cursor = cliente.retornaCliente(getContext());
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

                            //VERIFICA SE O CLIENTE FOI CADASTRADO E NAO SINCRONIZADO AINDA PARA NAO PODER EDITAR ANTES DE SINCRONIZAR

                            Cursor cursor1 = cliente.retornaCursorRawQUery(getContext(), "SELECT * FROM CLIENTE WHERE codigo = " + cliente1.getCodigo() + " and cadastroandroid = 1");

                            if (cursor1.getCount() > 0) {
                                MostraToast mostraToast = new MostraToast();
                                mostraToast.mostraToastLong(getContext(), "Não é possivel alterar um cliente que foi cadastrado no sistema, antes de sincronizar com o servidor.");

                            } else {

                                Bundle bundle = new Bundle();
                                bundle.putLong("codigo", cliente1.getCodigo());
                                clienteDados.setArguments(bundle);
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
                            }
                        }
                    }
            );
            listaCliente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final Cliente cliente1 = (Cliente) listaCliente.getItemAtPosition(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Confirm");
                    builder.setMessage("Confirma a exclusao do cliente " + cliente1.getNomecliente() + "?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            MostraToast toast = new MostraToast();
                            boolean retorno = cliente1.deletaCliente(getContext(), cliente1.getCodigo());
                            if (retorno == true) {
                                toast.mostraToastShort(getContext(), "Cliente excluido com sucesso");
                                ClienteFragment clienteFragment = new ClienteFragment();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, clienteFragment, clienteFragment.getTag()).commit();
                            } else {
                                toast.mostraToastShort(getContext(), "Erro ao deletar cliente");
                            }
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });
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
