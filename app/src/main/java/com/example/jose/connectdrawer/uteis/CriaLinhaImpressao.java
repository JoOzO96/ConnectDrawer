package com.example.jose.connectdrawer.uteis;

/**
 * Created by Usuario on 28/02/2018.
 */

public class CriaLinhaImpressao {
    public String linha(String texto){

        if (texto.length() < 48){
            adicionaCaracter(texto, " ", 48L);
        }

        return texto;
    }

    public String adicionaCaracter(String texto, String caracter, Long tamanho){

        for (int i = texto.length(); i == tamanho ; i++){
            texto += caracter;
        }

        return texto;
    }
}
