package com.example.jose.connectdrawer.Pedido;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteDados;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoDados extends Fragment {

    private EditText txpedido;
    private Spinner spcliente;
    private EditText txdata;
    private Spinner spcodvendedor;
    private Spinner spformadepagamento;
    //    private EditText txcodvendedor;
//    private EditText txformadepagamento;
//    private EditText txfrete;
//    private EditText txvalortotal;
//    private EditText txentrada;
//    private EditText txorpedi;
//    private EditText txcodbanco;
//    private EditText txobs;
//    private EditText txdesconto;
//    private EditText txnome;
//    private EditText txtotal;
//    private EditText txvenci1;
//    private EditText txvalor1;
//    private EditText txdias;
//    private EditText txjuro;
//    private EditText txsimnao;
//    private EditText txpgto;
//    private EditText txcheque;
//    private EditText txdata1;
//    private EditText txnotafisca;
//    private EditText txvia;
//    private EditText txbaixa;
//    private EditText txveiculo;
//    private EditText txplaca;
//    private EditText txano;
//    private EditText txnparce;
//    private EditText txcodinstituicao;
//    private EditText txnfc;
//    private EditText txcodcaixa;
//    private EditText txservicosolicitado;
    private Button btSalvar;
    private Button btCancelar;


    public PedidoDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedido_dados, container, false);
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<String> clienteList = new ArrayList<>();
        Pedido pedido = new Pedido();
        //PEGA AS IDS DOS CAMPOS NOS FORMULARIOS
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);


        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        Long codigoPedido = bundle.getLong("codigo");


        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoDados.class.getDeclaredFields()));

        if (codigoPedido > 1) {
            // codigo do pedido e maior que um, no caso, veio da lista.
            Cursor cursor = pedido.retornaPedidoFiltradaCursor(getContext(), codigoPedido);
            //TESTA SE O CURSOR RETORNOU ALGUM DADO
            if (cursor.getCount() > 0) {
                //CURSOR CONTEM UMA OU MAIS LINHA DE INFORMAÇÕES
                for (int i = 0; fieldListPassar.size() != i; i++) {

                    String mascara = null;
                    if (fieldListPassar.get(i).getName().toLowerCase().equals("$change") ||
                            fieldListPassar.get(i).getName().toLowerCase().equals("serialversionuid")) {
                    }else{
                        //TESTA SE O CAMPO QUE ESTA PASSANDO É UM EDITTEXT
                        if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {

                        }else if (fieldListPassar.get(i).getName().substring(0, 2).equals("sp")) {
                            //TESTA SE O CAMPO QUE ESTA PASSANDO É UM SPINNER
                            //TESTA QUAL SPINNER É, E COLOCA OS DADOS REFERENTES A ELE
                            if (fieldListPassar.get(i).getName().toLowerCase().equals("spcliente")){
                                //SPINNER DOS CLIENTES
                                Cliente cliente = new Cliente();
                                int posicao = 0;
                                Cursor cursorCliente = cliente.retornaCliente(getContext());
                                //TESTA SE A CONSULTA RETORNA ALGUM RESULTADO
                                if (cursorCliente.getCount()>0){
                                    //posição do spinner para sair selecionado

                                    cursor.moveToFirst();
                                    for (int j = 0; cursorCliente.getCount() != j; j++) {
                                        Cliente cliente1 = new Cliente();
                                        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
                                        for (int f = fieldListCliente.size() -1; 0 != f; f--) {
                                            if (fieldListCliente.get(f).getName().toLowerCase().equals("codigo") || fieldListCliente.get(f).getName().toLowerCase().equals("nomecliente")){

                                            }else{
                                                fieldListCliente.remove(f);
                                            }
                                        }


                                        for (int f = 0; fieldListCliente.size() != f; f++) {

                                            String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                                            String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorCliente);
                                            if (retorno != null) {
                                                Object retCliente = getSetDinamico.insereField(fieldListCliente.get(f), cliente1, retorno);
                                                cliente1 = (Cliente) retCliente;
                                            }


                                        }
                                        cursorCliente.moveToNext();
                                        clienteList.add(cliente1.toString());
                                        if (cursor.getString(cursor.getColumnIndex("codcliente")).equals(cliente1.getCodigo().toString())) {
                                            for (int k = 0; clienteList.size() != k; k++) {
                                                if (clienteList.get(k).equals(clienteList.toString())) {
                                                    posicao = k;
                                                }
                                            }
                                        }

                                    }
                                }
                                getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, clienteList, getContext(), posicao);
                            }
                        }
                    }
                }
            } else {
                //CURSOR SEM INFORMAÇÕES
            }
        } else

        {
            // codigo do pedido é diferente de 1.
        }

        return view;
    }

}
