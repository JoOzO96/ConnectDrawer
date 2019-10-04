package com.example.jose.connectdrawer.cliente;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.jose.connectdrawer.ConsultaSefaz.InfCadastro;
import com.example.jose.connectdrawer.Email.CriaEmail;
import com.example.jose.connectdrawer.Emitente.EmiteConfigura;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.ConsultaSefaz.ConsultaSefaz;
import com.example.jose.connectdrawer.uteis.CamposRequeridos;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.Mascara;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;

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
    private EditText txContatocobranca;
    private EditText txIncest;
    private EditText txEndereco;
    private EditText txBairro;
    private EditText txNume;
    private EditText txCep;
    private EditText txTelefone;
    private EditText txCelular;
    private EditText txFonetrab;
    private EditText txEmail;
    private EditText txObs;
    private EditText txFantasia;
    private AutoCompleteTextView auCidade;
    //    private Spinner spCidade;
    private Spinner spPosicao;
    private Spinner spCodvendedor;
    private Button btCadastroanimais;
    private Button btSefaz;
    private Button btSalvar;
    private Button btCancelar;
    private ListView listClienteanimais;
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
        btCadastroanimais = viewCliente.findViewById(R.id.btCadastroanimais);
        listClienteanimais = viewCliente.findViewById(R.id.listClienteanimais);
        final Cliente cliente = new Cliente();
        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        ClienteDados clienteDados = new ClienteDados();
        //PEGA A LISTA DE CAMPOS DA CLASSE
        Sessao.setaContext(getContext());
        List<Cidade> listaCidade = Sessao.retornaListaCidade();
        final List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(ClienteDados.class.getDeclaredFields()));

        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        final Bundle bundle = this.getArguments();
        final Long codigoCliente = bundle.getLong("codigo");
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
                                    if (cursor.getString(cursor.getColumnIndex("cpf")).equals("")) {
                                        Object retorno = getSetDinamico.retornaValorCursor(tipo, "cgc", cursor);
                                        if (retorno.equals("")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", "###.###.###-##");
                                        } else {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, retorno.toString(), "##.###.###/####-##");
                                        }
                                    } else {
                                        Object retorno = cursor.getString(cursor.getColumnIndex("cpf")); //getSetDinamico.retornaValorCursor(tipo, "cpf", cursor);
                                        if (retorno.equals("")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", "###.###.###-##");
                                        } else {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, retorno.toString(), "###.###.###-##");
                                        }
                                    }
                                } else {
                                    Object retorno = getSetDinamico.retornaValorCursor(tipo, "cgc", cursor);
                                    if (retorno.equals("")) {
                                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", "###.###.###-##");
                                    } else {
                                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, retorno.toString(), "##.###.###/####-##");
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

                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("au")) {

                            if (fieldListPassar.get(i).getName().equals("auCidade")) {
                                listaCidade = Sessao.retornaListaCidade();
                                int posicao = 0;
                                for (int k = 0; listaCidade.size() > k; k++) {
                                    if (cursor.getString(cursor.getColumnIndex("codcidade")).equals(listaCidade.get(k).getCodcidade().toString())) {
                                        posicao = k;
                                        break;
                                    }
                                }
                                auCidade = (AutoCompleteTextView) getSetDinamicoTelas.retornaIDCampo(viewCliente, "auCidade");
                                ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaCidade);
                                auCidade.setAdapter(adapter);
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, listaCidade.get(posicao).toString(), null);
                            }
                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("sp")) {
                            if (fieldListPassar.get(i).getName().toLowerCase().equals("spposicao")) {

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
                            } else if (fieldListPassar.get(i).getName().toLowerCase().equals("spcodvendedor")) {
                                Vendedor vendedor = new Vendedor();
                                List<Vendedor> vendedorList = vendedor.retornaListaVendedor(getContext());
                                List<String> stringList = new ArrayList<>();
                                int posicao = 0;
                                for (int k = 0; vendedorList.size() > k; k++) {
                                    stringList.add(vendedorList.get(k).toString());
                                }
                                if (cursor.getString(cursor.getColumnIndex("codvendedor")) != null) {
                                    for (int k = 0; vendedorList.size() > k; k++) {
                                        if (cursor.getString(cursor.getColumnIndex("codvendedor")).equals(vendedorList.get(k).getCodvendedor().toString())) {
                                            posicao = k;
                                            break;
                                        }
                                    }
                                    getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, stringList, getContext(), posicao);
                                }else   {
                                    getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, stringList, getContext(), 0);
                                }
                            }
                        } else if (fieldListPassar.get(i).getName().substring(0, 2).equals("li")) {
                            List<ClienteAnimais> clienteAnimaisList = new ClienteAnimais().retornaListaClienteAnimais(getContext(), codigoCliente);
                            ArrayAdapter<ClienteAnimais> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, clienteAnimaisList);
                            listClienteanimais.setAdapter(adapter);
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
                    } else if (fieldListPassar.get(i).getName().equals("auCidade")) {
                        Cursor cursorCidade = cidade.retornaCidade(getContext());
                        int posicao = 0;
                        auCidade = (AutoCompleteTextView) getSetDinamicoTelas.retornaIDCampo(viewCliente, "auCidade");
                        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaCidade);
                        auCidade.setAdapter(adapter);
                        if (listaCidade.isEmpty()) {
                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", null);
                        } else {
                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, "", null);
                        }

                    } else if (fieldListPassar.get(i).getName().equals("spPosicao")) {

                        int posicao = 0;

                        List<String> fieldListPosicao = new ArrayList<>();
                        fieldListPosicao.add("1 - Ótimo");
                        fieldListPosicao.add("2 - Bom");
                        fieldListPosicao.add("3 - Regular");
                        fieldListPosicao.add("4 - Ruim");
                        posicao = 0;
                        getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, fieldListPosicao, getContext(), posicao);
                    } else if (fieldListPassar.get(i).getName().toLowerCase().equals("spcodvendedor")) {
                        Vendedor vendedor = new Vendedor();
                        List<Vendedor> vendedorList = vendedor.retornaListaVendedor(getContext());
                        List<String> stringList = new ArrayList<>();
                        int posicao = 0;
                        for (int k = 0; vendedorList.size() > k; k++) {
                            stringList.add(vendedorList.get(k).toString());
                        }
                        getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), viewCliente, stringList, getContext(), posicao);

                    }
                }
            }
        }

        txCpfCnpj = (EditText) viewCliente.findViewById(R.id.txCpfCnpj);
        txCpfCnpj.addTextChangedListener(Mascara.cpfcnpj(txCpfCnpj));
        //CLIQUE PARA BUSCAR DADOS NO SEFAZ ATRAVÉZ DO SERVIDOR
        txCpfCnpj = (EditText) viewCliente.findViewById(R.id.txCpfCnpj);
