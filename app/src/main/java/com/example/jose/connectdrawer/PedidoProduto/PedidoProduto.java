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
import java.util.Date;
import java.util.List;

/**
 * Created by Jose on 30/06/2017.
 */

public class PedidoProduto {

    private Long idPedidoProduto;
    private String pedido;
    private String codproduto; // Cód Produto
    private Double quantidade;
    private Double valorunitario; // Valor Unitário
    private String descri;
    private Double custo;
    private Double desvalor;
    private Double quanti;
    private Double desconto;
    private Long retirada;
    private Double saldoret;
    private Long comip;

    public Long getIdPedidoProduto() {
        return idPedidoProduto;
    }

    public void setIdPedidoProduto(Long idPedidoProduto) {
        this.idPedidoProduto = idPedidoProduto;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(String codproduto) {
        this.codproduto = codproduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(Double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Double getDesvalor() {
        return desvalor;
    }

    public void setDesvalor(Double desvalor) {
        this.desvalor = desvalor;
    }

    public Double getQuanti() {
        return quanti;
    }

    public void setQuanti(Double quanti) {
        this.quanti = quanti;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Long getRetirada() {
        return retirada;
    }

    public void setRetirada(Long retirada) {
        this.retirada = retirada;
    }

    public Double getSaldoret() {
        return saldoret;
    }

    public void setSaldoret(Double saldoret) {
        this.saldoret = saldoret;
    }

    public Long getComip() {
        return comip;
    }

    public void setComip(Long comip) {
        this.comip = comip;
    }

    @Override
    public String toString() {
        return codproduto + " - " + descri;
    }

    public Cursor retornaPedidoProduto(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedidoproduto", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaItensPedido(Context context, Long codPedido){
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id, * FROM pedidoproduto where pedido = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoProdutoFiltradaCursor(Context context, Long codPedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedido where pedidoproduto = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoProdutoAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedidoproduto where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public boolean removerPedidoProduto(Context context, Long pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedidoproduto", "pedido = " + pedido, null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean cadastraPedidoProduto(Context context, PedidoProduto pedidoProduto){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(pedidoProduto.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), pedidoProduto, valores);
        }

        if (valores.get("idPedidoProduto") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("idPedidoProduto");
            valores.remove("cadastroandroid");
            valores.put("idPedidoProduto", retorno);
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
            long retorno = db.update("pedidoproduto", valores, "idPedidoProduto= " + valores.get("idPedidoProduto").toString(), null);
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
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(idPedidoProduto) from pedidoproduto", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

}
