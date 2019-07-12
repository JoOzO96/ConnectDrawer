package com.example.jose.connectdrawer.NotaProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.NotaFiscal.NotaFiscal;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NotaProduto {
    Long idnotaproduto;
    String codnota;
    Long codemitente;
    String codigo;
    Long auto;
    Double quantidade;
    Double valorunitario;
    Double valortotal;
    Double valornota;
    Double valoripi;
    Double aliqicms;
    Double aliqipi;
    String codicms;
    Long peso;
    String cfop;
    Double bicms;
    Double vicms;
    Double descopro;
    Long mvap;
    Double vbcst;
    Double vsst;
    Double vseguro;
    String descri;
    Double vfrete;
    Long codtipo;
    String codpis;
    Long porpis;
    String codcofins;
    Long porcofins;
    String codipi;
    String sst;
    Double voutros;
    Double totaltribpro;
    Long porimposto;
    Long pesoliq;
    Date datas;
    Long cstpis;
    Double vpis;
    Long cstcofins;
    Double vcofins;
    Double vcusto;
    Double totaltribest;
    String comple;
    String ncmproduto;

    public Long getIdnotaproduto() {
        return idnotaproduto;
    }

    public void setIdnotaproduto(Long idnotaproduto) {
        this.idnotaproduto = idnotaproduto;
    }

    public String getCodnota() {
        return codnota;
    }

    public void setCodnota(String codnota) {
        this.codnota = codnota;
    }

    public Long getCodemitente() {
        return codemitente;
    }

    public void setCodemitente(Long codemitente) {
        this.codemitente = codemitente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getAuto() {
        return auto;
    }

    public void setAuto(Long auto) {
        this.auto = auto;
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

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public Double getValornota() {
        return valornota;
    }

    public void setValornota(Double valornota) {
        this.valornota = valornota;
    }

    public Double getValoripi() {
        return valoripi;
    }

    public void setValoripi(Double valoripi) {
        this.valoripi = valoripi;
    }

    public Double getAliqicms() {
        return aliqicms;
    }

    public void setAliqicms(Double aliqicms) {
        this.aliqicms = aliqicms;
    }

    public Double getAliqipi() {
        return aliqipi;
    }

    public void setAliqipi(Double aliqipi) {
        this.aliqipi = aliqipi;
    }

    public String getCodicms() {
        return codicms;
    }

    public void setCodicms(String codicms) {
        this.codicms = codicms;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public Double getBicms() {
        return bicms;
    }

    public void setBicms(Double bicms) {
        this.bicms = bicms;
    }

    public Double getVicms() {
        return vicms;
    }

    public void setVicms(Double vicms) {
        this.vicms = vicms;
    }

    public Double getDescopro() {
        return descopro;
    }

    public void setDescopro(Double descopro) {
        this.descopro = descopro;
    }

    public Long getMvap() {
        return mvap;
    }

    public void setMvap(Long mvap) {
        this.mvap = mvap;
    }

    public Double getVbcst() {
        return vbcst;
    }

    public void setVbcst(Double vbcst) {
        this.vbcst = vbcst;
    }

    public Double getVsst() {
        return vsst;
    }

    public void setVsst(Double vsst) {
        this.vsst = vsst;
    }

    public Double getVseguro() {
        return vseguro;
    }

    public void setVseguro(Double vseguro) {
        this.vseguro = vseguro;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Double getVfrete() {
        return vfrete;
    }

    public void setVfrete(Double vfrete) {
        this.vfrete = vfrete;
    }

    public Long getCodtipo() {
        return codtipo;
    }

    public void setCodtipo(Long codtipo) {
        this.codtipo = codtipo;
    }

    public String getCodpis() {
        return codpis;
    }

    public void setCodpis(String codpis) {
        this.codpis = codpis;
    }

    public Long getPorpis() {
        return porpis;
    }

    public void setPorpis(Long porpis) {
        this.porpis = porpis;
    }

    public String getCodcofins() {
        return codcofins;
    }

    public void setCodcofins(String codcofins) {
        this.codcofins = codcofins;
    }

    public Long getPorcofins() {
        return porcofins;
    }

    public void setPorcofins(Long porcofins) {
        this.porcofins = porcofins;
    }

    public String getCodipi() {
        return codipi;
    }

    public void setCodipi(String codipi) {
        this.codipi = codipi;
    }

    public String getSst() {
        return sst;
    }

    public void setSst(String sst) {
        this.sst = sst;
    }

    public Double getVoutros() {
        return voutros;
    }

    public void setVoutros(Double voutros) {
        this.voutros = voutros;
    }

    public Double getTotaltribpro() {
        return totaltribpro;
    }

    public void setTotaltribpro(Double totaltribpro) {
        this.totaltribpro = totaltribpro;
    }

    public Long getPorimposto() {
        return porimposto;
    }

    public void setPorimposto(Long porimposto) {
        this.porimposto = porimposto;
    }

    public Long getPesoliq() {
        return pesoliq;
    }

    public void setPesoliq(Long pesoliq) {
        this.pesoliq = pesoliq;
    }

    public Date getDatas() {
        return datas;
    }

    public void setDatas(Date datas) {
        this.datas = datas;
    }

    public Long getCstpis() {
        return cstpis;
    }

    public void setCstpis(Long cstpis) {
        this.cstpis = cstpis;
    }

    public Double getVpis() {
        return vpis;
    }

    public void setVpis(Double vpis) {
        this.vpis = vpis;
    }

    public Long getCstcofins() {
        return cstcofins;
    }

    public void setCstcofins(Long cstcofins) {
        this.cstcofins = cstcofins;
    }

    public Double getVcofins() {
        return vcofins;
    }

    public void setVcofins(Double vcofins) {
        this.vcofins = vcofins;
    }

    public Double getVcusto() {
        return vcusto;
    }

    public void setVcusto(Double vcusto) {
        this.vcusto = vcusto;
    }

    public Double getTotaltribest() {
        return totaltribest;
    }

    public void setTotaltribest(Double totaltribest) {
        this.totaltribest = totaltribest;
    }

    public String getComple() {
        return comple;
    }

    public void setComple(String comple) {
        this.comple = comple;
    }

    public String getNcmproduto() {
        return ncmproduto;
    }

    public void setNcmproduto(String ncmproduto) {
        this.ncmproduto = ncmproduto;
    }

    public NotaProduto retornaNotaProdutoObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        NotaProduto notaProduto = new NotaProduto();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM notaproduto where idnotaproduto = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldList = new ArrayList<>(Arrays.asList(NotaProduto.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldList.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                String nomeCampo = fieldList.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object ret = getSetDinamico.insereField(fieldList.get(f), notaProduto, retorno);
                    notaProduto = (NotaProduto) ret;
                }
            }
        }
        db.close();
        return notaProduto;
    }


    public NotaProduto cadastraNotaProduto(Context context, NotaProduto notaProduto) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(notaProduto.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), notaProduto, valores);
        }

        if (valores.get("idnotaproduto") == null) {
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("idnotaproduto");
            valores.remove("cadastroandroid");
            valores.put("idnotaproduto", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("notaproduto", null, valores);
            db.close();

            notaProduto = notaProduto.retornaNotaProdutoObjeto(context, valores.getAsLong("idnotaproduto"));
            valores.clear();
            return notaProduto;
        } else {
            Cursor cursor = notaProduto.retornaNotaProdutoFiltradaCursor(context, Long.parseLong(valores.get("idnotaproduto").toString()));

            if (cursor.getCount() > 0) {
                valores.remove("alteradoandroid");
                valores.put("alteradoandroid", true);
                long retorno = db.update("notaproduto", valores, "idnotaproduto= " + valores.get("idnotaproduto").toString(), null);
                db.close();
                valores.clear();
                notaProduto = notaProduto.retornaNotaProdutoObjeto(context, retorno);
                return notaProduto;
            } else {
                long retorno = retornaMaiorCod(context);
                retorno = retorno + 1;
                valores.remove("cadastroandroid");
                retorno = db.insert("notaproduto", null, valores);
                db.close();
                valores.clear();
                notaProduto = notaProduto.retornaNotaProdutoObjeto(context, retorno);
                return notaProduto;
            }
        }
    }

    private Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(idnotaproduto) from notaproduto", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public Cursor retornaNotaProdutoFiltradaCursor(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NotaProduto where idNotaProduto = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
