package com.example.jose.connectdrawer.cliente;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.ConsultaSefaz.InfCadastro;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.ConsultaSefaz.ConsultaSefaz;
import com.example.jose.connectdrawer.uteis.CamposRequeridos;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.Mascara;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.lang.reflect.Field;
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
    private EditText txNume;
    private EditText txCep;
    private EditText txDataNasc;
    private EditText txTelefone;
    private EditText txCelular;
    private EditText txFonetrab;
    private EditText txEmail;
    private Spinner spCidade;
    private Spinner spPosicao;
    private Button btSefaz;
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
        final Cidade cidade = new Cidade();
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
        final List<String> cidadeList = new ArrayList<>();
        final GetSetDinamico getSetDinamico = new GetSetDinamico();
        final View viewCliente = inflater.inflate(R.layout.fragment_cliente_dados, container, false);
        btSalvar = (Button) viewCliente.findViewById(R.id.btSalvar);
        btCancelar = (Button) viewCliente.findViewById(R.id.btCancelar);
        btSefaz = viewCliente.findViewById(R.id.consultarSefaz);
        final Cliente cliente = new Cliente();
        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        ClienteDados clienteDados = new ClienteDados();
        //PEGA A LISTA DE CAMPOS DA CLASSE

        final List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(ClienteDados.class.getDeclaredFields()));

        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        final Bundle bundle = this.getArguments();
        Long codigoCliente = bundle.getLong("codigo");
        if (codigoCliente > 0) {
            boolean cpfcnpj = false;
            Cursor cursor = cliente.retornaClienteFiltrado(getContext(), codigoCliente);
            if (cursor.getCount() > 0) {
                for (int i = 0; fieldListPassar.size() != i; i++) {

                    String mascara = null;
                    if (fieldListPassar.get(i).getName().toLowerCase().equals("$change") ||
                            fieldListPassar.get(i).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                            String tipo = getSetDinamico.retornaTipoCampo(fieldListPassar.get(i));
                            String nomecampo = "";
                            nomecampo = fieldListPassar.get(i).getName().replace("tx", "").toLowerCase();
                            if (nomecampo.equals("cpfcnpj")) {
                                if (cursor.getString(cursor.getColumnIndex("cpf")) != null) {
                                    Object retorno = getSetDinamico.retornaValorCursor(tipo, "cpf", cursor);
                                    if (retorno != null) {
                                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, retorno.toString(), "###.###.###-##");
                                    } else {
                                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", "###.###.###-##");
                                    }
                                } else {
                                    if (cursor.getString(cursor.getColumnIndex("cgc")) != null) {
                                        Object retorno = getSetDinamico.retornaValorCursor(tipo, "cgc", cursor);
                                        if (retorno != null) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, retorno.toString(), "##.###.###/####-##");
                                        } else {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", "##.###.###/####-##");
                                        }
                                    }
                                }
                            } else {
                                if (fieldListPassar.get(i).getName().equals("txCep")) {
                                    mascara = "#####-###";
                                }
                                if (fieldListPassar.get(i).getName().equals("txTelefone")) {
                                    mascara = "(##)#####-####";
                                }
                                if (fieldListPassar.get(i).getName().equals("txCelular")) {
                                    mascara = "(##)#####-####";
                                }
                                if (fieldListPassar.get(i).getName().equals("txFonetrab")) {
                                    mascara = "(##)#####-####";
                                }
                                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                                if (retorno != null) {
                                    getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, retorno.toString(), mascara);
                                } else {
                                    getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", mascara);
                                }
                            }

                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("sp")) {

                            if (fieldListPassar.get(i).getName().equals("spCidade")) {

                                Cursor cursorCidade = cidade.retornaCidade(getContext());
                                List<Field> fieldListCidade = new ArrayList<>(Arrays.asList(cidade.getClass().getDeclaredFields()));
                                int posicao = 0;
                                if (cursorCidade.getCount() > 0) {
                                    cursor.moveToFirst();
                                    for (int j = 0; cursorCidade.getCount() != j; j++) {
                                        Cidade cidade1 = new Cidade();


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
                                getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, cidadeList, getContext(), posicao);

                            } else {

                                int posicao = 0;

                                List<String> fieldListPosicao = new ArrayList<>();
                                fieldListPosicao.add("1 - Ótimo");
                                fieldListPosicao.add("2 - Bom");
                                fieldListPosicao.add("3 - Regular");
                                fieldListPosicao.add("4 - Ruim");
                                String posicaoBanco = cursor.getString(cursor.getColumnIndex("posicao"));
                                if (posicaoBanco == null) {
                                    posicao = 0;
                                } else {
                                    if (posicaoBanco.equals("")) {
                                        posicao = 0;
                                    } else {
                                        posicao = Integer.parseInt(cursor.getString(cursor.getColumnIndex("posicao"))) - 1;
                                    }
                                }
                                getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, fieldListPosicao, getContext(), posicao);
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; fieldListPassar.size() != i; i++) {
                String mascara = null;
                if (fieldListPassar.get(i).getName().equals("txCep")) {
                    mascara = "#####-###";
                }
                if (fieldListPassar.get(i).getName().equals("txTelefone")) {
                    mascara = "(##)#####-####";
                }
                if (fieldListPassar.get(i).getName().equals("txCelular")) {
                    mascara = "(##)#####-####";
                }
                if (fieldListPassar.get(i).getName().equals("txFonetrab")) {
                    mascara = "(##)#####-####";
                }
                if (fieldListPassar.get(i).getName().toLowerCase().equals("$change") ||
                        fieldListPassar.get(i).getName().toLowerCase().equals("serialversionuid")) {

                } else {
                    if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", mascara);
                    } else if (fieldListPassar.get(i).getName().equals("spCidade")) {
                        Cursor cursorCidade = cidade.retornaCidade(getContext());

                        int posicao = 0;
                        if (cursorCidade.getCount() > 0) {
                            cursorCidade.moveToFirst();
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
                            }
                        }
                        getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, cidadeList, getContext(), posicao);
                    } else if (fieldListPassar.get(i).getName().equals("spPosicao")) {

                        int posicao = 0;

                        List<String> fieldListPosicao = new ArrayList<>();
                        fieldListPosicao.add("1 - Ótimo");
                        fieldListPosicao.add("2 - Bom");
                        fieldListPosicao.add("3 - Regular");
                        fieldListPosicao.add("4 - Ruim");
                        posicao = 0;
                        getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, fieldListPosicao, getContext(), posicao);
                    }
                }
            }
            txCpfCnpj = (EditText) viewCliente.findViewById(R.id.txCpfCnpj);
            txCpfCnpj.addTextChangedListener(Mascara.cpfcnpj(txCpfCnpj));


        }


        //CLIQUE PARA BUSCAR DADOS NO SEFAZ ATRAVÉZ DO SERVIDOR
        txCpfCnpj = (EditText) viewCliente.findViewById(R.id.txCpfCnpj);


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
                int retorno = camposRequeridosClass.retornaMensagemRequerido(camposRequeridos, camposRequeridosMensagem, camposRequeridosTamanho, fieldListPassar, viewCliente);
                List<Field> fieldListObjeto = new ArrayList<Field>(Arrays.asList(Cliente.class.getDeclaredFields()));
                if (retorno == 0) {
                    Cliente cliente1 = new Cliente();
                    for (int i = 0; fieldListObjeto.size() != i; i++) {
                        String valorCampo = "";
                        String nomecampo = fieldListObjeto.get(i).getName();
                        if (fieldListObjeto.get(i).getName().equals("cgc")) {
                            nomecampo = "CpfCnpj";
                        }
                        if (fieldListObjeto.get(i).getName().equals("cpf")) {
                            nomecampo = "CpfCnpj";
                        }
                        if (fieldListObjeto.get(i).getName().equals("nomecliente")) {
                            nomecampo = "nomeCliente";
                        }
                        if (nomecampo.equals("codcidade")) {
                            nomecampo = "Cidade";
                            spCidade = (Spinner) viewCliente.findViewById(R.id.spCidade);
                            valorCampo = getSetDinamicoTelas.retornaValorSpinner(viewCliente, nomecampo);
                        } else if (nomecampo.equals("posicao")) {
                            nomecampo = "Posicao";
                            spPosicao = (Spinner) viewCliente.findViewById(R.id.spPosicao);
                            valorCampo = getSetDinamicoTelas.retornaValorSpinner(viewCliente, nomecampo);
                        } else {
                            valorCampo = getSetDinamicoTelas.retornaValorEditText(viewCliente, nomecampo);
                        }
                        if (fieldListObjeto.get(i).getName().equals("cep")) {
                            valorCampo = Mascara.unmask(valorCampo);
                        }
                        if (fieldListObjeto.get(i).getName().equals("telefone")) {
                            valorCampo = Mascara.unmask(valorCampo);
                        }
                        if (fieldListObjeto.get(i).getName().equals("celular")) {
                            valorCampo = Mascara.unmask(valorCampo);
                        }
                        if (fieldListObjeto.get(i).getName().equals("fonetrab")) {
                            valorCampo = Mascara.unmask(valorCampo);
                        }
                        if (fieldListObjeto.get(i).getName().equals("cgc")) {
                            valorCampo = Mascara.unmask(valorCampo);
                        }
                        if (fieldListObjeto.get(i).getName().equals("cpf")) {
                            valorCampo = Mascara.unmask(valorCampo);
                        }
                        if (valorCampo != null) {
                            Object teste = null;
                            if (nomecampo.equals("CpfCnpj")) {
                                if (fieldListObjeto.get(i).getName().equals("cgc") && valorCampo.length() == 14) {
                                    teste = getSetDinamico.insereField(fieldListObjeto.get(i), cliente1, valorCampo);
                                    cliente1 = (Cliente) teste;
                                } else if (fieldListObjeto.get(i).getName().equals("cpf") && valorCampo.length() == 11) {
                                    teste = getSetDinamico.insereField(fieldListObjeto.get(i), cliente1, valorCampo);
                                    cliente1 = (Cliente) teste;
                                }
                            } else if (nomecampo.equals("Cidade")) {
                                teste = getSetDinamico.insereField(fieldListObjeto.get(i), cliente1, valorCampo);
                                cliente1 = (Cliente) teste;
                            } else if (nomecampo.equals("Posicao")) {
                                teste = getSetDinamico.insereField(fieldListObjeto.get(i), cliente1, valorCampo);
                                cliente1 = (Cliente) teste;
                            } else {
                                teste = getSetDinamico.insereField(fieldListObjeto.get(i), cliente1, valorCampo);
                                cliente1 = (Cliente) teste;
                            }
                        }

                    }
                    boolean status = cliente.cadastraCliente(getContext(), cliente1);
                    if (status == true) {
                        btCancelar.performClick();
                        if (cliente1.getCodigo() != null) {
                            new MostraToast().mostraToastShort(getContext(), "O cliente foi atualizado com sucesso");
                        } else {
                            new MostraToast().mostraToastShort(getContext(), "O cliente foi cadastrado com sucesso");
                        }


                    } else {
                        new MostraToast().mostraToastShort(getContext(), "Erro ao cadastrar cliente");
                    }
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteFragment clienteFragment = new ClienteFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clienteFragment, clienteFragment.getTag()).commit();
            }
        });

        btSefaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean encontrouCidade = false;
                if (txCpfCnpj.getText().equals("")) {

                } else {
                    List<InfCadastro> infCadastros = new ArrayList<>();
                    ConsultaSefaz consultaSefaz = new ConsultaSefaz();
                    infCadastros = consultaSefaz.buscaDados(getContext(), txCpfCnpj.getText().toString().replace(".", "").replace("/", "").replace("-", ""));
                    try {
                        if (infCadastros != null) {
                            for (int i = 0; fieldListPassar.size() != i; i++) {

                                String mascara = null;
                                if (fieldListPassar.get(i).getName().toLowerCase().equals("$change") ||
                                        fieldListPassar.get(i).getName().toLowerCase().equals("serialversionuid")) {
                                } else {
                                    if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                                        if (fieldListPassar.get(i).getName().toLowerCase().equals("txnomecliente")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getXnome(), null);
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txendereco")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getXlgr(), null);
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txnume")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getNro(), null);
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txcep")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getCep(), null);
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txie")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getIe(), null);
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txbairro")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getXbairro(), null);
                                        }

                                    } else if (fieldListPassar.get(i).getName().toLowerCase().equals("spcidade")) {
//                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getXlgr(), null);
                                        Cursor cursorCidade = cidade.retornaCidade(getContext());
                                        List<Field> fieldListCidade = new ArrayList<>(Arrays.asList(cidade.getClass().getDeclaredFields()));
                                        int posicao = 0;
                                        if (cursorCidade.getCount() > 0) {
                                            cursorCidade.moveToFirst();
                                            for (int j = 0; cursorCidade.getCount() != j; j++) {
                                                Cidade cidade1 = new Cidade();
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
                                                if (infCadastros.get(0).getCmun().equals(cidade1.getCodnacionalcidade())) {
                                                    for (int k = 0; cidadeList.size() != k; k++) {
                                                        if (cidadeList.get(k).equals(cidade1.toString())) {
                                                            posicao = k;
                                                            encontrouCidade = true;
                                                        }
                                                    }
                                                }
                                            }
                                        }


                                        if (encontrouCidade == false) {
                                            Cidade cidadeNova = new Cidade();
                                            cidadeNova.setCodnacionalcidade(infCadastros.get(0).getCmun());
                                            cidadeNova.setCep(infCadastros.get(0).getCep());
                                            cidadeNova.setNomecidade(infCadastros.get(0).getXmun());
                                            cidadeNova.setPais("Brasil");
                                            cidadeNova.setCodnacionaluf(infCadastros.get(0).getCmun().substring(0, 2));
                                            cidadeNova.setCadastroandroid(true);
                                            cidadeNova.setUf(infCadastros.get(0).getUf());
                                            cidadeNova.setCodnacionalpais("1058");
                                            cidadeList.clear();
                                            cidade.cadastraCidade(getContext(), cidadeNova);
                                            cursorCidade.moveToFirst();
                                            for (int j = 0; cursorCidade.getCount() != j; j++) {
                                                Cidade cidade1 = new Cidade();
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
                                                if (infCadastros.get(0).getCmun().equals(cidade1.getCodnacionalcidade().toString())) {
                                                    for (int k = 0; cidadeList.size() != k; k++) {
                                                        if (cidadeList.get(k).equals(cidade1.toString())) {
                                                            posicao = k;
                                                            encontrouCidade = true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, cidadeList, getContext(), posicao);
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Log.e("ERRO", ex.toString());
                    }
                }
            }
        });

        return viewCliente;


    }


}
