package com.example.jose.connectdrawer.Vendedor;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.Pedido.PedidoDados;
import com.example.jose.connectdrawer.Pedido.PedidoFragment;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VendedorFragment extends Fragment {
    private ListView listaVendedor;

    public VendedorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vendedor, container, false);

        listaVendedor = (ListView) view.findViewById(R.id.listaVendedor);

        this.setHasOptionsMenu(true);

        Vendedor vendedor = new Vendedor();
        Cursor cursor = vendedor.retornaVendedor(getContext());
        List<Vendedor> vendedorList = new ArrayList<>();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            for (Long cont = 0L; cursor.getCount() != cont; cont++) {
                Vendedor vendedorListar = new Vendedor();
                vendedorListar.setCodvendedor(cursor.getString(cursor.getColumnIndex("codvendedor")));
                vendedorListar.setNomevendedor(cursor.getString(cursor.getColumnIndex("nomevendedor")));

                vendedorList.add(vendedorListar);
                try {
                    cursor.moveToNext();
                }catch (IllegalStateException i){
                    cursor.close();
                    cursor = vendedor.retornaVendedor(getContext());
                    cursor.moveToPosition(Integer.parseInt(cont.toString()));
                }
            }
            cursor.close();
            ArrayAdapter<Vendedor> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, vendedorList);
            listaVendedor.setAdapter(adapter);
            listaVendedor.setEmptyView(view.findViewById(R.id.semdados));
            listaVendedor.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            Vendedor vendedor = (Vendedor) listaVendedor.getItemAtPosition(position);
                            VendedorDados vendedorDados = new VendedorDados();
                            Bundle bundle = new Bundle();
                            bundle.putString("codigo", vendedor.getCodvendedor());
                            vendedorDados.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, vendedorDados, vendedorDados.getTag()).commit();
                        }
                    }
            );
            listaVendedor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final Vendedor vendedor = (Vendedor) listaVendedor.getItemAtPosition(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Confirm");
                    builder.setMessage("Confirma a exclusao do pedido " + vendedor.getCodvendedor() + "?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            MostraToast toast = new MostraToast();
                            boolean retorno = vendedor.removerVendedor(getContext(), vendedor.getCodvendedor());
                            if (retorno == true) {
                                toast.mostraToastShort(getContext(), "Vendedor excluido com sucesso");
                                VendedorFragment vendedorFragment = new VendedorFragment();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, vendedorFragment, vendedorFragment.getTag()).commit();
                            } else {
                                toast.mostraToastShort(getContext(), "Erro ao deletar Vendedor");
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
