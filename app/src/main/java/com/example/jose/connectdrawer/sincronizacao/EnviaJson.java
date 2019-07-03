package com.example.jose.connectdrawer.sincronizacao;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Jose on 30/05/2017.
 */

public class EnviaJson extends AsyncTask<String, Integer, String> {

    public EnviaJson() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(String... params) {
        String jsonDeResposta = "";
        String jsonResposta = "";
        try {
            URL obj = null;

            obj = new URL(params[1]);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();  //abre conexao

            connection.setRequestMethod("POST"); //fala que quer um post

            connection.setRequestProperty("Content-type", "application/json"); //fala o que vai mandar

            connection.setDoOutput(true); //fala que voce vai enviar algo


            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.println(params[0]); //seta o que voce vai enviar

            connection.connect(); //envia para o servidor
//            Scanner retorno = new Scanner(connection.getInputStream());
//            jsonDeResposta = new Scanner(connection.getInputStream()).next();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            while ((jsonResposta = in.readLine()) != null) {
                jsonDeResposta += jsonResposta;
            }
//            while(retorno.hasNextLine()){
//                jsonResposta = retorno.nextLine();
//            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.e("RESPOSTA", jsonResposta);
        return jsonDeResposta;
    }
}
