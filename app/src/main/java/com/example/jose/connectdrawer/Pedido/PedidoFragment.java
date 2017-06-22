package com.example.jose.connectdrawer.Pedido;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteDados;
import com.example.jose.connectdrawer.cliente.ClienteFragment;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoFragment extends Fragment {

    private ListView listaPedido;

    public PedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);
        listaPedido = (ListView) view.findViewById(R.id.listPedido);

        this.setHasOptionsMenu(true);

        final Pedido pedido = new Pedido();
        Cursor cursor = pedido.retornaPedido(getContext());
        List<Pedido> pedidoList = new ArrayList<>();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 0L; cursor.getCount() != cont; cont++) {
                Pedido pedidoListar = new Pedido();
                pedidoListar.setPedido(cursor.getLong(cursor.getColumnIndex("pedido")));
                pedidoListar.setNome(cursor.getString(cursor.getColumnIndex("nome")));

                pedidoList.add(pedidoListar);
                try {
                    cursor.moveToNext();
                }catch (IllegalStateException i){
                    cursor.close();
                    cursor = pedido.retornaPedido(getContext());
                    cursor.moveToPosition(Integer.parseInt(cont.toString()));
                }
            }
            cursor.close();
            ArrayAdapter<Pedido> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pedidoList);
            listaPedido.setAdapter(adapter);
            listaPedido.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            Pedido pedido = (Pedido) listaPedido.getItemAtPosition(position);
                            PedidoDados pedidoDados = new PedidoDados();
                            Bundle bundle = new Bundle();
                            bundle.putLong("codigo", pedido.getPedido());
                            pedidoDados.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
                        }
                    }
            );
            listaPedido.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final Pedido pedido = (Pedido) listaPedido.getItemAtPosition(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Confirm");
                    builder.setMessage("Confirma a exclusao do pedido " + pedido.getPedido() + "?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            MostraToast toast = new MostraToast();
                            boolean retorno = pedido.removerPedido(getContext(), pedido.getPedido());
                            if (retorno == true) {
                                toast.mostraToastShort(getContext(), "Pedido excluido com sucesso");
                                PedidoFragment pedidoFragment = new PedidoFragment();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                            } else {
                                toast.mostraToastShort(getContext(), "Erro ao deletar pedido");
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

}
