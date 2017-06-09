package com.example.jose.connectdrawer.uteis;

import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Jose on 08/06/2017.
 */

public class CamposRequeridos {
    public int retornaMensagemRequerido(List<String> camposRequeridos, List<String> camposRequeridosMensagem, List<String> camposRequeridosTamanho, List<Field> fieldListPassar, View view) {
        String campo = "";
        MostraToast toast = new MostraToast();
        GetSetDinamicoTelas getSetDinamicoTelas = new GetSetDinamicoTelas();
        EditText texto = null;
        int valorFim = 0;
        for (int i = 0; camposRequeridos.size() != i; i++) {
//            for (int j = 0 ; valor.length() != j ; j++){
//                if (String.valueOf(valor.charAt(j+1)).equals("-")){
//                    valorFim = j +3 ;
//                    break;
//                }else{
//                    campo += valor.charAt(j);
//                }
//            }
            for (int j = 0; fieldListPassar.size() != j; j++) {
                if (fieldListPassar.get(j).getName().equals(camposRequeridos.get(i))) {
                    texto = (EditText) getSetDinamicoTelas.retornaIDCampo(view, camposRequeridos.get(i));
                    break;
                }
            }
            if (texto.getText() != null) {
                if (texto.length() == 0) {
                    toast.mostraToastShort(view.getContext(), camposRequeridosMensagem.get(i).substring(valorFim, camposRequeridosMensagem.get(i).length()));
                    return 1;
                } else if (texto.length() < Integer.parseInt(camposRequeridosTamanho.get(i))) {
                    toast.mostraToastShort(view.getContext(), "O campo " + camposRequeridos.get(i).replace("tx","") + " deve conter no minimo " + camposRequeridosTamanho.get(i) + " caracteres.");
                    return 1;
                }
            }
        }
        return 0;
    }
}
