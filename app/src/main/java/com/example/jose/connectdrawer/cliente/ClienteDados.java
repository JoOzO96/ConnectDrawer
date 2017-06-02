package com.example.jose.connectdrawer.cliente;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.Mascara;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private Spinner spCidade;

    public ClienteDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Cidade cidade = new Cidade();
        List<Cidade> cidadeList = new ArrayList<>();
        Cursor cursor = cidade.retornaCidade(getContext());
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; cursor.getCount() != i; i++) {
                Cidade cidade1 = new Cidade();
                Field[] fields = cidade1.getClass().getDeclaredFields();
                List<Field> fieldList = new ArrayList<>(Arrays.asList(fields));

                for (int f = 0; fieldList.size() != f; f++) {

                    String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                    String nomeCampo = fieldList.get(f).getName();
                    if (nomeCampo.equals("$change") || nomeCampo.equals("serialVersionUID")) {

                    } else {
                        if (nomeCampo.equals("UF")) {
                            nomeCampo = "uf";
                        }
                        Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);

                        Object teste = getSetDinamico.insereField(fieldList.get(f), cidade1, retorno);
                        cidade1 = (Cidade) teste;
                    }
                }
                cursor.moveToNext();
                //aqui tenho a cidade completa;
                Log.e("CIDADE", cidade1.toString());
            }
        }
        View view = inflater.inflate(R.layout.fragment_cliente_dados, container, false);
        txCodigo = (EditText) view.findViewById(R.id.txCodigo);
        txNomeCliente = (EditText) view.findViewById(R.id.txNomeCliente);
        txCpfCnpj = (EditText) view.findViewById(R.id.txCpfCnpj);
        txEndereco = (EditText) view.findViewById(R.id.txEndereco);
        txIe = (EditText) view.findViewById(R.id.txIe);
        txBairro = (EditText) view.findViewById(R.id.txBairro);
        txCep = (EditText) view.findViewById(R.id.txCep);
        spCidade = (Spinner) view.findViewById(R.id.spCidade);
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
        } else {
            txCpfCnpj.addTextChangedListener(Mascara.insert("##.###.###/####-##", txCpfCnpj));
            txCpfCnpj.setText(clienteFiltrado.getCgc());
        }
        if (clienteFiltrado.getCodCidade() != null) {

        }
        txBairro.setText(clienteFiltrado.getBairro());
        txCep.addTextChangedListener(Mascara.insert("##.###-###", txCep));
        txCep.setText(clienteFiltrado.getCep());
        txIe.setText(clienteFiltrado.getIncest());
        return view;
    }

}
