package com.example.jose.connectdrawer.PedidoProduto;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.Pedido.PedidoDados;
import com.example.jose.connectdrawer.Produto.Produto;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 30/06/2017.
 */

public class PedidoProdutoTela extends DialogFragment {
    private Button btCancelar;
    private Button btSalvar;
    private EditText txquantidade;
    private EditText txvalorunitario;
    private EditText txvalortotal;
    private Spinner spProduto;
    private Long contaAcessos;
    private Boolean evitaLoop;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.popuppedidoproduto, container);
        contaAcessos = 0L;
        final Banco myDb = new Banco(getContext());
        List<String> produtoList = new ArrayList<>();
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);

        Bundle bundle = this.getArguments();
        final String codItem = bundle.getString("codigoClicado");
        final String codPedido = bundle.getString("codigoPedido");
        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        final GetSetDinamico getSetDinamico = new GetSetDinamico();

        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoProdutoTela.class.getDeclaredFields()));
        //RETORNA O PRODUTO FILTRADO PELO BUNDLE
        Long codigoProduto = bundle.getLong("codigo");
        final Long idPedidoProduto = bundle.getLong("idPedidoProduto");
        Produto produto = new Produto();
        final List<Field> fieldListProduto = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));
        if (codigoProduto > 0) {


            PedidoProduto pedidoProduto = new PedidoProduto();
            Cursor cursorPedidoProduto = pedidoProduto.retornaItemPedido(getContext(), codigoProduto);
            if (cursorPedidoProduto.getCount() > 0) {
                cursorPedidoProduto.moveToFirst();
                //CONSEGUIU BUSCAR OS DADOS DO PRODUTO NO PEDIDO
                //SPINNER DOS Produtos

                int posicao = 0;
                Cursor cursorProduto = produto.retornaProduto(getContext());
                //TESTA SE A CONSULTA RETORNA ALGUM RESULTADO
                if (cursorProduto.getCount() > 0) {
                    for (int i = 0; fieldListPassar.size() != i; i++) {
                        //posição do spinner para sair selecionado

                        //COLOCA VALOR NO SPINNER DO PRODUTO
                        if (fieldListPassar.get(i).getName().toLowerCase().equals("spproduto")) {

                            for (int f = fieldListProduto.size() - 1; -1 != f; f--) {
                                if (fieldListProduto.get(f).getName().toLowerCase().equals("codproduto") || fieldListProduto.get(f).getName().toLowerCase().equals("mercadoria")) {
                                } else {
                                    fieldListProduto.remove(f);
                                }
                            }
                            cursorProduto.moveToFirst();
                            for (int j = 0; cursorProduto.getCount() != j; j++) {
                                produto = new Produto();
                                for (int f = 0; fieldListProduto.size() != f; f++) {
                                    String tipo = getSetDinamico.retornaTipoCampo(fieldListProduto.get(f));
                                    String nomeCampo = fieldListProduto.get(f).getName().toLowerCase();
                                    Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorProduto);
                                    if (retorno != null) {
                                        Object retProduto = getSetDinamico.insereField(fieldListProduto.get(f), produto, retorno);
                                        produto = (Produto) retProduto;
                                    }
                                }
                                produtoList.add(produto.toString());
                                cursorProduto.moveToNext();
                            }


                            for (int k = 0; produtoList.size() != k; k++) {
                                Integer codHifen = produtoList.get(k).indexOf("-");
                                String codProduto = produtoList.get(k).substring(0, codHifen - 1);

                                if (codProduto.equals(cursorPedidoProduto.getString(cursorPedidoProduto.getColumnIndex("codproduto")))) {
                                    posicao = k;
                                    break;
                                }
                            }
                            getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, produtoList, getContext(), posicao);
                            //FIM DO SPINNER DOS PRODUTOS
                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                            //TESTE DOS CAMPOS
                            String tipo = getSetDinamico.retornaTipoCampo(fieldListPassar.get(i));
                            String nomecampo = "";
                            nomecampo = fieldListPassar.get(i).getName().replace("tx", "").toLowerCase();

                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursorPedidoProduto);
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, retorno.toString(), null);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, "", null);
                            }
                        }

                    }
                }
                cursorPedidoProduto.close();
            }
        } else {
            Cursor cursorProduto = produto.retornaProduto(getContext());
            for (int i = 0; fieldListPassar.size() != i; i++) {
                if (fieldListPassar.get(i).getName().toLowerCase().equals("spproduto")) {
                    for (int f = fieldListProduto.size() - 1; -1 != f; f--) {
                        if (fieldListProduto.get(f).getName().toLowerCase().equals("codproduto") || fieldListProduto.get(f).getName().toLowerCase().equals("mercadoria")) {
                        } else {
                            fieldListProduto.remove(f);
                        }
                    }
                    for (int j = 0; cursorProduto.getCount() != j; j++) {
                        produto = new Produto();
                        for (int f = 0; fieldListProduto.size() != f; f++) {
                            String tipo = getSetDinamico.retornaTipoCampo(fieldListProduto.get(f));
                            String nomeCampo = fieldListProduto.get(f).getName().toLowerCase();
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorProduto);
                            if (retorno != null) {
                                Object retProduto = getSetDinamico.insereField(fieldListProduto.get(f), produto, retorno);
                                produto = (Produto) retProduto;
                            }
                        }
                        produtoList.add(produto.toString());
                        cursorProduto.moveToNext();
                    }
                    getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, produtoList, getContext(), 0);
                    //FIM DO SPINNER DOS PRODUTOS
                } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                    //TESTE DOS CAMPOS
                    String tipo = getSetDinamico.retornaTipoCampo(fieldListPassar.get(i));
                    String nomecampo = "";
                    nomecampo = fieldListPassar.get(i).getName().replace("tx", "").toLowerCase();

                    getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, "0", null);
                }
            }

        }
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produto1 = new Produto();
                List<Field> fieldListObjeto = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));
                Cursor cursor = produto1.retornaProdutoFiltradaCursor(getContext(), getSetDinamicoTelas.retornaValorSpinner(view, "Produto"));
                if (cursor.getCount() > 0) {
                    for (int f = 0; fieldListObjeto.size() != f; f++) {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldListObjeto.get(f));
                        String nomeCampo = fieldListObjeto.get(f).getName().toLowerCase();
                        Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                        if (retorno != null) {
                            Object retProduto = getSetDinamico.insereField(fieldListObjeto.get(f), produto1, retorno);
                            produto1 = (Produto) retProduto;
                        }
                    }
                }

                PedidoProduto pedidoProduto = new PedidoProduto();
                txvalorunitario = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalorunitario");
                txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
                txvalortotal = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalortotal");

                pedidoProduto.setValorunitario(Double.parseDouble(txvalorunitario.getText().toString()));
                pedidoProduto.setQuantidade(Double.parseDouble(txquantidade.getText().toString()));
                pedidoProduto.setValortotal(Double.parseDouble(txvalortotal.getText().toString().replace(",",".")));
                pedidoProduto.setCodproduto(produto1.getCodproduto());
                pedidoProduto.setDescri(produto1.getMercadoria());
                pedidoProduto.setPedido(Long.parseLong(codPedido));

                if (idPedidoProduto > 0) {
                    pedidoProduto.setIdPedidoProduto(idPedidoProduto);
                }

                boolean retorno = pedidoProduto.cadastraPedidoProduto(myDb.getWritableDatabase(), pedidoProduto);
                if (retorno == false) {
                    MostraToast mostraToast = new MostraToast();
                    mostraToast.mostraToastShort(getContext(), "Erro ao cadastrar produto no pedido");
                } else {
                    dismiss();
                    MostraToast mostraToast = new MostraToast();
                    if (idPedidoProduto > 0) {
                        mostraToast.mostraToastShort(getContext(), "Item atualizado com sucesso.");
                    } else {
                        mostraToast.mostraToastShort(getContext(), "Item adicionado com sucesso.");
                    }
                    PedidoDados pedidoDados = new PedidoDados();
                    Bundle bundle = new Bundle();
                    bundle.putLong("codigo", Long.parseLong(codPedido));
                    pedidoDados.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
                }
            }
        });

        spProduto = (Spinner) getSetDinamicoTelas.retornaIDCampo(view, "spProduto");
        spProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {


                Produto produtoClicado = new Produto();
                String texto = spProduto.getItemAtPosition(position).toString();

                Integer codHifen = texto.indexOf("-");
                String codProduto = texto.substring(0, codHifen - 1);
                Cursor cursor = produtoClicado.retornaProdutoFiltradaCursor(getContext(), codProduto);
                List<Field> fieldListProduto = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));
                if (!codItem.equals(codProduto)) {
                    if (cursor.getCount() > 0) {
                        for (int f = 0; fieldListProduto.size() != f; f++) {
                            String tipo = getSetDinamico.retornaTipoCampo(fieldListProduto.get(f));
                            String nomeCampo = fieldListProduto.get(f).getName().toLowerCase();
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                            if (retorno != null) {
                                Object retProduto = getSetDinamico.insereField(fieldListProduto.get(f), produtoClicado, retorno);
                                produtoClicado = (Produto) retProduto;
                            }
                        }

                        txvalorunitario = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalorunitario");
                        txvalorunitario.setText(String.valueOf(produtoClicado.getValoravista()));
                    } else {
                        MostraToast mostraToast = new MostraToast();
                        mostraToast.mostraToastShort(getContext(), "Não foi possivel buscar dados do produto.");
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txvalorunitario = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalorunitario");
        txvalorunitario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().length() > 0) {
                    Double quantidade;
                    Double valorunitario;
                    Double total;
                    txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
                    txvalortotal = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalortotal");
                    quantidade = Double.parseDouble(txquantidade.getText().toString());
                    total = Double.parseDouble(txvalortotal.getText().toString().replace(",","."));
                    valorunitario = total / quantidade;
                    if (after != 0 || count != 0) {
                        evitaLoop = false;
                        contaAcessos++;
                    } else {
                        evitaLoop = true;
                    }
                } else {
                    evitaLoop = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
                txvalortotal = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalortotal");
                if (!evitaLoop) {
                    if (txvalorunitario.getText().toString().length() > 0) {
                        if (!(s.toString().lastIndexOf(".") == (s.toString().length() - 1))) {
                            Double quantidade;
                            Double valorunitario;
                            Double total;
                            quantidade = Double.parseDouble(txquantidade.getText().toString());
                            valorunitario = Double.parseDouble(txvalorunitario.getText().toString());
                            total = quantidade * valorunitario;
                            evitaLoop = true;
                            DecimalFormat format = new DecimalFormat("0.00");
                            txvalortotal.setText(format.format(total).toString());

                        }
                    }
                }
            }
        });

        txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
        txquantidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                evitaLoop = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                DecimalFormat format = new DecimalFormat("0.00");
                if (txquantidade.getText().toString().length() > 0) {
                    if (!(s.toString().lastIndexOf(".") == (s.toString().length() - 1))) {
                        Double quantidade;
                        Double valorunitario;
                        Double total;
                        txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
                        txvalortotal = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalortotal");
                        quantidade = Double.parseDouble(txquantidade.getText().toString());
                        valorunitario = Double.parseDouble(txvalorunitario.getText().toString());
                        total = quantidade * valorunitario;
                        evitaLoop = true;
                        format.format(total);
                        txvalortotal.setText(total.toString());
                    }
                }
            }
        });

        txvalortotal = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalortotal");
        txvalortotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Double quantidade;
                Double valorunitario;
                Double total;
                txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
                txvalorunitario = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalorunitario");
                quantidade = Double.parseDouble(txquantidade.getText().toString());
                valorunitario = Double.parseDouble(txvalorunitario.getText().toString().replace(",","."));
                total = quantidade * valorunitario;
                if (Double.parseDouble(s.toString().replace(",",".")) == Double.parseDouble(total.toString().replace(",","."))) {
                    evitaLoop = false;
                    contaAcessos++;
                } else {
                    evitaLoop = true;
                }
                if (contaAcessos > 5) {
                    contaAcessos = 0L;
                    evitaLoop = true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txquantidade = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txquantidade");
                txvalorunitario = (EditText) getSetDinamicoTelas.retornaIDCampo(view, "txvalorunitario");
                if (!evitaLoop) {
                    if (txvalortotal.getText().toString().length() > 0) {
                        if (!(s.toString().lastIndexOf(".") == (s.toString().length() - 1))) {
                            Double quantidade;
                            Double valorunitario;
                            Double total;

                            quantidade = Double.parseDouble(txquantidade.getText().toString());
                            total = Double.parseDouble(txvalortotal.getText().toString().replace(",","."));
                            valorunitario = total / quantidade;
                            if (valorunitario > 0) {
                                evitaLoop = true;
                                DecimalFormat format = new DecimalFormat("0.00");
                                txvalorunitario.setText(format.format(valorunitario).toString());
                            }
                        }
                    }
                }
            }
        });

        return view;
    }


}