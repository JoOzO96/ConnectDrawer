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
    private static final int VERSAO = 8;

    public Banco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.delete("cliente",null,null);
        String sql = "CREATE TABLE cliente (" +
                "codigo long primary key," +
                "nomecliente text," +
                "cpf text," +
                "cgc text," +
                "datanasc real," +
                "endereco text," +
                "posicao text," +
                "pai text," +
                "mae text," +
                "bairro text," +
                "cep text," +
                "identidade text," +
                "trabalho text," +
                "enderecotrab text," +
                "codprofissao long," +
                "codcidade long," +
                "responsavel text," +
                "fone text," +
                "obs text," +
                "nume text," +
                "email text," +
                "pessoaauto text," +
                "limitecredito real," +
                "pessoaauto1 text," +
                "limitecredito1 real," +
                "pessoaauto2 text," +
                "limitecredito2 text," +
                "limitepessoal real," +
                "tipocliente long," +
                "codvendedor text," +
                "simples boolean," +
                "celular text," +
                "incest text," +
                "fisju text," +
                "fonetrab text," +
                "telefone text," +
                "conjuge text," +
                "fretecli text," +
                "antecipacao long," +
                "etiquetas boolean," +
                "sistema boolean," +
                "recibo booelan," +
                "vmanu real," +
                "codigopgto long," +
                "codrepresentante text," +
                "datacadastro real," +
                "dataalteracao real," +
                "liberalimite boolean," +
                "fantasia text," +
                "contatocobranca text," +
                "inativo boolean," +
                "clientetipo long," +
                "diacobranca long," +
                "diaparavencimento long," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE cidade (" +
                "codcidade long primary key," +
                "nomecidade text," +
                "uf text," +
                "codnacionaluf text," +
                "codnacionalcidade text," +
                "pais text," +
                "codnacionalpais text," +
                "cep text," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE pedido (" +
                "pedido long primary key," +
                "codpedido long ," +
                "codcliente long ," +
                "data real ," +
                "codvendedor text ," +
                "formadepagamento text ," +
                "frete real ," +
                "valortotal real ," +
                "entrada real ," +
                "orpedi text ," +
                "codbanco long ," +
                "obs text ," +
                "desconto long ," +
                "nome text ," +
                "total real ," +
                "venci1 real ," +
                "valor1 real ," +
                "dias long ," +
                "juro long ," +
                "simnao boolean ," +
                "nfe boolean," +
                "pgto long ," +
                "cheque text ," +
                "data1 real ," +
                "notafisca text ," +
                "via long ," +
                "baixa boolean ," +
                "veiculo text ," +
                "placa text ," +
                "ano text ," +
                "nparce long ," +
                "codinstituicao long ," +
                "nfc text ," +
                "codcaixa long ," +
                "codemitente long," +
                "codstatus long," +
                "servicosolicitado text ," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN pedido LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codpedido LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codcliente LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN data LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codvendedor TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN formadepagamento TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN frete DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN valortotal DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN entrada DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN orpedi TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codbanco LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN obs TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN desconto LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nome TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN total DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN venci1 LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN valor1 DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN dias LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN juro LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN simnao BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN pgto LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN cheque TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN data1 LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN notafisca TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN via LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN baixa BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN veiculo TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN placa TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN ano TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nparce LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codinstituiCao LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfc TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN dife LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN comis DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfe BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN vpago DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN troco DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codhistorico TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN descvalor LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codemitente LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codmecanico TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN valorservico DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN descoservico LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN juntapedido BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN km TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nparc LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN ncf TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN comi DOUBLE");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN desvalor LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN cpf TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN parce LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN impressora TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN inicialp LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN finalp LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN totalp LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN gerabloqueto BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN gerabloqueto1 BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codplanocontas LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codcentrocustos TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN documento BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codproduto TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN mensagem BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN estornop BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codstatus LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN baixaf BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN acreboleto LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN aparelho TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN defeito TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN pedidoreferencia LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN enderecopedido TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfce BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfcee BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codcaixa LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN juridica BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN ajuste TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN dataentrega LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN servicosolicitado TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN nnotaservico TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN codbandeira LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN orcamentofinalizado BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN coddigitador TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN horasdemotor LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN horasdetrilha LONG");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN especial BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN carga BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN enviadoemailnfse BOOLEAN");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN rota TEXT");
        executaSQL(db, "ALTER TABLE pedido ADD COLUMN geradoautomatico BOOLEAN");


        sql = "CREATE TABLE vendedor (" +
                "codvendedor text primary key," +
                "nomevendedor text," +
                "comi double," +
                "comis double," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);


        sql = "CREATE TABLE formapagamento (" +
                "codigo long primary key," +
                "pagamento text," +
                "prazo boolean," +
                "cartao boolean," +
                "codcaixa long," +
                "encaixa boolean," +
                "fechamento boolean," +
                "cheque boolean," +
                "listapre long," +
                "naolancareceber boolean," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);


        sql = "CREATE TABLE pedidoproduto (" +
                "idpedidoproduto long PRIMARY KEY," +
                "codmecanico text," +//cód mecanico
                "codpedido double," +//cód pedido
                "codproduto text," +//cód produto
                "comip double," +
                "conta integer," +
                "custo double," +
                "datas real," +
                "desconto double," +
                "descri text," +
                "desenho text," +
                "desvalor double," +
                "dot text," +
                "eminota boolean," +
                "eminotaagru boolean," +
                "lucro double," +
                "marca text," +
                "modelo text," +
                "nserie text," +
                "pedido long," +
                "porimposto double," +
                "quanti double," +
                "quantidade double," +
                "retirada double," +
                "saldoret double," +
                "tamanho text," +
                "valorunitario double," +//valor unitário
                "vcomi double," +
                "cadastroandroid boolean," +
                "totalimposto double," +
                "totalimpostoest double," +
                "valortotal double," +//valor total
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE produto (" +
                "codproduto text PRIMARY KEY," +
                "aliqcide text," +
                "ano text," +
                "anomodelo text," +
                "anp text," +
                "aplicacao text," +
                "ativo text," +
                "carencia text," +
                "cfop text," +
                "chassis text," +
                "classe text," +
                "codicms text," +
                "codcest text," +
                "codcofins text," +
                "codcofinse text," +
                "codeauto boolean," +
                "codemitente integer," +
                "codfornecedor text," +
                "codpedido text," +
                "codgrupo text," +
                "codifeanp text," +
                "codipi text," +
                "codipise text," +
                "codpis text," +
                "codpise text," +
                "codsubgrupo text," +
                "combustivel text," +
                "comentario text," +
                "comissao double," +
                "compra boolean," +
                "concentracao text," +
                "conversao text," +
                "conversao1 text," +
                "conversao2 text," +
                "cor text," +
                "cstcofins double," +
                "cstcofinse double," +
                "cstpis double," +
                "cstpise double," +
                "cusope double," +
                "custo double," +
                "custo1 double," +
                "custoimpostos double," +
                "custosobrelucro double," +
                "dataalte real," +
                "datacad real," +
                "datacompra real," +
                "datavencimentoprocuracao real," +
                "datavenda real," +
                "descobal double," +
                "desconto double," +
                "desp text," +
                "despesas double," +
                "dificms double," +
                "divisao double," +
                "dosagem text," +
                "ean text," +
                "embala text," +
                "equipamento text," +
                "especietipo text," +
                "fitoxidade text," +
                "formulacao text," +
                "frete double," +
                "genero text," +
                "imposobrelucro double," +
                "inativo boolean," +
                "infade text," +
                "infadicionais text," +
                "infespecifica text," +
                "ipi double," +
                "letras text," +
                "locregistro text," +
                "maquina boolean," +
                "marca text," +
                "markup double," +
                "maximo double," +
                "mercadoria text," +
                "minimo double," +
                "modelo text," +
                "montapedido boolean," +
                "mva double," +
                "mvafora double," +
                "ncm text," +
                "numero text," +
                "numeroori text," +
                "obs text," +
                "obsproduto text," +
                "oleosoja boolean," +
                "perconsumidor double," +
                "perprazo double," +
                "peso double," +
                "pesoc double," +
                "placa text," +
                "porcofins double," +
                "porcofinse double," +
                "porpis double," +
                "porpise double," +
                "porsimples double," +
                "porsub double," +
                "prateleira text," +
                "produtocereais boolean," +
                "produtoconversao boolean," +
                "produtonaofiscal boolean," +
                "produtotanque boolean," +
                "produtotrigo boolean," +
                "quantidade double," +
                "quantidadeconversao double," +
                "redu double," +
                "reentrada text," +
                "renavan text," +
                "sittrib text," +
                "tamanho text," +
                "taxafora double," +
                "tortasoja boolean," +
                "total double," +
                "toxidade text," +
                "unid text," +
                "vaca boolean," +
                "valodificms double," +
                "valoipi double," +
                "valorcompra double," +
                "valoravista double," +
                "valorconsumidor double," +
                "valorprazo double," +
                "valortaxa double," +
                "vcofins double," +
                "vcofinse double," +
                "veiculo boolean," +
                "vfrete double," +
                "vimpsimples double," +
                "vpis double," +
                "vpise double," +
                "vsubsti double," +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE NotaFiscal(" +
                "idnota long PRIMARY KEY," +
                "codnota text," +
                "codemitente long, " +
                "codigo long," +
                "codtipo long," +
                "codcliente long," +
                "nomecliente text," +
                "cgccpf text," +
                "marc boolean," +
                "cnpj text," +
                "cpf text," +
                "endereco text," +
                "cep text," +
                "codcidade long," +
                "bairro text," +
                "fonefax text," +
                "inscesta text," +
                "saida text," +
                "venda text," +
                "materia text," +
                "dataemissao long," +
                "datasaida long," +
                "hora long," +
                "codinstituicao long," +
                "praca text," +
                "fatura text," +
                "vencimento long," +
                "valor double," +
                "baseicms double," +
                "valoricms double," +
                "icmssub double," +
                "valoricmssub double," +
                "valordosprodutos double," +
                "valorseguro double," +
                "despesas double," +
                "valordoipi double," +
                "codtransportador," +
                "valorfrete double," +
                "valornota double," +
                "observacao double," +
                "pesobruto double," +
                "pesoliquido double," +
                "quantidade double," +
                "especie text," +
                "marca text," +
                "numero text," +
                "complemento text," +
                "codvendedor text," +
                "firma text," +
                "desconto double," +
                "cf text," +
                "tran text," +
                "cancela boolean," +
                "simnao boolean," +
                "nnota text," +
                "dupli boolean," +
                "norconti text," +
                "chaveref text," +
                "chave text," +
                "protocolo text," +
                "recibo text," +
                "emidesti text," +
                "issqn double," +
                "vissqn double," +
                "pedido text," +
                "protocoloc text," +
                "envemail boolean," +
                "notaref text," +
                "obsfisco text," +
                "justicancelamento text," +
                "funrural double," +
                "reajustadas double," +
                "valorfun double," +
                "valortributos double," +
                "totaltributos double," +
                "agrupa boolean," +
                "codpgto long," +
                "baseimpo double," +
                "desaduaneira double," +
                "valoimpor double, " +
                "valoriof double, " +
                "gerabloqueto boolean, " +
                "finalidade text, " +
                "presencial text, " +
                "destioperacao text, " +
                "codplanocontas long, " +
                "codcentrocustos text, " +
                "emailnota text, " +
                "ccocupom text, " +
                "placavei text, " +
                "operacaosefaz boolean, " +
                "estonodenfe boolean, " +
                "cadastroandroid boolean," +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE NotaProduto(" +
                "idnotaproduto long PRIMARY KEY," +
                "codnota text, " +
                "codemitente long, " +
                "codigo text," +
                "auto long, " +
                "quantidade double, " +
                "valorunitario double, " +
                "valortotal double, " +
                "valornota double, " +
                "valoripi double, " +
                "aliqicms double, " +
                "aliqipi double, " +
                "codicms text, " +
                "peso long, " +
                "cfop text, " +
                "bicms double, " +
                "vicms double, " +
                "descopro double, " +
                "mvap long, " +
                "vbcst double, " +
                "vsst double, " +
                "vseguro double, " +
                "descri text, " +
                "vfrete double, " +
                "codtipo long, " +
                "codpis text, " +
                "porpis long, " +
                "codcofins text, " +
                "porcofins long, " +
                "codipi text, " +
                "sst text, " +
                "voutros double, " +
                "totaltribpro double, " +
                "porimposto long, " +
                "pesoliq long, " +
                "datas long, " +
                "cstpis long, " +
                "vpis double, " +
                "cstcofins long, " +
                "vcofins double, " +
                "vcusto double, " +
                "totaltribest double, " +
                "comple text, " +
                "ncmproduto text," +
                "cadastroandroid boolean, " +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE Parcela(" +
                "idparcela long PRIMARY KEY," +
                "codpedido text, " +
                "dvenci long, " +
                "vparce double, " +
                "diave long, " +
                "fatura text, " +
                "valorboleto double, " +
                "gerarboleto boolean," +
                "cadastroandroid boolean, " +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE ParcelaNFE(" +
                "idparcela long PRIMARY KEY," +
                "codnota text, " +
                "dvenci long, " +
                "vparce double, " +
                "diave long, " +
                "fatura text, " +
                "valorboleto double, " +
                "gerarboleto boolean," +
                "cadastroandroid boolean, " +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE icms(" +
                "codicms text, " +
                "percentual text, " +
                "percen long, " +
                "percentualsimples long, " +
                "percentualdificms long, " +
                "cadastroandroid boolean, " +
                "deletadoandroid boolean," +
                "alteradoandroid boolean);";
        db.execSQL(sql);

        sql = "CREATE TABLE emitente(" +
                "codigoemitente long, " +
                "serie text, " +
                "cnpjemi text, " +
                "cpfemi text, " +
                "emitente text, " +
                "fantasia text, " +
                "endereco text, " +
                "numero text, " +
                "complemento text, " +
                "bairro text, " +
                "codmuni text, " +
                "municipio text, " +
                "uf text, " +
                "cep text, " +
                "codpais text, " +
                "pais text, " +
                "fone text, " +
                "ieemi text, " +
                "iesub text, " +
                "imemi text, " +
                "cnaeemi text, " +
                "tributa text, " +
                "certiemi text, " +
                "licenca text, " +
                "hambiente text, " +
                "emailemi text, " +
                "server text, " +
                "porta long, " +
                "autenticacao long, " +
                "usuario text, " +
                "senha text, " +
                "copiadanfe long, " +
                "csosn text, " +
                "perapro double, " +
                "user text, " +
                "modulo long, " +
                "cupomnfe long, " +
                "portaimpre text, " +
                "nporta text, " +
                "duplicata boolean, " +
                "emailcontador text, " +
                "mecanica boolean, " +
                "dadosobs text, " +
                "descpedidopor boolean, " +
                "descprodutopor boolean, " +
                "textologo boolean, " +
                "calprodutosimples boolean, " +
                "pedidoean boolean, " +
                "listainicio boolean, " +
                "tipoimpfiscal text, " +
                "geracodigogrupo boolean, " +
                "ipipeso boolean, " +
                "usaleitor boolean, " +
                "calculajuro boolean, " +
                "carne boolean, " +
                "numerocaracter long, " +
                "emite boolean, " +
                "pedisimples boolean, " +
                "entradaresumida long, " +
                "tipoetiqueta text, " +
                "industria boolean, " +
                "mostratamanho boolean, " +
                "placacf boolean, " +
                "recibofiscal boolean, " +
                "precopedido boolean, " +
                "pedidofiscal boolean, " +
                "servicocupom boolean, " +
                "margeminferior long, " +
                "mostrarservico boolean, " +
                "orgaestoque boolean, " +
                "liberadatare boolean, " +
                "emitentepadrao boolean, " +
                "emitentenfce boolean, " +
                "tokennfce text, " +
                "pincerti text, " +
                "liberacaixa boolean, " +
                "viarecibo long, " +
                "viaduplicata long, " +
                "codcedente text, " +
                "codpraca long, " +
                "agencia text, " +
                "conta text, " +
                "unidadeatendimento text, " +
                "nbanco text, " +
                "nomebanco text, " +
                "codcanaltransmissao long, " +
                "codcarteira text, " +
                "tipoimpressaoboleto text, " +
                "postartitulo boolean, " +
                "emissaoboleto text, " +
                "diasprotesto text, " +
                "juropordiaatrazo double, " +
                "descontoboleto double, " +
                "tipodesconto text, " +
                "tipojuro text, " +
                "postoagencia text, " +
                "juromes long, " +
                "versaosistema text, " +
                "clientepedido long, " +
                "fusohorario text, " +
                "numeparcela long, " +
                "naolancacaixa boolean, " +
                "dadosimpressora boolean, " +
                "produtocomprar boolean, " +
                "mostrafunrural boolean, " +
                "codhistorico text, " +
                "lemitedesconto long, " +
                "limitedesconto long, " +
                "alterarvalorunitario boolean, " +
                "imprimeobs020 boolean, " +
                "senhareceber text, " +
                "emitentelaticinio boolean, " +
                "bloqueianota boolean, " +
                "horacaixa boolean, " +
                "ato boolean, " +
                "xml boolean, " +
                "emaildados text, " +
                "dadospneus boolean, " +
                "produtocomposto boolean, " +
                "permitirestorno boolean, " +
                "diastravamento long, " +
                "emitesistemas boolean, " +
                "emitecapaboleto boolean, " +
                "mensagemboleto text, " +
                "notaservico text, " +
                "notaservicopm boolean, " +
                "duplicatapedido boolean, " +
                "protestoautomatico text, " +
                "mensagempedido boolean, " +
                "entradacodigoforne boolean, " +
                "bloqueiamovimentacao boolean, " +
                "datasatrazadascaixa boolean, " +
                "chequebaixadata boolean, " +
                "codigoboleto long, " +
                "exibiraparelho boolean, " +
                "duplicatabematech boolean, " +
                "mostrarcustolista boolean, " +
                "listadeprecozero boolean, " +
                "exibirlogo boolean, " +
                "anobase long, " +
                "utilizarmarkup boolean, " +
                "aredondavenda boolean, " +
                "lancaapenascontaspagarentrada boolean, " +
                "carneboleto boolean, " +
                "lancavalorentrada boolean, " +
                "importaentradaean boolean, " +
                "valorfretepedido double, " +
                "pedidopoleto boolean, " +
                "contacaixacheque text, " +
                "contacaixaveiculos text, " +
                "taxacelic long, " +
                "pedidoautoprazo boolean, " +
                "usarbarrabalanca boolean, " +
                "licencamdfe text, " +
                "versaomdfe text, " +
                "numerocopiacfe text, " +
                "baixacomposicaosaida boolean, " +
                "vendedorpadrao text, " +
                "ocultardadosemitente boolean, " +
                "tipodeentrada long, " +
                "ocultaminimo boolean, " +
                "formatacodigo boolean, " +
                "numerocopiapedido long, " +
                "ocultarlogo boolean, " +
                "liberartotalprodupedido boolean, " +
                "mostraendepedido boolean, " +
                "taxaimpostolucro long, " +
                "alteraprecopgto boolean, " +
                "menucereais long, " +
                "natentradapadrao text, " +
                "bloquearcondicional boolean, " +
                "abrirnfcevenda boolean, " +
                "movimentanotapedido boolean, " +
                "licencacte text, " +
                "bancopadrao long, " +
                "boletoservico boolean, " +
                "rntrcemperesa text, " +
                "cpfmotorista text, " +
                "nomemotorista text, " +
                "mostraprodutocomquantidade boolean, " +
                "acumularvendasinicio boolean, " +
                "naosomarquantinfe boolean, " +
                "imprimircapacarne boolean, " +
                "porcentagemavista long, " +
                "porcentagemprazo long, " +
                "versaosistemaconnect text, " +
                "recalcularlucro boolean, " +
                "exibirmarcalista boolean, " +
                "filtralistapreco boolean, " +
                "bloquearclientecpf boolean, " +
                "usarvalormediocompra boolean, " +
                "enviarinformacoelivro boolean, " +
                "numeroseapa text, " +
                "exibiroriginallista boolean, " +
                "ocultarprateleiralista boolean, " +
                "exibircodigofabricante boolean, " +
                "exibirdadoszanatta boolean, " +
                "exibirselosetiquetas boolean, " +
                "confirmarimpressaonfce boolean, " +
                "exibirdadospadrao boolean, " +
                "utilizarsalvamentoauto boolean, " +
                "exibirdadosfoco boolean, " +
                "emitirncfepedidosempre boolean, " +
                "exibirdadosbenevenuto boolean, " +
                "exibirdadoslaquent boolean, " +
                "codigomovimento text, " +
                "exibirdadosalencastro boolean, " +
                "mantervalormaiorentrada boolean, " +
                "exibirdadosclauciane boolean, " +
                "bancopadraoentrada long, " +
                "profissaopadrao long, " +
                "pgtopadrao long, " +
                "exibirdadostruck boolean, " +
                "emitedanfea4 boolean, " +
                "exibirdadoscrol boolean, " +
                "formatadecimais text, " +
                "buscarvendedorcliente boolean, " +
                "exibirdadosstarfrio boolean, " +
                "exibirdadosedificare boolean, " +
                "pedidopallun boolean, " +
                "importaricmsentrada boolean, " +
                "duasviasnfceprazo boolean, " +
                "bloquearvendanegativa boolean, " +
                "exibirdadosidentita boolean, " +
                "grupopadraoentrada text, " +
                "exibirdadoszepel boolean, " +
                "contacaixapadraoentrada text, " +
                "exibirdataentrega boolean, " +
                "chamaimportaarquivos boolean, " +
                "exibirdadoskadini boolean, " +
                "exibirdadoselizandra boolean, " +
                "emailextranotas text, " +
                "quantidadefixapedido long, " +
                "exibirdadosmannes boolean, " +
                "exibirdadosagrocampo boolean, " +
                "recalcularparcelanfe boolean, " +
                "exibirdadoslubritap boolean, " +
                "exibirdadosalger boolean, " +
                "visualizarboleto boolean, " +
                "exibirdadosstilo boolean, " +
                "exibirdadosgasparin boolean, " +
                "exibirdadosmarcolin boolean, " +
                "nomecertificadonfse text, " +
                "aliquotaiss long, " +
                "senhadesconto text, " +
                "senhaparcela text, " +
                "senhaproduto text); ";
        db.execSQL(sql);

        executaSQL(db, "CREATE TABLE emiteconfigura(codemitente long);");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosced BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN filtratextoescrita BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosalternativa BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN usaconversaoentrada BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoslaquentloja BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN diascarenciajuros LONG");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN valorboletospadrao DOUBLE");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosconversaoproduto BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN descontoapenasproduto BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN alterarobspedidofechado BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN imprimirnfsservicodireto BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosilha BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosrestaurante BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosconnect BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirmensagemeconomizou BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN obscte TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN bloquearquantidadenegativa BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN utilizarpontos BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN usarepsonlx300 BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN produtopredominantecte TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN produtocomponentecte TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosoliveira BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN ocultavencibarrapedido BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN produtoaidf TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN emitenfse BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoshs BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibircustoletras BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirvendedorcupom BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseusuario TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfsesenha TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfsecancelamento TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultalote TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultafaixa TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultarps TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultaprestados TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultaenvioloterps TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultaenviorps TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultasequencialote TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN caixapadrao LONG");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosmiotto BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosvestbem TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN naoalteracustoentrada BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosestilo BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN ocultarcestnota BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN cadastroduplicadosclientes BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN contacaixaveiculosvenda TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadostrattore BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN contacaixachequelaca TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN contacaixachequebaixa TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN utilizaimportacaoautomatica BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoscanal BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezatranferencia LONG");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN downloaddllnfe BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN downloaddllcte BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN downloaddllmdfe BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN enviadadossped BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN ordempedido LONG");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoslimalimao BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN cstpadraocadastro TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN converteimpostoentrada BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezadevolucao TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoslimpar BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoskitintas BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN gerablocok BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosinnovar BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN usarfatorconversao BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN fretenota TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN forcarcfopentrada BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosgellus BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN geranotalote BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosourobranco BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN certificadoandroid TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN alterarclientepedido BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN filtrarprodcompandroid BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseoptantesimplesnacional BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN regimeespecialtributacao LONG");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN gerarboletoautomatico BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezaretorno TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codsetorgeral TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirnomefantasia BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN naoalteravalordevenda BOOLEAN");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezaremessa TEXT");
        executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezavendadireta TEXT");

        executaSQL(db, "CREATE TABLE configuracoeslocais(id long);");
        executaSQL(db, "ALTER TABLE configuracoeslocais ADD COLUMN codvendedor text");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        if (oldVersion < 2) {
            sql = "CREATE TABLE NotaFiscal(" +
                    "idnota long PRIMARY KEY," +
                    "codnota text," +
                    "codemitente long, " +
                    "codigo long," +
                    "codtipo long," +
                    "codcliente long," +
                    "nomecliente text," +
                    "cgccpf text," +
                    "marc boolean," +
                    "cnpj text," +
                    "cpf text," +
                    "endereco text," +
                    "cep text," +
                    "codcidade long," +
                    "bairro text," +
                    "fonefax text," +
                    "inscesta text," +
                    "saida text," +
                    "venda text," +
                    "materia text," +
                    "dataemissao long," +
                    "datasaida long," +
                    "hora long," +
                    "codinstituicao long," +
                    "praca text," +
                    "fatura text," +
                    "vencimento long," +
                    "valor double," +
                    "baseicms double," +
                    "valoricms double," +
                    "icmssub double," +
                    "valoricmssub double," +
                    "valordosprodutos double," +
                    "valorseguro double," +
                    "despesas double," +
                    "valordoipi double," +
                    "codtransportador," +
                    "valorfrete double," +
                    "valornota double," +
                    "observacao double," +
                    "pesobruto double," +
                    "pesoliquido double," +
                    "quantidade double," +
                    "especie text," +
                    "marca text," +
                    "numero text," +
                    "complemento text," +
                    "codvendedor text," +
                    "firma text," +
                    "desconto double," +
                    "cf text," +
                    "tran text," +
                    "cancela boolean," +
                    "simnao boolean," +
                    "nnota text," +
                    "dupli boolean," +
                    "norconti text," +
                    "chave text," +
                    "chaveref text," +
                    "inscesta text," +
                    "protocolo text," +
                    "recibo text," +
                    "emidesti text," +
                    "issqn double," +
                    "vissqn double," +
                    "pedido text," +
                    "protocoloc text," +
                    "envemail boolean," +
                    "notaref text," +
                    "obsfisco text," +
                    "justicancelamento text," +
                    "funrural double," +
                    "reajustadas double," +
                    "valorfun double," +
                    "valortributos double," +
                    "totaltributos double," +
                    "agrupa boolean," +
                    "codpgto long," +
                    "baseimpo double," +
                    "desaduaneira double," +
                    "valoimpor double, " +
                    "valoriof double, " +
                    "gerabloqueto boolean, " +
                    "finalidade text, " +
                    "presencial text, " +
                    "destioperacao text, " +
                    "codplanocontas long, " +
                    "codcentrocustos text, " +
                    "emailnota text, " +
                    "ccocupom text, " +
                    "placavei text, " +
                    "operacaosefaz boolean, " +
                    "estonodenfe boolean, " +
                    "cadastroandroid boolean," +
                    "deletadoandroid boolean," +
                    "alteradoandroid boolean);";
            db.execSQL(sql);

            sql = "CREATE TABLE NotaProduto(" +
                    "idnotaproduto long PRIMARY KEY," +
                    "codnota text, " +
                    "codigo text," +
                    "codemitente long, " +
                    "auto long, " +
                    "quantidade double, " +
                    "valorunitário double, " +
                    "valortotal double, " +
                    "valornota double, " +
                    "valoripi double, " +
                    "aliqicms double, " +
                    "aliqipi double, " +
                    "codicms text, " +
                    "peso long, " +
                    "cfop text, " +
                    "bicms double, " +
                    "vicms double, " +
                    "descopro double, " +
                    "mvap long, " +
                    "vbcst double, " +
                    "vsst double, " +
                    "vseguro double, " +
                    "descri text, " +
                    "vfrete double, " +
                    "codtipo long, " +
                    "codpis text, " +
                    "porpis long, " +
                    "codcofins text, " +
                    "porcofins long, " +
                    "codipi text, " +
                    "sst text, " +
                    "voutros double, " +
                    "totaltribpro double, " +
                    "porimposto long, " +
                    "pesoliq long, " +
                    "datas long, " +
                    "cstpis long, " +
                    "vpis double, " +
                    "cstcofins long, " +
                    "vcofins double, " +
                    "vcusto double, " +
                    "totaltribest double, " +
                    "comple text, " +
                    "ncmproduto text," +
                    "cadastroandroid boolean, " +
                    "deletadoandroid boolean," +
                    "alteradoandroid boolean);";
            db.execSQL(sql);
        }

        if (oldVersion < 3) {
            sql = "CREATE TABLE Parcela(" +
                    "idparcela long PRIMARY KEY," +
                    "codpedido text, " +
                    "dvenci long, " +
                    "vparce double, " +
                    "diave long, " +
                    "fatura text, " +
                    "valorboleto double, " +
                    "gerarboleto boolean," +
                    "cadastroandroid boolean, " +
                    "deletadoandroid boolean," +
                    "alteradoandroid boolean);";
            db.execSQL(sql);

            sql = "CREATE TABLE icms(" +
                    "codicms text, " +
                    "percentual text, " +
                    "percen long, " +
                    "percentualsimples long, " +
                    "percentualdificms long, " +
                    "cadastroandroid boolean, " +
                    "deletadoandroid boolean," +
                    "alteradoandroid boolean);";
            db.execSQL(sql);

            sql = "CREATE TABLE ParcelaNFE(" +
                    "idparcela long PRIMARY KEY," +
                    "codnota text, " +
                    "dvenci long, " +
                    "vparce double, " +
                    "diave long, " +
                    "fatura text, " +
                    "valorboleto double, " +
                    "gerarboleto boolean," +
                    "cadastroandroid boolean, " +
                    "deletadoandroid boolean," +
                    "alteradoandroid boolean);";
            db.execSQL(sql);

            sql = "CREATE TABLE emitente(" +
                    "cnpjemi text, " +
                    "cpfemi text, " +
                    "emitente text, " +
                    "fantasia text, " +
                    "endereco text, " +
                    "numero text, " +
                    "complemento text, " +
                    "bairro text, " +
                    "codmuni text, " +
                    "municipio text, " +
                    "uf text, " +
                    "cep text, " +
                    "codpais text, " +
                    "pais text, " +
                    "fone text, " +
                    "ieemi text, " +
                    "iesub text, " +
                    "imemi text, " +
                    "cnaeemi text, " +
                    "tributa text, " +
                    "certiemi text, " +
                    "licenca text, " +
                    "hambiente text, " +
                    "emailemi text, " +
                    "server text, " +
                    "porta long, " +
                    "autenticacao long, " +
                    "usuario text, " +
                    "senha text, " +
                    "copiadanfe long, " +
                    "csosn text, " +
                    "perapro double, " +
                    "user text, " +
                    "modulo long, " +
                    "cupomnfe long, " +
                    "portaimpre text, " +
                    "nporta text, " +
                    "duplicata boolean, " +
                    "mecanica boolean, " +
                    "emailcontador text, " +
                    "dadosobs text, " +
                    "descpedidopor boolean, " +
                    "descprodutopor boolean, " +
                    "textologo boolean, " +
                    "calprodutosimples boolean, " +
                    "pedidoean boolean, " +
                    "listainicio boolean, " +
                    "tipoimpfiscal text, " +
                    "codigoemitente long, " +
                    "geracodigogrupo boolean, " +
                    "ipipeso boolean, " +
                    "usaleitor boolean, " +
                    "calculajuro boolean, " +
                    "carne boolean, " +
                    "numerocaracter long, " +
                    "serie text, " +
                    "pedisimples boolean, " +
                    "entradaresumida boolean, " +
                    "industria boolean, " +
                    "tipoetiqueta text, " +
                    "mostratamanho boolean, " +
                    "recibofiscal boolean, " +
                    "placacf boolean, " +
                    "precopedido boolean, " +
                    "pedidofiscal boolean, " +
                    "servicocupom boolean, " +
                    "margeminferior long, " +
                    "mostrarservico boolean, " +
                    "orgaestoque boolean, " +
                    "liberadatare boolean, " +
                    "emitentepadrao boolean, " +
                    "emitentenfce boolean, " +
                    "tokennfce text, " +
                    "pincerti text, " +
                    "liberacaixa boolean, " +
                    "viarecibo long, " +
                    "viaduplicata long, " +
                    "codcedente text, " +
                    "juromes long, " +
                    "codpraca long, " +
                    "agencia text, " +
                    "conta text, " +
                    "unidadeatendimento text, " +
                    "nbanco text, " +
                    "nomebanco text, " +
                    "codcanaltransmissao long, " +
                    "codcarteira text, " +
                    "tipoimpressaoboleto text, " +
                    "postartitulo boolean, " +
                    "emissaoboleto text, " +
                    "diasprotesto text, " +
                    "juropordiaatrazo double, " +
                    "descontoboleto double, " +
                    "tipodesconto text, " +
                    "tipojuro text, " +
                    "postoagencia text, " +
                    "versaosistema text, " +
                    "clientepedido long, " +
                    "fusohorario text, " +
                    "numeparcela long, " +
                    "naolancacaixa boolean, " +
                    "senhadesconto text, " +
                    "senhaparcela text, " +
                    "senhaproduto text, " +
                    "senhacaixa text, " +
                    "senhadevedor text, " +
                    "dadosimpressora boolean, " +
                    "produtocomprar boolean, " +
                    "mostrafunrural boolean, " +
                    "codhistorico text, " +
                    "limitedesconto long, " +
                    "alterarvalorunitario boolean, " +
                    "imprimeobs020 boolean, " +
                    "senhareceber text, " +
                    "emitentelaticinio boolean, " +
                    "bloqueianota boolean, " +
                    "horacaixa boolean, " +
                    "ato boolean, " +
                    "xml boolean, " +
                    "emaildados text, " +
                    "dadospneus boolean, " +
                    "produtocomposto boolean, " +
                    "permitirestorno boolean, " +
                    "diastravamento long, " +
                    "emitesistemas boolean, " +
                    "emitecapaboleto boolean, " +
                    "mensagemboleto text, " +
                    "notaservico text, " +
                    "notaservicopm boolean, " +
                    "duplicatapedido boolean, " +
                    "protestoautomatico text, " +
                    "mensagempedido boolean, " +
                    "entradacodigoforne boolean, " +
                    "bloqueiamovimentacao boolean, " +
                    "datasatrazadascaixa boolean, " +
                    "chequebaixadata boolean, " +
                    "codigoboleto long, " +
                    "exibiraparelho boolean, " +
                    "duplicatabematech boolean, " +
                    "mostrarcustolista boolean, " +
                    "listadeprecozero boolean, " +
                    "exibirlogo boolean, " +
                    "anobase long, " +
                    "utilizarmarkup boolean, " +
                    "aredondavenda boolean, " +
                    "lancaapenascontaspagarentrada boolean, " +
                    "carneboleto boolean, " +
                    "lancavalorentrada boolean, " +
                    "importaentradaean boolean, " +
                    "valorfretepedido double, " +
                    "pedidopoleto boolean, " +
                    "contacaixacheque text, " +
                    "contacaixaveiculos text, " +
                    "taxacelic long, " +
                    "pedidoautoprazo boolean, " +
                    "usarbarrabalanca boolean, " +
                    "licencamdfe text, " +
                    "versaomdfe text, " +
                    "numerocopiacfe long, " +
                    "baixacomposicaosaida boolean, " +
                    "vendedorpadrao text, " +
                    "ocultardadosemitente boolean, " +
                    "tipodeentrada long, " +
                    "ocultaminimo boolean, " +
                    "formatacodigo boolean, " +
                    "numerocopiapedido long, " +
                    "ocultarlogo boolean, " +
                    "liberartotalprodupedido boolean, " +
                    "mostraendepedido boolean, " +
                    "taxaimpostolucro long, " +
                    "menucereais long, " +
                    "natentradapadrao text, " +
                    "bloquearcondicional boolean, " +
                    "abrirnfcevenda boolean, " +
                    "movimentanotapedido boolean, " +
                    "licencacte text, " +
                    "bancopadrao long, " +
                    "boletoservico boolean, " +
                    "rntrcemperesa text, " +
                    "cpfmotorista text, " +
                    "nomemotorista text, " +
                    "mostraprodutocomquantidade boolean, " +
                    "acumularvendasinicio boolean, " +
                    "naosomarquantinfe boolean, " +
                    "imprimircapacarne boolean, " +
                    "porcentagemavista long, " +
                    "porcentagemprazo long, " +
                    "versaosistemaconnect text, " +
                    "recalcularlucro boolean, " +
                    "exibirmarcalista boolean, " +
                    "filtralistapreco boolean, " +
                    "bloquearclientecpf boolean, " +
                    "usarvalormediocompra boolean, " +
                    "enviarinformacoelivro boolean, " +
                    "numeroseapa text, " +
                    "exibiroriginallista boolean, " +
                    "ocultarprateleiralista boolean, " +
                    "exibircodigofabricante boolean, " +
                    "exibirdadoszanatta boolean, " +
                    "exibirselosetiquetas boolean, " +
                    "confirmarimpressaonfce boolean, " +
                    "codigomovimento text, " +
                    "exibirdadospadrao boolean, " +
                    "utilizarsalvamentoauto boolean, " +
                    "exibirdadosfoco boolean, " +
                    "emitirncfepedidosempre boolean, " +
                    "exibirdadosbenevenuto boolean, " +
                    "exibirdadoslaquent boolean, " +
                    "exibirdadoscrol boolean, " +
                    "formatadecimais text, " +
                    "buscarvendedorcliente boolean, " +
                    "exibirdadosstarfrio boolean, " +
                    "exibirdadosedificare boolean, " +
                    "pedidopallun boolean, " +
                    "importaricmsentrada boolean, " +
                    "duasviasnfceprazo boolean, " +
                    "bloquearvendanegativa boolean, " +
                    "exibirdadosidentita boolean, " +
                    "grupopadraoentrada text, " +
                    "exibirdadoszepel boolean, " +
                    "contacaixapadraoentrada text, " +
                    "exibirdataentrega boolean, " +
                    "chamaimportaarquivos boolean, " +
                    "exibirdadoskadini boolean, " +
                    "emailextranotas text, " +
                    "emitedanfea4 boolean, " +
                    "profissaopadrao long, " +
                    "pgtopadrao long, " +
                    "exibirdadostruck boolean, " +
                    "exibirdadosmannes boolean, " +
                    "exibirdadosalencastro boolean, " +
                    "mantervalormaiorentrada boolean, " +
                    "exibirdadosclauciane boolean, " +
                    "bancopadraoentrada long, " +
                    "exibirdadoselizandra boolean, " +
                    "quantidadefixapedido long, " +
                    "exibirdadosagrocampo boolean, " +
                    "recalcularparcelanfe boolean, " +
                    "exibirdadoslubritap boolean, " +
                    "exibirdadosalger boolean, " +
                    "visualizarboleto boolean, " +
                    "exibirdadosstilo boolean, " +
                    "exibirdadosgasparin boolean, " +
                    "exibirdadosmarcolin boolean, " +
                    "aliquotaiss long, " +
                    "nomecertificadonfse text); ";
            db.execSQL(sql);
        }
        if (oldVersion < 4) {
            db.execSQL("ALTER TABLE pedido ADD COLUMN nfe boolean");
        }

        if (oldVersion < 5) {
            db.execSQL("DROP TABLE EMITENTE");
            sql = "CREATE TABLE EMITENTE(" +
                    "codigoemitente long, " +
                    "serie text, " +
                    "cnpjemi text, " +
                    "cpfemi text, " +
                    "emitente text, " +
                    "fantasia text, " +
                    "endereco text, " +
                    "numero text, " +
                    "complemento text, " +
                    "bairro text, " +
                    "codmuni text, " +
                    "municipio text, " +
                    "uf text, " +
                    "cep text, " +
                    "codpais text, " +
                    "pais text, " +
                    "fone text, " +
                    "ieemi text, " +
                    "iesub text, " +
                    "imemi text, " +
                    "cnaeemi text, " +
                    "tributa text, " +
                    "certiemi text, " +
                    "licenca text, " +
                    "hambiente text, " +
                    "emailemi text, " +
                    "server text, " +
                    "porta long, " +
                    "autenticacao long, " +
                    "usuario text, " +
                    "senha text, " +
                    "copiadanfe long, " +
                    "csosn text, " +
                    "perapro long, " +
                    "user text, " +
                    "modulo long, " +
                    "cupomnfe long, " +
                    "portaimpre text, " +
                    "nporta text, " +
                    "duplicata boolean, " +
                    "emailcontador text, " +
                    "mecanica boolean, " +
                    "dadosobs text, " +
                    "descpedidopor boolean, " +
                    "descprodutopor boolean, " +
                    "textologo boolean, " +
                    "calprodutosimples boolean, " +
                    "pedidoean boolean, " +
                    "listainicio boolean, " +
                    "tipoimpfiscal text, " +
                    "geracodigogrupo boolean, " +
                    "ipipeso boolean, " +
                    "usaleitor boolean, " +
                    "calculajuro boolean, " +
                    "carne boolean, " +
                    "numerocaracter long, " +
                    "emite boolean, " +
                    "pedisimples boolean, " +
                    "entradaresumida boolean, " +
                    "tipoetiqueta text, " +
                    "industria boolean, " +
                    "mostratamanho boolean, " +
                    "placacf boolean, " +
                    "recibofiscal boolean, " +
                    "precopedido boolean, " +
                    "pedidofiscal boolean, " +
                    "servicocupom boolean, " +
                    "margeminferior long, " +
                    "mostrarservico boolean, " +
                    "orgaestoque boolean, " +
                    "liberadatare boolean, " +
                    "emitentepadrao boolean, " +
                    "emitentenfce boolean, " +
                    "tokennfce text, " +
                    "pincerti text, " +
                    "liberacaixa boolean, " +
                    "viarecibo long, " +
                    "viaduplicata long, " +
                    "codcedente text, " +
                    "codpraca long, " +
                    "agencia text, " +
                    "conta text, " +
                    "unidadeatendimento text, " +
                    "nbanco text, " +
                    "nomebanco text, " +
                    "codcanaltransmissao long, " +
                    "codcarteira text, " +
                    "tipoimpressaoboleto text, " +
                    "postartitulo boolean, " +
                    "emissaoboleto text, " +
                    "diasprotesto text, " +
                    "juropordiaatrazo double, " +
                    "descontoboleto double, " +
                    "tipodesconto text, " +
                    "tipojuro text, " +
                    "postoagencia text, " +
                    "juromes long, " +
                    "versaosistema text, " +
                    "clientepedido long, " +
                    "fusohorario text, " +
                    "numeparcela long, " +
                    "naolancacaixa boolean, " +
                    "dadosimpressora boolean, " +
                    "produtocomprar boolean, " +
                    "mostrafunrural boolean, " +
                    "codhistorico text, " +
                    "lemitedesconto long, " +
                    "limitedesconto long, " +
                    "alterarvalorunitario boolean, " +
                    "imprimeobs020 boolean, " +
                    "senhareceber text, " +
                    "emitentelaticinio boolean, " +
                    "bloqueianota boolean, " +
                    "horacaixa boolean, " +
                    "ato boolean, " +
                    "xml boolean, " +
                    "emaildados text, " +
                    "dadospneus boolean, " +
                    "produtocomposto boolean, " +
                    "permitirestorno boolean, " +
                    "diastravamento long, " +
                    "emitesistemas boolean, " +
                    "emitecapaboleto boolean, " +
                    "mensagemboleto text, " +
                    "notaservico text, " +
                    "notaservicopm boolean, " +
                    "duplicatapedido boolean, " +
                    "protestoautomatico text, " +
                    "mensagempedido boolean, " +
                    "entradacodigoforne boolean, " +
                    "bloqueiamovimentacao boolean, " +
                    "datasatrazadascaixa boolean, " +
                    "chequebaixadata boolean, " +
                    "codigoboleto long, " +
                    "exibiraparelho boolean, " +
                    "duplicatabematech boolean, " +
                    "mostrarcustolista boolean, " +
                    "listadeprecozero boolean, " +
                    "exibirlogo boolean, " +
                    "anobase long, " +
                    "utilizarmarkup boolean, " +
                    "aredondavenda boolean, " +
                    "lancaapenascontaspagarentrada boolean, " +
                    "carneboleto boolean, " +
                    "lancavalorentrada boolean, " +
                    "importaentradaean boolean, " +
                    "valorfretepedido double, " +
                    "pedidopoleto boolean, " +
                    "contacaixacheque text, " +
                    "contacaixaveiculos text, " +
                    "taxacelic long, " +
                    "pedidoautoprazo boolean, " +
                    "usarbarrabalanca boolean, " +
                    "licencamdfe text, " +
                    "versaomdfe text, " +
                    "numerocopiacfe text, " +
                    "baixacomposicaosaida boolean, " +
                    "vendedorpadrao text, " +
                    "ocultardadosemitente boolean, " +
                    "tipodeentrada long, " +
                    "ocultaminimo boolean, " +
                    "formatacodigo boolean, " +
                    "numerocopiapedido long, " +
                    "ocultarlogo boolean, " +
                    "liberartotalprodupedido boolean, " +
                    "mostraendepedido boolean, " +
                    "taxaimpostolucro long, " +
                    "alteraprecopgto boolean, " +
                    "menucereais long, " +
                    "natentradapadrao text, " +
                    "bloquearcondicional boolean, " +
                    "abrirnfcevenda boolean, " +
                    "movimentanotapedido boolean, " +
                    "licencacte text, " +
                    "bancopadrao long, " +
                    "boletoservico boolean, " +
                    "rntrcemperesa text, " +
                    "cpfmotorista text, " +
                    "nomemotorista text, " +
                    "mostraprodutocomquantidade boolean, " +
                    "acumularvendasinicio boolean, " +
                    "naosomarquantinfe boolean, " +
                    "imprimircapacarne boolean, " +
                    "porcentagemavista long, " +
                    "porcentagemprazo long, " +
                    "versaosistemaconnect text, " +
                    "recalcularlucro boolean, " +
                    "exibirmarcalista boolean, " +
                    "filtralistapreco boolean, " +
                    "bloquearclientecpf boolean, " +
                    "usarvalormediocompra boolean, " +
                    "enviarinformacoelivro boolean, " +
                    "numeroseapa text, " +
                    "exibiroriginallista boolean, " +
                    "ocultarprateleiralista boolean, " +
                    "exibircodigofabricante boolean, " +
                    "exibirdadoszanatta boolean, " +
                    "exibirselosetiquetas boolean, " +
                    "confirmarimpressaonfce boolean, " +
                    "exibirdadospadrao boolean, " +
                    "utilizarsalvamentoauto boolean, " +
                    "exibirdadosfoco boolean, " +
                    "emitirncfepedidosempre boolean, " +
                    "exibirdadosbenevenuto boolean, " +
                    "exibirdadoslaquent boolean, " +
                    "codigomovimento text, " +
                    "exibirdadosalencastro boolean, " +
                    "mantervalormaiorentrada boolean, " +
                    "exibirdadosclauciane boolean, " +
                    "bancopadraoentrada long, " +
                    "profissaopadrao long, " +
                    "pgtopadrao long, " +
                    "exibirdadostruck boolean, " +
                    "emitedanfea4 boolean, " +
                    "exibirdadoscrol boolean, " +
                    "formatadecimais text, " +
                    "buscarvendedorcliente boolean, " +
                    "exibirdadosstarfrio boolean, " +
                    "exibirdadosedificare boolean, " +
                    "pedidopallun boolean, " +
                    "importaricmsentrada boolean, " +
                    "duasviasnfceprazo boolean, " +
                    "bloquearvendanegativa boolean, " +
                    "exibirdadosidentita boolean, " +
                    "grupopadraoentrada text, " +
                    "exibirdadoszepel boolean, " +
                    "contacaixapadraoentrada text, " +
                    "exibirdataentrega boolean, " +
                    "chamaimportaarquivos boolean, " +
                    "exibirdadoskadini boolean, " +
                    "exibirdadoselizandra boolean, " +
                    "emailextranotas text, " +
                    "quantidadefixapedido long, " +
                    "exibirdadosmannes boolean, " +
                    "exibirdadosagrocampo boolean, " +
                    "recalcularparcelanfe boolean, " +
                    "exibirdadoslubritap boolean, " +
                    "exibirdadosalger boolean, " +
                    "visualizarboleto boolean, " +
                    "exibirdadosstilo boolean, " +
                    "exibirdadosgasparin boolean, " +
                    "exibirdadosmarcolin boolean, " +
                    "nomecertificadonfse text, " +
                    "aliquotaiss long, " +
                    "senhadesconto text, " +
                    "senhaparcela text, " +
                    "senhaproduto text); ";

            db.execSQL(sql);

        }
        if (oldVersion < 6) {


            executaSQL(db, "ALTER TABLE pedido ADD COLUMN pedido LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codpedido LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codcliente LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN data LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codvendedor TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN formadepagamento TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN frete DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN valortotal DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN entrada DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN orpedi TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codbanco LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN obs TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN desconto LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nome TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN total DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN venci1 LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN valor1 DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN dias LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN juro LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN sim/nao BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN pgto LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN cheque TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN data1 LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN notafisca TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN via LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN baixa BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN veiculo TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN placa TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN ano TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nparce LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codinstituiCao LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfc TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN dife LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN comis DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfe BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN vpago DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN troco DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codhistorico TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN descvalor LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codemitente LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codmecanico TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN valorservico DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN descoservico LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN juntapedido BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN km TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nparc LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN ncf TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN comi DOUBLE");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN desvalor LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN cpf TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN parce LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN impressora TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN inicialp LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN finalp LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN totalp LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN gerabloqueto BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN gerabloqueto1 BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codplanocontas LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codcentrocustos TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN documento BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codproduto TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN mensagem BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN estornop BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codstatus LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN baixaf BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN acreboleto LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN aparelho TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN defeito TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN pedidoreferencia LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN enderecopedido TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfce BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nfcee BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codcaixa LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN juridica BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN ajuste TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN dataentrega LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN servicosolicitado TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN nnotaservico TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN codbandeira LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN orcamentofinalizado BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN coddigitador TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN horasdemotor LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN horasdetrilha LONG");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN especial BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN carga BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN enviadoemailnfse BOOLEAN");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN rota TEXT");
            executaSQL(db, "ALTER TABLE pedido ADD COLUMN geradoautomatico BOOLEAN");
        }

        if (oldVersion < 7 ){
            executaSQL(db, "CREATE TABLE emiteconfigura(codemitente long);");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosced BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN filtratextoescrita BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosalternativa BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN usaconversaoentrada BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoslaquentloja BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN diascarenciajuros LONG");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN valorboletospadrao DOUBLE");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosconversaoproduto BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN descontoapenasproduto BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN alterarobspedidofechado BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN imprimirnfsservicodireto BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosilha BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosrestaurante BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosconnect BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirmensagemeconomizou BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN obscte TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN bloquearquantidadenegativa BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN utilizarpontos BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN usarepsonlx300 BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN produtopredominantecte TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN produtocomponentecte TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosoliveira BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN ocultavencibarrapedido BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN produtoaidf TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN emitenfse BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoshs BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibircustoletras BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirvendedorcupom BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseusuario TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfsesenha TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfsecancelamento TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultalote TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultafaixa TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultarps TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultaprestados TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultaenvioloterps TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultaenviorps TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseconsultasequencialote TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN caixapadrao LONG");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosmiotto BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosvestbem TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN naoalteracustoentrada BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosestilo BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN ocultarcestnota BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN cadastroduplicadosclientes BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN contacaixaveiculosvenda TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadostrattore BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN contacaixachequelaca TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN contacaixachequebaixa TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN utilizaimportacaoautomatica BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoscanal BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezatranferencia LONG");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN downloaddllnfe BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN downloaddllcte BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN downloaddllmdfe BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN enviadadossped BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN ordempedido LONG");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoslimalimao BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN cstpadraocadastro TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN converteimpostoentrada BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezadevolucao TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoslimpar BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadoskitintas BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN gerablocok BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosinnovar BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN usarfatorconversao BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN fretenota TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN forcarcfopentrada BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosgellus BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN geranotalote BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirdadosourobranco BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN certificadoandroid TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN alterarclientepedido BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN filtrarprodcompandroid BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN nfseoptantesimplesnacional BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN regimeespecialtributacao LONG");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN gerarboletoautomatico BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezaretorno TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codsetorgeral TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN exibirnomefantasia BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN naoalteravalordevenda BOOLEAN");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezaremessa TEXT");
            executaSQL(db,"ALTER TABLE emiteconfigura ADD COLUMN codnaturezavendadireta TEXT");
        }

        if (oldVersion < 8 ) {
            executaSQL(db, "CREATE TABLE configuracoeslocais(id long);");
            executaSQL(db, "ALTER TABLE configuracoeslocais ADD COLUMN codvendedor text");
        }


    }


    public boolean executaSQL(SQLiteDatabase db, String sql) {
        try {
            db.execSQL(sql);
        } catch (Exception e) {

        }
        return true;
    }

}
