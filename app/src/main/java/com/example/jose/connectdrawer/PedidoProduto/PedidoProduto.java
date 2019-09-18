package com.example.jose.connectdrawer.PedidoProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Jose on 30/06/2017.
 */

public class PedidoProduto {

    private Long idpedidoproduto;
    private String codmecanico;//Cód Mecanico
    private Double codpedido;//Cód Pedido
    private String codproduto;//Cód Produto
    private Double comip;
    private Integer conta;
    private Double custo;
    private Date datas;
    private Double desconto;
    private String descri;
    private String desenho;
    private Double desvalor;
    private String dot;
    private Boolean eminota;
    private Boolean eminotaagru;
    private Double lucro;
    private String marca;
    private String modelo;
    private String nserie;
    private Long pedido;
    private Double porimposto;
    private Double quanti;
    private Double quantidade;
    private Double retirada;
    private Double saldoret;
    private String tamanho;
    private Double totalimposto;
    private Double totalimpostoest;
    private Double valortotal;//Valor Total
    private Double valorunitario;//Valor Unitário
    private Double vcomi;

    public Long getIdpedidoproduto() {
        return idpedidoproduto;
    }

    public void setIdpedidoproduto(Long idpedidoproduto) {
        this.idpedidoproduto = idpedidoproduto;
    }

    public String getCodmecanico() {
        return codmecanico;
    }

    public Double getComip() {
        return comip;
    }

    public Double getRetirada() {
        return retirada;
    }

    public void setCodmecanico(String codmecanico) {
        this.codmecanico = codmecanico;
    }

    public Double getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(Double codpedido) {
        this.codpedido = codpedido;
    }

    public void setComip(Double comip) {
        this.comip = comip;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Date getDatas() {
        return datas;
    }

    public void setDatas(Date datas) {
        this.datas = datas;
    }

    public String getDesenho() {
        return desenho;
    }

    public void setDesenho(String desenho) {
        this.desenho = desenho;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public Boolean getEminota() {
        return eminota;
    }

    public void setEminota(Boolean eminota) {
        this.eminota = eminota;
    }

    public Boolean getEminotaagru() {
        return eminotaagru;
    }

    public void setEminotaagru(Boolean eminotaagru) {
        this.eminotaagru = eminotaagru;
    }

    public Double getLucro() {
        return lucro;
    }

    public void setLucro(Double lucro) {
        this.lucro = lucro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Double getPorimposto() {
        return porimposto;
    }

    public void setPorimposto(Double porimposto) {
        this.porimposto = porimposto;
    }

    public void setRetirada(Double retirada) {
        this.retirada = retirada;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getTotalimposto() {
        return totalimposto;
    }

    public void setTotalimposto(Double totalimposto) {
        this.totalimposto = totalimposto;
    }

    public Double getTotalimpostoest() {
        return totalimpostoest;
    }

    public void setTotalimpostoest(Double totalimpostoest) {
        this.totalimpostoest = totalimpostoest;
    }

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public Double getVcomi() {
        return vcomi;
    }

    public void setVcomi(Double vcomi) {
        this.vcomi = vcomi;
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
    public Double getSaldoret() {
        return saldoret;
    }

    public void setSaldoret(Double saldoret) {
        this.saldoret = saldoret;
    }

    @Override
    public String toString() {
        return codproduto + " - " + descri + " Quant: " + quantidade +  " R$:" + valortotal;
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

    public List<PedidoProduto> retornaObjetoItensPedido(Context context, Long codPedido){
        Banco myDb = new Banco(context);
        PedidoProduto pedidoProduto = new PedidoProduto();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT rowid _id, * FROM pedidoproduto where pedido = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            List<Field> fieldList = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));

            for (int f = 0; fieldList.size() != f; f++) {
                if (fieldList.get(f).getName().toLowerCase().equals("alteradoandroid")){
                    fieldList.remove(f);
                }
                if (fieldList.get(f).getName().toLowerCase().equals("cadastroandroid")){
                    fieldList.remove(f);
                }
                if (fieldList.get(f).getName().toLowerCase().equals("deletadooandroid")){
                    fieldList.remove(f);
                }
            }

            for (int j = 0; cursor.getCount() != j; j++) {
                pedidoProduto = new PedidoProduto();
                for (int f = 0; fieldList.size() != f; f++) {
                    String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                    String nomeCampo = fieldList.get(f).getName().toLowerCase();
                    Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                    if (retorno != null) {
                        Object retornoFuncao = getSetDinamico.insereField(fieldList.get(f), pedidoProduto, retorno);
                        pedidoProduto = (PedidoProduto) retornoFuncao;
                    }
                }
                cursor.moveToNext();
                pedidoProdutoList.add(pedidoProduto);
            }
        }
        db.close();
        return pedidoProdutoList;
    }

    public Double retornaTotalPedido(Context context, Long codPedido){
        Banco myDb = new Banco(context);
        Double valorTotal = 0D;
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(valortotal) FROM pedidoproduto where pedido = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            valorTotal = cursor.getDouble(0);
        }

        db.close();
        return valorTotal;
    }

    public Cursor retornaItemPedido(Context context, Long idPedidoProduto){
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id, * FROM pedidoproduto where idpedidoproduto = " + idPedidoProduto, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoProdutoFiltradaCursor(Context context, Long codPedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedidoproduto where pedido = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<PedidoProduto> retornaPedidoProdutoObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedidoproduto where pedido = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }

        List<Field> fieldListPedidoProduto = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));
        fieldListPedidoProduto.remove(14);
        for (int j = 0; cursor.getCount() != j; j++) {
            PedidoProduto pedidoProduto = new PedidoProduto();

            for (int f = 0; fieldListPedidoProduto.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldListPedidoProduto.get(f));
                String nomeCampo = fieldListPedidoProduto.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retPedidoProduto = getSetDinamico.insereField(fieldListPedidoProduto.get(f), pedidoProduto, retorno);
                    pedidoProduto = (PedidoProduto) retPedidoProduto;
                }
            }
            cursor.moveToNext();
            pedidoProdutoList.add(pedidoProduto);
        }
//        db.close();
        return pedidoProdutoList;
    }

    public Cursor retornaPedidoProdutoAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedidoproduto  where " + tipo + " = 1",null);   // " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public boolean removerPedidoProduto(Context context, Long idPedidoProduto) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedidoproduto", "idPedidoProduto = " + idPedidoProduto, null);
        if (retorno > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean cadastraPedidoProduto(SQLiteDatabase db, PedidoProduto pedidoProduto){
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(pedidoProduto.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), pedidoProduto, valores);
        }

        if (valores.get("idpedidoproduto") == null){
            long retorno = retornaMaiorCod(db);
            retorno = retorno + 1;
            valores.remove("idPedidoProduto");
            valores.remove("cadastroandroid");
            valores.put("idpedidoproduto", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("pedidoproduto", null, valores);
            valores.clear();

            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            valores.remove("alteradoandroid");
            valores.put("alteradoandroid", true);
            long retorno = db.update("pedidoproduto", valores, "idpedidoproduto= " + valores.get("idpedidoproduto").toString(), null);
            valores.clear();
            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Long retornaMaiorCod(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(idpedidoproduto) from pedidoproduto", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }


    public void removePedidoProdutoAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo, "0");
        int retorno = db.update("pedidoproduto", values, campo + " = 1", null);

    }

    public void colocaPedidoProdutoAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo, "1");
        int retorno = db.update("pedidoproduto", values, campo + " = 0", null);

    }

}
