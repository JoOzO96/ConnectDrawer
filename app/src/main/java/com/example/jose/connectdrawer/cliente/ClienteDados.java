package com.example.jose.connectdrawer.cliente;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jose.connectdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteDados extends Fragment {

    private EditText txCodigo;
    private EditText txNomeCliente;
    private EditText txCpfCnpj;

    public ClienteDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_dados, container, false);
        txCodigo = (EditText) view.findViewById(R.id.txCodigo);
        txNomeCliente = (EditText) view.findViewById(R.id.txNomeCliente);
        txCpfCnpj = (EditText) view.findViewById(R.id.txCpfCnpj);
        Bundle bundle = this.getArguments();
        Long codigoCliente = bundle.getLong("codigo");
        Cliente cliente = new Cliente();
        Cliente clienteFiltrado = cliente.retornaClienteFiltrado(getContext(), codigoCliente);
        txCodigo.setText(clienteFiltrado.getCodigo().toString());
        txNomeCliente.setText(clienteFiltrado.getNomeCliente());
        if (clienteFiltrado.getCpf().length() > 1) {
            txCpfCnpj.setText(clienteFiltrado.getNomeCliente());
        }else{
            txCpfCnpj.setText(clienteFiltrado.getCgc());
        }

        return view;
    }

}
