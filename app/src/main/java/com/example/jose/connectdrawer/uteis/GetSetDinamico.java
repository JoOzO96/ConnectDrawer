package com.example.jose.connectdrawer.uteis;

import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jose on 02/06/2017.
 */

public class GetSetDinamico {

    public Object insereField(Field field, Object objetoRecebido, Object recebido) {
        String primeiro = field.getName().substring(0, 1);
        String nomeCampo = field.getName().substring(1, field.getName().length());
        Object objetoInstanciado = null;
        try {
            objetoInstanciado = Class.forName(objetoRecebido.getClass().toString().replace("class ","")).newInstance();
            objetoInstanciado = objetoRecebido;
            Class[] cArg = new Class[1];
            if (recebido != null) {
                if (field.getType().getSimpleName().toUpperCase().equals("STRING") || field.getType().getSimpleName().toUpperCase().equals("EDITTEXT")) {
                    cArg[0] = String.class;
                    Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoInstanciado, recebido.toString());
                } else if (field.getType().getSimpleName().toUpperCase().equals("LONG")) {
                    cArg[0] = Long.class;
                    Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoInstanciado, Long.parseLong(recebido.toString()));
                } else if (field.getType().getSimpleName().toUpperCase().equals("DOUBLE")) {
                    cArg[0] = Double.class;
                    Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoInstanciado, Double.parseDouble(recebido.toString()));
                } else if (field.getType().getSimpleName().toUpperCase().equals("BOOLEAN")) {
                    cArg[0] = Boolean.class;
                    Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
                            field.getType());
                    method.invoke(objetoInstanciado, Boolean.parseBoolean(recebido.toString()));
                }
            }
            return objetoInstanciado;
        } catch (IllegalAccessException e) {
            Log.i("IllegalAccessException", "ACESSO ILEGAL");
        } catch (NoSuchMethodException e) {
            Log.i("NoSuchMethodException", "METODO NAO ENCONTRADO");
        } catch (SecurityException e) {

        } catch (IllegalArgumentException e) {

        } catch (InvocationTargetException e) {

        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        }
        return objetoInstanciado;
    }

    public String retornaTipoCampo(Field field) {
        String tipo = field.getType().getSimpleName().toUpperCase();
        return tipo;
    }

    public Object retornaValorCursor(String tipo, String nome, Cursor cursor) {
        Object objeto = null;
        if (nome.equals("$change") || nome.equals("serialversionuid") || nome.equals("context")) {

        } else {
            if (tipo.equals("STRING") || tipo.equals("EDITTEXT")) {
                objeto = cursor.getString(cursor.getColumnIndex(nome));
            } else if (tipo.equals("LONG")) {
                objeto = cursor.getLong(cursor.getColumnIndex(nome));
            } else if (tipo.equals("INT")) {
                objeto = cursor.getInt(cursor.getColumnIndex(nome));
            }else if (tipo.equals("DOUBLE")) {
                objeto = cursor.getDouble(cursor.getColumnIndex(nome));
            } else if (tipo.equals("BOOLEAN")) {
                objeto = cursor.getInt(cursor.getColumnIndex(nome)) == 0;
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
            String primeiro = field.getName().substring(0, 1);
            String nomeCampo = field.getName().substring(1, field.getName().length());
            Object obj1;
            obj1 = obj;
            Method method = obj.getClass().getMethod("get" + primeiro.toUpperCase() + nomeCampo, null);
            Object object = method.invoke(obj1, null);

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
}
