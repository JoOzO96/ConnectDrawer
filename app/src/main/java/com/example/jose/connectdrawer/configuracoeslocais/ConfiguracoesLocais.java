package com.example.jose.connectdrawer.configuracoeslocais;

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
import java.util.List;

public class ConfiguracoesLocais {

    private Long id;
    private String codvendedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
    }

    public ConfiguracoesLocais retornaconfiguracoeslocais(Context context) {
        ConfiguracoesLocais configuracoesLocais = new ConfiguracoesLocais();
        Banco myDb = new Banco(context);
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM configuracoeslocais", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(ConfiguracoesLocais.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldListCliente.size() != f; f++) {
                String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retObjeto = getSetDinamico.insereField(fieldListCliente.get(f), configuracoesLocais, retorno);
                    configuracoesLocais = (ConfiguracoesLocais) retObjeto;
                }
            }
        }
        db.close();
        return configuracoesLocais;
    }

    public void cadastraconfiguracoeslocais(Context context, ConfiguracoesLocais configuracoesLocais) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(ConfiguracoesLocais.class.getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), configuracoesLocais, valores);
        }

        if (valores.get("id") == null) {
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("id");
            valores.put("id", retorno);
            retorno = db.insert("configuracoeslocais", null, valores);
            db.close();
            valores.clear();
        } else {
            configuracoesLocais = configuracoesLocais.retornaconfiguracoeslocais(context);
            long retorno = db.update("configuracoeslocais", valores, "id= " + valores.get("id").toString(), null);
            db.close();
            valores.clear();
        }
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(codigo) from cliente", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }
}
