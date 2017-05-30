package com.example.jose.connectdrawer.cidade;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.Mascara;
import com.example.jose.connectdrawer.uteis.MostraToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class CidadeDados extends Fragment {
    private EditText txCodCidade;
    private EditText txNomeCidade;
    private EditText txUf;
    private EditText txCodNacionalCidade;
    private EditText txPais;
    private EditText txCodNacionalPais;
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

        txCodCidade = (EditText) view.findViewById(R.id.txCodCidade);
        txNomeCidade = (EditText) view.findViewById(R.id.txNomeCidade);
        txUf = (EditText) view.findViewById(R.id.txUf);
        txCodNacionalCidade = (EditText) view.findViewById(R.id.txCodNacionalCidade);
        txPais = (EditText) view.findViewById(R.id.txPais);
        txCodNacionalPais = (EditText) view.findViewById(R.id.txCodNacionalPais);
        txCep = (EditText) view.findViewById(R.id.txCep);
        txCep.addTextChangedListener(Mascara.insert("#####-###", txCep));
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);

        Bundle bundle = this.getArguments();
        if (bundle == null) {
            txCodCidade.setText("");
            txNomeCidade.setText("");
            txUf.setText("");
            txCodNacionalCidade.setText("");
            txPais.setText("");
            txCodNacionalPais.setText("");
            txCep.setText("");
        } else {
            Long codigoCidade = bundle.getLong("codigo");
            Cidade cidade = new Cidade();
            Cursor cursor = cidade.retornaCidadeFiltradaCursor(getContext(), codigoCidade);

            if (cursor.getCount() > 0) {
                if (cursor.getString(cursor.getColumnIndex("codCidade")) != null) {
                    txCodCidade.setText(cursor.getString(cursor.getColumnIndex("codCidade")));
                } else {
                    txCodCidade.setText("");
                }
                if (cursor.getString(cursor.getColumnIndex("nomeCidade")) != null) {
                    txNomeCidade.setText(cursor.getString(cursor.getColumnIndex("nomeCidade")));
                } else {
                    txNomeCidade.setText("");
                }
                if (cursor.getString(cursor.getColumnIndex("uf")) != null) {
                    txUf.setText(cursor.getString(cursor.getColumnIndex("uf")));
                } else {
                    txUf.setText("");
                }
                if (cursor.getString(cursor.getColumnIndex("codNacionalCidade")) != null) {
                    txCodNacionalCidade.setText(cursor.getString(cursor.getColumnIndex("codNacionalCidade")));
                } else {
                    txCodNacionalCidade.setText("");
                }
                if (cursor.getString(cursor.getColumnIndex("pais")) != null) {
                    txPais.setText(cursor.getString(cursor.getColumnIndex("pais")));
                } else {
                    txPais.setText("");
                }
                if (cursor.getString(cursor.getColumnIndex("codNacionalPais")) != null) {
                    txCodNacionalPais.setText(cursor.getString(cursor.getColumnIndex("codNacionalPais")));
                } else {
                    txCodNacionalPais.setText("");
                }
                if (cursor.getString(cursor.getColumnIndex("cep")) != null) {
                    txCep.setText(cursor.getString(cursor.getColumnIndex("cep")));
                } else {
                    txCep.setText("");
                }

            } else {
                txCodCidade.setText("");
                txNomeCidade.setText("");
                txUf.setText("");
                txCodNacionalCidade.setText("");
                txPais.setText("");
                txCodNacionalPais.setText("");
                txCep.setText("");

            }

        }


        // BOTAO SALVAR ON CLICK LISTNER
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostraToast toast = new MostraToast();
                int status = 0;
                if (txNomeCidade.length() == 0) {
                    toast.mostraToastShort(getContext(), "O nome da cidade deve ser informado");
                    status = 1;
                } else if (txUf.length() == 0) {
                    toast.mostraToastShort(getContext(), "A UF deve ser informada");
                    status = 1;
                } else if (txCodNacionalCidade.length() == 0) {
                    toast.mostraToastShort(getContext(), "O codigo nacional deve ser informado");
                    status = 1;
                } else if (txPais.length() == 0) {
                    toast.mostraToastShort(getContext(), "O pais deve ser informado");
                    status = 1;
                } else if (txCodNacionalPais.length() == 0) {
                    toast.mostraToastShort(getContext(), "O codigo do pais deve ser informado");
                    status = 1;
                }
                if (status == 0) {
                    if (txCodCidade.length() > 0) {
                        Cidade cidade = new Cidade();
                        cidade.setNomeCidade(txNomeCidade.getText().toString().toUpperCase());
                        cidade.setUF(txUf.getText().toString().toUpperCase());
                        cidade.setCodNacionalCidade(txCodNacionalCidade.getText().toString());
                        cidade.setCodNacionalUf(txCodNacionalCidade.getText().toString().substring(0, 2));
                        cidade.setCodNacionalPais(txCodNacionalPais.getText().toString());
                        cidade.setPais(txPais.getText().toString().toUpperCase());
                        cidade.setCep(txCep.getText().toString());
                        cidade.setCadastroAndroid(true);

                        cidade.setCodCidade(Long.parseLong(txCodCidade.getText().toString()));
                        boolean retorno = cidade.atualizaDados(getContext(), cidade);
                        if (retorno == false) {
                            toast.mostraToastShort(getContext(), "Erro ao atualizar cidade");
                        } else {
                            btCancelar.performClick();
                        }
                    } else {
                        Cidade cidade = new Cidade();
                        cidade.setNomeCidade(txNomeCidade.getText().toString().toUpperCase());
                        cidade.setUF(txUf.getText().toString().toUpperCase());
                        cidade.setCodNacionalCidade(txCodNacionalCidade.getText().toString());
                        cidade.setCodNacionalUf(txCodNacionalCidade.getText().toString().substring(0, 1));
                        cidade.setCodNacionalPais(txCodNacionalPais.getText().toString());
                        cidade.setPais(txPais.getText().toString().toUpperCase());
                        cidade.setCep(txCep.getText().toString());
                        cidade.setCadastroAndroid(true);

                        cidade.setCodCidade(cidade.retornaMaiorCodCidade(getContext()) + 1);
                        boolean retorno = cidade.insereDados(getContext(), cidade);
                        if (retorno == false) {
                            toast.mostraToastShort(getContext(), "Erro ao cadastrar cidade");
                        } else {
                            btCancelar.performClick();
                        }
                    }
                }
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

            if (txCodCidade.getText().toString() == "") {

            }

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cidadeDados, cidadeDados.getTag()).commit();
        } else if (id == R.id.deletar) {

        }

        return super.onOptionsItemSelected(item);
    }



}
