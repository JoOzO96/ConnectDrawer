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
        try {
            Class classe = Class.forName(object.getClass().toString().replace("class ", ""));
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            Object retorno = null;
            String tipo = "";
            if (field.getName().toLowerCase().equals("serialversionuid") || field.getName().toLowerCase().equals("$change")){

            }else {
                retorno = getSetDinamico.retornaValorCampo(field, object);
                tipo = getSetDinamico.retornaTipoCampo(field);

                if (retorno != null) {
                    if (tipo.toUpperCase().equals("STRING")) {
                        contentValues.put(field.getName().toString(), retorno.toString());
                    } else if (tipo.toUpperCase().equals("BOOLEAN")) {
                        contentValues.put(field.getName().toString(), false);
                    } else if (tipo.toUpperCase().equals("LONG")) {
                        contentValues.put(field.getName().toString(), Long.parseLong(retorno.toString()));
                    } else if (tipo.toUpperCase().equals("DATE")) {
                        contentValues.put(field.getName().toString(), retorno.toString());
                        Log.i("DATA",""+ retorno.toString());
                    } else if (tipo.toUpperCase().equals("DOUBLE")) {
                        contentValues.put(field.getName().toString(), Double.parseDouble(retorno.toString()));
                    }

                }
            }
            return contentValues;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
