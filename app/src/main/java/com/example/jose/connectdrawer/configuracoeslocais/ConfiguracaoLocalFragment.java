package com.example.jose.connectdrawer.configuracoeslocais;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.main.ConnectMain;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.GetSetDinamicoTelas;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracaoLocalFragment extends Fragment {
    private Spinner spCodvendedor;
    ConfiguracoesLocais configuracoesLocais = new ConfiguracoesLocais();
    List<String> vendedorList = new ArrayList<>();
    final GetSetDinamico getSetDinamico = new GetSetDinamico();
    final GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
    private Button btSalvar;
    public ConfiguracaoLocalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_configuracao_local, container, false);
        btSalvar = (Button) view.findViewById(R.id.btSalvar);
        final List<Field> fieldListPassar = new ArrayList<>(Arrays.asList(ConfiguracaoLocalFragment.class.getDeclaredFields()));
        final List<Field> fieldListPassarClasse = new ArrayList<>(Arrays.asList(ConfiguracoesLocais.class.getDeclaredFields()));
        configuracoesLocais = configuracoesLocais.retornaconfiguracoeslocais(getContext());
        for (int i = 0; fieldListPassar.size() != i; i++) {
            if (fieldListPassar.get(i).getName().substring(0, 2).equals("sp")){
                if (fieldListPassar.get(i).getName().equals("spCodvendedor")) {
                    Vendedor vendedor = new Vendedor();
                    int posicao = 0;
                    Cursor cursorVendedor = vendedor.retornaVendedor(getContext());
                    //TESTA SE A CONSULTA RETORNA ALGUM RESULTADO
                    if (cursorVendedor.getCount() > 0) {
                        //posição do spinner para sair selecionado
                        List<Field> fieldListVendedor = new ArrayList<>(Arrays.asList(Vendedor.class.getDeclaredFields()));
                        for (int f = fieldListVendedor.size() - 1; -1 != f; f--) {
                            if (fieldListVendedor.get(f).getName().toLowerCase().equals("codvendedor") || fieldListVendedor.get(f).getName().toLowerCase().equals("nomevendedor")) {
                            } else {
                                fieldListVendedor.remove(f);
                            }
                        }
                        for (int j = 0; cursorVendedor.getCount() != j; j++) {
                            vendedor = new Vendedor();
                            for (int f = 0; fieldListVendedor.size() != f; f++) {
                                String tipo = getSetDinamico.retornaTipoCampo(fieldListVendedor.get(f));
                                String nomeCampo = fieldListVendedor.get(f).getName().toLowerCase();
                                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursorVendedor);
                                if (retorno != null) {
                                    Object retVendedor = getSetDinamico.insereField(fieldListVendedor.get(f), vendedor, retorno);
                                    vendedor = (Vendedor) retVendedor;
                                }
                            }
                            vendedorList.add(vendedor.toString());
                            cursorVendedor.moveToNext();
                        }
                    }
                    for (int k = 0; vendedorList.size() != k; k++) {
                        Integer codHifen = vendedorList.get(k).indexOf("-");
                        String codVendedor = vendedorList.get(k).substring(0, codHifen - 1);
                        if (codVendedor.equals(configuracoesLocais.getCodvendedor())) {
                            posicao = k;
                            break;
                        }
                    }
                    getSetDinamicoTelas.colocaValorSpinner(fieldListPassar.get(i), view, vendedorList, getContext(), posicao);
                }
            }
        }
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int f = 0; fieldListPassar.size() != f; f++) {

                    if (fieldListPassar.get(f).getName().toLowerCase().substring(0, 2).equals("sp")) {
                        String nomeCampo = "";
                        nomeCampo = fieldListPassar.get(f).getName();
                        if (nomeCampo.equals("spCodvendedor")) {
                            nomeCampo = "codvendedor";
                        }
                        if (!nomeCampo.equals("")) {

                            for (int p = 0; fieldListPassarClasse.size() != p; p++) {

                                if (fieldListPassarClasse.get(p).getName().toLowerCase().equals(nomeCampo)) {
                                    Object retorno = getSetDinamicoTelas.retornaValorSpinner(view, fieldListPassarClasse.get(p).getName().replace("sp", ""));
                                    if (retorno.toString().equals("ERRO")) {

                                    } else {
                                        Object retObjeto = getSetDinamico.insereField(fieldListPassarClasse.get(p), configuracoesLocais, retorno);
                                        configuracoesLocais = (ConfiguracoesLocais) retObjeto;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                configuracoesLocais.cadastraconfiguracoeslocais(getContext(), configuracoesLocais);

                MostraToast mostraToast = new MostraToast();
                mostraToast.mostraToastLong(getContext(), "Configurações salvas.");
                Intent intent = new Intent(getContext(), ConnectMain.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

}
