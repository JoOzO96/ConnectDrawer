package com.example.jose.connectdrawer.Pedido;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProdutoTela;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoDados extends Fragment {

    private EditText txPedido;
    private Spinner spCodcliente;
    //    private EditText txdata;
    private Spinner spCodvendedor;
    private Spinner spFormadepagamento;
    private Button btAdicionarItens;
    private ListView listItenspedido;
    private TextView porc_lucro;
    private Long codClienteTela;
    private Double comissaoVendedor;
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
        porc_lucro = (TextView) view.findViewById(R.id.porc_lucro);
        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        final GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<String> clienteList = new ArrayList<>();
        List<String> vendedorList = new ArrayList<>();
        List<String> formaPagamentoList = new ArrayList<>();
        final Pedido pedido = new Pedido();
        Double porcentagem_lucro = 0D;
        //PEGA AS IDS DOS CAMPOS NOS FORMULARIOS
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);


        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        Long codigoPedido = bundle.getLong("codigo");


        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        final List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoDados.class.getDeclaredFields()));

        if (codigoPedido >= 1) {
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

                            String tipo = getSetDinamico.retornaTipoCampo(fieldListPassar.get(i));
                            String nomecampo = "";
                            nomecampo = fieldListPassar.get(i).getName().replace("tx", "").toLowerCase();
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                            mascara = null;
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, retorno.toString(), mascara);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, "", mascara);
                            }

                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("sp")) {
                            //TESTA SE O CAMPO QUE ESTA PASSANDO É UM SPINNER
                            //TESTA QUAL SPINNER É, E COLOCA OS DADOS REFERENTES A ELE
                            if (fieldListPassar.get(i).getName().equals("spCodcliente")) {
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
                                getSetDinamicoTelas.colocaValorSpinnerColorido(fieldListPassar.get(i), view, clienteList, getContext(), posicao);
                            } else if (fieldListPassar.get(i).getName().equals("spCodvendedor")) {
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
                            } else if (fieldListPassar.get(i).getName().equals("spFormadepagamento")) {
                                //SPINNER DOS FORMAS DE PAGAMENTO
                                FormaPagamento formaPagamento = new FormaPagamento();
                                int posicao = 0;
                                Cursor cursorFormaPagamento = FormaPagamento.retornaFormaPagamento(getContext());
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
                Double custo = 0D;
                Double valorVenda = 0D;
                Cursor cursorPedidoProduto = pedidoProduto.retornaItensPedido(getContext(), codigoPedido);
                List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
                if (cursorPedidoProduto.getCount() > 0) {

                    for (Long cont = 0L; cursorPedidoProduto.getCount() != cont; cont++) {
                        PedidoProduto pedidoProdutoListar = new PedidoProduto();
                        pedidoProdutoListar.setCodproduto(cursorPedidoProduto.getString(cursorPedidoProduto.getColumnIndex("codproduto")));
                        pedidoProdutoListar.setDescri(cursorPedidoProduto.getString(cursorPedidoProduto.getColumnIndex("descri")));
                        pedidoProdutoListar.setIdPedidoProduto(cursorPedidoProduto.getLong(cursorPedidoProduto.getColumnIndex("idPedidoProduto")));
                        pedidoProdutoListar.setCusto(cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("custo")));
                        custo += cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("custo"));
                        valorVenda += cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("valorunitario"));
                        pedidoProdutoList.add(pedidoProdutoListar);
                        try {
                            cursorPedidoProduto.moveToNext();
                        } catch (IllegalStateException i) {
                        }
                    }
                    porcentagem_lucro = valorVenda * 100 / custo - 100;
                    porc_lucro.setText(porcentagem_lucro.shortValue() + "%");
                    ArrayAdapter<PedidoProduto> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pedidoProdutoList);
                    listItenspedido.setAdapter(adapter);
                } else {
                    //O PEDIDO NAO TEM NENHUM ITEM
                    porc_lucro.setText(porcentagem_lucro + "%");
                }


            } else {
                //CURSOR SEM INFORMAÇÕES
            }
        } else {
            // codigo do pedido é diferente de 1. NO CASO QUANDO O PEDIDO E NOVO

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
                        if (fieldListPassar.get(i).getName().equals("spCodcliente")) {
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

                            getSetDinamicoTelas.colocaValorSpinnerColorido(fieldListPassar.get(i), view, clienteList, getContext(), posicao);

                        } else if (fieldListPassar.get(i).getName().equals("spCodvendedor")) {
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
                            getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, vendedorList, getContext(), posicao);
                        } else if (fieldListPassar.get(i).getName().equals("spFormadepagamento")) {
                            //SPINNER DOS FORMAS DE PAGAMENTO
                            FormaPagamento formaPagamento = new FormaPagamento();
                            int posicao = 0;
                            Cursor cursorFormaPagamento = FormaPagamento.retornaFormaPagamento(getContext());
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
                            getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, formaPagamentoList, getContext(), posicao);
                        }
                    }
                }
            }

        }

        btAdicionarItens.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                PedidoProdutoTela pedidoProdutoTela = new PedidoProdutoTela();
                txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");

                if (txPedido.length() == 0) {
                    SalvaPedido(view, -1L);
                    Bundle bundle = new Bundle();
                    bundle.putDouble("comissaoVendedor", comissaoVendedor);
                    bundle.putString("codigoClicado", "");
                    bundle.putLong("idPedidoProduto", -1L);
                    bundle.putLong("codigocliente", codClienteTela);
                    bundle.putString("codigoPedido", String.valueOf(pedido.retornaMaiorCod(getContext())));
                    pedidoProdutoTela.setArguments(bundle);
                    pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
                } else {
                    SalvaPedido(view, 1L);
                    Bundle bundle = new Bundle();
                    bundle.putString("codigo", "0");
                    bundle.putDouble("comissaoVendedor", comissaoVendedor);
                    bundle.putString("codigoClicado", "");
                    bundle.putLong("idPedidoProduto", -1L);
                    bundle.putLong("codigocliente", codClienteTela);
                    bundle.putString("codigoPedido", txPedido.getText().toString());
                    pedidoProdutoTela.setArguments(bundle);
                    pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener()

        {
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


        listItenspedido.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                PedidoProduto pedidoProduto = (PedidoProduto) listItenspedido.getItemAtPosition(position);
                PedidoProdutoTela pedidoProdutoTela = new PedidoProdutoTela();
                bundle.putLong("codigo", pedidoProduto.getIdPedidoProduto());
                bundle.putString("codigoClicado", pedidoProduto.getCodproduto());
                bundle.putLong("idPedidoProduto", pedidoProduto.getIdPedidoProduto());
                txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");
                bundle.putString("codigoPedido", txPedido.getText().toString());
                pedidoProdutoTela.setArguments(bundle);

                pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
            }
        });
        listItenspedido.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()

        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view1, int position,
                                           long id) {
                final PedidoProduto pedidoProduto = (PedidoProduto) listItenspedido.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Confirma a exclusao do item " + pedidoProduto.getDescri() + "?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        MostraToast toast = new MostraToast();
                        txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");
                        boolean retorno = pedidoProduto.removerPedidoProduto(getContext(), pedidoProduto.getIdPedidoProduto());
                        if (retorno == true) {
                            toast.mostraToastShort(getContext(), "Item excluido com sucesso");
                            PedidoDados pedidoDados = new PedidoDados();
                            Bundle bundle = new Bundle();
                            bundle.putLong("codigo", Long.parseLong(txPedido.getText().toString()));
                            pedidoDados.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
                        } else {
                            toast.mostraToastShort(getContext(), "Erro ao deletar item");
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


        btSalvar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                SalvaPedido(view, 1L);
            }
        });

        return view;
    }


    public void SalvaPedido(View view, Long clique) {
        if (clique == 1L) {
            //fieldListPedidoDados CONTEM OS DADOS DA TELA DO SISTEMA
            List<Field> fieldListPedidoDados = new ArrayList<>(Arrays.asList(PedidoDados.class.getDeclaredFields()));
            //fieldListPedido CONTEM OS DADOS DO OBJETO PEDIDO QUE SERA SALVO NO BANCO
            List<Field> fieldListPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
            Pedido pedido = new Pedido();
            for (int f = 0; fieldListPedidoDados.size() != f; f++) {

                if (fieldListPedidoDados.get(f).getName().toLowerCase().substring(0, 2).equals("sp")) {
                    String nomeCampo = "";
                    nomeCampo = fieldListPedidoDados.get(f).getName();
                    if (nomeCampo.equals("spCodcliente")) {
                        nomeCampo = "codcliente";
                    } else if (nomeCampo.equals("spFormadepagamento")) {
                        nomeCampo = "formadepagamento";
                    } else if (nomeCampo.equals("spCodvendedor")) {
                        nomeCampo = "codvendedor";
                    }
                    if (!nomeCampo.equals("")) {

                        for (int p = 0; fieldListPedido.size() != p; p++) {

                            if (fieldListPedido.get(p).getName().toLowerCase().equals(nomeCampo)) {
                                Object retorno = getSetDinamicoTelas.retornaValorSpinner(view, fieldListPedido.get(p).getName().replace("sp", ""));
                                Object retornoPedido = getSetDinamico.insereField(fieldListPedido.get(p), pedido, retorno);
                                pedido = (Pedido) retornoPedido;
                                break;
                            }
                        }

                    } else {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Erro ao recuperar dados do pedido.");
                    }
                } else if (fieldListPedidoDados.get(f).getName().toLowerCase().substring(0, 2).equals("tx")) {

                    String nomeCampo = "";
                    nomeCampo = fieldListPedidoDados.get(f).getName();
                    String valorCampo = "";
                    if (!nomeCampo.equals("")) {

                        for (int p = 0; fieldListPedido.size() != p; p++) {
                            if (fieldListPedido.get(p).getName().equals(nomeCampo.replace("tx", "").toLowerCase())) {
                                valorCampo = getSetDinamicoTelas.retornaValorEditText(view, fieldListPedido.get(p).getName());
                                Object retorno = getSetDinamico.insereField(fieldListPedido.get(p), pedido, valorCampo);
                                pedido = (Pedido) retorno;
                                break;
                            }
                        }

                    }
                }
            }
            Cliente cliente = new Cliente();
            codClienteTela = pedido.getCodcliente();
            Cursor cursorCliente = cliente.retornaClienteFiltradoCursor(getContext(), pedido.getCodcliente());
            List<Field> clienteFieldList = new ArrayList<Field>(Arrays.asList(cliente.getClass().getDeclaredFields()));
            if (cursorCliente.getCount() > 0) {

                for (int f = 0; clienteFieldList.size() != f; f++) {

                    String tipo = getSetDinamico.retornaTipoCampo(clienteFieldList.get(f));
                    String nomeCampo = clienteFieldList.get(f).getName().toLowerCase();
                    Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorCliente);
                    if (retorno != null) {
                        Object retCliente = getSetDinamico.insereField(clienteFieldList.get(f), cliente, retorno);
                        cliente = (Cliente) retCliente;
                    }
                }

            }
            pedido.setNome(cliente.getNomecliente());
            pedido.setData(new Date().getTime());
            boolean retorno = pedido.cadastraPedido(getContext(), pedido);

            Vendedor vendedor = new Vendedor();
            Cursor cursorVendedor = vendedor.retornaVendedorFiltradaCursor(getContext(), pedido.getCodvendedor());
            if (cursorVendedor.getCount() > 0){
                comissaoVendedor = cursorVendedor.getDouble(cursorVendedor.getColumnIndex("comi"));
            }
            if (retorno) {
                if (clique == 1) {
                    if (txPedido == null) {
                        pedido.setPedido(pedido.retornaMaiorCod(getContext()));
                    }
                    if (pedido.getPedido() > 0) {
                        PedidoFragment pedidoFragment = new PedidoFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Pedido atualizado com sucesso.");

                    } else {
                        PedidoFragment pedidoFragment = new PedidoFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Pedido salvo com sucesso.");
                    }
                }
            } else {
                PedidoFragment pedidoFragment = new PedidoFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastShort(getContext(), "Erro ao cadastrar o pedido.");
            }
            comissaoVendedor = cursorVendedor.getDouble(cursorVendedor.getColumnIndex("comi"));

        } else {


            //fieldListPedidoDados CONTEM OS DADOS DA TELA DO SISTEMA
            List<Field> fieldListPedidoDados = new ArrayList<>(Arrays.asList(PedidoDados.class.getDeclaredFields()));
            //fieldListPedido CONTEM OS DADOS DO OBJETO PEDIDO QUE SERA SALVO NO BANCO
            List<Field> fieldListPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
            Pedido pedido = new Pedido();
            for (int f = 0; fieldListPedidoDados.size() != f; f++) {

                if (fieldListPedidoDados.get(f).getName().toLowerCase().substring(0, 2).equals("sp")) {
                    String nomeCampo = "";
                    nomeCampo = fieldListPedidoDados.get(f).getName();
                    if (nomeCampo.equals("spCodcliente")) {
                        nomeCampo = "codcliente";
                    } else if (nomeCampo.equals("spFormadepagamento")) {
                        nomeCampo = "formadepagamento";
                    } else if (nomeCampo.equals("spCodvendedor")) {
                        nomeCampo = "codvendedor";
                    }
                    if (!nomeCampo.equals("")) {

                        for (int p = 0; fieldListPedido.size() != p; p++) {

                            if (fieldListPedido.get(p).getName().toLowerCase().equals(nomeCampo)) {
                                Object retorno = getSetDinamicoTelas.retornaValorSpinner(view, fieldListPedido.get(p).getName().replace("sp", ""));
                                Object retornoPedido = getSetDinamico.insereField(fieldListPedido.get(p), pedido, retorno);
                                pedido = (Pedido) retornoPedido;
                                break;
                            }
                        }

                    } else {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Erro ao recuperar dados do pedido.");
                    }
                } else if (fieldListPedidoDados.get(f).getName().toLowerCase().substring(0, 2).equals("tx")) {

                    String nomeCampo = "";
                    nomeCampo = fieldListPedidoDados.get(f).getName();
                    String valorCampo = "";
                    if (!nomeCampo.equals("")) {

                        for (int p = 0; fieldListPedido.size() != p; p++) {
                            if (fieldListPedido.get(p).getName().equals(nomeCampo.replace("tx", "").toLowerCase())) {
                                valorCampo = getSetDinamicoTelas.retornaValorEditText(view, fieldListPedido.get(p).getName());
                                Object retorno = getSetDinamico.insereField(fieldListPedido.get(p), pedido, valorCampo);
                                pedido = (Pedido) retorno;
                                break;
                            }
                        }

                    }
                }
            }
            Cliente cliente = new Cliente();
            codClienteTela = pedido.getCodcliente();
            Cursor cursorCliente = cliente.retornaClienteFiltradoCursor(getContext(), pedido.getCodcliente());
            List<Field> clienteFieldList = new ArrayList<Field>(Arrays.asList(cliente.getClass().getDeclaredFields()));
            if (cursorCliente.getCount() > 0) {

                for (int f = 0; clienteFieldList.size() != f; f++) {

                    String tipo = getSetDinamico.retornaTipoCampo(clienteFieldList.get(f));
                    String nomeCampo = clienteFieldList.get(f).getName().toLowerCase();
                    Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorCliente);
                    if (retorno != null) {
                        Object retCliente = getSetDinamico.insereField(clienteFieldList.get(f), cliente, retorno);
                        cliente = (Cliente) retCliente;
                    }
                }

            }
            pedido.setNome(cliente.getNomecliente());
            pedido.setData(new Date().getTime());
            boolean retorno = pedido.cadastraPedido(getContext(), pedido);

            Vendedor vendedor = new Vendedor();
            Cursor cursorVendedor = vendedor.retornaVendedorFiltradaCursor(getContext(), pedido.getCodvendedor());
            if (cursorVendedor.getCount() > 0){
                comissaoVendedor = cursorVendedor.getDouble(cursorVendedor.getColumnIndex("comi"));
            }
            if (retorno) {
                if (clique == 1) {
                    if (txPedido == null) {
                        pedido.setPedido(pedido.retornaMaiorCod(getContext()));
                    }
                    if (pedido.getPedido() > 0) {
                        PedidoFragment pedidoFragment = new PedidoFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Pedido atualizado com sucesso.");

                    } else {
                        PedidoFragment pedidoFragment = new PedidoFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Pedido salvo com sucesso.");
                    }
                }
            } else {
                PedidoFragment pedidoFragment = new PedidoFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastShort(getContext(), "Erro ao cadastrar o pedido.");
            }


        }
    }

}