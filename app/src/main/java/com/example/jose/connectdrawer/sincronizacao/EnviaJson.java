package com.example.jose.connectdrawer.sincronizacao;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import static android.R.attr.data;

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
        try {
            URL obj = null;

            obj = new URL("http://192.168.0.103:8080/ConnectServices/recebeCidade");

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();  //abre conexao

            connection.setRequestMethod("POST"); //fala que quer um post

            connection.setRequestProperty("Content-type", "text/plain"); //fala o que vai mandar

            connection.setDoOutput(true); //fala que voce vai enviar algo


            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.println(params[0]); //seta o que voce vai enviar

            connection.connect(); //envia para o servidor

            String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
