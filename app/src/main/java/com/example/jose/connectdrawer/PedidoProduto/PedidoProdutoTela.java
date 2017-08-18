package com.example.jose.connectdrawer.PedidoProduto;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.Produto.Produto;
import com.example.jose.connectdrawer.R;
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
        List<String> produtoList = new ArrayList<>();
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);

        Bundle bundle = this.getArguments();
        final String codItem = bundle.getString("codigoClicado");

        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        final GetSetDinamico getSetDinamico = new GetSetDinamico();

        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoProdutoTela.class.getDeclaredFields()));
        //RETORNA O PRODUTO FILTRADO PELO BUNDLE
        Long codigoProduto = bundle.getLong("codigo");
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
                List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(PedidoProdutoTela.class.getDeclaredFields()));
                for (int i = 0; fieldListPassar.size() != i; i++) {
                    if (fieldListPassar.get(i).getName().toLowerCase().substring(0, 2).equals("tx")) {

                    } else if (fieldListPassar.get(i).getName().toLowerCase().equals("spproduto")) {
                        String valorCampo = getSetDinamicoTelas.retornaValorSpinner(view, "Produto");
                        Cursor cursorProduto = produto1.retornaProdutoFiltradaCursor(getContext(), valorCampo);
                        if (cursorProduto.getCount() > 0) {

                        }
                    }

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
                    total = Double.parseDouble(txvalortotal.getText().toString());
                    valorunitario = total / quantidade;
                    if (after != 0 || count != 0) {
                        evitaLoop = false;
                        contaAcessos++;
                    } else {
                        evitaLoop = true;
                    }
                }else{
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
                            DecimalFormat format = new DecimalFormat("0.000");
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
                DecimalFormat format = new DecimalFormat("0.000");
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
                valorunitario = Double.parseDouble(txvalorunitario.getText().toString());
                total = quantidade * valorunitario;
                if (Double.parseDouble(s.toString()) == Double.parseDouble(total.toString())) {
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
                            total = Double.parseDouble(txvalortotal.getText().toString());
                            valorunitario = total / quantidade;
                            evitaLoop = true;
                            DecimalFormat format = new DecimalFormat("0.000");
                            txvalorunitario.setText(format.format(valorunitario).toString());
                        }
                    }
                }
            }
        });

        return view;
    }


}
