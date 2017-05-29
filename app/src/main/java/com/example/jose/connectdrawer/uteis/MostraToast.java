package com.example.jose.connectdrawer.uteis;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jose on 29/05/2017.
 */

public class MostraToast {
    public void mostraToastShort(Context context,String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }
    public void mostraToastLong(Context context,String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
}
