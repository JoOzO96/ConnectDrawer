package com.example.jose.connectdrawer.uteis;

import android.content.ContentValues;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Jose on 09/06/2017.
 */

public class DadosBanco {
    public ContentValues insereValoresContent(Field field, Object object){
        try {
            Class classe = Class.forName(object.getClass().toString().replace("class ", ""));
            GetSetDinamico getSetDinamico = new GetSetDinamico();
            Object retorno = null;
            String tipo = "";
            retorno = getSetDinamico.retornaValorCampo(field, object);
            tipo = getSetDinamico.retornaTipoCampo(field);

            Log.i("Valor, Tipo", retorno + " - " + tipo);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
