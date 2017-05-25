package com.example.jose.connectdrawer.cidade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.banco.Banco;

/**
 * Created by Jose on 25/05/2017.
 */

public class Cidade {
    private Long codCidade;
    private String nomeCidade;
    private String UF;
    private String codNacionalUf;
    private String codNacionalCidade;
    private String pais;
    private String codNacionalPais;
    private String cep;


    public Long getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Long codCidade) {
        this.codCidade = codCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCodNacionalUf() {
        return codNacionalUf;
    }

    public void setCodNacionalUf(String codNacionalUf) {
        this.codNacionalUf = codNacionalUf;
    }

    public String getCodNacionalCidade() {
        return codNacionalCidade;
    }

    public void setCodNacionalCidade(String codNacionalCidade) {
        this.codNacionalCidade = codNacionalCidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodNacionalPais() {
        return codNacionalPais;
    }

    public void setCodNacionalPais(String codNacionalPais) {
        this.codNacionalPais = codNacionalPais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return  codCidade + ", " + nomeCidade +  ", " + UF;
    }



    public Cursor retornaCidade(Context context){
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cidade", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaCidadeFiltrada(Context context, Long codCidade){
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cidade where codCidade = " + codCidade, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
