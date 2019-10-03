package com.example.jose.connectdrawer.cliente;

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
import java.util.Objects;

public class ClienteAnimais {
    Long idclienteanimal;
    Long codcliente;
    String nomeanimal;
    Date datanascimentoanimal;
    String especieanimal;
    String sexoanimal;
    String racaanimal;
    String pelagem;
    Double pesoanimal;
    String situacaoanimal;
    String obsanimal;

    public Long getIdclienteanimal() {
        return idclienteanimal;
    }

    public void setIdclienteanimal(Long idclienteanimal) {
        this.idclienteanimal = idclienteanimal;
    }

    public Long getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Long codcliente) {
        this.codcliente = codcliente;
    }

    public String getNomeanimal() {
        return nomeanimal;
    }

    public void setNomeanimal(String nomeanimal) {
        this.nomeanimal = nomeanimal;
    }

    public Date getDatanascimentoanimal() {
        return datanascimentoanimal;
    }

    public void setDatanascimentoanimal(Date datanascimentoanimal) {
        this.datanascimentoanimal = datanascimentoanimal;
    }

    public String getEspecieanimal() {
        return especieanimal;
    }

    public void setEspecieanimal(String especieanimal) {
        this.especieanimal = especieanimal;
    }

    public String getSexoanimal() {
        return sexoanimal;
    }

    public void setSexoanimal(String sexoanimal) {
        this.sexoanimal = sexoanimal;
    }

    public String getRacaanimal() {
        return racaanimal;
    }

    public void setRacaanimal(String racaanimal) {
        this.racaanimal = racaanimal;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public Double getPesoanimal() {
        return pesoanimal;
    }

    public void setPesoanimal(Double pesoanimal) {
        this.pesoanimal = pesoanimal;
    }

    public String getSituacaoanimal() {
        return situacaoanimal;
    }

    public void setSituacaoanimal(String situacaoanimal) {
        this.situacaoanimal = situacaoanimal;
    }

    public String getObsanimal() {
        return obsanimal;
    }

    public void setObsanimal(String obsanimal) {
        this.obsanimal = obsanimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteAnimais)) return false;
        ClienteAnimais that = (ClienteAnimais) o;
        return Objects.equals(getCodcliente(), that.getCodcliente()) &&
                Objects.equals(getNomeanimal(), that.getNomeanimal()) &&
                Objects.equals(getDatanascimentoanimal(), that.getDatanascimentoanimal()) &&
                Objects.equals(getEspecieanimal(), that.getEspecieanimal()) &&
                Objects.equals(getSexoanimal(), that.getSexoanimal()) &&
                Objects.equals(getRacaanimal(), that.getRacaanimal()) &&
                Objects.equals(getPelagem(), that.getPelagem()) &&
                Objects.equals(getPesoanimal(), that.getPesoanimal()) &&
                Objects.equals(getSituacaoanimal(), that.getSituacaoanimal()) &&
                Objects.equals(getObsanimal(), that.getObsanimal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodcliente(), getNomeanimal(), getDatanascimentoanimal(), getEspecieanimal(), getSexoanimal(), getRacaanimal(), getPelagem(), getPesoanimal(), getSituacaoanimal(), getObsanimal());
    }

    @Override
    public String toString() {
        return idclienteanimal + " - " + nomeanimal;
    }


    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(idclienteanimal) from clienteanimais", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public Cursor retornaClienteAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM clienteanimais where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Boolean cadastraClienteAnimais(Context context, ClienteAnimais clienteAnimais) {
        Banco myDb;
        myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(clienteAnimais.getClass().getDeclaredFields()));
        Long retorno = 0L;
        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), clienteAnimais, valores);
        }

        if (valores.get("idclienteanimal") == null) {
            valores.remove("cadastroandroid");
            valores.put("cadastroandroid", true);
            valores.put("idclienteanimal", retornaMaiorCod(context) + 1);
            retorno = db.insert("clienteanimais", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        } else {
            ClienteAnimais clienteanimaisret = clienteAnimais.retornaClienteAnimais(context, valores.getAsLong("idclienteanimal"));

            if (!clienteanimaisret.equals(clienteAnimais)) {
                if (clienteanimaisret.equals(new ClienteAnimais())) {
                    valores.remove("cadastroandroid");
                    valores.put("cadastroandroid", true);
                    retorno = db.insert("clienteanimais", null, valores);
                    db.close();
                    valores.clear();
                    return retorno != -1;
                } else {
                    valores.remove("alteradoandroid");
                    valores.put("alteradoandroid", true);
                    retorno = (long) db.update("clienteanimais", valores, "idclienteanimal = " + idclienteanimal, null);
                    db.close();
                    valores.clear();
                    return retorno != -1;
                }
            }
            return true;
        }
    }

    public ClienteAnimais retornaClienteAnimais(Context context, Long idclienteanimal) {
        Banco myDb = new Banco(context);
        ClienteAnimais clienteAnimais = new ClienteAnimais();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM clienteanimais where idclienteanimal = " + idclienteanimal, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(ClienteAnimais.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldListCliente.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retCliente = getSetDinamico.insereField(fieldListCliente.get(f), clienteAnimais, retorno);
                    clienteAnimais = (ClienteAnimais) retCliente;
                }
            }
        }
        db.close();
        return clienteAnimais;
    }

    public List<ClienteAnimais> retornaListaClienteAnimais(Context context, Long codcliente) {
        Banco myDb = new Banco(context);
        List<ClienteAnimais> clienteAnimaisList = new ArrayList<>();
        ClienteAnimais clienteAnimais = new ClienteAnimais();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM clienteanimais where codcliente = " + codcliente, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                clienteAnimais = new ClienteAnimais();
                clienteAnimais = retornaClienteAnimais(context, cursor.getLong(cursor.getColumnIndex("idclienteanimal")));
                clienteAnimaisList.add(clienteAnimais);
                cursor.moveToNext();
            }
        }
        return clienteAnimaisList;
    }
}
