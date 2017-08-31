package com.example.jose.connectdrawer.cidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 25/05/2017.
 */

public class Cidade {
    private Long codcidade;
    private String nomecidade;
    private String uf;
    private String codnacionaluf;
    private String codnacionalcidade;
    private String pais;
    private String codnacionalpais;
    private String cep;
    private Boolean cadastroandroid;
    private Boolean deletadoandroid;
    private Boolean alteradoandroid;
    private Context context;

    public Cidade() {
    }

    public Long getCodcidade() {
        return codcidade;
    }

    public void setCodcidade(Long codcidade) {
        this.codcidade = codcidade;
    }

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
        this.nomecidade = nomecidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodnacionaluf() {
        return codnacionaluf;
    }

    public void setCodnacionaluf(String codnacionaluf) {
        this.codnacionaluf = codnacionaluf;
    }

    public String getCodnacionalcidade() {
        return codnacionalcidade;
    }

    public void setCodnacionalcidade(String codnacionalcidade) {
        this.codnacionalcidade = codnacionalcidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodnacionalpais() {
        return codnacionalpais;
    }

    public void setCodnacionalpais(String codnacionalpais) {
        this.codnacionalpais = codnacionalpais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Boolean getCadastroandroid() {
        return cadastroandroid;
    }

    public void setCadastroandroid(Boolean cadastroandroid) {
        this.cadastroandroid = cadastroandroid;
    }

    public Boolean getDeletadoandroid() {
        return deletadoandroid;
    }

    public void setDeletadoandroid(Boolean deletadoandroid) {
        this.deletadoandroid = deletadoandroid;
    }

    public Boolean getAlteradoandroid() {
        return alteradoandroid;
    }

    public void setAlteradoandroid(Boolean alteradoandroid) {
        this.alteradoandroid = alteradoandroid;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return codcidade + " - " + nomecidade + " - " + uf;
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
        valores.put("codcidade", cidade.getCodcidade());
        valores.put("nomecidade", cidade.getNomecidade());
        valores.put("uf", cidade.getUf());
        valores.put("codnacionaluf", cidade.getCodnacionaluf());
        valores.put("codnacionalcidade", cidade.getCodnacionalcidade());
        valores.put("pais", cidade.getPais());
        valores.put("codnacionalpais", cidade.getCodnacionalpais());
        valores.put("cep", cidade.getCep());
        valores.put("cadastroandroid", cidade.getCadastroandroid());

        long result = db.insert("cidade", null, valores);
        db.close();
        valores.clear();
        return result != -1;
    }


    public boolean remover(Context context, Cidade cidade) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("cidade", "codCidade = " + cidade.getCodcidade(), null);
        return retorno > 0;
    }


    public Boolean cadastraCidade(Context context, Cidade cidade){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(cidade.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), cidade, valores);
        }

        if (valores.get("codcidade") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("codcidade");
            valores.remove("cadastroandroid");
            valores.put("codcidade", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("cidade", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        }else{
            valores.remove("alteradoandroid");
            valores.put("alteradoandroid", true);
            long retorno = db.update("cidade", valores, "codcidade= " + valores.get("codcidade").toString(), null);
            db.close();
            valores.clear();
            return retorno != -1;
        }
    }
    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(codcidade) from cidade", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public void alteraCodCidade(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("codcidade", codigoServidor);
        int retorno = db.update("cidade", values, "codcidade = " + codigoAndroid, null);

    }

    public void removeCidadeAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo, "0");
        int retorno = db.update("cidade", values, campo + " = 1", null);

    }
}
