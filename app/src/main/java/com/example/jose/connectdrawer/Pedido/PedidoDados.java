package com.example.jose.connectdrawer.Pedido;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;

import com.example.jose.connectdrawer.A7.Bluetooth;
import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.Email.CriaEmail;
import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.ImprimirTexto;
import com.example.jose.connectdrawer.NotaFiscal.NotaFiscal;
import com.example.jose.connectdrawer.NotaFiscal.NotaFiscalService;
import com.example.jose.connectdrawer.NotaProduto.NotaProduto;
import com.example.jose.connectdrawer.Parcelas.ParcelasDados;
import com.example.jose.connectdrawer.ParcelasNFE.ParcelaNFE;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProdutoTela;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.sincronizacao.EnviaJson;
import com.example.jose.connectdrawer.sincronizacao.RetRetrofit;
import com.example.jose.connectdrawer.sincronizacao.SincMac;
import com.example.jose.connectdrawer.uteis.CriaImpressao;
import com.example.jose.connectdrawer.uteis.DateDeserializer;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoDados extends Fragment {
    //    // Message types sent from the BluetoothService Handler
//    public static final int MESSAGE_STATE_CHANGE = 1;
//    public static final int MESSAGE_READ = 2;
//    public static final int MESSAGE_WRITE = 3;
//    public static final int MESSAGE_DEVICE_NAME = 4;
//    public static final int MESSAGE_TOAST = 5;
//    public static final int MESSAGE_CONNECTION_LOST = 6;
//    public static final int MESSAGE_UNABLE_CONNECT = 7;
    public static final int CENTRALIZADO = 1;
    public static final int ESQUERDA = 0;
    public static final int DIREITA = 2;
    public static final int BORDAS = 3;
    //    String address = "0F:02:17:70:78:22";
//    private static final String THAI = "CP874";
//    private BluetoothService mService = null;
//    private BluetoothAdapter mBluetoothAdapter = null;
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
    private Button btGerarNfe;
    private Button btGerarParcelas;

    public PedidoDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final MostraToast mostraToast = new MostraToast();
        Banco myDb = new Banco(getContext());
        SQLiteDatabase db = myDb.getReadableDatabase();
        final View view = inflater.inflate(R.layout.fragment_pedido_dados, container, false);
        btAdicionarItens = (Button) view.findViewById(R.id.btAdicionaritens);
        btGerarNfe = (Button) view.findViewById(R.id.btgerarnfe);
        btGerarParcelas = (Button) view.findViewById(R.id.btgerarparcelas);
        listItenspedido = (ListView) view.findViewById(R.id.listItenspedido);
        porc_lucro = (TextView) view.findViewById(R.id.porc_lucro);
        spFormadepagamento = (Spinner) view.findViewById(R.id.spFormadepagamento);
        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        final GetSetDinamico getSetDinamico = new GetSetDinamico();
        List<String> clienteList = new ArrayList<>();
        List<String> vendedorList = new ArrayList<>();
        final List<String> formaPagamentoList = new ArrayList<>();
        final Pedido pedido = new Pedido();
        Double porcentagem_lucro = 0D;
        txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");
        //PEGA AS IDS DOS CAMPOS NOS FORMULARIOS
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);
        final Context context = getContext();
        final Intent abreTelaImpressao = new Intent(getContext(), ImprimirTexto.class);

        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        final Bundle bundle = this.getArguments();
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
                                Cursor cursorCliente = cliente.retornaCliente(db);
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
                                    db.close();
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
                        pedidoProdutoListar.setIdpedidoproduto(cursorPedidoProduto.getLong(cursorPedidoProduto.getColumnIndex("idpedidoproduto")));
                        pedidoProdutoListar.setCusto(cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("custo")));
                        custo += cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("custo"));
                        valorVenda += cursorPedidoProduto.getDouble(cursorPedidoProduto.getColumnIndex("valorunitario"));
                        pedidoProdutoList.add(pedidoProdutoListar);
                        try {
                            cursorPedidoProduto.moveToNext();
                        } catch (IllegalStateException i) {
                            CriaEmail criaEmail = new CriaEmail();
                            Mac mac = new Mac();
                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), i.getMessage());
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
                            Cursor cursorCliente = cliente.retornaCliente(db);
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
                            db.close();
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

        btAdicionarItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                PedidoProdutoTela pedidoProdutoTela = new PedidoProdutoTela();


                if (txPedido.length() == 0) {

                    Integer retorno = SalvaPedido(view, -1L, false);
                    if (retorno < 0) {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Erro ao salvar dados do pedido.");
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putDouble("comissaoVendedor", comissaoVendedor);
                        bundle.putString("codigoClicado", "");
                        bundle.putLong("idPedidoProduto", -1L);
                        bundle.putLong("codigocliente", codClienteTela);
                        bundle.putString("codigoPedido", String.valueOf(pedido.retornaMaiorCod(getContext())));
                        pedidoProdutoTela.setArguments(bundle);
                        pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
                    }
                } else {
                    Integer retorno = SalvaPedido(view, 1L, false);
                    if (retorno < 0) {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Erro ao salvar dados do pedido.");
                    } else {

                        Bundle bundle = new Bundle();
                        bundle.putLong("codigo", 0L);
                        bundle.putDouble("comissaoVendedor", comissaoVendedor);
                        bundle.putString("codigoClicado", "");
                        bundle.putLong("idPedidoProduto", -1L);
                        bundle.putLong("codigocliente", codClienteTela);
                        bundle.putString("codigoPedido", txPedido.getText().toString());
                        pedidoProdutoTela.setArguments(bundle);
                        pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
                    }
                }
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
        spFormadepagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String pgto = formaPagamentoList.get(i);
                FormaPagamento formaPagamento = new FormaPagamento();

                formaPagamento = formaPagamento.retornaFormaPagamentoObjeto(getContext(), Long.parseLong(pgto.substring(0, pgto.indexOf("-") - 1)));

                if (formaPagamento.getPrazo() == true) {
                    btGerarParcelas.setVisibility(View.VISIBLE);
                } else {
                    btGerarParcelas.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btGerarNfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SalvaPedido(view, 2L, false);
                    NotaFiscal notaFiscal = new NotaFiscal();
                    RetRetrofit retRetrofit = new RetRetrofit();
                    SincMac sincMac = new SincMac();
                    String ip = sincMac.iniciasinc(getContext());
                    ControleCodigo controleCodigo1 = null;
                    if (ip != null) {
                        Retrofit retrofit = retRetrofit.retornaRetrofit(ip);
                        NotaFiscalService notaFiscalService = retrofit.create(NotaFiscalService.class);
                        final Call<ControleCodigo> requeControleCodigo = notaFiscalService.notafiscal();
                        final Response<ControleCodigo>[] response = new Response[]{null};
                        Thread thread = new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            response[0] = requeControleCodigo.execute();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        }
                                    }
                                }
                        );
                        thread.setPriority(Thread.MAX_PRIORITY);
                        thread.start();

                        try {
                            thread.join(120000);

                            if (thread.isAlive()) {
                                thread.interrupt();
                                MostraToast mostraToast = new MostraToast();
                                mostraToast.mostraToastLong(Sessao.retornaContext(), "Erro ao consultar dados da sequencia da NF-e.");
                            }

                            if (response[0].body() != null) {
                                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

                                controleCodigo1 = response[0].body();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            CriaEmail criaEmail = new CriaEmail();
                            Mac mac = new Mac();
                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            CriaEmail criaEmail = new CriaEmail();
                            Mac mac = new Mac();
                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                        }

                        if (controleCodigo1 != null) {
                            txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");
                            notaFiscal = (NotaFiscal) getSetDinamico.colocaDadosNotaFiscal(getContext(), notaFiscal, txPedido.getText().toString(), controleCodigo1.getCodigoBanco() + 1);

                            if (!notaFiscal.getNomecliente().equals("NOTA JA ENVIADA")) {

                                List<NotaFiscal> listaNotaFiscal = new ArrayList<>();
                                listaNotaFiscal.add(notaFiscal);
                                EnviaJson enviaJson = new EnviaJson();
                                String url = "http://192.168.0.199:45455/api/notafiscal";
                                List<ControleCodigo> retorno = null;
                                String retornoEnvio = "";
                                GsonBuilder gsonBuilder = new GsonBuilder()
                                        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss");
                                gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
                                Gson gson = gsonBuilder.create();
                                String gsonRetorno = gson.toJson(listaNotaFiscal);
                                try {
                                    enviaJson.execute(gsonRetorno, url);
                                    retornoEnvio = enviaJson.get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    CriaEmail criaEmail = new CriaEmail();
                                    Mac mac = new Mac();
                                    criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                    CriaEmail criaEmail = new CriaEmail();
                                    Mac mac = new Mac();
                                    criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                }
                                ControleCodigo controleCodigo[] = gson.fromJson(retornoEnvio, ControleCodigo[].class);

                                if (controleCodigo[0].getMensagem() != null) {
                                    if (!controleCodigo[0].getMensagem().equals("NF-e atualizada, tentando novo envio.")) {
                                        mostraToast.mostraToastLong(context, controleCodigo[0].getMensagem());
                                    } else {
                                        NotaProduto notaProduto = new NotaProduto();
                                        List<NotaProduto> notaProdutoList = notaProduto.retornaListaProdutosNota(context, notaFiscal.getCodnota());
                                        enviaJson = new EnviaJson();
                                        url = "http://192.168.0.199:45455/api/notaproduto";
                                        retorno = null;
                                        retornoEnvio = "";
                                        gsonRetorno = gson.toJson(notaProdutoList);
                                        try {
                                            enviaJson.execute(gsonRetorno, url);
                                            retornoEnvio = enviaJson.get();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        }
                                        controleCodigo = gson.fromJson(retornoEnvio, ControleCodigo[].class);
                                        for (int i = 0; i < controleCodigo.length; i++) {
                                            notaProduto = new NotaProduto();
                                            if (notaProdutoList.get(i).getAuto() != controleCodigo[i].getCodigoBanco()) {
                                                notaProdutoList.get(i).setAuto(controleCodigo[i].getCodigoBanco());
                                                notaProduto.cadastraNotaProduto(context, notaProdutoList.get(i));
                                            }
                                        }
                                        List<ParcelaNFE> parcelasNFEList = getSetDinamico.colocaDadosParcelasNFE(getContext(), notaFiscal, txPedido.getText().toString());
                                        enviaJson = new EnviaJson();
                                        url = "http://192.168.0.199:45455/api/parcelasnfe";
                                        retorno = null;
                                        retornoEnvio = "";
                                        gsonRetorno = gson.toJson(parcelasNFEList);
                                        try {
                                            enviaJson.execute(gsonRetorno, url);
                                            retornoEnvio = enviaJson.get();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        }

                                        enviaJson = new EnviaJson();
                                        url = "http://192.168.0.199:45455/api/envianfe";
                                        retorno = null;
                                        retornoEnvio = "";
                                        gsonRetorno = gson.toJson(notaFiscal);
                                        try {
                                            enviaJson.execute(gsonRetorno, url);
                                            retornoEnvio = enviaJson.get();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                            CriaEmail criaEmail = new CriaEmail();
                                            Mac mac = new Mac();
                                            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                                        }

                                        ControleCodigo controle = new ControleCodigo();
                                        controle = gson.fromJson(retornoEnvio, ControleCodigo.class);

                                        try {
                                            String jsonretorno = "";
                                            jsonretorno = controle.getMensagem();
                                            NotaFiscal notaFiscalFinal = new NotaFiscal();

                                            notaFiscalFinal = gson.fromJson(controle.getMensagem(), NotaFiscal.class);
                                            notaFiscalFinal.setIdnota(notaFiscal.getIdnota());
                                            notaFiscalFinal = notaFiscal.cadastraNota(context, notaFiscalFinal);
                                            Bluetooth impressaoA7 = new Bluetooth();
                                            impressaoA7.imprimeA7(getContext(), notaFiscal.getCodnota());
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            mostraToast.mostraToastLong(context, controle.getMensagem());
                                        }

//                                    Bluetooth impressaoA7 = new Bluetooth();
//                                    impressaoA7.imprimeA7(getContext(), notaFiscal.getCodnota());
                                    }
                                }

                            } else {
                                Bluetooth impressaoA7 = new Bluetooth();
                                impressaoA7.imprimeA7(getContext(), notaFiscal.getCodnota());
                            }
                        }
                    } else {
                        mostraToast.mostraToastLong(getContext(), "Não foi possivel verificar a sequencia da NF-e");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    CriaEmail criaEmail = new CriaEmail();
                    Mac mac = new Mac();
                    criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), ex.getMessage());
                }
            }
        });

