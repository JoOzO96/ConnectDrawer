package com.example.jose.connectdrawer.Pedido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jose.connectdrawer.Parcelas.Parcelas;
import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;
import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Jose on 21/06/2017.
 */

public class Pedido {

    Long pedido;
    Long codpedido;
    Long codcliente;
    Date data;
    String codvendedor;
    String formadepagamento;
    Double frete;
    Double valortotal;
    Double entrada;
    String orpedi;
    Long codbanco;
    String obs;
    Long desconto;
    String nome;
    Double total;
    Date venci1;
    Double valor1;
    Long dias;
    Long juro;
    Boolean simnao;
    Long pgto;
    String cheque;
    Date data1;
    String notafisca;
    Long via;
    Boolean baixa;
    String veiculo;
    String placa;
    String ano;
    Long nparce;
    Long codinstituiCao;
    String nfc;
    Long dife;
    Double comis;
    Boolean nfe;
    Double vpago;
    Double troco;
    String codhistorico;
    Long descvalor;
    Long codemitente;
    String codmecanico;
    Double valorservico;
    Long descoservico;
    Boolean juntapedido;
    String km;
    Long nparc;
    String ncf;
    Double comi;
    Long desvalor;
    String cpf;
    Long parce;
    String impressora;
    Long inicialp;
    Long finalp;
    Long totalp;
    Boolean gerabloqueto;
    Boolean gerabloqueto1;
    Long codplanocontas;
    String codcentrocustos;
    Boolean documento;
    String codproduto;
    Boolean mensagem;
    Boolean estornop;
    Long codstatus;
    Boolean baixaf;
    Long acreboleto;
    String aparelho;
    String defeito;
    Long pedidoreferencia;
    String enderecopedido;
    Boolean nfce;
    Boolean nfcee;
    Long codcaixa;
    Boolean juridica;
    String ajuste;
    Long dataentrega;
    String servicosolicitado;
    String nnotaservico;
    Long codbandeira;
    Boolean orcamentofinalizado;
    String coddigitador;
    Long horasdemotor;
    Long horasdetrilha;
    Boolean especial;
    String rota;
    Boolean geradoautomatico;

    public Long getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(Long codpedido) {
        this.codpedido = codpedido;
    }

    public Long getCodinstituiCao() {
        return codinstituiCao;
    }

    public void setCodinstituiCao(Long codinstituiCao) {
        this.codinstituiCao = codinstituiCao;
    }

    public Long getDife() {
        return dife;
    }

    public void setDife(Long dife) {
        this.dife = dife;
    }

    public Double getComis() {
        return comis;
    }

    public void setComis(Double comis) {
        this.comis = comis;
    }

    public Double getVpago() {
        return vpago;
    }

