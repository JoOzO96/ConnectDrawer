package com.example.jose.connectdrawer.uteis;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.support.annotation.RestrictTo;
import android.util.Log;

import com.example.jose.connectdrawer.Emitente.EmiteConfigura;
import com.example.jose.connectdrawer.Emitente.Emitente;
import com.example.jose.connectdrawer.Icms.Icms;
import com.example.jose.connectdrawer.NotaFiscal.NotaFiscal;
import com.example.jose.connectdrawer.NotaProduto.NotaProduto;
import com.example.jose.connectdrawer.Parcelas.Parcelas;
import com.example.jose.connectdrawer.ParcelasNFE.ParcelaNFE;
import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.Produto.Produto;
import com.example.jose.connectdrawer.cliente.Cliente;
import com.example.jose.connectdrawer.sincronizacao.RetRetrofit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by Jose on 02/06/2017.
 */

public class GetSetDinamico {

    public Object insereField(Field field, Object objetoRecebido, Object recebido) {
        String primeiro = field.getName().substring(0, 1);
        String nomeCampo = field.getName().substring(1, field.getName().length());
//        Object objetoInstanciado = null;
        try {
//            objetoInstanciado = Class.forName(objetoRecebido.getClass().toString().replace("class ","")).newInstance();
//            objetoInstanciado = objetoRecebido;
            Class[] cArg = new Class[1];
            if (recebido != null) {
                if (field.getType().getSimpleName().toUpperCase().equals("STRING") || field.getType().getSimpleName().toUpperCase().equals("EDITTEXT")) {
                    cArg[0] = String.class;
                    Method method = objetoRecebido.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoRecebido, recebido.toString());
                } else if (field.getType().getSimpleName().toUpperCase().equals("DATE")) {
                    cArg[0] = Date.class;
                    Method method = objetoRecebido.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    Date data = new Date();
                    data.setTime(Long.parseLong(recebido.toString()));
                    method.invoke(objetoRecebido, data);
                } else if (field.getType().getSimpleName().toUpperCase().equals("LONG")) {
                    cArg[0] = Long.class;
                    Method method = objetoRecebido.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoRecebido, Long.parseLong(recebido.toString()));
                } else if (field.getType().getSimpleName().toUpperCase().equals("DOUBLE")) {
                    cArg[0] = Double.class;
                    Method method = objetoRecebido.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoRecebido, Double.parseDouble(recebido.toString()));
                } else if (field.getType().getSimpleName().toUpperCase().equals("BOOLEAN")) {
                    cArg[0] = Boolean.class;
                    Method method = objetoRecebido.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoRecebido, Boolean.parseBoolean(recebido.toString()));
                }
            }
            return objetoRecebido;
        } catch (IllegalAccessException e) {
            Log.i("IllegalAccessException", "ACESSO ILEGAL");
        } catch (NoSuchMethodException e) {
            Log.i("NoSuchMethodException", "METODO NAO ENCONTRADO," + primeiro.toUpperCase() + nomeCampo);
        } catch (SecurityException e) {

        } catch (IllegalArgumentException e) {

        } catch (InvocationTargetException e) {

//        } catch (ClassNotFoundException e) {
//
//        } catch (InstantiationException e) {

        }
        return objetoRecebido;
    }

    public String retornaTipoCampo(Field field) {
        String tipo = field.getType().getSimpleName().toUpperCase();
        return tipo;
    }

    public Object retornaValorCursor(String tipo, String nome, Cursor cursor1) {
        Object objeto = null;
        if (nome.equals("$change") || nome.equals("serialversionuid") || nome.equals("context")) {

        } else {
            if (tipo.equals("STRING") || tipo.equals("EDITTEXT")) {
                objeto = cursor1.getString(cursor1.getColumnIndex(nome));
            } else if (tipo.equals("LONG")) {
                objeto = cursor1.getLong(cursor1.getColumnIndex(nome));
            } else if (tipo.equals("DATE")) {
                objeto = cursor1.getLong(cursor1.getColumnIndex(nome));
            } else if (tipo.equals("INT")) {
                objeto = cursor1.getInt(cursor1.getColumnIndex(nome));
            } else if (tipo.equals("DOUBLE")) {
                objeto = cursor1.getDouble(cursor1.getColumnIndex(nome));
            } else if (tipo.equals("BOOLEAN") || tipo.equals("CHECKBOX")) {
                objeto = cursor1.getInt(cursor1.getColumnIndex(nome)) == 1;
            }
        }
        return objeto;
    }


    public Object setValorObjetoCursor(Field field, Object object, Cursor cursor) {
        String tipo = retornaTipoCampo(field);
        Object retorno = null;
        if (field.getName().toLowerCase().equals("$change") || field.getName().toLowerCase().equals("serialversionuid") || field.getName().toLowerCase().equals("context")) {
        } else {

            if (tipo.equals("STRING")) {
                retorno = cursor.getString(cursor.getColumnIndex(field.getName().toLowerCase()));
            } else if (tipo.equals("LONG")) {
                retorno = cursor.getLong(cursor.getColumnIndex(field.getName().toLowerCase()));
            } else if (tipo.equals("DOUBLE")) {
                retorno = cursor.getDouble(cursor.getColumnIndex(field.getName().toLowerCase()));
            } else if (tipo.equals("BOOLEAN")) {
                retorno = cursor.getInt(cursor.getColumnIndex(field.getName().toLowerCase())) == 0;
            }
            object = insereField(field, object, retorno);
        }
        return object;
    }

    public Object retornaValorCampo(Field field, Object obj) {
        try {
//            String primeiro = field.getName().substring(0, 1);
//            String nomeCampo = field.getName().substring(1, field.getName().length());
////            Object obj1;
////            obj1 = obj;
            Method method = obj.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length()), null);
            Object object = method.invoke(obj, null);

            return object;
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return field;
    }

    public Object retornaValorCampoTela(String field, Object obj) {
        try {
//            String primeiro = field.getName().substring(0, 1);
//            String nomeCampo = field.getName().substring(1, field.getName().length());
////            Object obj1;
////            obj1 = obj;
            Method method = obj.getClass().getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1), null);
            Object object = method.invoke(obj, null);

            return object;
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return field;
    }

    public Object colocaDadosNotaFiscal(Context context, NotaFiscal notaFiscal, String numeroPedido, Long codigoBanco) {
        try {

            GetSetDinamico getSetDinamico = new GetSetDinamico();
            Cliente cliente = new Cliente();
            Pedido pedido = new Pedido();
            NotaProduto notaProduto = new NotaProduto();
            PedidoProduto pedidoProduto = new PedidoProduto();
            Produto produto;
            Emitente emitente = new Emitente();
            EmiteConfigura emiteConfigura = new EmiteConfigura();
            emitente = emitente.retornaEmitenteObjeto(context, 1L);
            emiteConfigura = emiteConfigura.retornaEmiteConfiguraObjeto(context, 1L);
            List<Field> fieldsPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
            Cursor cursorPedido = pedido.retornaPedidoFiltradaCursor(context, Long.parseLong(numeroPedido));
            List<NotaProduto> notaProdutoList = new ArrayList<>();

            for (int i = 0; fieldsPedido.size() > i; i++) {
                if (fieldsPedido.get(i).getName().toLowerCase().equals("$change") ||
                        fieldsPedido.get(i).getName().toLowerCase().equals("serialversionuid")) {
                } else {
                    pedido = (Pedido) getSetDinamico.setValorObjetoCursor(fieldsPedido.get(i), pedido, cursorPedido);
                }
            }
            cliente = cliente.retornaClienteObjeto(context, pedido.getCodcliente());

            if (cursorPedido.getCount() > 0) {

                if (pedido.getNotafisca() == null) {
                    if (codigoBanco > 0) {
                        String codNota = notaFiscal.retornaCodNota(context);
                        notaFiscal.setCodnota(notaFiscal.formataCodNota(codigoBanco.toString()));
                    }
                } else {
                    if (pedido.getNotafisca().equals("")) {
                        if (codigoBanco > 0) {
                            String codNota = notaFiscal.retornaCodNota(context);
                            notaFiscal.setCodnota(notaFiscal.formataCodNota(codigoBanco.toString()));
                        }
                    } else {
                        notaFiscal = notaFiscal.retornaObjetoNota(context, notaFiscal.retornaIdnota(context, pedido.getNotafisca()));

                        notaFiscal.setCodnota(pedido.getNotafisca());
                        if (notaFiscal.getProtocolo() != null) {
                            notaFiscal.setNomecliente("NOTA JA ENVIADA");
                            return notaFiscal;
                        } else {

                        }
                    }

                }

                notaFiscal.setCodtipo(Long.parseLong(emiteConfigura.getCodnaturezavendadireta()));
                notaFiscal.setCodtransportador(1L);
                notaFiscal.setCodemitente(1L);
                notaFiscal.setCodcliente(pedido.getCodcliente());
                notaFiscal.setNomecliente(cliente.getNomecliente());
                notaFiscal.setEmidesti("0");
                if (cliente.getCpf() != null) {
                    notaFiscal.setCpf(cliente.getCpf());
                    notaFiscal.setMarc(true);
                } else {
                    notaFiscal.setCnpj(cliente.getCgc());
                }
                notaFiscal.setCodcidade(cliente.getCodcidade());
                notaFiscal.setNumero(cliente.getNume());
                notaFiscal.setInscesta(cliente.getIncest());
                notaFiscal.setMarc(false);
                notaFiscal.setCnpj(cliente.getCgc());
                notaFiscal.setEndereco(cliente.getEndereco());
                notaFiscal.setCep(cliente.getCep());
                notaFiscal.setBairro(cliente.getBairro());
                notaFiscal.setFonefax(cliente.getTelefone());
                notaFiscal.setSaida("1");
                notaFiscal.setDataemissao(new Date());
                notaFiscal.setDatasaida(new Date());
                notaFiscal.setHora(new Date());
                notaFiscal.setCodpgto(pedido.getPgto());
                notaFiscal.setFinalidade("1");
                notaFiscal.setNorconti("1");
                notaFiscal.setBaseicms(0D);
                notaFiscal.setValoricms(0D);
                notaFiscal.setIcmssub(0D);
                notaFiscal.setValoricmssub(0D);
                notaFiscal.setValorseguro(0D);
                notaFiscal.setValordoipi(0D);
                notaFiscal.setValordosprodutos(0D);
                notaFiscal.setValornota(0D);
                if (cliente.getEmail() != null) {
                    notaFiscal.setEmailnota(cliente.getEmail());
                }
                notaFiscal = notaFiscal.cadastraNota(context, notaFiscal);


                notaProdutoList = colocaDadosNotaProduto(context, notaFiscal, numeroPedido);

                if (notaProdutoList.size() > 0) {
                    for (int i = 0; i < notaProdutoList.size(); i++) {

                        notaFiscal.setBaseicms(notaFiscal.getBaseicms() + notaProdutoList.get(i).getBicms());
                        notaFiscal.setValoricms(notaFiscal.getValoricms() + notaProdutoList.get(i).getValornota());
                        notaFiscal.setIcmssub(notaFiscal.getIcmssub() + notaProdutoList.get(i).getVbcst());
                        notaFiscal.setValoricmssub(notaFiscal.getValoricmssub() + notaProdutoList.get(i).getVsst());
                        notaFiscal.setValorseguro(notaFiscal.getValorseguro() + notaProdutoList.get(i).getVseguro());
                        notaFiscal.setValordoipi(notaFiscal.getValordoipi() + notaProdutoList.get(i).getValoripi());
                        notaFiscal.setValordosprodutos(notaFiscal.getValordosprodutos() + notaProdutoList.get(i).getValortotal());
                        notaFiscal.setValornota(notaFiscal.getValornota() + notaProdutoList.get(i).getValortotal() + notaProdutoList.get(i).getVsst());
                    }
                }
                notaFiscal = notaFiscal.cadastraNota(context, notaFiscal);
                pedido.setNotafisca(notaFiscal.getCodnota());
                pedido.cadastraPedido(context, pedido);


            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return notaFiscal;
    }

    public List<NotaProduto> colocaDadosNotaProduto(Context context, NotaFiscal notaFiscal, String codPedido) {
        List<NotaProduto> listNotaProduto = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        PedidoProduto pedidoProduto = new PedidoProduto();
        Pedido pedido = new Pedido();
        List<Field> fieldsPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
        Cursor cursorPedido = pedido.retornaPedidoFiltradaCursor(context, Long.parseLong(codPedido));
        List<Field> fieldsPedidoProduto = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));
        List<Field> fieldsProduto = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));
        Produto produto;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        NotaProduto notaProduto = new NotaProduto();
        notaProduto.limpaProdutos(context, notaFiscal.getCodnota());
        for (int i = 0; fieldsPedido.size() > i; i++) {
            if (fieldsPedido.get(i).getName().toLowerCase().equals("$change") ||
                    fieldsPedido.get(i).getName().toLowerCase().equals("serialversionuid")) {
            } else {
                pedido = (Pedido) getSetDinamico.setValorObjetoCursor(fieldsPedido.get(i), pedido, cursorPedido);
            }
        }

        Cursor cursorPedidoProduto = pedidoProduto.retornaPedidoProdutoFiltradaCursor(context, pedido.getPedido());

        if (cursorPedidoProduto.getCount() > 0) {

            for (int i = 0; i < cursorPedidoProduto.getCount(); i++) {
                pedidoProduto = new PedidoProduto();
                produto = new Produto();
                for (int j = 0; fieldsPedidoProduto.size() > j; j++) {
                    if (fieldsPedidoProduto.get(j).getName().toLowerCase().equals("$change") ||
                            fieldsPedidoProduto.get(j).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        pedidoProduto = (PedidoProduto) getSetDinamico.setValorObjetoCursor(fieldsPedidoProduto.get(j), pedidoProduto, cursorPedidoProduto);
                    }
                }
                Cursor cursorProduto = produto.retornaProdutoFiltradaCursor(context, pedidoProduto.getCodproduto());
                for (int j = 0; fieldsProduto.size() > j; j++) {
                    if (fieldsProduto.get(j).getName().toLowerCase().equals("$change") ||
                            fieldsProduto.get(j).getName().toLowerCase().equals("serialversionuid")) {
                    } else {
                        produto = (Produto) getSetDinamico.setValorObjetoCursor(fieldsProduto.get(j), produto, cursorProduto);
                    }
                }
                Icms icms = new Icms();
                icms = icms.retornaIcmsObjeto(context, produto.getCodicms());

                notaProduto = new NotaProduto();
                notaProduto.setAliqicms(0D);
                notaProduto.setAliqipi(0D);
                notaProduto.setCodemitente(notaFiscal.getCodemitente());
                notaProduto.setCodnota(notaFiscal.getCodnota());
                notaProduto.setCodigo(pedidoProduto.getCodproduto());
                notaProduto.setQuantidade(pedidoProduto.getQuantidade());
                notaProduto.setValorunitario(pedidoProduto.getValorunitario());
                notaProduto.setValortotal(pedidoProduto.getValortotal());
                notaProduto.setValornota(pedidoProduto.getValortotal() * icms.getPercen() / 100);
                notaProduto.setValoripi(0D);
                notaProduto.setAliqipi(0D);
                notaProduto.setCodicms(icms.getCodicms());
                notaProduto.setPeso(0L);
                notaProduto.setCfop(produto.getCfop());
                notaProduto.setBicms(Sessao.retornaFormatado(pedidoProduto.getValortotal()));
                notaProduto.setVicms(Sessao.retornaFormatado(pedidoProduto.getValortotal() * 18 / 100));
                notaProduto.setDescopro(0D);
                if (produto.getMva() > 0) {
                    notaProduto.setMvap(produto.getMva().longValue());
                    notaProduto.setVbcst(Sessao.retornaFormatado((pedidoProduto.getValortotal() + (pedidoProduto.getValortotal() * produto.getMva() / 100))));
                    notaProduto.setVsst(Sessao.retornaFormatado((notaProduto.getVbcst() * 18 / 100) - notaProduto.getVicms()));
                }
                notaProduto.setVseguro(0D);
                notaProduto.setDescri(produto.getMercadoria());
                notaProduto.setVfrete(0D);
                notaProduto.setCodtipo(1L);
                notaProduto.setCodpis(produto.getCodpis());
                notaProduto.setPorpis(produto.getPorpis().longValue());
                notaProduto.setCodcofins(produto.getCodcofins());
                notaProduto.setPorcofins(produto.getPorcofins().longValue());
                notaProduto.setCodipi(produto.getCodipi());
                notaProduto.setSst(produto.getSittrib());
                notaProduto.setVoutros(0D);
                notaProduto.setDatas(new Date());
                notaProduto.setVcusto(produto.getCusto1());
                notaProduto.setNcmproduto(produto.getNcm());
                if (1 == 1) { //antes de sinronizar os dados do emitente vai ficar assim
                    notaProduto.setBicms(0D);
                    notaProduto.setVicms(0D);
                }
                notaProduto = notaProduto.cadastraNotaProduto(context, notaProduto);

                listNotaProduto.add(notaProduto);
                cursorPedidoProduto.moveToNext();
            }


        }


        return listNotaProduto;
    }

    public List<ParcelaNFE> colocaDadosParcelasNFE(Context context, NotaFiscal notaFiscal, String codPedido) {
        List<ParcelaNFE> parcelaNFEList = new ArrayList<>();
        Parcelas parcelas = new Parcelas();
        List<Parcelas> parcelasList = parcelas.retornaListaDeParcelas(context, Long.parseLong(codPedido));

        for (int i = 0; i < parcelasList.size(); i++) {
            ParcelaNFE parcelaNFE = new ParcelaNFE();
            parcelaNFE.setCodnota(notaFiscal.getCodnota());
            parcelaNFE.setDiave(parcelasList.get(i).getDiave());
            parcelaNFE.setDvenci(parcelasList.get(i).getDvenci());
            parcelaNFE.setFatura(parcelasList.get(i).getFatura());
            parcelaNFE.setValorboleto(parcelasList.get(i).getValorboleto());
            parcelaNFE.setVparce(parcelasList.get(i).getVparce());
            parcelaNFEList.add(parcelaNFE);
        }
        return parcelaNFEList;
    }
}
