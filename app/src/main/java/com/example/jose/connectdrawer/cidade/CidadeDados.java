package com.example.jose.connectdrawer.cidade;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteDados;
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
public class CidadeDados extends Fragment {
    private EditText txCodcidade;
    private EditText txNomecidade;
    private EditText txUf;
    private EditText txCodnacionalcidade;
    private EditText txPais;
    private EditText txCodnacionalpais;
    private EditText txCep;
    private Button btSalvar;
    private Button btCancelar;
    private List<String> camposRequeridos;
    private List<String> camposRequeridosMensagem;
    private List<String> camposRequeridosTamanho;

    public CidadeDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        final View view = inflater.inflate(R.layout.fragment_cidade_dados, container, false);
        camposRequeridos = new ArrayList<>();
        camposRequeridosMensagem = new ArrayList<>();
        camposRequeridosTamanho = new ArrayList<>();
        camposRequeridos.add("txNomecidade");
        camposRequeridosMensagem.add("O nome da cidade é obrigatório");
        camposRequeridosTamanho.add("5");
        camposRequeridos.add("txUf");
        camposRequeridosMensagem.add("A UF é obrigatoria");
        camposRequeridosTamanho.add("2");
        camposRequeridos.add("txCodnacionalcidade");
        camposRequeridosMensagem.add("O Codigo Nacional da Cidade é obrigado");
        camposRequeridosTamanho.add("7");
        camposRequeridos.add("txPais");
        camposRequeridosMensagem.add("O nome do país é obrigatorio");
        camposRequeridosTamanho.add("5");
        camposRequeridos.add("txCodnacionalpais");
        camposRequeridosMensagem.add("O codigo internacional do país é obrigatorio. (Brasil = 1058)");
        camposRequeridosTamanho.add("4");
        //PEGA AS IDS DOS CAMPOS NOS FORMULARIOS
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);

        List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(CidadeDados.class.getDeclaredFields()));
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
        CidadeDados cidadeDados = new CidadeDados();
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            List<Field> fieldList = new ArrayList<>(Arrays.asList(cidadeDados.getClass().getDeclaredFields()));
            for (int i = 0; fieldList.size() != i; i++) {
                String nomecampo = "";
                nomecampo = fieldList.get(i).getName().replace("tx", "").toLowerCase();
                if (fieldList.get(i).getName().toLowerCase().substring(0, 2).equals("tx")) {
                    if (nomecampo.equals("cep")) {
                        getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, "", "#####-###");
                    } else {
                        getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, "", null);
                    }
                }
            }
        } else {

            GetSetDinamico getSetDinamico = new GetSetDinamico();
            List<Field> fieldList = new ArrayList<>(Arrays.asList(cidadeDados.getClass().getDeclaredFields()));
            Long codigoCidade = bundle.getLong("codcidade");
            Cidade cidade = new Cidade();
            Cursor cursor = cidade.retornaCidadeFiltradaCursor(getContext(), codigoCidade);

            if (cursor.getCount() > 0) {
                for (int i = 0; fieldList.size() != i; i++) {
                    if (fieldList.get(i).getName().toLowerCase().equals("$change") ||
                            fieldList.get(i).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        if (fieldList.get(i).getName().substring(0, 2).equals("tx")) {
                            String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(i));
                            String nomecampo = "";
                            String mascara = null;
                            if (fieldList.get(i).getName().equals("txCep")) {
                                mascara = "#####-###";
                            }
                            nomecampo = fieldList.get(i).getName().replace("tx", "").toLowerCase();
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldList, retorno.toString(), mascara);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldList, "", mascara);
                            }
                        }
                    }
                }
            }
        }


        // BOTAO SALVAR ON CLICK LISTNER
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostraToast toast = new MostraToast();
                GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
                GetSetDinamico getSetDinamico = new GetSetDinamico();
                CidadeDados cidadeDados = new CidadeDados();

                //PEGA A LISTA DE CAMPOS DA CLASSE
                List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(CidadeDados.class.getDeclaredFields()));
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
                int retorno = camposRequeridosClass.retornaMensagemRequerido(camposRequeridos, camposRequeridosMensagem, camposRequeridosTamanho, fieldListPassar, view);
                List<Field> fieldListObjeto = new ArrayList<Field>(Arrays.asList(Cidade.class.getDeclaredFields()));

                if (retorno == 0) {
                    Cidade cidade = new Cidade();
                    for (int i = 0; fieldListObjeto.size() != i; i++) {
                        String valorCampo = "";
                        String nomecampo = fieldListObjeto.get(i).getName();
                        valorCampo = getSetDinamicoTelas.retornaValorEditText(view, nomecampo);
                        if (valorCampo != null) {
                            Object teste;
                            teste = getSetDinamico.insereField(fieldListObjeto.get(i), cidade, valorCampo);
                            cidade = (Cidade) teste;
                        }
                    }
                    boolean status = cidade.cadastraCidade(getContext(), cidade);
                    if (status == true) {
                        btCancelar.performClick();
                        new MostraToast().mostraToastShort(getContext(), "A cidade foi cadastrado com sucesso");

                    } else {
                        new MostraToast().mostraToastShort(getContext(), "Erro ao cadastrar a cidade");
                    }
                }
