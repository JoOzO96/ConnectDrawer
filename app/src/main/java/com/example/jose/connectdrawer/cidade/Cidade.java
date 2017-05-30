package com.example.jose.connectdrawer.cidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.banco.Banco;

import java.util.Date;

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
    private Boolean cadastroAndroid;
    private Boolean deletadoAndroid;
    private Boolean alteradoAndroid;
    private Context context;


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

    public Boolean getCadastroAndroid() {
        return cadastroAndroid;
    }

    public void setCadastroAndroid(Boolean cadastroAndroid) {
        this.cadastroAndroid = cadastroAndroid;
    }

    public Boolean getDeletadoAndroid() {
        return deletadoAndroid;
    }

    public void setDeletadoAndroid(Boolean deletadoAndroid) {
        this.deletadoAndroid = deletadoAndroid;
    }

    public Boolean getAlteradoAndroid() {
        return alteradoAndroid;
    }

    public void setAlteradoAndroid(Boolean alteradoAndroid) {
        this.alteradoAndroid = alteradoAndroid;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return codCidade + ", " + nomeCidade + ", " + UF;
    }

    public Cidade(Long codCidade, String nomeCidade, String UF, String codNacionalUf, String codNacionalCidade, String pais, String codNacionalPais, String cep) {
        this.codCidade = codCidade;
        this.nomeCidade = nomeCidade;
        this.UF = UF;
        this.codNacionalUf = codNacionalUf;
        this.codNacionalCidade = codNacionalCidade;
        this.pais = pais;
        this.codNacionalPais = codNacionalPais;
        this.cep = cep;
    }

    public Cidade() {
    }

    public Cursor retornaCidade(Context context) {
        this.context = context;
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cidade", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaCidadeFiltradaCursor(Context context, Long codCidade) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cidade where codCidade = " + codCidade, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaCidadeAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cidade where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public boolean insereDados(Context context, Cidade cidade) {
        Banco myDb = new Banco(context);
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        valores.put("codCidade", cidade.getCodCidade());
        valores.put("nomeCidade", cidade.getNomeCidade());
        valores.put("UF", cidade.getUF());
        valores.put("codNacionalUf", cidade.getCodNacionalUf());
        valores.put("codNacionalCidade", cidade.getCodNacionalCidade());
        valores.put("pais", cidade.getPais());
        valores.put("codNacionalPais", cidade.getCodNacionalPais());
        valores.put("cep", cidade.getCep());
        valores.put("cadastroAndroid", cidade.getCadastroAndroid());

        long result = db.insert("cidade", null, valores);
        db.close();
        valores.clear();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean atualizaDados(Context context, Cidade cidade) {
        Banco myDb = new Banco(context);
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        valores.put("codCidade", cidade.getCodCidade());
        valores.put("nomeCidade", cidade.getNomeCidade());
        valores.put("UF", cidade.getUF());
        valores.put("codNacionalUf", cidade.getCodNacionalUf());
        valores.put("codNacionalCidade", cidade.getCodNacionalCidade());
        valores.put("pais", cidade.getPais());
        valores.put("codNacionalPais", cidade.getCodNacionalPais());
        valores.put("cep", cidade.getCep());

        long result = db.update("cidade", valores, "codCidade = " + cidade.getCodCidade(), null);
        db.close();
        valores.clear();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Long retornaMaiorCodCidade(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(codCidade) from cidade", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public boolean remover(Context context, Cidade cidade) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("cidade", "codCidade = " + cidade.getCodCidade(), null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }
}
