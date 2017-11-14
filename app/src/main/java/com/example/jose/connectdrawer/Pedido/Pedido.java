package com.example.jose.connectdrawer.Pedido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Jose on 21/06/2017.
 */

public class Pedido {
    private Long pedido;
    private Long codcliente;
    private Long data;
    private String codvendedor;
    private String formadepagamento;
    private Double frete;
    private Double valortotal;
    private Double entrada;
    private String orpedi;
    private Long codbanco;
    private String obs;
    private Long desconto;
    private String nome;
    private Double total;
    private Date venci1;
    private Double valor1;
    private Long dias;
    private Long juro;
    private Boolean simnao;
    private Long pgto;
    private String cheque;
    private Date data1;
    private String notafisca;
    private Long via;
    private Boolean baixa;
    private String veiculo;
    private String placa;
    private String ano;
    private Long nparce;
    private Long codinstituicao;
    private String nfc;
    private Long codcaixa;
    private String servicosolicitado;
    private List<PedidoProduto> itensPedido;

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Long getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Long codcliente) {
        this.codcliente = codcliente;
    }

    public Long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
    }

    public String getFormadepagamento() {
        return formadepagamento;
    }

    public void setFormadepagamento(String formadepagamento) {
        this.formadepagamento = formadepagamento;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public Double getEntrada() {
        return entrada;
    }

    public void setEntrada(Double entrada) {
        this.entrada = entrada;
    }

    public String getOrpedi() {
        return orpedi;
    }

    public void setOrpedi(String orpedi) {
        this.orpedi = orpedi;
    }

    public Long getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(Long codbanco) {
        this.codbanco = codbanco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Long getDesconto() {
        return desconto;
    }

    public void setDesconto(Long desconto) {
        this.desconto = desconto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getVenci1() {
        return venci1;
    }

    public void setVenci1(Date venci1) {
        this.venci1 = venci1;
    }

    public Double getValor1() {
        return valor1;
    }

    public void setValor1(Double valor1) {
        this.valor1 = valor1;
    }

    public Long getDias() {
        return dias;
    }

    public void setDias(Long dias) {
        this.dias = dias;
    }

    public Long getJuro() {
        return juro;
    }

    public void setJuro(Long juro) {
        this.juro = juro;
    }

    public Boolean getSimnao() {
        return simnao;
    }

    public void setSimnao(Boolean simnao) {
        this.simnao = simnao;
    }

    public Long getPgto() {
        return pgto;
    }

    public void setPgto(Long pgto) {
        this.pgto = pgto;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public Date getData1() {
        return data1;
    }

    public void setData1(Date data1) {
        this.data1 = data1;
    }

    public String getNotafisca() {
        return notafisca;
    }

    public void setNotafisca(String notafisca) {
        this.notafisca = notafisca;
    }

    public Long getVia() {
        return via;
    }

    public void setVia(Long via) {
        this.via = via;
    }

    public Boolean getBaixa() {
        return baixa;
    }

    public void setBaixa(Boolean baixa) {
        this.baixa = baixa;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Long getNparce() {
        return nparce;
    }

    public void setNparce(Long nparce) {
        this.nparce = nparce;
    }

    public Long getCodinstituicao() {
        return codinstituicao;
    }

    public void setCodinstituicao(Long codinstituicao) {
        this.codinstituicao = codinstituicao;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public Long getCodcaixa() {
        return codcaixa;
    }

    public void setCodcaixa(Long codcaixa) {
        this.codcaixa = codcaixa;
    }

    public String getServicosolicitado() {
        return servicosolicitado;
    }

    public void setServicosolicitado(String servicosolicitado) {
        this.servicosolicitado = servicosolicitado;
    }

    public List<PedidoProduto> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoProduto> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "" + pedido + " - " + nome;
    }

    public Cursor retornaPedido(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedido", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoFiltradaCursor(Context context, Long codPedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedido where pedido = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedido where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public boolean remover(Context context, Pedido pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedido", "pedido = " + pedido.getPedido(), null);
        return retorno > 0;
    }

    public boolean removerPedido(Context context, Long pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedido", "pedido = " + pedido, null);
        return retorno > 0;
    }


    public Boolean cadastraPedido(Context context, Pedido pedido) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(pedido.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), pedido, valores);
        }

        if (valores.get("pedido") == null) {
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("pedido");
            valores.remove("cadastroandroid");
            valores.put("pedido", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("pedido", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        } else {
            valores.remove("alteradoandroid");
            valores.put("alteradoandroid", true);
            long retorno = db.update("pedido", valores, "pedido= " + valores.get("pedido").toString(), null);
            db.close();
            valores.clear();
            return retorno != -1;
        }
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(pedido) from pedido", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public void alteraCodPedido(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("pedido", codigoServidor);
        int retorno = db.update("pedido", values, "pedido = " + codigoAndroid, null);
        values.clear();
    }

    public void alteraCodPedidoProduto(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("pedido", codigoServidor);
        int retorno = db.update("pedidoproduto", values, "pedido = " + codigoAndroid, null);

    }
}