//                if (txNomeCidade.length() == 0) {
//                    toast.mostraToastShort(getContext(), "O nome da cidade deve ser informado");
//                    status = 1;
//                } else if (txUf.length() == 0) {
//                    toast.mostraToastShort(getContext(), "A UF deve ser informada");
//                    status = 1;
//                } else if (txCodNacionalCidade.length() == 0) {
//                    toast.mostraToastShort(getContext(), "O codigo nacional deve ser informado");
//                    status = 1;
//                } else if (txPais.length() == 0) {
//                    toast.mostraToastShort(getContext(), "O pais deve ser informado");
//                    status = 1;
//                } else if (txCodNacionalPais.length() == 0) {
//                    toast.mostraToastShort(getContext(), "O codigo do pais deve ser informado");
//                    status = 1;
//                }
//                if (status == 0) {
//                    if (txCodCidade.length() > 0) {
//                        Cidade cidade = new Cidade();
//                        cidade.setNomecidade(txNomeCidade.getText().toString().toUpperCase());
//                        cidade.setUf(txUf.getText().toString().toUpperCase());
//                        cidade.setCodnacionalcidade(txCodNacionalCidade.getText().toString());
//                        cidade.setCodnacionaluf(txCodNacionalCidade.getText().toString().substring(0, 2));
//                        cidade.setCodnacionalpais(txCodNacionalPais.getText().toString());
//                        cidade.setPais(txPais.getText().toString().toUpperCase());
//                        cidade.setCep(txCep.getText().toString());
//                        cidade.setCadastroandroid(true);
//
//                        cidade.setCodcidade(Long.parseLong(txCodCidade.getText().toString()));
//                        boolean retorno = cidade.atualizaDados(getContext(), cidade);
//                        if (retorno == false) {
//                            toast.mostraToastShort(getContext(), "Erro ao atualizar cidade");
//                        } else {
//                            btCancelar.performClick();
//                        }
//                    } else {
//                        Cidade cidade = new Cidade();
//                        cidade.setNomecidade(txNomeCidade.getText().toString().toUpperCase());
//                        cidade.setUf(txUf.getText().toString().toUpperCase());
//                        cidade.setCodnacionalcidade(txCodNacionalCidade.getText().toString());
//                        cidade.setCodnacionaluf(txCodNacionalCidade.getText().toString().substring(0, 2));
//                        cidade.setCodnacionalpais(txCodNacionalPais.getText().toString());
//                        cidade.setPais(txPais.getText().toString().toUpperCase());
//                        cidade.setCep(txCep.getText().toString());
//                        cidade.setCadastroandroid(true);
//
//                        cidade.setCodcidade(cidade.retornaMaiorCodCidade(getContext()) + 1);
//                        boolean retorno = cidade.insereDados(getContext(), cidade);
//                        if (retorno == false) {
//                            toast.mostraToastShort(getContext(), "Erro ao cadastrar cidade");
//                        } else {
//                            btCancelar.performClick();
//                        }
//                    }
//                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                CidadeFragment cidadeFragment = new CidadeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, cidadeFragment, cidadeFragment.getTag()).commit();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_salvar_deletar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.salvar) {
            CidadeDados cidadeDados = new CidadeDados();

//            if (txCodCidade.getText().toString() == "") {
//
//            }

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cidadeDados, cidadeDados.getTag()).commit();
        } else if (id == R.id.deletar) {

        }

        return super.onOptionsItemSelected(item);
    }


}
