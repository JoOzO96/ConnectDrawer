package com.example.jose.connectdrawer.Vendedor;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.Pedido.PedidoDados;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.cliente.ClienteDados;
import com.example.jose.connectdrawer.cliente.ClienteFragment;
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
public class VendedorDados extends Fragment {
    private EditText txCodvendedor;
    private EditText txNomevendedor;
    private EditText txComi;
    private EditText txComis;
    private Button btSalvar;
    private Button btCancelar;
    private List<String> camposRequeridos;
    private List<String> camposRequeridosMensagem;
    private List<String> camposRequeridosTamanho;

    public VendedorDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_vendedor_dados, container, false);
        camposRequeridos = new ArrayList<>();
        camposRequeridosMensagem = new ArrayList<>();
        camposRequeridosTamanho = new ArrayList<>();
        //PEGA AS IDS DOS CAMPOS NOS FORMULARIOS
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        btCancelar = (Button) view.findViewById(R.id.btCancelar);

        //RETORNA O CLIENTE FILTRADO PELO BUNDLE
        Bundle bundle = this.getArguments();
        String codigoVendedor = bundle.getString("codigo");
        final GetSetDinamico getSetDinamico = new GetSetDinamico();
        final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();


        // PEGA A LISTA DE CAMPOS QUE POSSUI A CLASSE
        List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(VendedorDados.class.getDeclaredFields()));
        final Vendedor vendedor = new Vendedor();
        if (Long.parseLong(codigoVendedor) > 1) {
            // codigo do pedido e maior que um, no caso, veio da lista.
            Cursor cursor = vendedor.retornaVendedorFiltradaCursor(getContext(), codigoVendedor);
            //TESTA SE O CURSOR RETORNOU ALGUM DADO
            if (cursor.getCount() > 0) {
                //CURSOR CONTEM UMA OU MAIS LINHA DE INFORMAÇÕES
                for (int i = 0; fieldListPassar.size() != i; i++) {
                    String mascara = null;
                    if (fieldListPassar.get(i).getName().toLowerCase().equals("$change") ||
                            fieldListPassar.get(i).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        //TESTA SE O CAMPO QUE ESTA PASSANDO É UM EDITTEXT
                        if (fieldListPassar.get(i).getName().substring(0, 2).equals("tx")) {
                            String tipo = getSetDinamico.retornaTipoCampo(fieldListPassar.get(i));
                            String nomecampo = "";
                            nomecampo = fieldListPassar.get(i).getName().replace("tx", "").toLowerCase();
                            Object retorno = getSetDinamico.retornaValorCursor(tipo, nomecampo, cursor);
                            if (retorno != null) {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, retorno.toString(), mascara);
                            } else {
                                getSetDinamicoTelas.colocaValorEditText(fieldListPassar.get(i), view, fieldListPassar, "", mascara);
                            }
                        }
                    }
                }
            }
        }


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VendedorDados vendedorDados = new VendedorDados();

                //PEGA A LISTA DE CAMPOS DA CLASSE
                List<Field> fieldListClasse = new ArrayList<>(Arrays.asList(VendedorDados.class.getDeclaredFields()));
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
                List<Field> fieldListObjeto = new ArrayList<Field>(Arrays.asList(Vendedor.class.getDeclaredFields()));
                if (retorno == 0) {
                    Vendedor vendedor1 = new Vendedor();
                    for (int i = 0; fieldListObjeto.size() != i; i++) {
                        String valorCampo = "";
                        String nomecampo = fieldListObjeto.get(i).getName();
                        valorCampo = getSetDinamicoTelas.retornaValorEditText(view, nomecampo);
                        if (valorCampo != null) {
                            Object teste = null;
                            teste = getSetDinamico.insereField(fieldListObjeto.get(i), vendedor1, valorCampo);
                            vendedor1 = (Vendedor) teste;
                        }
                    }
                    boolean status = vendedor.cadastraVendedor(getContext(), vendedor1);
                    if (status == true) {
                        btCancelar.performClick();
                        new MostraToast().mostraToastShort(getContext(), "O vendedor foi cadastrado com sucesso");

                    } else {
                        new MostraToast().mostraToastShort(getContext(), "Erro ao cadastrar vendedor");
                    }
                }

            }

        });


        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VendedorFragment vendedorFragment = new VendedorFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, vendedorFragment, vendedorFragment.getTag()).commit();
            }
        });
        return view;
    }

}
