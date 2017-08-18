package com.example.jose.connectdrawer.Pedido;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProdutoTela;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteDados;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Button btAdicionarItens;
    private ListView listItenspedido;
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
        final View view = inflater.inflate(R.layout.fragment_pedido_dados, container, false);
        btAdicionarItens = (Button) view.findViewById(R.id.btAdicionaritens);
        listItenspedido = (ListView) view.findViewById(R.id.listItenspedido);
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<String> clienteList = new ArrayList<>();
        List<String> vendedorList = new ArrayList<>();
        List<String> formaPagamentoList = new ArrayList<>();
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
                    } else {
                        //TESTA SE O CAMPO QUE ESTA PASSANDO É UM EDITTEXT
                        if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {

                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("sp")) {
                            //TESTA SE O CAMPO QUE ESTA PASSANDO É UM SPINNER
                            //TESTA QUAL SPINNER É, E COLOCA OS DADOS REFERENTES A ELE
                            if (fieldListPassar.get(i).getName().toLowerCase().equals("spcliente")) {
                                //SPINNER DOS CLIENTES
                                Cliente cliente = new Cliente();
                                int posicao = 0;
                                Cursor cursorCliente = cliente.retornaCliente(getContext());
                                //TESTA SE A CONSULTA RETORNA ALGUM RESULTADO
                                if (cursorCliente.getCount() > 0) {
                                    //posição do spinner para sair selecionado
                                    List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
                                    for (int f = fieldListCliente.size() - 1; -1 != f; f--) {
                                        if (fieldListCliente.get(f).getName().toLowerCase().equals("codigo") || fieldListCliente.get(f).getName().toLowerCase().equals("nomecliente")) {

                                        } else {
                                            fieldListCliente.remove(f);
                                        }
                                    }
                                    cursor.moveToFirst();
                                    for (int j = 0; cursorCliente.getCount() != j; j++) {
                                        Cliente cliente1 = new Cliente();

                                        for (int f = 0; fieldListCliente.size() != f; f++) {

                                            String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                                            String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorCliente);
                                            if (retorno != null) {
                                                Object retCliente = getSetDinamico.insereField(fieldListCliente.get(f), cliente1, retorno);
                                                cliente1 = (Cliente) retCliente;
                                            }
                                        }
                                        clienteList.add(cliente1.toString());
                                        cursorCliente.moveToNext();
                                    }
                                }
                                for (int k = 0; clienteList.size() != k; k++) {
                                    Integer codHifen = clienteList.get(k).indexOf("-");
                                    String codCliente = clienteList.get(k).substring(0, codHifen - 1);
                                    if (codCliente.equals(cursor.getString(cursor.getColumnIndex("codcliente")))) {
                                        posicao = k;
                                        break;
                                    }
                                }
                                getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, clienteList, getContext(), posicao);
                            } else if (fieldListPassar.get(i).getName().toLowerCase().equals("spcodvendedor")) {
                                //SPINNER DOS Vendedores
                                Vendedor vendedor = new Vendedor();
                                int posicao = 0;
                                Cursor cursorVendedor = vendedor.retornaVendedor(getContext());
                                //TESTA SE A CONSULTA RETORNA ALGUM RESULTADO
                                if (cursorVendedor.getCount() > 0) {
                                    //posição do spinner para sair selecionado
                                    List<Field> fieldListVendedor = new ArrayList<>(Arrays.asList(Vendedor.class.getDeclaredFields()));
                                    for (int f = fieldListVendedor.size() - 1; -1 != f; f--) {
                                        if (fieldListVendedor.get(f).getName().toLowerCase().equals("codvendedor") || fieldListVendedor.get(f).getName().toLowerCase().equals("nomevendedor")) {
                                        } else {
                                            fieldListVendedor.remove(f);
                                        }
                                    }
                                    cursor.moveToFirst();
                                    for (int j = 0; cursorVendedor.getCount() != j; j++) {
                                        vendedor = new Vendedor();
                                        for (int f = 0; fieldListVendedor.size() != f; f++) {
                                            String tipo = getSetDinamico.retornaTipoCampo(fieldListVendedor.get(f));
                                            String nomeCampo = fieldListVendedor.get(f).getName().toLowerCase();
                                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorVendedor);
                                            if (retorno != null) {
                                                Object retVendedor = getSetDinamico.insereField(fieldListVendedor.get(f), vendedor, retorno);
                                                vendedor = (Vendedor) retVendedor;
                                            }
                                        }
                                        vendedorList.add(vendedor.toString());
                                        cursorVendedor.moveToNext();
                                    }
                                }
                                for (int k = 0; vendedorList.size() != k; k++) {
                                    Integer codHifen = vendedorList.get(k).indexOf("-");
                                    String codVendedor = vendedorList.get(k).substring(0, codHifen - 1);
                                    if (codVendedor.equals(cursor.getString(cursor.getColumnIndex("codvendedor")))) {
                                        posicao = k;
                                        break;
                                    }
                                }
                                getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, vendedorList, getContext(), posicao);
                            } else if (fieldListPassar.get(i).getName().toLowerCase().equals("spformadepagamento")) {
                                //SPINNER DOS FORMAS DE PAGAMENTO
                                FormaPagamento formaPagamento = new FormaPagamento();
                                int posicao = 0;
                                Cursor cursorFormaPagamento = formaPagamento.retornaFormaPagamento(getContext());
                                //TESTA SE A CONSULTA RETORNA ALGUM RESULTADO
                                if (cursorFormaPagamento.getCount() > 0) {
                                    //posição do spinner para sair selecionado
                                    List<Field> fieldListFormaPagamento = new ArrayList<>(Arrays.asList(FormaPagamento.class.getDeclaredFields()));
                                    for (int f = fieldListFormaPagamento.size() - 1; -1 != f; f--) {
                                        if (fieldListFormaPagamento.get(f).getName().toLowerCase().equals("codigo") || fieldListFormaPagamento.get(f).getName().toLowerCase().equals("pagamento")) {
                                        } else {
                                            fieldListFormaPagamento.remove(f);
                                        }
                                    }
                                    cursor.moveToFirst();
                                    for (int j = 0; cursorFormaPagamento.getCount() != j; j++) {
                                        formaPagamento = new FormaPagamento();
                                        for (int f = 0; fieldListFormaPagamento.size() != f; f++) {
                                            String tipo = getSetDinamico.retornaTipoCampo(fieldListFormaPagamento.get(f));
                                            String nomeCampo = fieldListFormaPagamento.get(f).getName().toLowerCase();
                                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorFormaPagamento);
                                            if (retorno != null) {
                                                Object retFormaPagamento = getSetDinamico.insereField(fieldListFormaPagamento.get(f), formaPagamento, retorno);
                                                formaPagamento = (FormaPagamento) retFormaPagamento;
                                            }
                                        }
                                        formaPagamentoList.add(formaPagamento.toString());
                                        cursorFormaPagamento.moveToNext();
                                    }
                                }
                                for (int k = 0; formaPagamentoList.size() != k; k++) {
                                    Integer codHifen = formaPagamentoList.get(k).indexOf("-");
                                    String codFormaPagamento = formaPagamentoList.get(k).substring(0, codHifen - 1);
                                    if (codFormaPagamento.equals(cursor.getString(cursor.getColumnIndex("formadepagamento")))) {
                                        posicao = k;
                                        break;
                                    }
                                }
                                getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, formaPagamentoList, getContext(), posicao);
                            }
                        }
                    }
                }


                // AQUI TESTA SE O PEDIDO TEM PRODUTOS, SE TIVER PRODUTOS ELE IRA POPULAR A LISTA

                PedidoProduto pedidoProduto = new PedidoProduto();

                Cursor cursorPedidoProduto = pedidoProduto.retornaItensPedido(getContext(), codigoPedido);
                List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
                if (cursorPedidoProduto.getCount() > 0) {

                    for (Long cont = 0L; cursorPedidoProduto.getCount() != cont; cont++) {
                        PedidoProduto pedidoProdutoListar = new PedidoProduto();
                        pedidoProdutoListar.setCodproduto(cursorPedidoProduto.getString(cursorPedidoProduto.getColumnIndex("codproduto")));
                        pedidoProdutoListar.setDescri(cursorPedidoProduto.getString(cursorPedidoProduto.getColumnIndex("descri")));
                        pedidoProdutoListar.setIdPedidoProduto(cursorPedidoProduto.getLong(cursorPedidoProduto.getColumnIndex("idPedidoProduto")));
                        pedidoProdutoList.add(pedidoProdutoListar);
                        try {
                            cursorPedidoProduto.moveToNext();
                        } catch (IllegalStateException i) {
                        }
                    }

                    ArrayAdapter<PedidoProduto> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pedidoProdutoList);
                    listItenspedido.setAdapter(adapter);
                } else {
                    //O PEDIDO NAO TEM NENHUM ITEM
                }


            } else {
                //CURSOR SEM INFORMAÇÕES
            }
        } else

        {
            // codigo do pedido é diferente de 1.
        }

        btAdicionarItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                PedidoProdutoTela pedidoProdutoTela = new PedidoProdutoTela();
                Bundle bundle = new Bundle();
                bundle.putString("codigo", "0");
                pedidoProdutoTela.setArguments(bundle);
                pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PedidoFragment pedidoFragment = new PedidoFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
            }
        });


        //PARTE AONDE PEGA O CLIQUE DO BOTAO PARA ADICIONAR O ITEM

        //PARTE AONDE PEGA O CLIQUE NA LISTA E ABRE A EDIÇÃO DO PRODUTO RESPECTIVO
        final FragmentManager fragmentManager = getFragmentManager();


        listItenspedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                PedidoProduto pedidoProduto = (PedidoProduto) listItenspedido.getItemAtPosition(position);
                PedidoProdutoTela pedidoProdutoTela = new PedidoProdutoTela();
                bundle.putLong("codigo", pedidoProduto.getIdPedidoProduto());
                bundle.putString("codigoClicado", pedidoProduto.getCodproduto());
                pedidoProdutoTela.setArguments(bundle);

                pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
            }
        });
        listItenspedido.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


        return view;
    }

}
