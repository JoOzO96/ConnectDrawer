package com.example.jose.connectdrawer.cidade;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.mascara.Mascara;

/**
 * A simple {@link Fragment} subclass.
 */
public class CidadeDados extends Fragment {
    private EditText txCodCidade;
    private EditText txNomeCidade;
    private EditText txUf;
    private EditText txCodNacionalUf;
    private EditText txCodNacionalCidade;
    private EditText txPais;
    private EditText txCodNacionalPais;
    private EditText txCep;

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
        txCodNacionalUf = (EditText) view.findViewById(R.id.txCodNacionalUf);
        txCodNacionalCidade = (EditText) view.findViewById(R.id.txCodNacionalCidade);
        txPais = (EditText) view.findViewById(R.id.txPais);
        txCodNacionalPais = (EditText) view.findViewById(R.id.txCodNacionalPais);
        txCep = (EditText) view.findViewById(R.id.txCep);
        txCep.addTextChangedListener(Mascara.insert("#####-###", txCep));

        Bundle bundle = this.getArguments();
        Long codigoCidade = bundle.getLong("codigo");
        Cidade cidade = new Cidade();
        Cursor cursor= cidade.retornaCidadeFiltradaCursor(getContext(), codigoCidade);

        if (cursor.getCount() > 0){
            if (cursor.getString(cursor.getColumnIndex("codCidade"))!=null){
                txCodCidade.setText(cursor.getString(cursor.getColumnIndex("codCidade")));
            }else{
                txCodCidade.setText("");
            }
            if (cursor.getString(cursor.getColumnIndex("NomeCidade"))!=null){
                txNomeCidade.setText(cursor.getString(cursor.getColumnIndex("NomeCidade")));
            }else{
                txNomeCidade.setText("");
            }
            if (cursor.getString(cursor.getColumnIndex("Uf"))!=null){
                txUf.setText(cursor.getString(cursor.getColumnIndex("Uf")));
            }else{
                txUf.setText("");
            }
            if (cursor.getString(cursor.getColumnIndex("NomeCidade"))!=null){
                txNomeCidade.setText(cursor.getString(cursor.getColumnIndex("NomeCidade")));
            }else{
                txNomeCidade.setText("");
            }
            txUf.setText("");
            txCodNacionalUf.setText("");
            txCodNacionalCidade.setText("");
            txPais.setText("");
            txCodNacionalPais.setText("");
            txCep.setText("");


        }else{
            txCodCidade.setText("");
            txNomeCidade.setText("");
            txUf.setText("");
            txCodNacionalUf.setText("");
            txCodNacionalCidade.setText("");
            txPais.setText("");
            txCodNacionalPais.setText("");
            txCep.setText("");

        }



        return view;
    }

}