    public void setVpago(Double vpago) {
        this.vpago = vpago;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public String getCodhistorico() {
        return codhistorico;
    }

    public void setCodhistorico(String codhistorico) {
        this.codhistorico = codhistorico;
    }

    public Long getDescvalor() {
        return descvalor;
    }

    public void setDescvalor(Long descvalor) {
        this.descvalor = descvalor;
    }

    public String getCodmecanico() {
        return codmecanico;
    }

    public void setCodmecanico(String codmecanico) {
        this.codmecanico = codmecanico;
    }

    public Double getValorservico() {
        return valorservico;
    }

    public void setValorservico(Double valorservico) {
        this.valorservico = valorservico;
    }

    public Long getDescoservico() {
        return descoservico;
    }

    public void setDescoservico(Long descoservico) {
        this.descoservico = descoservico;
    }

    public Boolean getJuntapedido() {
        return juntapedido;
    }

    public void setJuntapedido(Boolean juntapedido) {
        this.juntapedido = juntapedido;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public Long getNparc() {
        return nparc;
    }

    public void setNparc(Long nparc) {
        this.nparc = nparc;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public Double getComi() {
        return comi;
    }

    public void setComi(Double comi) {
        this.comi = comi;
    }

    public Long getDesvalor() {
        return desvalor;
    }

    public void setDesvalor(Long desvalor) {
        this.desvalor = desvalor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getParce() {
        return parce;
    }

    public void setParce(Long parce) {
        this.parce = parce;
    }

    public String getImpressora() {
        return impressora;
    }

    public void setImpressora(String impressora) {
        this.impressora = impressora;
    }

    public Long getInicialp() {
        return inicialp;
    }

    public void setInicialp(Long inicialp) {
        this.inicialp = inicialp;
    }

    public Long getFinalp() {
        return finalp;
    }

    public void setFinalp(Long finalp) {
        this.finalp = finalp;
    }

    public Long getTotalp() {
        return totalp;
    }

    public void setTotalp(Long totalp) {
        this.totalp = totalp;
    }

    public Boolean getGerabloqueto() {
        return gerabloqueto;
    }

    public void setGerabloqueto(Boolean gerabloqueto) {
        this.gerabloqueto = gerabloqueto;
    }

    public Boolean getGerabloqueto1() {
        return gerabloqueto1;
    }

    public void setGerabloqueto1(Boolean gerabloqueto1) {
        this.gerabloqueto1 = gerabloqueto1;
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

    public Boolean getDocumento() {
        return documento;
    }

    public void setDocumento(Boolean documento) {
        this.documento = documento;
    }

    public String getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(String codproduto) {
        this.codproduto = codproduto;
    }

    public Boolean getMensagem() {
        return mensagem;
    }

    public void setMensagem(Boolean mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getEstornop() {
        return estornop;
    }

    public void setEstornop(Boolean estornop) {
        this.estornop = estornop;
    }

    public Boolean getBaixaf() {
        return baixaf;
    }

    public void setBaixaf(Boolean baixaf) {
        this.baixaf = baixaf;
    }

    public Long getAcreboleto() {
        return acreboleto;
    }

    public void setAcreboleto(Long acreboleto) {
        this.acreboleto = acreboleto;
    }

    public String getAparelho() {
        return aparelho;
    }

    public void setAparelho(String aparelho) {
        this.aparelho = aparelho;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public Long getPedidoreferencia() {
        return pedidoreferencia;
    }

    public void setPedidoreferencia(Long pedidoreferencia) {
        this.pedidoreferencia = pedidoreferencia;
    }

    public String getEnderecopedido() {
        return enderecopedido;
    }

    public void setEnderecopedido(String enderecopedido) {
        this.enderecopedido = enderecopedido;
    }

    public Boolean getNfce() {
        return nfce;
    }

    public void setNfce(Boolean nfce) {
        this.nfce = nfce;
    }

    public Boolean getNfcee() {
        return nfcee;
    }

    public void setNfcee(Boolean nfcee) {
        this.nfcee = nfcee;
    }

    public Boolean getJuridica() {
        return juridica;
    }

    public void setJuridica(Boolean juridica) {
        this.juridica = juridica;
    }

    public String getAjuste() {
        return ajuste;
    }

    public void setAjuste(String ajuste) {
        this.ajuste = ajuste;
    }

    public Long getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Long dataentrega) {
        this.dataentrega = dataentrega;
    }

    public String getNnotaservico() {
        return nnotaservico;
    }

    public void setNnotaservico(String nnotaservico) {
        this.nnotaservico = nnotaservico;
    }

    public Long getCodbandeira() {
        return codbandeira;
    }

    public void setCodbandeira(Long codbandeira) {
        this.codbandeira = codbandeira;
    }

    public Boolean getOrcamentofinalizado() {
        return orcamentofinalizado;
    }

    public void setOrcamentofinalizado(Boolean orcamentofinalizado) {
        this.orcamentofinalizado = orcamentofinalizado;
    }

    public String getCoddigitador() {
        return coddigitador;
    }

    public void setCoddigitador(String coddigitador) {
        this.coddigitador = coddigitador;
    }

    public Long getHorasdemotor() {
        return horasdemotor;
    }

    public void setHorasdemotor(Long horasdemotor) {
        this.horasdemotor = horasdemotor;
    }

    public Long getHorasdetrilha() {
        return horasdetrilha;
    }

    public void setHorasdetrilha(Long horasdetrilha) {
        this.horasdetrilha = horasdetrilha;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public Boolean getGeradoautomatico() {
        return geradoautomatico;
    }

    public void setGeradoautomatico(Boolean geradoautomatico) {
        this.geradoautomatico = geradoautomatico;
    }

    public Boolean getNfe() {
        return nfe;
    }

    public void setNfe(Boolean nfe) {
        this.nfe = nfe;
    }

    private List<PedidoProduto> itensPedido;

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Long getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Long codcliente) {
        this.codcliente = codcliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
    }

    public String getFormadepagamento() {
        return formadepagamento;
    }

    public void setFormadepagamento(String formadepagamento) {
        this.formadepagamento = formadepagamento;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public Double getEntrada() {
        return entrada;
    }

    public void setEntrada(Double entrada) {
        this.entrada = entrada;
    }

    public Long getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(Long codbanco) {
        this.codbanco = codbanco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Long getDesconto() {
        return desconto;
    }

    public void setDesconto(Long desconto) {
        this.desconto = desconto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getVenci1() {
        return venci1;
    }

    public void setVenci1(Date venci1) {
        this.venci1 = venci1;
    }

    public Double getValor1() {
        return valor1;
    }

    public void setValor1(Double valor1) {
        this.valor1 = valor1;
    }

    public Long getDias() {
        return dias;
    }

    public void setDias(Long dias) {
        this.dias = dias;
    }

    public Long getJuro() {
        return juro;
    }

    public void setJuro(Long juro) {
        this.juro = juro;
    }

    public Boolean getSimnao() {
        return simnao;
    }

    public void setSimnao(Boolean simnao) {
        this.simnao = simnao;
    }

    public Long getPgto() {
        return pgto;
    }

    public void setPgto(Long pgto) {
        this.pgto = pgto;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public Date getData1() {
        return data1;
    }

    public void setData1(Date data1) {
        this.data1 = data1;
    }

    public String getNotafisca() {
        return notafisca;
    }

    public void setNotafisca(String notafisca) {
        this.notafisca = notafisca;
    }

    public Long getVia() {
        return via;
    }

    public void setVia(Long via) {
        this.via = via;
    }

    public Boolean getBaixa() {
        return baixa;
    }

    public void setBaixa(Boolean baixa) {
        this.baixa = baixa;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Long getNparce() {
        return nparce;
    }

    public void setNparce(Long nparce) {
        this.nparce = nparce;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public Long getCodcaixa() {
        return codcaixa;
    }

    public void setCodcaixa(Long codcaixa) {
        this.codcaixa = codcaixa;
    }

    public String getServicosolicitado() {
        return servicosolicitado;
    }

    public void setServicosolicitado(String servicosolicitado) {
        this.servicosolicitado = servicosolicitado;
    }

    public Long getCodstatus() {
        return codstatus;
    }

    public void setCodstatus(Long codstatus) {
        this.codstatus = codstatus;
    }

    public List<PedidoProduto> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoProduto> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "" + pedido + " - " + nome;
    }

    public Cursor retornaPedido(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedido", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoFiltradaCursor(Context context, Long codPedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedido where pedido = " + codPedido, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaPedidoAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedido where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public boolean remover(Context context, Pedido pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedido", "pedido = " + pedido.getPedido(), null);
        return retorno > 0;
    }

    public boolean removerPedido(Context context, Long pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedido", "pedido = " + pedido, null);
        return retorno > 0;
    }

    public boolean removerPedidoProduto(Context context, Long pedido) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        int retorno = db.delete("pedidoproduto", "pedido = " + pedido, null);
        return retorno > -1;
    }


    public Boolean cadastraPedido(Context context, Pedido pedido) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(pedido.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), pedido, valores);
        }

        if (valores.get("pedido") == null) {
            long retorno = retornaMaiorCod(context);
            retorno = retorno + 1;
            valores.remove("pedido");
            valores.remove("orpedi");
            valores.remove("simnao");
            valores.remove("cadastroandroid");
            valores.put("pedido", retorno);
            valores.put("orpedi", 1);
            valores.put("simnao", false);
            valores.put("cadastroandroid", true);
            retorno = db.insert("pedido", null, valores);
            db.close();
            valores.clear();
            return retorno != -1;
        } else {
            valores.remove("alteradoandroid");
            valores.put("alteradoandroid", true);
            long retorno = db.update("pedido", valores, "pedido= " + valores.get("pedido").toString(), null);
            db.close();
            valores.clear();
            return retorno != -1;
        }
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,max(pedido) from pedido", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public void alteraCodPedido(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("pedido", codigoServidor);
        int retorno = db.update("pedido", values, "pedido = " + codigoAndroid, null);
        values.clear();
    }

    public void alteraCodPedidoProduto(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("pedido", codigoServidor);
        int retorno = db.update("pedidoproduto", values, "pedido = " + codigoAndroid, null);

    }

    public void alteraParcelas(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        Parcelas parcelas = new Parcelas();
        List<Parcelas> parcelasList = parcelas.retornaListaDeParcelas(context, codigoAndroid);
        parcelas.limpaParcelas(context, codigoAndroid);
        for (int i = 0; i < parcelasList.size(); i++){
            parcelasList.get(i).setCodPedido(codigoServidor.toString());
            parcelasList.get(i).setFatura(codigoServidor + "/" + i);
            parcelas.cadastraParcela(context, parcelasList.get(i));
        }

//        values.put("pedido", codigoServidor);
//        int retorno = db.update("pedidoproduto", values, "pedido = " + codigoAndroid, null);

    }

    public void removePedidoAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo, "0");
        int retorno = db.update("pedido", values, campo + " = 1", null);

    }

    public Pedido retornaPedidoObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        Pedido pedido = new Pedido();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM pedido where pedido = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            for (int f = 0; fieldListCliente.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retCliente = getSetDinamico.insereField(fieldListCliente.get(f), pedido, retorno);
                    pedido = (Pedido) retCliente;
                }
            }
        }
        db.close();
        return pedido;
    }

    public Long getCodemitente() {
        return codemitente;
    }

    public void setCodemitente(Long codemitente) {
        this.codemitente = codemitente;
    }

    public String getOrpedi() {
        return orpedi;
    }

    public void setOrpedi(String orpedi) {
        this.orpedi = orpedi;
    }
}
