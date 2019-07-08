package com.example.jose.connectdrawer.Parcelas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parcelas {
    Long idParcela;
    String codPedido;
    Date dvenci;
    Double vparce;
    Long diave;
    String fatura;
    Double valorboleto;

    public Long getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(Long idParcela) {
        this.idParcela = idParcela;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public Date getDvenci() {
        return dvenci;
    }

    public void setDvenci(Date dvenci) {
        this.dvenci = dvenci;
    }

    public Double getVparce() {
        return vparce;
    }

    public void setVparce(Double vparce) {
        this.vparce = vparce;
    }

    public Long getDiave() {
        return diave;
    }

    public void setDiave(Long diave) {
        this.diave = diave;
    }

    public String getFatura() {
        return fatura;
    }

    public void setFatura(String fatura) {
        this.fatura = fatura;
    }

    public Double getValorboleto() {
        return valorboleto;
    }

    public void setValorboleto(Double valorboleto) {
        this.valorboleto = valorboleto;
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(idParcela) from parcela", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public Boolean cadastraParcela(Context context, Parcelas parcelas){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(Parcelas.class.getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), parcelas, valores);
        }

        if (valores.get("idParcela") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("codigo");
            valores.remove("cadastroandroid");
            valores.put("codigo", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("cliente", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        }else{
            Cursor cursor = parcelas.retornaParcelaFiltradaCursor(context, Long.parseLong(valores.get("idParcela").toString()));

            if (cursor.getCount() > 0){
                valores.remove("alteradoandroid");
                valores.put("alteradoandroid", true);
                long retorno = db.update("Parcela", valores, "idParcela= " + valores.get("idParcela").toString(), null);
                db.close();
                valores.clear();
                return retorno != -1;
            }else{
                long retorno = retornaMaiorCod(context);
                retorno = retorno + 1;
                valores.remove("cadastroandroid");
                retorno = db.insert("Parcela", null, valores);
                db.close();
                valores.clear();
                return retorno != -1;
            }
        }
    }

    public Parcelas retornaParcelasObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        Parcelas parcelas = new Parcelas();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM parcela where idParcela = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Parcelas.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldListCliente.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retParcelas = getSetDinamico.insereField(fieldListCliente.get(f), parcelas, retorno);
                    parcelas = (Parcelas) retParcelas;
                }
            }
        }
        db.close();
        return parcelas;
    }

    public Cursor retornaParcelaFiltradaCursor(Context context, Long idParcela) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM parcela where idParcela = " + idParcela, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
