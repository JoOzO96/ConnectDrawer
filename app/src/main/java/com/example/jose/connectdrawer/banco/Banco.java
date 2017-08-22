package com.example.jose.connectdrawer.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
                "idPedidoProduto long PRIMARY KEY,"+
                "codmecanico text,"+//cód mecanico
                "codpedido double,"+//cód pedido
                "codproduto text,"+//cód produto
                "comip double,"+
                "conta integer,"+
                "custo double,"+
                "datas real,"+
                "desconto double,"+
                "descri text,"+
                "desenho text,"+
                "desvalor double,"+
                "dot text,"+
                "eminota boolean,"+
                "eminotaagru boolean,"+
                "lucro double,"+
                "marca text,"+
                "modelo text,"+
                "nserie text,"+
                "pedido long,"+
                "porimposto double,"+
                "quanti double,"+
                "quantidade double,"+
                "retirada double,"+
                "saldoret double,"+
                "tamanho text,"+
                "totalimposto double,"+
                "totalimpostoest double,"+
                "valortotal double,"+//valor total
                "valorunitario double,"+//valor unitário
                "vcomi double,"+
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE produto (" +
                "codproduto text PRIMARY KEY,"+
                "aliqcide text,"+
                "ano text,"+
                "anomodelo text,"+
                "anp text,"+
                "aplicacao text,"+
                "ativo text,"+
                "carencia text,"+
                "cfop text,"+
                "chassis text,"+
                "classe text,"+
                "codicms text,"+
                "codcest text,"+
                "codcofins text,"+
                "codcofinse text,"+
                "codeauto boolean,"+
                "codemitente integer,"+
                "codfornecedor text,"+
                "codpedido text,"+
                "codgrupo text,"+
                "codifeanp text,"+
                "codipi text,"+
                "codipise text,"+
                "codpis text,"+
                "codpise text,"+
                "codsubgrupo text,"+
                "combustivel text,"+
                "comentario text,"+
                "comissao double,"+
                "compra boolean,"+
                "concentracao text,"+
                "conversao text,"+
                "conversao1 text,"+
                "conversao2 text,"+
                "cor text,"+
                "cstcofins double,"+
                "cstcofinse double,"+
                "cstpis double,"+
                "cstpise double,"+
                "cusope double,"+
                "custo double,"+
                "custo1 double,"+
                "custoimpostos double,"+
                "custosobrelucro double,"+
                "dataalte real,"+
                "datacad real,"+
                "datacompra real,"+
                "datavencimentoprocuracao real,"+
                "datavenda real,"+
                "descobal double,"+
                "desconto double,"+
                "desp text,"+
                "despesas double,"+
                "dificms double,"+
                "divisao double,"+
                "dosagem text,"+
                "ean text,"+
                "embala text,"+
                "equipamento text,"+
                "especietipo text,"+
                "fitoxidade text,"+
                "formulacao text,"+
                "frete double,"+
                "genero text,"+
                "imposobrelucro double,"+
                "inativo boolean,"+
                "infade text,"+
                "infadicionais text,"+
                "infespecifica text,"+
                "ipi double,"+
                "letras text,"+
                "locregistro text,"+
                "maquina boolean,"+
                "marca text,"+
                "markup double,"+
                "maximo double,"+
                "mercadoria text,"+
                "minimo double,"+
                "modelo text,"+
                "montapedido boolean,"+
                "mva double,"+
                "mvafora double,"+
                "ncm text,"+
                "numero text,"+
                "numeroori text,"+
                "obs text,"+
                "obsproduto text,"+
                "oleosoja boolean,"+
                "perconsumidor double,"+
                "perprazo double,"+
                "peso double,"+
                "pesoc double,"+
                "placa text,"+
                "porcofins double,"+
                "porcofinse double,"+
                "porpis double,"+
                "porpise double,"+
                "porsimples double,"+
                "porsub double,"+
                "prateleira text,"+
                "produtocereais boolean,"+
                "produtoconversao boolean,"+
                "produtonaofiscal boolean,"+
                "produtotanque boolean,"+
                "produtotrigo boolean,"+
                "quantidade double,"+
                "quantidadeconversao double,"+
                "redu double,"+
                "reentrada text,"+
                "renavan text,"+
                "sittrib text,"+
                "tamanho text,"+
                "taxafora double,"+
                "tortasoja boolean,"+
                "total double,"+
                "toxidade text,"+
                "unid text,"+
                "vaca boolean,"+
                "valodificms double,"+
                "valoipi double,"+
                "valorcompra double,"+
                "valoravista double,"+
                "valorconsumidor double,"+
                "valorprazo double,"+
                "valortaxa double,"+
                "vcofins double,"+
                "vcofinse double,"+
                "veiculo boolean,"+
                "vfrete double,"+
                "vimpsimples double,"+
                "vpis double,"+
                "vpise double,"+
                "vsubsti double,"+
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }






























}
