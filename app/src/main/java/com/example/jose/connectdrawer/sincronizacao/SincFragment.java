package com.example.jose.connectdrawer.sincronizacao;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.main.ConnectMain;
import com.example.jose.connectdrawer.uteis.Sessao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SincFragment extends Fragment {
    private ProgressBar progressBar;
    private TextView textoSinc;

    public SincFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sinc, container, false);
//        return inflater.inflate(R.layout.fragment_sinc, container, false);
        List<String> listString = new ArrayList<>();
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        textoSinc = (TextView) view.findViewById(R.id.textoSinc);
        Sessao.setTextView(textoSinc);
        Sessao.setProgressBar(progressBar);

//            runOnUiThread(new Runnable(){
//
//                Handler handler = new Handler();
//
//                @Override
//                public void run() {
//                    Sessao.setaContext(ConnectMain.this);
//                    Sessao.getProgress("Sicronizando");
//                }
//            });
//        Sessao.setaContext(getContext());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Sessao.getProgress("Sincronizando");
//            }
//        });
//        Sessao.getProgress("Sincronizando");
        final Handler handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Sessao.setHandler(handler);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Sessao.colocaTexto("OI");
//                    }
//                });
                Sincroniza sincroniza = new Sincroniza();
                sincroniza.iniciaSincronizacao(Sessao.retornaContext());


            }
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

//        if (!thread.isAlive()){
//            progressBar.setVisibility(View.INVISIBLE);
//        }

//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }



        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

//        Sessao.terminaProgress();
    }
}