//        txCpfCnpj.setText("14412635000100");
        txCpfCnpj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (txCpfCnpj.getText().length() == 14 || txCpfCnpj.getText().length() == 18) {
                        Cliente clienteTeste = new Cliente();
                        clienteTeste = clienteTeste.retornaClienteObjetoCpfCnpj(getContext(), txCpfCnpj.getText().toString());

                        if (clienteTeste.getNomecliente() != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setTitle("Cadastro duplicado");
                            builder.setMessage("O cliente " + clienteTeste.getNomecliente() + ", já possui cadastro, deseja localizar?");

                            final Cliente finalClienteTeste = clienteTeste;
                            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {

                                    ClienteDados clienteDados1 = new ClienteDados();
                                    Bundle bundle = new Bundle();
                                    bundle.putLong("codigo", finalClienteTeste.getCodigo());
                                    clienteDados1.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, clienteDados1, clienteDados1.getTag()).commit();
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
                        }
                    } else {

                    }

                }
            }
        });
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
                            nomecampo = "auCidade";
                        }
                        if (nomecampo.equals("posicao")) {
                            nomecampo = "Posicao";
                            spPosicao = (Spinner) viewCliente.findViewById(R.id.spPosicao);
                            valorCampo = getSetDinamicoTelas.retornaValorSpinner(viewCliente, nomecampo);
                        } else if (nomecampo.equals("codvendedor")) {
                            nomecampo = "Codvendedor";
                            spPosicao = (Spinner) viewCliente.findViewById(R.id.spCodvendedor);
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
                    if (cliente.getNomecliente() != null) {
                        cliente.setNomecliente(cliente.getNomecliente().toUpperCase());
                    }
                    if (cliente.getEndereco() != null) {
                        cliente.setEndereco(cliente.getEndereco().toUpperCase());
                    }
                    if (cliente.getBairro() != null) {
                        cliente.setBairro(cliente.getBairro().toUpperCase());
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
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txincest")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getIe(), null);
                                        } else if (fieldListPassar.get(i).getName().toLowerCase().equals("txbairro")) {
                                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, infCadastros.get(0).getXbairro(), null);
                                        }

                                    } else if (fieldListPassar.get(i).getName().toLowerCase().equals("aucidade")) {
                                        Sessao.setaContext(getContext());
                                        List<Cidade> listaCidadeNova = Sessao.retornaListaCidade();
                                        int posicao = 0;
                                        for (int k = 0; listaCidadeNova.size() > k; k++) {
                                            if (infCadastros.get(0).getCmun().equals(listaCidadeNova.get(k).getCodnacionalcidade())) {
                                                posicao = k;
                                                break;
                                            }
                                        }

                                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), viewCliente, fieldListPassar, listaCidadeNova.get(posicao).toString(), null);
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        CriaEmail criaEmail = new CriaEmail();
                        Mac mac = new Mac();
                        criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), ex.getMessage());
                    }
                }
            }
        });

        listClienteanimais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClienteAnimaisFragment clienteAnimaisFragment = new ClienteAnimaisFragment();
                ClienteAnimais  clienteAnimais = (ClienteAnimais) listClienteanimais.getItemAtPosition(i);
                Bundle bundle1 = new Bundle();
                bundle1.putLong("codigo", codigoCliente);
                bundle1.putLong("idclienteanimal", clienteAnimais.getIdclienteanimal());
                clienteAnimaisFragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clienteAnimaisFragment, clienteAnimaisFragment.getTag()).commit();
            }
        });

        listClienteanimais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClienteAnimaisFragment clienteAnimaisFragment = new ClienteAnimaisFragment();
                ClienteAnimais  clienteAnimais = (ClienteAnimais) listClienteanimais.getItemAtPosition(i);
                clienteAnimais.removeAnimal(getContext(), clienteAnimais.getIdclienteanimal());
                ClienteDados clienteDados = new ClienteDados();
                Bundle bundle = new Bundle();
                bundle.putLong("codigo", codigoCliente);
                clienteDados.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
                return true;
            }
        });

        btCadastroanimais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteAnimaisFragment clienteAnimaisFragment = new ClienteAnimaisFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putLong("codigo", codigoCliente);
                clienteAnimaisFragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clienteAnimaisFragment, clienteAnimaisFragment.getTag()).commit();
            }
        });


        EmiteConfigura emiteConfigura = new EmiteConfigura().retornaEmiteConfiguraObjeto(getContext(), 1L);
        if (!emiteConfigura.getEmpresapet()){
            listClienteanimais.setVisibility(View.INVISIBLE);
            btCadastroanimais.setVisibility(View.INVISIBLE);
        }
        return viewCliente;


    }


}
