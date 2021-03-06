package com.example.jose.connectdrawer.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.renderscript.RenderScript;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jose.connectdrawer.A7.A7Dados;
import com.example.jose.connectdrawer.FormaPagamento.FormaPagamento;
import com.example.jose.connectdrawer.FormaPagamento.FormaPagamentoFragment;
import com.example.jose.connectdrawer.Impressora.ImpressaoActibity;
import com.example.jose.connectdrawer.ImprimirTexto;
import com.example.jose.connectdrawer.Pedido.PedidoFragment;
import com.example.jose.connectdrawer.Produto.ProdutoFragment;
import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.Vendedor.VendedorFragment;
import com.example.jose.connectdrawer.cidade.CidadeFragment;
import com.example.jose.connectdrawer.cliente.ClienteFragment;
import com.example.jose.connectdrawer.configuracoeslocais.ConfiguracaoLocalFragment;
import com.example.jose.connectdrawer.login.LoginActivity;
import com.example.jose.connectdrawer.sincronizacao.SincCliente;
import com.example.jose.connectdrawer.sincronizacao.SincFragment;
import com.example.jose.connectdrawer.sincronizacao.Sincroniza;
import com.example.jose.connectdrawer.uteis.MostraToast;
import com.example.jose.connectdrawer.uteis.Sessao;

import java.util.ArrayList;
import java.util.List;

public class ConnectMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Sessao.setaContext(this);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Sessao.retornaListaCidade();
            }
        });

        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        Sessao.retornaClientes();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.connect_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Long fechado = 0L;
        int id = item.getItemId();

        if (id == R.id.nav_cliente) {
            ClienteFragment clienteFragment = new ClienteFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, clienteFragment, clienteFragment.getTag()).commit();

        } else if (id == R.id.nav_cidade) {
            CidadeFragment cidadeFragment = new CidadeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cidadeFragment, cidadeFragment.getTag()).commit();

        } else if (id == R.id.nav_pedido) {
            PedidoFragment pedidoFragment = new PedidoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, pedidoFragment, pedidoFragment.getTag()).commit();

        } else if (id == R.id.nav_vendedor) {
            VendedorFragment vendedorFragment = new VendedorFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, vendedorFragment, vendedorFragment.getTag()).commit();
        } else if (id == R.id.nav_formapagamento) {
            FormaPagamentoFragment formaPagamentoFragment = new FormaPagamentoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, formaPagamentoFragment, formaPagamentoFragment.getTag()).commit();
        } else if (id == R.id.nav_configuracoes) {
            ConfiguracaoLocalFragment configuracaoLocalFragment = new ConfiguracaoLocalFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, configuracaoLocalFragment, configuracaoLocalFragment.getTag()).commit();
        } else if (id == R.id.nav_produto) {
            ProdutoFragment produtoFragment = new ProdutoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, produtoFragment, produtoFragment.getTag()).commit();

//        } else if (id == R.id.nav_impressao) {
////            Intent intent = new Intent(this, ImpressaoActibity.class);
////            startActivity(intent);
//            Intent intent = new Intent(this, ImprimirTexto.class);
//            startActivity(intent);

        } else if (id == R.id.nav_sync) {
            Sessao.setaContext(this);
            SincFragment sincFragment = new SincFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, sincFragment, sincFragment.getTag()).commit();
            fechado = 0L;

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Sessao.setTextView(textoSinc);
//                    Sessao.colocaTexto("Iniciando");
//                    Sessao.setaContext(ConnectMain.this);
//                }
//            });
//        } else if (id == R.id.nav_deleta) {
//            Context context = this;
//            context.deleteDatabase("connect.db");
//        } else if (id == R.id.nav_login) {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_slideshow) {
//
//
//            PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
//            printManager.print("SAIUDHASUDH", new PrintDocumentAdapter() {
//                @Override
//                public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes1, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
//
//                }
//
//                @Override
//                public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
//
//                }
//            }, null);

//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

        }
        if (fechado < 1L) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }
}
