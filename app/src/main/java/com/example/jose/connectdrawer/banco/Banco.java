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
                "nomecliente text,"+
                "cpf text,"+
                "cgc text,"+
                "datanasc real,"+
                "endereco text,"+
                "posicao text,"+
                "pai text,"+
                "mae text,"+
                "bairro text,"+
                "cep text,"+
                "identidade text,"+
                "trabalho text,"+
                "enderecotrab text,"+
                "codprofissao long,"+
                "codcidade long,"+
                "responsavel text,"+
                "fone text,"+
                "obs text,"+
                "nume text,"+
                "email text,"+
                "pessoaauto text,"+
                "limitecredito real,"+
                "pessoaauto1 text,"+
                "limitecredito1 real,"+
                "pessoaauto2 text,"+
                "limitecredito2 real,"+
                "limitepessoal real,"+
                "tipocliente long,"+
                "codvendedor text,"+
                "simples boolean,"+
                "celular text,"+
                "incest text,"+
                "fisju text,"+
                "fonetrab text,"+
                "telefone text,"+
                "conjuge text,"+
                "fretecli text,"+
                "antecipacao long,"+
                "etiquetas boolean,"+
                "sistema boolean,"+
                "recibo booelan,"+
                "vmanu real,"+
                "codigopgto long,"+
                "codrepresentante text,"+
                "datacadastro real,"+
                "dataalteracao real,"+
                "liberalimite boolean,"+
                "fantasia text,"+
                "contatocobranca text,"+
                "inativo boolean,"+
                "clientetipo long,"+
                "diacobranca long,"+
                "diaparavencimento long," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE cidade (" +
                "codcidade long primary key,"+
                "nomecidade text,"+
                "uf text,"+
                "codnacionaluf text,"+
                "codnacionalcidade text,"+
                "pais text,"+
                "codnacionalpais text,"+
                "cep text," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE pedido (" +
                "pedido long primary key,"+
                "codpedido long ,"+
                "codcliente long ,"+
                "data real ,"+
                "codvendedor text ,"+
                "formadepagamento text ,"+
                "frete real ,"+
                "valortotal real ,"+
                "entrada real ,"+
                "orpedi text ,"+
                "codbanco long ,"+
                "obs text ,"+
                "desconto long ,"+
                "nome text ,"+
                "total real ,"+
                "venci1 real ,"+
                "valor1 real ,"+
                "dias long ,"+
                "juro long ,"+
                "simnao boolean ,"+
                "pgto long ,"+
                "cheque text ,"+
                "data1 real ,"+
                "notafisca text ,"+
                "via long ,"+
                "baixa boolean ,"+
                "veiculo text ,"+
                "placa text ,"+
                "ano text ,"+
                "nparce long ,"+
                "codinstituicao long ,"+
                "nfc text ,"+
                "codcaixa long ,"+
                "servicosolicitado text ,"+
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE vendedor (" +
                "codvendedor text primary key,"+
                "nomevendedor text,"+
                "comi double,"+
                "comis double,"+
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);


        sql = "CREATE TABLE formapagamento (" +
                "codigo long primary key,"+
                "pagamento text,"+
                "prazo boolean,"+
                "cartao boolean,"+
                "codcaixa long,"+
                "encaixa boolean,"+
                "fechamento boolean,"+
                "cheque boolean,"+
                "listapre long,"+
                "naolancareceber boolean,"+
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);


        sql = "CREATE TABLE pedidoproduto (" +
                "id long,"+
                "pedido long,"+
                "codproduto long,"+
                "quantidade double,"+
                "valorunitario double,"+
                "descri double,"+
                "custo double,"+
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean," +
                "PRIMARY KEY(id, pedido, codproduto);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }






























}
