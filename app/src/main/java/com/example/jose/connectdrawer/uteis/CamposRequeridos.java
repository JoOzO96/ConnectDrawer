package com.example.jose.connectdrawer.uteis;

import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Jose on 08/06/2017.
 */

public class CamposRequeridos {
    public int retornaMensagemRequerido(List<String> camposRequeridosMensagem, List<Field> fieldListPassar, View view){
        String campo = "";
        MostraToast toast = new MostraToast();
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        EditText texto= null;
        int valorFim = 0;
        for (int i = 0 ; camposRequeridosMensagem.size() != i ; i++){
            String valor = camposRequeridosMensagem.get(i);
            for (int j = 0 ; valor.length() != j ; j++){
                if (String.valueOf(valor.charAt(j+1)).equals("-")){
                    valorFim = j +3 ;
                    break;
                }else{
                    campo += valor.charAt(j);
                }
            }
            for (int j = 0 ; fieldListPassar.size() != j; j++){
                if (fieldListPassar.get(j).getName().equals(campo)){
                    texto = (EditText) getSetDinamicoTelas.retornaIDCampo(view, campo);
                    break;
                }
            }
            if(texto.getText() != null){
                if (texto.length() == 0){
                    toast.mostraToastShort(view.getContext(),camposRequeridosMensagem.get(i).substring(valorFim,camposRequeridosMensagem.get(i).length()));
                    return 1;
                }
            }else{
                return 0;
            }
        }
        return 1;
    }
}
