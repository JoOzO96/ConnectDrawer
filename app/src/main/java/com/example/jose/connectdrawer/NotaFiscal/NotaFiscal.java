package com.example.jose.connectdrawer.NotaFiscal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NotaFiscal {
    Long idNotaFiscal;
    String codnota;
    Long codemitente;
    Long codtipo;
    Long codcliente;
    String nomecliente;
    String cgccpf;
    Boolean marc;
    String cnpj;
    String cpf;
    String endereco;
    String cep;
    Long codcidade;
    String bairro;
    String fonefax;
    String inscesta;
    String saida;
    String venda;
    String materia;
    Date dataemissao;
    Date datasaida;
    Date hora;
    Long codinstituicao;
    String praca;
    String fatura;
    Long vencimento;
    Double valor;
    Double baseicms;
    Double valoricms;
    Double icmssub;
    Double valoricmssub;
    Double valordosprodutos;
    Double valorseguro;
    Double despesas;
    Double valordoipi;
    Long codtransportador;
    Double valorfrete;
    Double valornota;
    String observacao;
    Long pesobruto;
    Long pesoliquido;
    Long quantidade;
    String especie;
    String marca;
    String numero;
    String complemento;
    String codvendedor;
    String firma;
    Long desconto;
    String cf;
    String tran;
    Boolean cancela;
    Boolean simnao;
    String nnota;
    Boolean dupli;
    String norconti;
    String chave;
    String protocolo;
    String recibo;
    String emidesti;
    Double issqn;
    Double vissqn;
    Long pedido;
    String protocoloc;
    Boolean envemail;
    String notaref;
    String chaveref;
    String obsfisco;
    String justicancelamento;
    Double funrural;
    Double reajustadas;
    Double valorfun;
    Double valortributos;
    Double totaltributos;
    Boolean agrupa;
    Long codpgto;
    Double baseimpo;
    Double desaduaneira;
    Double valoimpor;
    Double valoriof;
    Boolean gerabloqueto;
    String finalidade;
    String presencial;
    String destioperacao;
    Long codplanocontas;
    String codcentrocustos;
    String emailnota;
    String ccocupom;
    String placavei;
    Boolean operacaosefaz;

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

    public Long getCodtipo() {
        return codtipo;
    }

    public void setCodtipo(Long codtipo) {
        this.codtipo = codtipo;
    }

    public Long getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Long codcliente) {
        this.codcliente = codcliente;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getCgccpf() {
        return cgccpf;
    }

    public void setCgccpf(String cgccpf) {
        this.cgccpf = cgccpf;
    }

    public Boolean getMarc() {
        return marc;
    }

    public void setMarc(Boolean marc) {
        this.marc = marc;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getCodcidade() {
        return codcidade;
    }

    public void setCodcidade(Long codcidade) {
        this.codcidade = codcidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFonefax() {
        return fonefax;
    }

    public void setFonefax(String fonefax) {
        this.fonefax = fonefax;
    }

    public String getInscesta() {
        return inscesta;
    }

    public void setInscesta(String inscesta) {
        this.inscesta = inscesta;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getVenda() {
        return venda;
    }

    public void setVenda(String venda) {
        this.venda = venda;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    public Date getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(Date datasaida) {
        this.datasaida = datasaida;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Long getCodinstituicao() {
        return codinstituicao;
    }

    public void setCodinstituicao(Long codinstituicao) {
        this.codinstituicao = codinstituicao;
    }

    public String getPraca() {
        return praca;
    }

    public void setPraca(String praca) {
        this.praca = praca;
    }

    public String getFatura() {
        return fatura;
    }

    public void setFatura(String fatura) {
        this.fatura = fatura;
    }

    public Long getVencimento() {
        return vencimento;
    }

    public void setVencimento(Long vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getBaseicms() {
        return baseicms;
    }

    public void setBaseicms(Double baseicms) {
        this.baseicms = baseicms;
    }

    public Double getValoricms() {
        return valoricms;
    }

    public void setValoricms(Double valoricms) {
        this.valoricms = valoricms;
    }

    public Double getIcmssub() {
        return icmssub;
    }

    public void setIcmssub(Double icmssub) {
        this.icmssub = icmssub;
    }

    public Double getValoricmssub() {
        return valoricmssub;
    }

    public void setValoricmssub(Double valoricmssub) {
        this.valoricmssub = valoricmssub;
    }

    public Double getValordosprodutos() {
        return valordosprodutos;
    }

    public void setValordosprodutos(Double valordosprodutos) {
        this.valordosprodutos = valordosprodutos;
    }

    public Double getValorseguro() {
        return valorseguro;
    }

    public void setValorseguro(Double valorseguro) {
        this.valorseguro = valorseguro;
    }

    public Double getDespesas() {
        return despesas;
    }

    public void setDespesas(Double despesas) {
        this.despesas = despesas;
    }

    public Double getValordoipi() {
        return valordoipi;
    }

    public void setValordoipi(Double valordoipi) {
        this.valordoipi = valordoipi;
    }

    public Long getCodtransportador() {
        return codtransportador;
    }

    public void setCodtransportador(Long codtransportador) {
        this.codtransportador = codtransportador;
    }

    public Double getValorfrete() {
        return valorfrete;
    }

    public void setValorfrete(Double valorfrete) {
        this.valorfrete = valorfrete;
    }

    public Double getValornota() {
        return valornota;
    }

    public void setValornota(Double valornota) {
        this.valornota = valornota;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getPesobruto() {
        return pesobruto;
    }

    public void setPesobruto(Long pesobruto) {
        this.pesobruto = pesobruto;
    }

    public Long getPesoliquido() {
        return pesoliquido;
    }

    public void setPesoliquido(Long pesoliquido) {
        this.pesoliquido = pesoliquido;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Long getDesconto() {
        return desconto;
    }

    public void setDesconto(Long desconto) {
        this.desconto = desconto;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getTran() {
        return tran;
    }

    public void setTran(String tran) {
        this.tran = tran;
    }

    public Boolean getCancela() {
        return cancela;
    }

    public void setCancela(Boolean cancela) {
        this.cancela = cancela;
    }

    public Boolean getSimnao() {
        return simnao;
    }

    public void setSimnao(Boolean simnao) {
        this.simnao = simnao;
    }

    public String getNnota() {
        return nnota;
    }

    public void setNnota(String nnota) {
        this.nnota = nnota;
    }

    public Boolean getDupli() {
        return dupli;
    }

    public void setDupli(Boolean dupli) {
        this.dupli = dupli;
    }

    public String getNorconti() {
        return norconti;
    }

    public void setNorconti(String norconti) {
        this.norconti = norconti;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public String getEmidesti() {
        return emidesti;
    }

    public void setEmidesti(String emidesti) {
        this.emidesti = emidesti;
    }

    public Double getIssqn() {
        return issqn;
    }

    public void setIssqn(Double issqn) {
        this.issqn = issqn;
    }

    public Double getVissqn() {
        return vissqn;
    }

    public void setVissqn(Double vissqn) {
        this.vissqn = vissqn;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public String getProtocoloc() {
        return protocoloc;
    }

    public void setProtocoloc(String protocoloc) {
        this.protocoloc = protocoloc;
    }

    public Boolean getEnvemail() {
        return envemail;
    }

    public void setEnvemail(Boolean envemail) {
        this.envemail = envemail;
    }

    public String getNotaref() {
        return notaref;
    }

    public void setNotaref(String notaref) {
        this.notaref = notaref;
    }

    public String getChaveref() {
        return chaveref;
    }

    public void setChaveref(String chaveref) {
        this.chaveref = chaveref;
    }

    public String getObsfisco() {
        return obsfisco;
    }

    public void setObsfisco(String obsfisco) {
        this.obsfisco = obsfisco;
    }

    public String getJusticancelamento() {
        return justicancelamento;
    }

    public void setJusticancelamento(String justicancelamento) {
        this.justicancelamento = justicancelamento;
    }

    public Double getFunrural() {
        return funrural;
    }

    public void setFunrural(Double funrural) {
        this.funrural = funrural;
    }

    public Double getReajustadas() {
        return reajustadas;
    }

    public void setReajustadas(Double reajustadas) {
        this.reajustadas = reajustadas;
    }

    public Double getValorfun() {
        return valorfun;
    }

    public void setValorfun(Double valorfun) {
        this.valorfun = valorfun;
    }

    public Double getValortributos() {
        return valortributos;
    }

    public void setValortributos(Double valortributos) {
        this.valortributos = valortributos;
    }

    public Double getTotaltributos() {
        return totaltributos;
    }

    public void setTotaltributos(Double totaltributos) {
        this.totaltributos = totaltributos;
    }

    public Boolean getAgrupa() {
        return agrupa;
    }

    public void setAgrupa(Boolean agrupa) {
        this.agrupa = agrupa;
    }

    public Long getCodpgto() {
        return codpgto;
    }

    public void setCodpgto(Long codpgto) {
        this.codpgto = codpgto;
    }

    public Double getBaseimpo() {
        return baseimpo;
    }

    public void setBaseimpo(Double baseimpo) {
        this.baseimpo = baseimpo;
    }

    public Double getDesaduaneira() {
        return desaduaneira;
    }

    public void setDesaduaneira(Double desaduaneira) {
        this.desaduaneira = desaduaneira;
    }

    public Double getValoimpor() {
        return valoimpor;
    }

    public void setValoimpor(Double valoimpor) {
        this.valoimpor = valoimpor;
    }

    public Double getValoriof() {
        return valoriof;
    }

    public void setValoriof(Double valoriof) {
        this.valoriof = valoriof;
    }

    public Boolean getGerabloqueto() {
        return gerabloqueto;
    }

    public void setGerabloqueto(Boolean gerabloqueto) {
        this.gerabloqueto = gerabloqueto;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getPresencial() {
        return presencial;
    }

    public void setPresencial(String presencial) {
        this.presencial = presencial;
    }

    public String getDestioperacao() {
        return destioperacao;
    }

    public void setDestioperacao(String destioperacao) {
        this.destioperacao = destioperacao;
    }

    public Long getCodplanocontas() {
        return codplanocontas;
    }

    public void setCodplanocontas(Long codplanocontas) {
        this.codplanocontas = codplanocontas;
    }

    public String getCodcentrocustos() {
        return codcentrocustos;
    }

    public void setCodcentrocustos(String codcentrocustos) {
        this.codcentrocustos = codcentrocustos;
    }

    public String getEmailnota() {
        return emailnota;
    }

    public void setEmailnota(String emailnota) {
        this.emailnota = emailnota;
    }

    public String getCcocupom() {
        return ccocupom;
    }

    public void setCcocupom(String ccocupom) {
        this.ccocupom = ccocupom;
    }

    public String getPlacavei() {
        return placavei;
    }

    public void setPlacavei(String placavei) {
        this.placavei = placavei;
    }

    public Boolean getOperacaosefaz() {
        return operacaosefaz;
    }

    public void setOperacaosefaz(Boolean operacaosefaz) {
        this.operacaosefaz = operacaosefaz;
    }

    public Boolean getEstonodenfe() {
        return estonodenfe;
    }

    public void setEstonodenfe(Boolean estonodenfe) {
        this.estonodenfe = estonodenfe;
    }

    Boolean estonodenfe;

    public String retornaCodNota(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NotaFiscal", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            db.close();
            return formataCodNota(cursor.getString(cursor.getColumnIndex("codnota")));
        } else {
            db.close();
            return formataCodNota("1");
        }
    }

    private String formataCodNota(String codnota){
        for (int i = codnota.length(); i == 9 ; i ++){
            codnota = "0" + codnota;
        }
        return codnota;
    }

    private  Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(idnotafiscal) from notafiscal", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    private  Cursor retornaClienteFiltradaCursor(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT idNotaFiscal FROM notafiscal where idNotaFiscal = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Boolean cadastraNota(Context context, NotaFiscal notaFiscal){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(notaFiscal.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            valores = dadosBanco.insereValoresContent(fieldList.get(i), notaFiscal, valores);
        }

        if (valores.get("idNotaFiscal") == null){
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("codigo");
            valores.remove("cadastroandroid");
            valores.put("codigo", retorno);
            valores.put("cadastroandroid", true);
            retorno = db.insert("cliente", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        }else{
            Cursor cursor = notaFiscal.retornaClienteFiltradaCursor(context, Long.parseLong(valores.get("codigo").toString()));

            if (cursor.getCount() > 0){
                valores.remove("alteradoandroid");
                valores.put("alteradoandroid", true);
                long retorno = db.update("cliente", valores, "codigo= " + valores.get("codigo").toString(), null);
                db.close();
                valores.clear();
                return retorno != -1;
            }else{
                long retorno = retornaMaiorCod(context);
                retorno = retorno + 1;
                valores.remove("cadastroandroid");
                retorno = db.insert("cliente", null, valores);
                db.close();
                valores.clear();
                return retorno != -1;
            }
        }
    }


}