//        btGerarNfe.setVisibility(view.INVISIBLE);


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
                bundle.putLong("codigo", pedidoProduto.getIdpedidoproduto());
                bundle.putString("codigoClicado", pedidoProduto.getCodproduto());
                bundle.putLong("idPedidoProduto", pedidoProduto.getIdpedidoproduto());
                txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");
                bundle.putString("codigoPedido", txPedido.getText().toString());
                pedidoProdutoTela.setArguments(bundle);

                pedidoProdutoTela.show(fragmentManager, "Pedido Produto");
            }
        });
        listItenspedido.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view1,
                                           int position,
                                           long id) {
                final PedidoProduto pedidoProduto = (PedidoProduto) listItenspedido.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Confirma a exclusao do item " + pedidoProduto.getDescri() + "?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        MostraToast toast = new MostraToast();
                        txPedido = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txPedido");
                        boolean retorno = pedidoProduto.removerPedidoProduto(getContext(), pedidoProduto.getIdpedidoproduto());
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

        btGerarParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvaPedido(view, 2L, false);
                ParcelasDados parcelas = new ParcelasDados();
                Bundle bundle = new Bundle();
                bundle.putLong("codigo", Long.parseLong(txPedido.getText().toString()));
                parcelas.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, parcelas, parcelas.getTag()).commit();
            }
        });


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer retorno = SalvaPedido(view, 1L, true);
                if (retorno < 0) {
                    MostraToast mostraToast = new MostraToast();
                    mostraToast.mostraToastShort(getContext(), "Erro ao salvar dados do pedido.");
                } else {

                }
            }
        });

        return view;
    }


    public Integer SalvaPedido(View view, Long clique, Boolean imprime) {
        PedidoProduto pedidoProduto = new PedidoProduto();
        if (clique == 1L) {
            //fieldListPedidoDados CONTEM OS DADOS DA TELA DO SISTEMA
            List<Field> fieldListPedidoDados = new ArrayList<>(Arrays.asList(PedidoDados.class.getDeclaredFields()));
            //fieldListPedido CONTEM OS DADOS DO OBJETO PEDIDO QUE SERA SALVO NO BANCO
            List<Field> fieldListPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
            Pedido pedido = new Pedido();


            pedido.setPgto(1L);
            pedido.setCodemitente(1L);
            pedido.setOrpedi("2");
            pedido.setCodstatus(1L);
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
                                if (retorno.toString().equals("ERRO")) {
                                    return -1;
                                } else {
                                    Object retornoPedido = getSetDinamico.insereField(fieldListPedido.get(p), pedido, retorno);
                                    pedido = (Pedido) retornoPedido;
                                    break;
                                }
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

            } else {
                return -1;
            }
            pedido.setNome(cliente.getNomecliente());
            pedido.setData(new Date());

            Double valorTotalPedido = pedidoProduto.retornaTotalPedido(getContext(), pedido.getPedido());

            pedido.setValortotal(valorTotalPedido);

            boolean retorno = pedido.cadastraPedido(getContext(), pedido);
            Vendedor vendedor = new Vendedor();
            Cursor cursorVendedor = vendedor.retornaVendedorFiltradaCursor(getContext(), pedido.getCodvendedor());
            if (cursorVendedor.getCount() > 0) {
                comissaoVendedor = cursorVendedor.getDouble(cursorVendedor.getColumnIndex("comi"));
            } else {
                return -1;
            }

            if (retorno) {
                if (clique == 1) {
                    if (txPedido == null) {
                        pedido.setPedido(pedido.retornaMaiorCod(getContext()));
                    }
                    if (pedido.getPedido() > 0) {
                        if (imprime) {
                            imprimePedido(1L, pedido);
                        } else {
                            //montaTelaPedido(1L);
                        }


                    } else {
                        if (imprime) {
                            imprimePedido(2L, pedido);
                        } else {
                            montaTelaPedido(2L);
                        }

                    }
                }
            } else {
                PedidoFragment pedidoFragment = new PedidoFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();
                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastShort(getContext(), "Erro ao cadastrar o pedido.");
                return -1;
            }

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
                        return -1;
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

            } else {
                return -1;
            }
            pedido.setNome(cliente.getNomecliente());
            pedido.setData(new Date());
            Double valorTotalPedido = pedidoProduto.retornaTotalPedido(getContext(), pedido.getPedido());

            pedido.setValortotal(valorTotalPedido);

            boolean retorno = pedido.cadastraPedido(getContext(), pedido);

            Vendedor vendedor = new Vendedor();
            Cursor cursorVendedor = vendedor.retornaVendedorFiltradaCursor(getContext(), pedido.getCodvendedor());
            if (cursorVendedor.getCount() > 0) {
                comissaoVendedor = cursorVendedor.getDouble(cursorVendedor.getColumnIndex("comi"));
            } else {
                return -1;
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
                return -1;
            }


        }
        return 1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void imprimePedido(final Long status, final Pedido pedido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Confirma");
        builder.setMessage("Deseja imprimir o pedido?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                CriaImpressao impressao = new CriaImpressao();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date d = new Date();
                Cliente cliente = new Cliente();
                Cidade cidade = new Cidade();
                Vendedor vendedor = new Vendedor();
                PedidoProduto pedidoProduto = new PedidoProduto();
                List<PedidoProduto> pedidoProdutoLista = new ArrayList<>();
                cliente = cliente.retornaClienteObjeto(getContext(), pedido.getCodcliente());
                cidade = cidade.retornaCidadeObjeto(getContext(), cliente.getCodcidade());
                vendedor = vendedor.retornaVendedorObjeto(getContext(), pedido.getCodvendedor());
                pedidoProdutoLista = pedidoProduto.retornaPedidoProdutoObjeto(getContext(), pedido.getPedido());
                try {
                    impressao.conectaImpressora(getContext());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    CriaEmail criaEmail = new CriaEmail();
                    Mac mac = new Mac();
                    criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                }
                impressao.imprime("KADINI E KADINI LTDA", 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("RUA MANOEL TEIXEIRA, 108", 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("TAPEJARA - RS - CEP:99950-000", 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime(impressao.adicionaCaracter("", "-", 48L), 0, 0, 0, 0, CENTRALIZADO);

                impressao.imprime("DATA:" + format.format(pedido.getData()) + "@ FECH:" + format.format(pedido.getData()), 0, 0, 0, 0, BORDAS);

                if (pedido.getCodcliente() != 1L) {
                    impressao.imprime("CLI.:" + pedido.getCodcliente() + " - " + pedido.getNome(), 0, 0, 0, 0, ESQUERDA);
                    if (cliente.getCpf().length() > 0) {
                        impressao.imprime("CNPJ: " + "@ CPF:" + cliente.getCpf(), 0, 0, 0, 0, BORDAS);
                    } else {
                        impressao.imprime("CNPJ:" + cliente.getCgc() + "@ CPF: ", 0, 0, 0, 0, BORDAS);
                    }
                    impressao.imprime("IE..:" + cliente.getIncest() + "@RG: " + cliente.getIdentidade(), 0, 0, 0, 0, BORDAS);
                    impressao.imprime("CID.:" + cidade.getNomecidade() + " - " + cidade.getUf(), 0, 0, 0, 0, ESQUERDA);
                    impressao.imprime("BAIR:" + cliente.getBairro() + "@CEP: " + cliente.getCep(), 0, 0, 0, 0, BORDAS);
                    impressao.imprime("END.:" + cliente.getEndereco(), 0, 0, 0, 0, ESQUERDA);
                    impressao.imprime("FONE:" + cliente.getTelefone(), 0, 0, 0, 0, ESQUERDA);
                }
                impressao.imprime(impressao.adicionaCaracter("", "-", 48L), 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("PEDIDO " + pedido.getPedido(), 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("NAO E DOCUMENTO FISCAL", 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("VEND:" + vendedor.getCodvendedor() + "-" + vendedor.getNomevendedor() + "@ NR. ITENS:" + pedidoProdutoLista.size(), 0, 0, 0, 0, BORDAS);
                impressao.imprime(impressao.adicionaCaracter("", "-", 48L), 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("       ITEM           DESCRICAO           UN", 0, 0, 0, 0, ESQUERDA);
                impressao.imprime("    QNT.     V. UNIT.       DESC.        TOTAL", 0, 0, 0, 0, ESQUERDA);
                impressao.imprime(impressao.adicionaCaracter("", "-", 48L), 0, 0, 0, 0, CENTRALIZADO);

                Double somaitem = 0D;
                for (int i = 0; pedidoProdutoLista.size() > i; i++) {
                    impressao.imprime(1 + i + "@" + pedidoProdutoLista.get(i).getCodproduto() + "-" + pedidoProdutoLista.get(i).getDescri() + "@UN", 0, 0, 0, 0, BORDAS);
                    impressao.imprime("      " + pedidoProdutoLista.get(i).getQuantidade() + "     " + pedidoProdutoLista.get(i).getValorunitario() + "   " + pedidoProdutoLista.get(i).getDesvalor() + "    " + +pedidoProdutoLista.get(i).getValortotal(), 0, 0, 0, 0, ESQUERDA);

                    somaitem += pedidoProdutoLista.get(i).getValortotal();
                }
                impressao.imprime(impressao.adicionaCaracter("", "-", 48L), 0, 0, 0, 0, CENTRALIZADO);
                impressao.imprime("VALOR TOTAL: " + somaitem, 0, 0, 0, 0, CENTRALIZADO);
                Bluetooth bluetooth = new Bluetooth();
                impressao.imprimeimagem(bluetooth.imprimeNota(getContext(), "000000070"));

                impressao.avanco(2);

                try {
                    impressao.desconectaImpressora();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    CriaEmail criaEmail = new CriaEmail();
                    Mac mac = new Mac();
                    criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), e.getMessage());
                }


//                            texto += linhaImpressao.adicionaCaracter("KADINI E KADINI LTDA", " ", 48L);
//                            texto += linhaImpressao.adicionaCaracter("SEGUNDA LINHA", " ", 48L);
//                            texto += linhaImpressao.adicionaCaracter("TERCEIRA LINHA", " ", 48L);

//                           intent.putExtra("texto", texto);

//                                SendDataByte(PrinterCommand.POS_Print_Text(
//                                        linhaImpressao.adicionaCaracter("KADINI E KADINI", " ", 48L),
//                                        THAI, 255, 0, 0, 0));
//                                SendDataByte(Command.LF);
//                                SendDataByte(PrinterCommand.POS_Print_Text(
//                                        linhaImpressao.adicionaCaracter("SEGUNDA LINHA", " ", 48L),
//                                        THAI, 255, 0, 0, 0));
//                                SendDataByte(Command.LF);
//                                SendDataByte(PrinterCommand.POS_Print_Text(
//                                        linhaImpressao.adicionaCaracter("TERCEIRA LINHA", " ", 48L),
//                                        THAI, 255, 0, 0, 0));
//                                SendDataByte(Command.LF);

                dialog.dismiss();
                if (status < 3) {
                    montaTelaPedido(status);
                }
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
                montaTelaPedido(status);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }


    public void montaTelaPedido(Long status) {
        if (status == 1) {
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


}