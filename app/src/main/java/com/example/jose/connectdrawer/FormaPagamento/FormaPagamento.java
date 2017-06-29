package com.example.jose.connectdrawer.FormaPagamento;

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
 * Created by Jose on 23/06/2017.
 */

public class FormaPagamento {
    private Long codigo; //Código
    private String pagamento;
    private Boolean prazo;
    private Boolean cartao;
    private Long codcaixa;
    private Boolean encaixa;
    private Boolean fechamento;
    private Boolean cheque;
    private String listapre;
    private Boolean naolancareceber;

    @Override
    public String toString() {
        return codigo +" - "+ pagamento;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public Boolean getPrazo() {
        return prazo;
    }

    public void setPrazo(Boolean prazo) {
        this.prazo = prazo;
    }

    public Boolean getCartao() {
        return cartao;
    }

    public void setCartao(Boolean cartao) {
        this.cartao = cartao;
    }

    public Long getCodcaixa() {
        return codcaixa;
    }

    public void setCodcaixa(Long codcaixa) {
        this.codcaixa = codcaixa;
    }

    public Boolean getEncaixa() {
        return encaixa;
    }

    public void setEncaixa(Boolean encaixa) {
        this.encaixa = encaixa;
    }

    public Boolean getFechamento() {
        return fechamento;
    }

    public void setFechamento(Boolean fechamento) {
        this.fechamento = fechamento;
    }

    public Boolean getCheque() {
        return cheque;
    }

    public void setCheque(Boolean cheque) {
        this.cheque = cheque;
    }

    public String getListapre() {
        return listapre;
    }

    public void setListapre(String listapre) {
        this.listapre = listapre;
    }

    public Boolean getNaolancareceber() {
        return naolancareceber;
    }

    public void setNaolancareceber(Boolean naolancareceber) {
        this.naolancareceber = naolancareceber;
    }



    public static Cursor retornaFormaPagamento(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM formapagamento", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaFormaPagamentoFiltradaCursor(Context context, Long codFormaPagamento) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM formapagamento where codigo = " + codFormaPagamento , null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaFormaPagamentoAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM formapagamento where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public boolean remover(Context context, FormaPagamento formaPagamento) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("formapagamento", "codigo = " + formaPagamento.getCodigo(), null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removerFormaPagamento(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("formapagamento", "codigo = " + codigo, null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean cadastraFormaPagamento(Context context, FormaPagamento formaPagamento) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(formaPagamento.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), formaPagamento, valores);
        }

        if (valores.get("codvendedor") == null) {
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("codvendedor");
            valores.remove("cadastroandroid");
            valores.put("codvendedor", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("formapagamento", null, valores);
            db.close();
            valores.clear();
            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            valores.remove("alteradoandroid");
            valores.put("alteradoandroid", true);
            long retorno = db.update("formapagamento", valores, "codigo = " + valores.get("codigo"), null);
            db.close();
            valores.clear();
            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

    public Boolean cadastraFormaPagamentoSinc(Context context, FormaPagamento formaPagamento) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(formaPagamento.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), formaPagamento, valores);
        }
        long retorno = 0;
        retorno = db.insert("formapagamento", null, valores);
        if (retorno == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(codigo) from formapagamento", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }
}

