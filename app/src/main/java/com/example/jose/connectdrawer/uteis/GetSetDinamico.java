package com.example.jose.connectdrawer.uteis;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.jose.connectdrawer.NotaFiscal.NotaFiscal;
import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.cliente.Cliente;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
            } else if (tipo.equals("INT")) {
                objeto = cursor1.getInt(cursor1.getColumnIndex(nome));
            }else if (tipo.equals("DOUBLE")) {
                objeto = cursor1.getDouble(cursor1.getColumnIndex(nome));
            } else if (tipo.equals("BOOLEAN")) {
                objeto = cursor1.getInt(cursor1.getColumnIndex(nome)) == 0;
            }
        }
        return objeto;
    }


    public Object setValorObjetoCursor(Field field, Object object, Cursor cursor){
        String tipo = retornaTipoCampo(field);
        Object retorno = null;
        if (field.getName().toLowerCase().equals("$change") || field.getName().toLowerCase().equals("serialversionuid") || field.getName().toLowerCase().equals("context")){
        }else{

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


    public Object colocaDadosNotaFiscal(Context context, NotaFiscal notaFiscal, String numeroPedido){
        try {
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            Cliente cliente = new Cliente();
            Pedido pedido = new Pedido();
            List<Field> fieldsPedido = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
            List<Field> fieldsCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
            Cursor cursorPedido = pedido.retornaPedidoFiltradaCursor(context, Long.parseLong(numeroPedido));


            for (int i = 0; fieldsPedido.size() > i; i++) {
                if (fieldsPedido.get(i).getName().toLowerCase().equals("$change") ||
                        fieldsPedido.get(i).getName().toLowerCase().equals("serialversionuid")) {
                } else {
                    pedido = (Pedido) getSetDinamico.setValorObjetoCursor(fieldsPedido.get(i), pedido, cursorPedido);
                }
            }
            cliente = cliente.retornaClienteObjeto(context, pedido.getCodcliente());

            if (cursorPedido.getCount() > 0) {
                String codNota = notaFiscal.retornaCodNota(context);
                notaFiscal.setCodnota("000000003");
                notaFiscal.setCodemitente(1L);
                notaFiscal.setCodtipo(1L);
                notaFiscal.setCodcliente(pedido.getCodcliente());
                notaFiscal.setNomecliente(cliente.getNomecliente());
                notaFiscal.setCgccpf(cliente.getCpf());
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

            }


        }catch (Exception ex){

        }
        return notaFiscal;
    }

}
