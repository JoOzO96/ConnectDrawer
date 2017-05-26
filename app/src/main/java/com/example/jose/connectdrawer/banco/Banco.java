package com.example.jose.connectdrawer.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jose on 18/05/2017.
 */

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "connect.db";
    private static final int VERSAO = 1;

    public Banco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.delete("cliente",null,null);
        String sql = "CREATE TABLE cliente (" +
                "codigo long primary key,"+
                "nomeCliente text,"+
                "cpf text,"+
                "cgc text,"+
                "dataNasc real,"+
                "endereco text,"+
                "posicao text,"+
                "pai text,"+
                "mae text,"+
                "bairro text,"+
                "cep text,"+
                "identidade text,"+
                "trabalho text,"+
                "enderecotrab text,"+
                "codProfissao long,"+
                "codCidade long,"+
                "responsavel text,"+
                "fone text,"+
                "obs text,"+
                "nume text,"+
                "email text,"+
                "pessoaAuto text,"+
                "limiteCredito real,"+
                "pessoaAuto1 text,"+
                "limiteCredito1 real,"+
                "pessoaAuto2 text,"+
                "limiteCredito2 real,"+
                "limitePessoal real,"+
                "tipoCliente long,"+
                "codVendedor text,"+
                "simples boolean,"+
                "celular text,"+
                "incest text,"+
                "fisJu text,"+
                "fonetrab text,"+
                "telefone text,"+
                "conjuge text,"+
                "freteCli text,"+
                "antecipacao long,"+
                "etiquetas boolean,"+
                "sistema boolean,"+
                "recibo booelan,"+
                "vmanu real,"+
                "codigoPgto long,"+
                "codRepresentante text,"+
                "dataCadastro real,"+
                "dataAlteracao real,"+
                "liberaLimite boolean,"+
                "fantasia text,"+
                "contatoCobranca text,"+
                "inativo boolean,"+
                "clienteTipo long,"+
                "diaCobranca long,"+
                "diaParaVencimento long," +
                "cadastroAndroid boolean," +
                "deletadoAndroid boolean," +
                "alteradoAndroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE cidade (" +
                "codCidade long primary key,"+
                "nomeCidade text,"+
                "uf text,"+
                "codNacionalUf text,"+
                "codNacionalCidade text,"+
                "pais text,"+
                "codNacionalPais text,"+
                "cep text);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }






























}
