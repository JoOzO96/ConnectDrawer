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

    public CidadeDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cidade_dados, container, false);

        //PEGA AS IDS DOS CAMPOS NOS FORMULARIOS

        txCodcidade = (EditText) view.findViewById(R.id.txCodcidade);
        txNomecidade = (EditText) view.findViewById(R.id.txNomecidade);
        txUf = (EditText) view.findViewById(R.id.txUf);
        txCodnacionalcidade = (EditText) view.findViewById(R.id.txCodnacionalcidade);
        txPais = (EditText) view.findViewById(R.id.txPais);
        txCodnacionalpais = (EditText) view.findViewById(R.id.txCodnacionalpais);
        txCep = (EditText) view.findViewById(R.id.txCep);
        txCep.addTextChangedListener(Mascara.insert("#####-###", txCep));
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

        Bundle bundle = this.getArguments();
        if (bundle == null) {
            txCodcidade.setText("");
            txNomecidade.setText("");
            txUf.setText("");
            txCodnacionalcidade.setText("");
            txPais.setText("");
            txCodnacionalpais.setText("");
            txCep.setText("");
        } else {
            CidadeDados cidadeDados = new CidadeDados();
            GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            List<Field> fieldList = new ArrayList<>(Arrays.asList(cidadeDados.getClass().getDeclaredFields()));
            Long codigoCidade = bundle.getLong("codigo");
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
                            nomecampo = fieldList.get(i).getName().replace("tx", "").toLowerCase();
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, retorno.toString(), null);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldList.get(i), view, fieldListPassar, "", null);
                            }
                        }
                    }
                }
            }


//            if (cursor.getCount() > 0) {
//                if (cursor.getString(cursor.getColumnIndex("codcidade")) != null) {
//                    txCodcidade.setText(cursor.getString(cursor.getColumnIndex("codcidade")));
//                } else {
//                    txCodcidade.setText("");
//                }
//                if (cursor.getString(cursor.getColumnIndex("nomecidade")) != null) {
//                    txNomeCidade.setText(cursor.getString(cursor.getColumnIndex("nomecidade")));
//                } else {
//                    txNomeCidade.setText("");
//                }
//                if (cursor.getString(cursor.getColumnIndex("uf")) != null) {
//                    txUf.setText(cursor.getString(cursor.getColumnIndex("uf")));
//                } else {
//                    txUf.setText("");
//                }
//                if (cursor.getString(cursor.getColumnIndex("codnacionalcidade")) != null) {
//                    txCodNacionalCidade.setText(cursor.getString(cursor.getColumnIndex("codnacionalcidade")));
//                } else {
//                    txCodNacionalCidade.setText("");
//                }
//                if (cursor.getString(cursor.getColumnIndex("pais")) != null) {
//                    txPais.setText(cursor.getString(cursor.getColumnIndex("pais")));
//                } else {
//                    txPais.setText("");
//                }
//                if (cursor.getString(cursor.getColumnIndex("codnacionalpais")) != null) {
//                    txCodNacionalPais.setText(cursor.getString(cursor.getColumnIndex("codnacionalpais")));
//                } else {
//                    txCodNacionalPais.setText("");
//                }
//                if (cursor.getString(cursor.getColumnIndex("cep")) != null) {
//                    txCep.setText(cursor.getString(cursor.getColumnIndex("cep")));
//                } else {
//                    txCep.setText("");
//                }
//
//            } else {
//                txCodCidade.setText("");
//                txNomeCidade.setText("");
//                txUf.setText("");
//                txCodNacionalCidade.setText("");
//                txPais.setText("");
//                txCodNacionalPais.setText("");
//                txCep.setText("");
//
//            }

        }


        // BOTAO SALVAR ON CLICK LISTNER
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostraToast toast = new MostraToast();
                int status = 0;
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
