package com.example.jose.connectdrawer.cliente;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.Mascara;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteDados extends Fragment {

    private EditText txCodigo;
    private EditText txNomeCliente;
    private EditText txCpfCnpj;
    private EditText txIe;
    private EditText txEndereco;
    private EditText txBairro;
    private EditText txCep;
    private EditText txDataNasc;

    public ClienteDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        View view = inflater.inflate(R.layout.fragment_cliente_dados, container, false);
        txCodigo = (EditText) view.findViewById(R.id.txCodigo);
        txNomeCliente = (EditText) view.findViewById(R.id.txNomeCliente);
        txCpfCnpj = (EditText) view.findViewById(R.id.txCpfCnpj);
        txEndereco = (EditText) view.findViewById(R.id.txEndereco);
        txIe = (EditText) view.findViewById(R.id.txIe);
        txBairro = (EditText) view.findViewById(R.id.txBairro);
        txCep = (EditText) view.findViewById(R.id.txCep);
        txDataNasc = (EditText) view.findViewById(R.id.txDataNasc);
        Bundle bundle = this.getArguments();
        Long codigoCliente = bundle.getLong("codigo");
        Cliente cliente = new Cliente();
        Cliente clienteFiltrado = cliente.retornaClienteFiltrado(getContext(), codigoCliente);
        txCodigo.setText(clienteFiltrado.getCodigo().toString());
        txNomeCliente.setText(clienteFiltrado.getNomeCliente());
        txEndereco.setText(clienteFiltrado.getEndereco());
        if (clienteFiltrado.getCpf() != null) {
            txCpfCnpj.addTextChangedListener(Mascara.insert("###.###.###-##", txCpfCnpj));
            txCpfCnpj.setText(clienteFiltrado.getCpf());
        }else{
            txCpfCnpj.addTextChangedListener(Mascara.insert("##.###.###/####-##", txCpfCnpj));
            txCpfCnpj.setText(clienteFiltrado.getCgc());
        }
        if (clienteFiltrado.getDataNasc() != null){
            txDataNasc.addTextChangedListener(Mascara.insert("##/##/####", txDataNasc));
            String dataNasc = simpleDateFormat.format(clienteFiltrado.getDataNasc());
            txDataNasc.setText(dataNasc);
        }
        txBairro.setText(clienteFiltrado.getBairro());
        txCep.addTextChangedListener(Mascara.insert("##.###-###", txCep));
        txCep.setText(clienteFiltrado.getCep());
        txIe.setText(clienteFiltrado.getIncest());
        return view;
    }

}
