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
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
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
                List<Field> fieldList = new ArrayList<>(Arrays.asList(cidade1.getClass().getDeclaredFields()));

                for (int f = 0; fieldList.size() != f; f++) {

                    String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                    String nomeCampo = fieldList.get(f).getName().toLowerCase();
                    Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                    if (retorno != null) {
                        Object teste = getSetDinamico.insereField(fieldList.get(f), cidade1, retorno);
                        cidade1 = (Cidade) teste;
                    }

                }
                cursor.moveToNext();
                cidadeList.add(cidade1);
            }
        }
        View view = inflater.inflate(R.layout.fragment_cliente_dados, container, false);
        Cliente cliente = new Cliente();
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        ClienteDados clienteDados = new ClienteDados();
        //PEGA A LISTA DE CAMPOS DA CLASSE
        List<Field> fieldList = new ArrayList<>(Arrays.asList(clienteDados.getClass().getDeclaredFields()));
        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        Long codigoCliente = bundle.getLong("codigo");
        boolean cpfcnpj = false;
        cursor = cliente.retornaClienteFiltrado(getContext(), codigoCliente);
        if (cursor.getCount() > 0) {
            for (int i = 0; fieldList.size() != i; i++) {
                if (fieldList.get(i).getName().toLowerCase().equals("$change") ||
                        fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                } else {
                    if (fieldList.get(i).getName().substring(0, 2).equals("tx")) {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(i));
                        String nomecampo = "";
                        nomecampo = fieldList.get(i).getName().replace("tx", "").toLowerCase();
                        if (nomecampo.equals("cpfcnpj")) {
                            if (cursor.getString(cursor.getColumnIndex("cpj")).length() > 0) {
                                Object retorno = getSetDinamico.retornaValorCursor(tipo, "cpf", cursor);
                                if (retorno != null) {
                                    getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, retorno.toString(), null);
                                } else {
                                    getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, "", null);
                                }
                            }else{
                                Object retorno = getSetDinamico.retornaValorCursor(tipo, "cnpj", cursor);
                                if (retorno != null) {
                                    getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, retorno.toString(), null);
                                } else {
                                    getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, "", null);
                                }
                            }
                        } else {
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, retorno.toString(), null);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, "", null);
                            }
                        }
                    }
                }
            }
        }


//        txCodigo = (EditText) view.findViewById(R.id.txCodigo);
//        txNomeCliente = (EditText) view.findViewById(R.id.txNomeCliente);
//        txCpfCnpj = (EditText) view.findViewById(R.id.txCpfCnpj);
//        txEndereco = (EditText) view.findViewById(R.id.txEndereco);
//        txIe = (EditText) view.findViewById(R.id.txIe);
//        txBairro = (EditText) view.findViewById(R.id.txBairro);
//        txCep = (EditText) view.findViewById(R.id.txCep);
//        spCidade = (Spinner) view.findViewById(R.id.spCidade);
//        Bundle bundle = this.getArguments();
//        Long codigoCliente = bundle.getLong("codigo");
//        Cliente cliente = new Cliente();
//        Cliente clienteFiltrado = cliente.retornaClienteFiltrado(getContext(), codigoCliente);
//        txCodigo.setText(clienteFiltrado.getCodigo().toString());
//        txNomeCliente.setText(clienteFiltrado.getNomecliente());
//        txEndereco.setText(clienteFiltrado.getEndereco());
//        if (clienteFiltrado.getCpf() != null) {
//            txCpfCnpj.addTextChangedListener(Mascara.insert("###.###.###-##", txCpfCnpj));
//            txCpfCnpj.setText(clienteFiltrado.getCpf());
//        } else {
//            txCpfCnpj.addTextChangedListener(Mascara.insert("##.###.###/####-##", txCpfCnpj));
//            txCpfCnpj.setText(clienteFiltrado.getCgc());
//        }
//        if (clienteFiltrado.getCodcidade() != null) {
//
//        }
//        txBairro.setText(clienteFiltrado.getBairro());
//        txCep.addTextChangedListener(Mascara.insert("##.###-###", txCep));
//        txCep.setText(clienteFiltrado.getCep());
//        txIe.setText(clienteFiltrado.getIncest());
        return view;
    }

}
