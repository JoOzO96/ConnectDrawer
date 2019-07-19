package com.example.jose.connectdrawer.ParcelasNFE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.Pedido.Pedido;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ParcelaNFE {
    Long idParcela;
    String codnota;
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

    public String getCodnota() {
        return codnota;
    }

    public void setCodnota(String codnota) {
        this.codnota = codnota;
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

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataVencimento = simpleDateFormat.format(dvenci);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return fatura + " - Venc: " +  dataVencimento +  " Valor: " + decimalFormat.format(vparce) +" Dias: " + diave;
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(idParcela) from parcelanfe", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public Boolean cadastraParcela(Context context, ParcelaNFE parcelaNE){
        Banco myDb = new Banco(context);

        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(ParcelaNFE.class.getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), parcelaNE, valores);
        }

        if (valores.get("idParcela") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("idParcela");
            valores.remove("cadastroandroid");
            valores.put("idParcela", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("parcelanfe", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        }else{
            Cursor cursor = parcelaNE.retornaParcelaFiltradaCursor(context, Long.parseLong(valores.get("idParcela").toString()));

            if (cursor.getCount() > 0){
                valores.remove("alteradoandroid");
                valores.put("alteradoandroid", true);
                long retorno = db.update("parcelanfe", valores, "idParcela= " + valores.get("idParcela").toString(), null);
                db.close();
                valores.clear();
                return retorno != -1;
            }else{
                long retorno = retornaMaiorCod(context);
                retorno = retorno + 1;
                valores.remove("cadastroandroid");
                valores.put("cadastroandroid", true);
                retorno = db.insert("parcelanfe", null, valores);
                db.close();
                valores.clear();
                return retorno != -1;
            }
        }
    }

    public void limpaParcelaNE(Context context, Long codnota) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        db.delete("parcelanfe","codnota="+codnota,null );
    }

    public ParcelaNFE retornaParcelaneObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        ParcelaNFE parcelaNE = new ParcelaNFE();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM parcelanfe where idParcela = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(ParcelaNFE.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldListCliente.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retParcelasNe = getSetDinamico.insereField(fieldListCliente.get(f), parcelaNE, retorno);
                    parcelaNE = (ParcelaNFE) retParcelasNe;
                }
            }
        }
        db.close();
        return parcelaNE;
    }

    public Cursor retornaParcelaFiltradaCursor(Context context, Long idParcela) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM parcelanfe where idParcela = " + idParcela, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public boolean retornaExistenciaParcelane(Context context, Long codigoPedido) {
        Boolean existe = false;
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM parcelanfe where codnota = " + codigoPedido, null);
        if (cursor.getCount() > 0) {
            existe = true;
        }
        db.close();
        return  existe;
    }

    public List<ParcelaNFE> retornaListaDeParcelane(Context context, Long codnota) {
        Boolean existe = false;
        List<ParcelaNFE> parcelaneList = new ArrayList<>();
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM parcelanfe where codnota = " + codnota + " ORDER BY dvenci", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for(int i = 0 ; i < cursor.getCount() ; i++) {
                parcelaneList.add(retornaParcelaneObjeto(context, cursor.getLong(cursor.getColumnIndex("idparcela"))));
                cursor.moveToNext();
            }

        }
//        db.close();
        return  parcelaneList;
    }

    public void recalculaValorParcelane(Context context, Long codnota, Long idParcela){
        Double valorPedido = 0D;
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Pedido pedido = new Pedido();
        pedido = pedido.retornaPedidoObjeto(context, codnota);
        valorPedido = pedido.getValortotal();
        List<ParcelaNFE> parcelaneList = new ArrayList<>();


        parcelaneList = retornaListaDeParcelane(context, codnota);

        for (int i = 0; i<parcelaneList.size();i++){
            valorPedido -= parcelaneList.get(i).getVparce();
        }
        limpaParcelaNE(context, codnota);
        for (int i = 0; i<parcelaneList.size();i++){
            if (parcelaneList.get(i).getIdParcela() != idParcela){
                parcelaneList.get(i).setVparce((parcelaneList.get(i).getVparce()) + (valorPedido / (parcelaneList.size() - 1)));
                cadastraParcela(context, parcelaneList.get(i));
            }else {
                cadastraParcela(context, parcelaneList.get(i));
            }
        }
    }

    public Cursor retornaParcelaneAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT codnota FROM parcelanfe where " + tipo + " = 1 GROUP BY codnota", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void removeParcelaneAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo, "0");
        int retorno = db.update("parcelanfe", values, campo + " = 1", null);

    }

}
