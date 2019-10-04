package com.example.jose.connectdrawer.cliente;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.Mascara;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteAnimaisFragment extends Fragment {

    private GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
    private GetSetDinamico getSetDinamico = new GetSetDinamico();
    private ClienteAnimais clienteAnimais = new ClienteAnimais();
    private Button btSalvar;
    private Button btCancelar;
    private TextView txCodcliente;
    private TextView txNomeanimal;
    private TextView txEspecieanimal;
    private TextView txRacaanimal;
    private TextView txPelagem;
    private TextView txPesoanimal;
    private TextView txSituacaoanimal;
    private TextView txObsanimal;
    private TextView txDatanascimentoanimal;
    private TextView txSexoanimal;
    private Long codigo;

    public ClienteAnimaisFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cliente_animais, container, false);
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(ClienteAnimaisFragment.class.getDeclaredFields()));
        btSalvar = view.findViewById(R.id.btSalvar);
        btCancelar = view.findViewById(R.id.btCancelar);
        Bundle bundle = this.getArguments();
        codigo = bundle.getLong("codigo");
        final Long idclienteanimal = bundle.getLong("idclienteanimal");
        clienteAnimais = new ClienteAnimais().retornaClienteAnimais(getContext(), idclienteanimal);
        for (int i = 0; fieldListPassar.size() != i; i++) {

            String mascara = null;
            if (fieldListPassar.get(i).getName().toLowerCase().equals("$change") ||
                    fieldListPassar.get(i).getName().toLowerCase().equals("serialversionuid")) {
            } else {
                if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                    String tipo = getSetDinamico.retornaTipoCampo(fieldListPassar.get(i));
                    String nomecampo = "";
                    nomecampo = fieldListPassar.get(i).getName().replace("tx", "").toLowerCase();
                    if (nomecampo.equals("datanascimentoanimal")) {
                        mascara = "##/##/####";
                    }
                    Object retorno = getSetDinamico.retornaValorCampoTela(fieldListPassar.get(i).getName().replace("tx", "").toLowerCase(), clienteAnimais);

                    if (retorno != null) {
                        if (nomecampo.equals("datanascimentoanimal")) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date data = new Date();
                            data = (Date) retorno;
                            if (data.getTime() == 0) {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, "", mascara);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, simpleDateFormat.format(data), mascara);
                            }
                        } else {
                            getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, retorno.toString(), mascara);
                        }

                    } else {
                        getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, "", mascara);
                    }
                }
            }
        }

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClienteDados clienteDados = new ClienteDados();
                Bundle bundle = new Bundle();
                bundle.putLong("codigo", codigo);
                clienteDados.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaCadastro(view);
                ClienteDados clienteDados = new ClienteDados();
                Bundle bundle = new Bundle();
                bundle.putLong("codigo", codigo);
                clienteDados.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clienteDados, clienteDados.getTag()).commit();
            }
        });
        return view;


    }

    private void salvaCadastro(View view) {
        List<Field> fieldList = new ArrayList<Field>(Arrays.asList(ClienteAnimais.class.getDeclaredFields()));
        String valorCampo = "";
        String nomecampo = "";
        clienteAnimais.setCodcliente(codigo);
        for (int i = 0; fieldList.size() != i; i++) {
            valorCampo = "";
            nomecampo = fieldList.get(i).getName();
            if (fieldList.get(i).getType().getSimpleName().toUpperCase().equals("BOOLEAN")) {
                valorCampo = getSetDinamicoTelas.retornaValorEditText(view, nomecampo);
            } else {
                valorCampo = getSetDinamicoTelas.retornaValorEditText(view, nomecampo);
            }
            if (fieldList.get(i).getName().equals("datanascimentoanimal")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date data = simpleDateFormat.parse(valorCampo);
                    valorCampo = String.valueOf(data.getTime());
                } catch (ParseException e) {
                    valorCampo = "0";
                }

            }

            if (valorCampo != null) {
                Object retorno = null;
                retorno = getSetDinamico.insereField(fieldList.get(i), clienteAnimais, valorCampo);
                clienteAnimais = (ClienteAnimais) retorno;
            }

        }
        clienteAnimais.cadastraClienteAnimais(getContext(), clienteAnimais);
    }
}
