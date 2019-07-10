package com.example.jose.connectdrawer.Parcelas;

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
            valores.remove("idParcela");
            valores.remove("cadastroandroid");
            valores.put("idParcela", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("parcela", null, valores);
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
                valores.put("cadastroandroid", true);
                retorno = db.insert("parcela", null, valores);
                db.close();
                valores.clear();
                return retorno != -1;
            }
        }
    }

    public void limpaParcelas(Context context, Long codPedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        db.delete("parcela","codpedido="+codPedido,null );
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

    public boolean retornaExistenciaParcelas(Context context, Long codigoPedido) {
        Boolean existe = false;
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM parcela where codPedido = " + codigoPedido, null);
        if (cursor.getCount() > 0) {
            existe = true;
        }
        db.close();
        return  existe;
    }

    public List<Parcelas> retornaListaDeParcelas(Context context, Long codigoPedido) {
        Boolean existe = false;
        List<Parcelas> parcelasList = new ArrayList<>();
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM parcela where codPedido = " + codigoPedido + " ORDER BY dvenci", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for(int i = 0 ; i < cursor.getCount() ; i++) {
                parcelasList.add(retornaParcelasObjeto(context, cursor.getLong(cursor.getColumnIndex("idparcela"))));
                cursor.moveToNext();
            }

        }
//        db.close();
        return  parcelasList;
    }

    public void recalculaValorParcelas(Context context, Long codPedido, Long idParcela){
        Double valorPedido = 0D;
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Pedido pedido = new Pedido();
        pedido = pedido.retornaPedidoObjeto(context, codPedido);
        valorPedido = pedido.getValortotal();
        List<Parcelas> parcelasList = new ArrayList<>();


        parcelasList = retornaListaDeParcelas(context, codPedido);

        for (int i = 0; i<parcelasList.size();i++){
            valorPedido -= parcelasList.get(i).getVparce();
        }
        limpaParcelas(context, codPedido);
        for (int i = 0; i<parcelasList.size();i++){
            if (parcelasList.get(i).getIdParcela() != idParcela){
                parcelasList.get(i).setVparce((parcelasList.get(i).getVparce()) + (valorPedido / (parcelasList.size() - 1)));
                cadastraParcela(context, parcelasList.get(i));
            }else {
                cadastraParcela(context, parcelasList.get(i));
            }
        }
    }

    public Cursor retornaParcelaAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT codpedido FROM parcela where " + tipo + " = 1 GROUP BY codpedido", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void removeParcelaAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo, "0");
        int retorno = db.update("parcela", values, campo + " = 1", null);

    }

}
