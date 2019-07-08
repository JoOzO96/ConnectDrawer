package com.example.jose.connectdrawer.Parcelas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jose.connectdrawer.Email.CriaEmail;
import com.example.jose.connectdrawer.Email.EnviaEmail;
import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.Mascara;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParcelasDados extends Fragment {
    private EditText txCodpedido;
    private EditText txDataVencimento;
    private EditText txNumeroparcelas;
    private Button btGerarParcelas;
    private ListView listaParcelas;

    public ParcelasDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parcelas, container, false);
        try {
            Bundle bundle = this.getArguments();
            final Long codigoPedido = bundle.getLong("codigo");
            final List<Parcelas> parcelasList = new ArrayList<>();
            txCodpedido = view.findViewById(R.id.txCodpedido);
            txDataVencimento = view.findViewById(R.id.txDataVencimento);
            txNumeroparcelas = view.findViewById(R.id.txNumeroparcelas);
            btGerarParcelas = view.findViewById(R.id.btgerarparcelas);
            listaParcelas = view.findViewById(R.id.listaParcelas);
            txCodpedido.setText(codigoPedido.toString());
            txDataVencimento.addTextChangedListener(Mascara.datas(txDataVencimento));

            btGerarParcelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        parcelasList.clear();
                        Parcelas parcelas = new Parcelas();
                        Double resto = 0D;
                        Pedido pedido = new Pedido();
                        pedido = pedido.retornaPedidoObjeto(getContext(), codigoPedido);
                        Calendar calendar = Calendar.getInstance();
                        Date data = new Date();
                        resto = pedido.getValortotal();
                        for (int i = 0; i <= Integer.parseInt(txNumeroparcelas.getText().toString()); i++) {
                            parcelas = new Parcelas();
                            parcelas.setCodPedido(codigoPedido.toString());
                            calendar.add(Calendar.MONTH, i);
                            data = calendar.getTime();
                            parcelas.setDvenci(data);
                            if (i == Integer.parseInt(txNumeroparcelas.getText().toString()) - 1) {
                                parcelas.setVparce(resto);
                            } else {
                                parcelas.setVparce(pedido.getValortotal() / Integer.parseInt(txNumeroparcelas.getText().toString()));
                                resto -= parcelas.getVparce();
                            }
                            parcelas.setFatura(codigoPedido + "/" + i);
                            long diffInMillies = Math.abs(new Date().getTime() - parcelas.getDvenci().getTime());
                            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                            parcelas.setDiave(diff);
                            parcelas.cadastraParcela(getContext(), parcelas);
                            parcelasList.add(parcelas);
                        }


                        ArrayAdapter<Parcelas> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, parcelasList);
                        listaParcelas.setEmptyView(v.findViewById(R.id.semdados));
                        listaParcelas.setAdapter(adapter);
                    } catch (Exception ex) {
                        StringWriter errors = new StringWriter();
                        ex.printStackTrace(new PrintWriter(errors));
                        CriaEmail criaEmail = new CriaEmail();
                        Mac mac = new Mac();
                        criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), errors.toString());
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception ex) {
            CriaEmail criaEmail = new CriaEmail();
            Mac mac = new Mac();
            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), ex.getMessage());
        }
        return view;
    }

}
