package com.example.jose.connectdrawer.Icms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Icms {
    String codicms;
    String percentual;
    Long percen;
    Long percentualsimples;
    Long percentualdificms;

    public String getCodicms() {
        return codicms;
    }

    public void setCodicms(String codicms) {
        this.codicms = codicms;
    }

    public String getPercentual() {
        return percentual;
    }

    public void setPercentual(String percentual) {
        this.percentual = percentual;
    }

    public Long getPercen() {
        return percen;
    }

    public void setPercen(Long percen) {
        this.percen = percen;
    }

    public Long getPercentualsimples() {
        return percentualsimples;
    }

    public void setPercentualsimples(Long percentualsimples) {
        this.percentualsimples = percentualsimples;
    }

    public Long getPercentualdificms() {
        return percentualdificms;
    }

    public void setPercentualdificms(Long percentualdificms) {
        this.percentualdificms = percentualdificms;
    }

    public Icms retornaIcmsObjeto(Context context, String codigo) {
        Banco myDb = new Banco(context);
        Icms icms = new Icms();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM Icms where codicms = '" + codigo + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldList = new ArrayList<>(Arrays.asList(Icms.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldList.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                String nomeCampo = fieldList.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retIcms = getSetDinamico.insereField(fieldList.get(f), icms, retorno);
                    icms = (Icms) retIcms;
                }
            }
        }
        db.close();
        return icms;
    }

    public Cursor retornaIcmsFiltradaCursor(Context context, String codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM icms where codicms = '" + codigo + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Boolean cadastraIcms(Context context, Icms icms) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(icms.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), icms, valores);
        }

        long retorno = 0L;
        retorno = db.insert("icms", null, valores);
        db.close();
        valores.clear();
        return retorno != -1;

    }
}
