package com.example.jose.connectdrawer.PedidoProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 30/06/2017.
 */

public class PedidoProduto {




    public Cursor retornaPedido(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedidoproduto", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoFiltradaCursor(Context context, Long codPedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedido where pedidoproduto = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedidoproduto where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }



    public boolean remover(Context context, Pedido pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedidoproduto", "pedido = " + pedido.getPedido(), null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removerPedido(Context context, Long pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedidoproduto", "pedido = " + pedido, null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean cadastraPedido(Context context, Pedido pedido){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(pedido.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), pedido, valores);
        }

        if (valores.get("codpedido") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("codpedido");
            valores.remove("cadastroandroid");
            valores.put("codpedido", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("pedidoproduto", null, valores);
            db.close();
            valores.clear();
            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            valores.remove("alteradoandroid");
            valores.put("alteradoandroid", true);
            long retorno = db.update("pedidoproduto", valores, "pedido= " + valores.get("codpedido").toString(), null);
            db.close();
            valores.clear();
            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(pedido) from pedidoproduto", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

}
