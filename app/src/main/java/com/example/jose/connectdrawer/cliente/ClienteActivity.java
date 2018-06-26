package com.example.jose.connectdrawer.cliente;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.banco.Banco;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Banco myDb = new Banco(this);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cliente cliente = new Cliente();
        ListView listCliente = (ListView) findViewById(R.id.listDados);
        Cursor cursor = cliente.retornaCliente(db);
        List<Cliente> clienteList = new ArrayList<>();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 0L; cursor.getCount() != cont; cont++) {
                Cliente clienteListar = new Cliente();
                clienteListar.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
//                clienteListar.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));

                clienteList.add(clienteListar);
                cursor.moveToNext();

            }
            ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clienteList);
            listCliente.setAdapter(adapter);

        }
    }
}
