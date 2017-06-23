package com.example.jose.connectdrawer.Vendedor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.Vendedor.Vendedor;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 23/06/2017.
 */

public class Vendedor {

    private String codvendedor;
    private String nomevendedor;
    private Long comi;
    private Long comis;

    @Override
    public String toString() {
        return codvendedor + " - " + nomevendedor ;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
    }

    public String getNomevendedor() {
        return nomevendedor;
    }

    public void setNomevendedor(String nomevendedor) {
        this.nomevendedor = nomevendedor;
    }

    public Long getComi() {
        return comi;
    }

    public void setComi(Long comi) {
        this.comi = comi;
    }

    public Long getComis() {
        return comis;
    }

    public void setComis(Long comis) {
        this.comis = comis;
    }



    public Cursor retornaVendedor(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM vendedor", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaVendedorFiltradaCursor(Context context, Long codVendedor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM vendedor where codvendedor = " + codVendedor, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaVendedorAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM vendedor where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }



    public boolean remover(Context context, Vendedor pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("vendedor", "codvendedor = " + pedido.getCodvendedor(), null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removerVendedor(Context context, Long codvendedor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("vendedor", "codvendedor = " + codvendedor, null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean cadastraVendedor(Context context, Vendedor vendedor){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(vendedor.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), vendedor, valores);
        }

        if (valores.get("codvendedor") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("codpedido");
            valores.remove("cadastroandroid");
            valores.put("codvendedor", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("vendedor", null, valores);
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
            long retorno = db.update("vendedor", valores, "codvendedor = " + valores.get("codvendedor").toString(), null);
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
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(codvendedor) from vendedor", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }
}
