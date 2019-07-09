package com.example.jose.connectdrawer.Parcelas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jose.connectdrawer.Email.CriaEmail;
import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.Pedido.PedidoDados;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.Mac;
import com.example.jose.connectdrawer.uteis.Mascara;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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
    private Button btSalvar;
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
            final Parcelas parcelas = new Parcelas();
            final Long codigoPedido = bundle.getLong("codigo");
            List<Parcelas> parcelasList = new ArrayList<>();
            txCodpedido = view.findViewById(R.id.txCodpedido);
            txDataVencimento = view.findViewById(R.id.txDataVencimento);
            txNumeroparcelas = view.findViewById(R.id.txNumeroparcelas);
            btGerarParcelas = view.findViewById(R.id.btgerarparcelas);
            btSalvar = view.findViewById(R.id.btSalvar);
            listaParcelas = view.findViewById(R.id.listaParcelas);
            txCodpedido.setText(codigoPedido.toString());
            txDataVencimento.addTextChangedListener(Mascara.datas(txDataVencimento));
            if (parcelas.retornaExistenciaParcelas(getContext(), codigoPedido)) {
                parcelasList = parcelas.retornaListaDeParcelas(getContext(), codigoPedido);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Parcelas parcelas1 = parcelasList.get(0);
                Long data = parcelasList.get(0).getDvenci().getTime();
                txDataVencimento.setText(simpleDateFormat.format(data));
                txNumeroparcelas.setText(String.valueOf(parcelasList.size()));
                ArrayAdapter<Parcelas> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, parcelasList);
                listaParcelas.setEmptyView(view.findViewById(R.id.semdados));
                listaParcelas.setAdapter(adapter);
            } else {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                calendar.add(Calendar.MONTH, 1);
                Date data = new Date();
                data = calendar.getTime();
                txDataVencimento.setText(simpleDateFormat.format(data));
                txNumeroparcelas.requestFocus();
            }
            btGerarParcelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        MostraToast mostraToast = new MostraToast();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date dataVencimento = simpleDateFormat.parse(txDataVencimento.getText().toString());
                        if (dataVencimento.getTime() <= new Date().getTime()) {
                            mostraToast.mostraToastLong(getContext(), "A data de vencimento deve ser maior que a data atual.");
                            txDataVencimento.requestFocus();
                        } else {
                            List<Parcelas> parcelasList = new ArrayList<>();
                            parcelasList.clear();
                            parcelas.limpaParcelas(getContext(), codigoPedido);
                            Integer numeroDeParcelas = Integer.parseInt(txNumeroparcelas.getText().toString());
                            Parcelas parcelas = new Parcelas();
                            Double resto = 0D;
                            Pedido pedido = new Pedido();
                            pedido = pedido.retornaPedidoObjeto(getContext(), codigoPedido);
                            Calendar calendar = Calendar.getInstance();
                            Date data = new Date();
                            resto = pedido.getValortotal();
                            for (int i = 0; i < numeroDeParcelas; i++) {
                                parcelas = new Parcelas();
                                parcelas.setCodPedido(codigoPedido.toString());
                                calendar.add(Calendar.MONTH, i);
                                data = calendar.getTime();
                                parcelas.setDvenci(data);
                                if (i == Integer.parseInt(txNumeroparcelas.getText().toString()) - 1) {
                                    parcelas.setVparce(resto);
                                } else {
                                    parcelas.setVparce(pedido.getValortotal() / numeroDeParcelas);
                                    resto -= parcelas.getVparce();
                                }
                                parcelas.setFatura(codigoPedido + "/" + (i + 1));
                                long diffInMillies = Math.abs(new Date().getTime() - parcelas.getDvenci().getTime());
                                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                                parcelas.setDiave(diff);
                                parcelas.cadastraParcela(getContext(), parcelas);
                                parcelasList.add(parcelas);
                            }


                            ArrayAdapter<Parcelas> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, parcelasList);
                            listaParcelas.setEmptyView(v.findViewById(R.id.semdados));
                            listaParcelas.setAdapter(adapter);
                        }

                        ParcelasDados parcelas = new ParcelasDados();
                        Bundle bundle = new Bundle();
                        bundle.putLong("codigo", codigoPedido);
                        parcelas.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, parcelas, parcelas.getTag()).commit();
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

            txDataVencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean b) {
                    try {
                        MostraToast mostraToast = new MostraToast();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date data = simpleDateFormat.parse(txDataVencimento.getText().toString());
                        if (data.getTime() <= new Date().getTime()) {
                            mostraToast.mostraToastLong(getContext(), "A data de vencimento deve ser maior que a data atual.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PedidoDados pedidoDados = new PedidoDados();
                    Bundle bundle = new Bundle();
                    bundle.putLong("codigo", codigoPedido);
                    pedidoDados.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
                }
            });

        } catch (Exception ex) {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            CriaEmail criaEmail = new CriaEmail();
            Mac mac = new Mac();
//            criaEmail.enviarEmail(getContext(), mac.retornaMac(getContext()), errors.toString());
            ex.printStackTrace();
        }

        listaParcelas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getFragmentManager();
                Parcelas parcelas = (Parcelas) listaParcelas.getItemAtPosition(i);
                ParcelaTela parcelasTela = new ParcelaTela();
                Bundle bundle = new Bundle();
                bundle.putLong("idparcela", parcelas.getIdParcela());
                bundle.putLong("idpedido", Long.parseLong(parcelas.getCodPedido()));
                parcelasTela.setArguments(bundle);
                parcelasTela.show(fragmentManager, "Parcelas");

            }
        });
        return view;
    }

}
