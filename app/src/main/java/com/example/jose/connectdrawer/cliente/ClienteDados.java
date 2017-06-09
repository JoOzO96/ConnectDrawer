package com.example.jose.connectdrawer.cliente;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.cidade.CidadeDados;
import com.example.jose.connectdrawer.uteis.CamposRequeridos;
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
    private EditText txIncest;
    private EditText txEndereco;
    private EditText txBairro;
    private EditText txCep;
    private EditText txDataNasc;
    private EditText txTelefone;
    private EditText txCelular;
    private EditText txFonetrab;
    private Spinner spCidade;
    private Button btSalvar;
    private Button btCancelar;
    private List<String> camposRequeridos;
    private List<String> camposRequeridosMensagem;
    private List<String> camposRequeridosTamanho;

    public ClienteDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Cidade cidade = new Cidade();
        camposRequeridos = new ArrayList<>();
        camposRequeridosMensagem = new ArrayList<>();
        camposRequeridosTamanho = new ArrayList<>();
        camposRequeridos.add("txNomeCliente");
        camposRequeridosMensagem.add("O nome do cliente é obrigatorio");
        camposRequeridosTamanho.add("5");
        camposRequeridos.add("txCpfCnpj");
        camposRequeridosMensagem.add("O CPF ou CNPJ é obrigatorio");
        camposRequeridosTamanho.add("0");
        camposRequeridos.add("txEndereco");
        camposRequeridosMensagem.add("O endereço é obrigatorio");
        camposRequeridosTamanho.add("5");
        camposRequeridos.add("txBairro");
        camposRequeridosMensagem.add("O bairro é obrigatorio");
        camposRequeridosTamanho.add("5");
        camposRequeridos.add("txCep");
        camposRequeridosMensagem.add("O CEP é obrigatorio");
        camposRequeridosTamanho.add("9");
        camposRequeridos.add("txTelefone");
        camposRequeridosMensagem.add("O telefone é obrigatorio");
        camposRequeridosTamanho.add("13");
        List<String> cidadeList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        final View view = inflater.inflate(R.layout.fragment_cliente_dados, container, false);
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);
        final Cliente cliente = new Cliente();
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        ClienteDados clienteDados = new ClienteDados();
        //PEGA A LISTA DE CAMPOS DA CLASSE
        List<Field> fieldList = new ArrayList<>(Arrays.asList(clienteDados.getClass().getDeclaredFields()));

        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(ClienteDados.class.getDeclaredFields()));
        List<Field> fieldListRid = new ArrayList<>(Arrays.asList(R.id.class.getDeclaredFields()));
        List<Field> fieldListPassar = new ArrayList<>();

        for (int i = 0; fieldListRid.size() != i; i++) {
            for (int j = 0; fieldListClasse.size() != j; j++) {
                if (fieldListRid.get(i).getName().toLowerCase().equals(fieldListClasse.get(j).getName().toLowerCase())) {
                    fieldListPassar.add(fieldListRid.get(i));
                    break;
                } else {
                }
            }
        }


        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        Long codigoCliente = bundle.getLong("codigo");
        boolean cpfcnpj = false;
        Cursor cursor = cliente.retornaClienteFiltrado(getContext(), codigoCliente);
        if (cursor.getCount() > 0) {
            for (int i = 0; fieldList.size() != i; i++) {

                String mascara = null;
                if (fieldList.get(i).getName().toLowerCase().equals("$change") ||
                        fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                } else {
                    if (fieldList.get(i).getName().substring(0, 2).equals("tx")) {
                        String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(i));
                        String nomecampo = "";
                        nomecampo = fieldList.get(i).getName().replace("tx", "").toLowerCase();
                        if (nomecampo.equals("cpfcnpj")) {
                            if (cursor.getString(cursor.getColumnIndex("cpf")) != null) {
                                Object retorno = getSetDinamico.retornaValorCursor(tipo, "cpf", cursor);
                                if (retorno != null) {
                                    getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, retorno.toString(), "###.###.###-##");
                                } else {
                                    getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, "", "###.###.###-##");
                                }
                            } else {
                                if (cursor.getString(cursor.getColumnIndex("cgc")) != null) {
                                    Object retorno = getSetDinamico.retornaValorCursor(tipo, "cgc", cursor);
                                    if (retorno != null) {
                                        getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, retorno.toString(), "##.###.###/####-##");
                                    } else {
                                        getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, "", "##.###.###/####-##");
                                    }
                                }
                            }
                        } else {
                            if (fieldList.get(i).getName().equals("txCep")) {
                                mascara = "#####-###";
                            }
                            if (fieldList.get(i).getName().equals("txTelefone")) {
                                mascara = "(##)#####-####";
                            }
                            if (fieldList.get(i).getName().equals("txCelular")) {
                                mascara = "(##)#####-####";
                            }
                            if (fieldList.get(i).getName().equals("txFonetrab")) {
                                mascara = "(##)#####-####";
                            }
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, retorno.toString(), mascara);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, "", mascara);
                            }
                        }

