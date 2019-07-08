package com.example.jose.connectdrawer.Email;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.jose.connectdrawer.uteis.MostraToast;

public class CriaEmail {

    public void enviarEmail(final Context context, String assunto, String mensagem){
        final MostraToast mostraToast = new MostraToast();
        final String nome = "José Alcides";
        final String email = "jose@xmax.com.br";
        final String subject = "Exception gerada " + assunto;
        final String body = mensagem;

        if(!isOnline(context)) {
//            mostraToast.mostraToastLong(context, "Não estava online para enviar e-mail!");
            System.exit(0);
        }

        new Thread(new Runnable(){
            @Override
            public void run() {
                EnviaEmail.Mail m = new EnviaEmail.Mail();

                String[] toArr = {email};
                m.setTo(toArr);

                //m.setFrom("seunome@seuemail.com.br"); //caso queira enviar em nome de outro
                m.setSubject(subject);
                m.setBody(body);

                try {
                    //m.addAttachment("pathDoAnexo");//anexo opcional
                    m.send();
                }
                catch(RuntimeException rex){ }//erro ignorado
                catch(Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }

//                mostraToast.mostraToastLong(context, "Email enviado!");
            }
        }).start();
    }

    public boolean isOnline(Context context) {
        final MostraToast mostraToast = new MostraToast();
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        catch(Exception ex){
            mostraToast.mostraToastLong(context,  "Erro ao verificar se estava online! (" + ex.getMessage() + ")");
            return false;
        }
    }
}
