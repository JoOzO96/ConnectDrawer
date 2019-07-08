package com.example.jose.connectdrawer.uteis;

import android.content.ContentValues;
import android.util.Log;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jose on 09/06/2017.
 */

public class DadosBanco {
    public ContentValues insereValoresContent(Field field, Object object, ContentValues contentValues) {
//            Class classe = Class.forName(object.getClass().toString().replace("class ", ""));
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            Object retorno = null;
            String tipo = "";
            if (field.getName().toLowerCase().equals("serialversionuid") || field.getName().toLowerCase().equals("$change")) {

            } else {
                retorno = getSetDinamico.retornaValorCampo(field, object);
                tipo = getSetDinamico.retornaTipoCampo(field);

                if (retorno != null) {
                    if (tipo.toUpperCase().equals("STRING")) {
                        contentValues.put(field.getName().toString(), retorno.toString());
                    } else if (tipo.toUpperCase().equals("BOOLEAN")) {
                        Integer verdadeiro = 0;
                        if (retorno == true){
                            verdadeiro = 1;
                        }else{
                            verdadeiro = 0;
                        }
                        contentValues.put(field.getName().toString(), verdadeiro);
                    } else if (tipo.toUpperCase().equals("LONG")) {
                        contentValues.put(field.getName().toString(), Long.parseLong(retorno.toString()));
                    } else if (tipo.toUpperCase().equals("DATE")) {
                        Date data = (Date) retorno;
                        contentValues.put(field.getName().toString(), data.getTime());
//                        Log.i("DATA", "" + retorno.toString());
                    } else if (tipo.toUpperCase().equals("DOUBLE")) {
                        contentValues.put(field.getName().toString(), Double.parseDouble(retorno.toString()));
                    }

                }
            }
            return contentValues;
    }
}
