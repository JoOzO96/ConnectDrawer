package com.example.jose.connectdrawer.Parcelas;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jose.connectdrawer.Pedido.PedidoDados;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.Mascara;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ParcelaTela extends DialogFragment {
    private EditText txDataVencimento;
    private EditText txValorParcela;
    private EditText txDias;
    private Button btCancelar;
    private Button btSalvar;
    private Boolean evitaLoop = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.popupparcelas, container);
        txDataVencimento = view.findViewById(R.id.txDataVencimento);
        txValorParcela = view.findViewById(R.id.txValorParcela);
        txValorParcela = view.findViewById(R.id.txValorParcela);
        txDias = view.findViewById(R.id.txDias);
        btCancelar = view.findViewById(R.id.btCancelar);
        btSalvar = view.findViewById(R.id.btSalvar);
        Bundle bundle = this.getArguments();
        final Long idParcela = bundle.getLong("idparcela");
        final Long idPedido = bundle.getLong("idpedido");
        Parcelas parcelas = new Parcelas();
        parcelas = parcelas.retornaParcelasObjeto(getContext(), idParcela);
        final Parcelas finalParcela = parcelas;



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        DecimalFormat decimalFormat = new DecimalFormat("0.00");


        txDataVencimento.setText(simpleDateFormat.format(parcelas.getDvenci()));
        txValorParcela.setText(decimalFormat.format(parcelas.getVparce()));
        txDias.setText(parcelas.getDiave().toString());

        txDataVencimento.addTextChangedListener(Mascara.datas(txDataVencimento));

        txDataVencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    long diffInMillies = Math.abs(new Date().getTime() - simpleDateFormat.parse(txDataVencimento.getText().toString()).getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    txDias.setText(String.valueOf(diff));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        txDias.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, Integer.parseInt(txDias.getText().toString()));
                Date data = calendar.getTime();
                txDataVencimento.setText(simpleDateFormat.format(data));
                Mascara.datas(txDataVencimento);
            }
        });



        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MostraToast mostraToast = new MostraToast();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = simpleDateFormat.parse(txDataVencimento.getText().toString());
                    if (data.getTime() <= new Date().getTime()) {
                        mostraToast.mostraToastLong(getContext(), "A data de vencimento deve ser maior que a data atual.");
                    } else {
                        Parcelas parcelas = new Parcelas();
                        parcelas = finalParcela;
                        parcelas.setDiave(Long.parseLong(txDias.getText().toString()));
                        parcelas.setDvenci(simpleDateFormat.parse(txDataVencimento.getText().toString()));
                        parcelas.setVparce(Double.parseDouble(txValorParcela.getText().toString()));
                        finalParcela.cadastraParcela(getContext(), parcelas);
                        PedidoDados pedidoDados = new PedidoDados();
                        Bundle bundle = new Bundle();
                        bundle.putLong("codigo", idPedido);
                        pedidoDados.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        parcelas.recalculaValorParcelas(getContext(), idPedido, idParcela);
                        dismiss();
                        fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });


        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                PedidoDados pedidoDados = new PedidoDados();
                Bundle bundle = new Bundle();
                bundle.putLong("codigo", idPedido);
                pedidoDados.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
            }
        });
        return view;
    }
}