//                        }
                    } else if (fieldList.get(i).getName().substring(0, 2).equals("sp")) {
                        Cursor cursorCidade = cidade.retornaCidade(getContext());

                        int posicao = 0;
                        if (cursorCidade.getCount() > 0) {
                            cursor.moveToFirst();
                            for (int j = 0; cursorCidade.getCount() != j; j++) {
                                Cidade cidade1 = new Cidade();
                                List<Field> fieldListCidade = new ArrayList<>(Arrays.asList(cidade1.getClass().getDeclaredFields()));

                                for (int f = 0; fieldListCidade.size() != f; f++) {

                                    String tipo = getSetDinamico.retornaTipoCampo(fieldListCidade.get(f));
                                    String nomeCampo = fieldListCidade.get(f).getName().toLowerCase();
                                    Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorCidade);
                                    if (retorno != null) {
                                        Object teste = getSetDinamico.insereField(fieldListCidade.get(f), cidade1, retorno);
                                        cidade1 = (Cidade) teste;
                                    }


                                }
                                cursorCidade.moveToNext();
                                cidadeList.add(cidade1.toString());
                                if (cursor.getString(cursor.getColumnIndex("codcidade")).equals(cidade1.getCodcidade().toString())) {
                                    for (int k = 0; cidadeList.size() != k; k++) {
                                        if (cidadeList.get(k).equals(cidade1.toString())) {
                                            posicao = k;
                                        }
                                    }
                                }
                            }
                        }
                        getSetDinamicoTelas.colocaValorSpinner(fieldList.get(i), view, cidadeList, getContext(), posicao);
                    }
                }
            }
        }

        //CLIQUE DO BOTAO SALVAR
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDados clienteDados = new ClienteDados();
                //PEGA A LISTA DE CAMPOS DA CLASSE
                List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(ClienteDados.class.getDeclaredFields()));
                List<Field> fieldListRid = new ArrayList<>(Arrays.asList(R.id.class.getDeclaredFields()));
                List<Field> fieldListPassar = new ArrayList<>();

                for (int i = 0; fieldListRid.size() != i; i++) {
                    for (int j = 0; fieldListClasse.size() != j; j++) {
                        if (fieldListRid.get(i).getName().toLowerCase().equals(fieldListClasse.get(j).getName().toLowerCase())) {
                            fieldListPassar.add(fieldListRid.get(i));
                            break;
                        }
                    }
                }

                CamposRequeridos camposRequeridosClass = new CamposRequeridos();
                int retorno = camposRequeridosClass.retornaMensagemRequerido(camposRequeridos , camposRequeridosMensagem, camposRequeridosTamanho, fieldListPassar, view);

                if (retorno == 0){
                    //cliente.cadastraCliente()
                }

            }
        });
        return view;


    }


}
