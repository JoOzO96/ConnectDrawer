package com.example.jose.connectdrawer.Emitente;

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

public class EmiteConfigura {


    Long codemitente;
    Boolean exibirdadosced;
    Boolean exibirdadoslaquentloja;
    Long diascarenciajuros;
    Boolean filtratextoescrita;
    Boolean exibirdadosalternativa;
    Boolean usaconversaoentrada;
    Double valorboletospadrao;
    Boolean exibirdadosconversaoproduto;
    Boolean descontoapenasproduto;
    Boolean alterarobspedidofechado;
    Boolean imprimirnfsservicodireto;
    Boolean exibirdadosilha;
    Boolean exibirdadosrestaurante;
    Boolean exibirdadosconnect;
    Boolean exibirmensagemeconomizou;
    String obscte;
    Boolean bloquearquantidadenegativa;
    Boolean utilizarpontos;
    Boolean usarepsonlx300;
    Boolean exibirdadosoliveira;
    String produtopredominantecte;
    Boolean ocultavencibarrapedido;
    Boolean emitenfse;
    Boolean exibirdadoshs;
    Boolean naoalteracustoentrada;
    Long caixapadrao;
    Boolean exibirdadosmiotto;
    Boolean exibircustoletras;
    Boolean exibirvendedorcupom;
    String nfseusuario;
    String nfsesenha;
    String nfsecancelamento;
    String nfseconsultalote;
    String nfseconsultafaixa;
    String nfseconsultarps;
    String nfseconsultaprestados;
    String nfseconsultaenvioloterps;
    String nfseconsultaenviorps;
    String nfseconsultasequencialote;
    Boolean exibirdadosvestbem;
    Boolean ocultarcestnota;
    Boolean cadastroduplicadosclientes;
    Boolean exibirdadosestilo;
    Boolean exibirdadostrattore;
    String contacaixachequelaca;
    String contacaixachequebaixa;
    String contacaixaveiculosvenda;
    Boolean utilizaimportacaoautomatica;
    Long codnaturezatranferencia;
    Boolean downloaddllnfe;
    Boolean downloaddllcte;
    Boolean downloaddllmdfe;
    Boolean exibirdadoscanal;
    Long ordempedido;
    Boolean exibirdadoslimalimao;
    Boolean exibirdadoslimpar;
    Boolean gerablocok;
    String cstpadraocadastro;
    Boolean converteimpostoentrada;
    String codnaturezadevolucao;
    Boolean exibirdadoskitintas;
    String fretenota;
    Boolean exibirdadosinnovar;
    Boolean usarfatorconversao;
    Boolean forcarcfopentrada;
    Boolean geranotalote;
    Boolean exibirdadosgellus;
    Boolean exibirdadosourobranco;
    String produtocomponentecte;
    String certificadoandroid;
    Boolean gerarboletoautomatico;
    Boolean alterarclientepedido;
    Boolean filtrarprodcompandroid;
    Boolean nfseoptantesimplesnacional;
    Long regimeespecialtributacao;
    String codnaturezaretorno;
    Boolean naoalteravalordevenda;
    String codsetorgeral;
    Boolean exibirnomefantasia;
    String codnaturezaremessa;
    String codnaturezavendadireta;
    String produtoaidf;
    Boolean enviadadossped;
    Boolean usarconversaocst;
    Boolean exportarapenasquantidade;
    Boolean permitirdowloadnfecte;
    Double porcentagempadraoctefrete;
    Boolean alterarcodigoentrada;
    Boolean empresapet;

    public Long getCodemitente() {
        return codemitente;
    }

    public void setCodemitente(Long codemitente) {
        this.codemitente = codemitente;
    }

    public Boolean getExibirdadosced() {
        return exibirdadosced;
    }

    public void setExibirdadosced(Boolean exibirdadosced) {
        this.exibirdadosced = exibirdadosced;
    }

    public Boolean getFiltratextoescrita() {
        return filtratextoescrita;
    }

    public void setFiltratextoescrita(Boolean filtratextoescrita) {
        this.filtratextoescrita = filtratextoescrita;
    }

    public Boolean getExibirdadoslaquentloja() {
        return exibirdadoslaquentloja;
    }

    public void setExibirdadoslaquentloja(Boolean exibirdadoslaquentloja) {
        this.exibirdadoslaquentloja = exibirdadoslaquentloja;
    }

    public Long getDiascarenciajuros() {
        return diascarenciajuros;
    }

    public void setDiascarenciajuros(Long diascarenciajuros) {
        this.diascarenciajuros = diascarenciajuros;
    }

    public Boolean getExibirdadosalternativa() {
        return exibirdadosalternativa;
    }

    public void setExibirdadosalternativa(Boolean exibirdadosalternativa) {
        this.exibirdadosalternativa = exibirdadosalternativa;
    }

    public Boolean getUsaconversaoentrada() {
        return usaconversaoentrada;
    }

    public void setUsaconversaoentrada(Boolean usaconversaoentrada) {
        this.usaconversaoentrada = usaconversaoentrada;
    }

    public Double getValorboletospadrao() {
        return valorboletospadrao;
    }

    public void setValorboletospadrao(Double valorboletospadrao) {
        this.valorboletospadrao = valorboletospadrao;
    }

    public Boolean getExibirdadosconversaoproduto() {
        return exibirdadosconversaoproduto;
    }

    public void setExibirdadosconversaoproduto(Boolean exibirdadosconversaoproduto) {
        this.exibirdadosconversaoproduto = exibirdadosconversaoproduto;
    }

    public Boolean getDescontoapenasproduto() {
        return descontoapenasproduto;
    }

    public void setDescontoapenasproduto(Boolean descontoapenasproduto) {
        this.descontoapenasproduto = descontoapenasproduto;
    }

    public Boolean getAlterarobspedidofechado() {
        return alterarobspedidofechado;
    }

    public void setAlterarobspedidofechado(Boolean alterarobspedidofechado) {
        this.alterarobspedidofechado = alterarobspedidofechado;
    }

    public Boolean getImprimirnfsservicodireto() {
        return imprimirnfsservicodireto;
    }

    public void setImprimirnfsservicodireto(Boolean imprimirnfsservicodireto) {
        this.imprimirnfsservicodireto = imprimirnfsservicodireto;
    }

    public Boolean getExibirdadosilha() {
        return exibirdadosilha;
    }

    public void setExibirdadosilha(Boolean exibirdadosilha) {
        this.exibirdadosilha = exibirdadosilha;
    }

    public Boolean getExibirdadosrestaurante() {
        return exibirdadosrestaurante;
    }

    public void setExibirdadosrestaurante(Boolean exibirdadosrestaurante) {
        this.exibirdadosrestaurante = exibirdadosrestaurante;
    }

    public Boolean getExibirdadosconnect() {
        return exibirdadosconnect;
    }

    public void setExibirdadosconnect(Boolean exibirdadosconnect) {
        this.exibirdadosconnect = exibirdadosconnect;
    }

    public Boolean getExibirmensagemeconomizou() {
        return exibirmensagemeconomizou;
    }

    public void setExibirmensagemeconomizou(Boolean exibirmensagemeconomizou) {
        this.exibirmensagemeconomizou = exibirmensagemeconomizou;
    }

    public String getObscte() {
        return obscte;
    }

    public void setObscte(String obscte) {
        this.obscte = obscte;
    }

    public Boolean getBloquearquantidadenegativa() {
        return bloquearquantidadenegativa;
    }

    public void setBloquearquantidadenegativa(Boolean bloquearquantidadenegativa) {
        this.bloquearquantidadenegativa = bloquearquantidadenegativa;
    }

    public Boolean getUtilizarpontos() {
        return utilizarpontos;
    }

    public void setUtilizarpontos(Boolean utilizarpontos) {
        this.utilizarpontos = utilizarpontos;
    }

    public Boolean getUsarepsonlx300() {
        return usarepsonlx300;
    }

    public void setUsarepsonlx300(Boolean usarepsonlx300) {
        this.usarepsonlx300 = usarepsonlx300;
    }

    public String getProdutopredominantecte() {
        return produtopredominantecte;
    }

    public void setProdutopredominantecte(String produtopredominantecte) {
        this.produtopredominantecte = produtopredominantecte;
    }

    public String getProdutocomponentecte() {
        return produtocomponentecte;
    }

    public void setProdutocomponentecte(String produtocomponentecte) {
        this.produtocomponentecte = produtocomponentecte;
    }

    public String getProdutoaidf() {
        return produtoaidf;
    }

    public void setProdutoaidf(String produtoaidf) {
        this.produtoaidf = produtoaidf;
    }

    public Boolean getExibirdadosoliveira() {
        return exibirdadosoliveira;
    }

    public void setExibirdadosoliveira(Boolean exibirdadosoliveira) {
        this.exibirdadosoliveira = exibirdadosoliveira;
    }

    public Boolean getOcultavencibarrapedido() {
        return ocultavencibarrapedido;
    }

    public void setOcultavencibarrapedido(Boolean ocultavencibarrapedido) {
        this.ocultavencibarrapedido = ocultavencibarrapedido;
    }

    public Boolean getEmitenfse() {
        return emitenfse;
    }

    public void setEmitenfse(Boolean emitenfse) {
        this.emitenfse = emitenfse;
    }

    public Boolean getExibirdadoshs() {
        return exibirdadoshs;
    }

    public void setExibirdadoshs(Boolean exibirdadoshs) {
        this.exibirdadoshs = exibirdadoshs;
    }

    public Boolean getExibircustoletras() {
        return exibircustoletras;
    }

    public void setExibircustoletras(Boolean exibircustoletras) {
        this.exibircustoletras = exibircustoletras;
    }

    public String getNfseusuario() {
        return nfseusuario;
    }

    public void setNfseusuario(String nfseusuario) {
        this.nfseusuario = nfseusuario;
    }

    public String getNfsesenha() {
        return nfsesenha;
    }

    public void setNfsesenha(String nfsesenha) {
        this.nfsesenha = nfsesenha;
    }

    public String getNfsecancelamento() {
        return nfsecancelamento;
    }

    public void setNfsecancelamento(String nfsecancelamento) {
        this.nfsecancelamento = nfsecancelamento;
    }

    public String getNfseconsultalote() {
        return nfseconsultalote;
    }

    public void setNfseconsultalote(String nfseconsultalote) {
        this.nfseconsultalote = nfseconsultalote;
    }

    public String getNfseconsultafaixa() {
        return nfseconsultafaixa;
    }

    public void setNfseconsultafaixa(String nfseconsultafaixa) {
        this.nfseconsultafaixa = nfseconsultafaixa;
    }

    public String getNfseconsultarps() {
        return nfseconsultarps;
    }

    public void setNfseconsultarps(String nfseconsultarps) {
        this.nfseconsultarps = nfseconsultarps;
    }

    public String getNfseconsultaprestados() {
        return nfseconsultaprestados;
    }

    public void setNfseconsultaprestados(String nfseconsultaprestados) {
        this.nfseconsultaprestados = nfseconsultaprestados;
    }

    public String getNfseconsultaenvioloterps() {
        return nfseconsultaenvioloterps;
    }

    public void setNfseconsultaenvioloterps(String nfseconsultaenvioloterps) {
        this.nfseconsultaenvioloterps = nfseconsultaenvioloterps;
    }

    public String getNfseconsultaenviorps() {
        return nfseconsultaenviorps;
    }

    public void setNfseconsultaenviorps(String nfseconsultaenviorps) {
        this.nfseconsultaenviorps = nfseconsultaenviorps;
    }

    public String getNfseconsultasequencialote() {
        return nfseconsultasequencialote;
    }

    public void setNfseconsultasequencialote(String nfseconsultasequencialote) {
        this.nfseconsultasequencialote = nfseconsultasequencialote;
    }

    public Boolean getExibirvendedorcupom() {
        return exibirvendedorcupom;
    }

    public void setExibirvendedorcupom(Boolean exibirvendedorcupom) {
        this.exibirvendedorcupom = exibirvendedorcupom;
    }

    public Long getCaixapadrao() {
        return caixapadrao;
    }

    public void setCaixapadrao(Long caixapadrao) {
        this.caixapadrao = caixapadrao;
    }

    public Boolean getCadastroduplicadosclientes() {
        return cadastroduplicadosclientes;
    }

    public void setCadastroduplicadosclientes(Boolean cadastroduplicadosclientes) {
        this.cadastroduplicadosclientes = cadastroduplicadosclientes;
    }

    public Boolean getOcultarcestnota() {
        return ocultarcestnota;
    }

    public void setOcultarcestnota(Boolean ocultarcestnota) {
        this.ocultarcestnota = ocultarcestnota;
    }

    public Boolean getNaoalteracustoentrada() {
        return naoalteracustoentrada;
    }

    public void setNaoalteracustoentrada(Boolean naoalteracustoentrada) {
        this.naoalteracustoentrada = naoalteracustoentrada;
    }

    public Boolean getExibirdadosmiotto() {
        return exibirdadosmiotto;
    }

    public void setExibirdadosmiotto(Boolean exibirdadosmiotto) {
        this.exibirdadosmiotto = exibirdadosmiotto;
    }

    public Boolean getExibirdadosvestbem() {
        return exibirdadosvestbem;
    }

    public void setExibirdadosvestbem(Boolean exibirdadosvestbem) {
        this.exibirdadosvestbem = exibirdadosvestbem;
    }

    public Boolean getExibirdadosestilo() {
        return exibirdadosestilo;
    }

    public void setExibirdadosestilo(Boolean exibirdadosestilo) {
        this.exibirdadosestilo = exibirdadosestilo;
    }

    public String getContacaixaveiculosvenda() {
        return contacaixaveiculosvenda;
    }

    public void setContacaixaveiculosvenda(String contacaixaveiculosvenda) {
        this.contacaixaveiculosvenda = contacaixaveiculosvenda;
    }

    public Boolean getExibirdadostrattore() {
        return exibirdadostrattore;
    }

    public void setExibirdadostrattore(Boolean exibirdadostrattore) {
        this.exibirdadostrattore = exibirdadostrattore;
    }

    public String getContacaixachequelaca() {
        return contacaixachequelaca;
    }

    public void setContacaixachequelaca(String contacaixachequelaca) {
        this.contacaixachequelaca = contacaixachequelaca;
    }

    public String getContacaixachequebaixa() {
        return contacaixachequebaixa;
    }

    public void setContacaixachequebaixa(String contacaixachequebaixa) {
        this.contacaixachequebaixa = contacaixachequebaixa;
    }

    public Boolean getUtilizaimportacaoautomatica() {
        return utilizaimportacaoautomatica;
    }

    public void setUtilizaimportacaoautomatica(Boolean utilizaimportacaoautomatica) {
        this.utilizaimportacaoautomatica = utilizaimportacaoautomatica;
    }

    public Boolean getGerablocok() {
        return gerablocok;
    }

    public void setGerablocok(Boolean gerablocok) {
        this.gerablocok = gerablocok;
    }

    public String getCstpadraocadastro() {
        return cstpadraocadastro;
    }

    public void setCstpadraocadastro(String cstpadraocadastro) {
        this.cstpadraocadastro = cstpadraocadastro;
    }

    public Boolean getConverteimpostoentrada() {
        return converteimpostoentrada;
    }

    public void setConverteimpostoentrada(Boolean converteimpostoentrada) {
        this.converteimpostoentrada = converteimpostoentrada;
    }

    public String getCodnaturezadevolucao() {
        return codnaturezadevolucao;
    }

    public void setCodnaturezadevolucao(String codnaturezadevolucao) {
        this.codnaturezadevolucao = codnaturezadevolucao;
    }

    public Boolean getExibirdadoskitintas() {
        return exibirdadoskitintas;
    }

    public void setExibirdadoskitintas(Boolean exibirdadoskitintas) {
        this.exibirdadoskitintas = exibirdadoskitintas;
    }

    public String getFretenota() {
        return fretenota;
    }

    public void setFretenota(String fretenota) {
        this.fretenota = fretenota;
    }

    public Boolean getExibirdadosinnovar() {
        return exibirdadosinnovar;
    }

    public void setExibirdadosinnovar(Boolean exibirdadosinnovar) {
        this.exibirdadosinnovar = exibirdadosinnovar;
    }

    public Boolean getUsarfatorconversao() {
        return usarfatorconversao;
    }

    public void setUsarfatorconversao(Boolean usarfatorconversao) {
        this.usarfatorconversao = usarfatorconversao;
    }

    public Boolean getForcarcfopentrada() {
        return forcarcfopentrada;
    }

    public void setForcarcfopentrada(Boolean forcarcfopentrada) {
        this.forcarcfopentrada = forcarcfopentrada;
    }

    public Boolean getGeranotalote() {
        return geranotalote;
    }

    public void setGeranotalote(Boolean geranotalote) {
        this.geranotalote = geranotalote;
    }

    public Boolean getExibirdadoscanal() {
        return exibirdadoscanal;
    }

    public void setExibirdadoscanal(Boolean exibirdadoscanal) {
        this.exibirdadoscanal = exibirdadoscanal;
    }

    public Long getCodnaturezatranferencia() {
        return codnaturezatranferencia;
    }

    public void setCodnaturezatranferencia(Long codnaturezatranferencia) {
        this.codnaturezatranferencia = codnaturezatranferencia;
    }

    public Boolean getDownloaddllnfe() {
        return downloaddllnfe;
    }

    public void setDownloaddllnfe(Boolean downloaddllnfe) {
        this.downloaddllnfe = downloaddllnfe;
    }

    public Boolean getDownloaddllcte() {
        return downloaddllcte;
    }

    public void setDownloaddllcte(Boolean downloaddllcte) {
        this.downloaddllcte = downloaddllcte;
    }

    public Boolean getDownloaddllmdfe() {
        return downloaddllmdfe;
    }

    public void setDownloaddllmdfe(Boolean downloaddllmdfe) {
        this.downloaddllmdfe = downloaddllmdfe;
    }

    public Boolean getEnviadadossped() {
        return enviadadossped;
    }

    public void setEnviadadossped(Boolean enviadadossped) {
        this.enviadadossped = enviadadossped;
    }

    public Long getOrdempedido() {
        return ordempedido;
    }

    public void setOrdempedido(Long ordempedido) {
        this.ordempedido = ordempedido;
    }

    public Boolean getExibirdadoslimalimao() {
        return exibirdadoslimalimao;
    }

    public void setExibirdadoslimalimao(Boolean exibirdadoslimalimao) {
        this.exibirdadoslimalimao = exibirdadoslimalimao;
    }

    public Boolean getExibirdadoslimpar() {
        return exibirdadoslimpar;
    }

    public void setExibirdadoslimpar(Boolean exibirdadoslimpar) {
        this.exibirdadoslimpar = exibirdadoslimpar;
    }

    public Boolean getExibirdadosgellus() {
        return exibirdadosgellus;
    }

    public void setExibirdadosgellus(Boolean exibirdadosgellus) {
        this.exibirdadosgellus = exibirdadosgellus;
    }

    public Boolean getExibirdadosourobranco() {
        return exibirdadosourobranco;
    }

    public void setExibirdadosourobranco(Boolean exibirdadosourobranco) {
        this.exibirdadosourobranco = exibirdadosourobranco;
    }

    public String getCertificadoandroid() {
        return certificadoandroid;
    }

    public void setCertificadoandroid(String certificadoandroid) {
        this.certificadoandroid = certificadoandroid;
    }

    public Boolean getAlterarclientepedido() {
        return alterarclientepedido;
    }

    public void setAlterarclientepedido(Boolean alterarclientepedido) {
        this.alterarclientepedido = alterarclientepedido;
    }

    public Boolean getFiltrarprodcompandroid() {
        return filtrarprodcompandroid;
    }

    public void setFiltrarprodcompandroid(Boolean filtrarprodcompandroid) {
        this.filtrarprodcompandroid = filtrarprodcompandroid;
    }

    public Boolean getNfseoptantesimplesnacional() {
        return nfseoptantesimplesnacional;
    }

    public void setNfseoptantesimplesnacional(Boolean nfseoptantesimplesnacional) {
        this.nfseoptantesimplesnacional = nfseoptantesimplesnacional;
    }

    public Long getRegimeespecialtributacao() {
        return regimeespecialtributacao;
    }

    public void setRegimeespecialtributacao(Long regimeespecialtributacao) {
        this.regimeespecialtributacao = regimeespecialtributacao;
    }

    public Boolean getGerarboletoautomatico() {
        return gerarboletoautomatico;
    }

    public void setGerarboletoautomatico(Boolean gerarboletoautomatico) {
        this.gerarboletoautomatico = gerarboletoautomatico;
    }

    public String getCodnaturezaretorno() {
        return codnaturezaretorno;
    }

    public void setCodnaturezaretorno(String codnaturezaretorno) {
        this.codnaturezaretorno = codnaturezaretorno;
    }

    public String getCodsetorgeral() {
        return codsetorgeral;
    }

    public void setCodsetorgeral(String codsetorgeral) {
        this.codsetorgeral = codsetorgeral;
    }

    public Boolean getExibirnomefantasia() {
        return exibirnomefantasia;
    }

    public void setExibirnomefantasia(Boolean exibirnomefantasia) {
        this.exibirnomefantasia = exibirnomefantasia;
    }

    public Boolean getNaoalteravalordevenda() {
        return naoalteravalordevenda;
    }

    public void setNaoalteravalordevenda(Boolean naoalteravalordevenda) {
        this.naoalteravalordevenda = naoalteravalordevenda;
    }

    public String getCodnaturezaremessa() {
        return codnaturezaremessa;
    }

    public void setCodnaturezaremessa(String codnaturezaremessa) {
        this.codnaturezaremessa = codnaturezaremessa;
    }

    public String getCodnaturezavendadireta() {
        return codnaturezavendadireta;
    }

    public void setCodnaturezavendadireta(String codnaturezavendadireta) {
        this.codnaturezavendadireta = codnaturezavendadireta;
    }

    public Boolean getUsarconversaocst() {
        return usarconversaocst;
    }

    public void setUsarconversaocst(Boolean usarconversaocst) {
        this.usarconversaocst = usarconversaocst;
    }

    public Boolean getExportarapenasquantidade() {
        return exportarapenasquantidade;
    }

    public void setExportarapenasquantidade(Boolean exportarapenasquantidade) {
        this.exportarapenasquantidade = exportarapenasquantidade;
    }

    public Boolean getPermitirdowloadnfecte() {
        return permitirdowloadnfecte;
    }

    public void setPermitirdowloadnfecte(Boolean permitirdowloadnfecte) {
        this.permitirdowloadnfecte = permitirdowloadnfecte;
    }

    public Double getPorcentagempadraoctefrete() {
        return porcentagempadraoctefrete;
    }

    public void setPorcentagempadraoctefrete(Double porcentagempadraoctefrete) {
        this.porcentagempadraoctefrete = porcentagempadraoctefrete;
    }

    public Boolean getAlterarcodigoentrada() {
        return alterarcodigoentrada;
    }

    public void setAlterarcodigoentrada(Boolean alterarcodigoentrada) {
        this.alterarcodigoentrada = alterarcodigoentrada;
    }

    public Boolean getEmpresapet() {
        return empresapet;
    }

    public void setEmpresapet(Boolean empresapet) {
        this.empresapet = empresapet;
    }

    public Boolean cadastraEmiteConfigura(Context context, EmiteConfigura emiteConfigura) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(emiteConfigura.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), emiteConfigura, valores);
        }

        db.delete("emiteconfigura","", null);
        db.insert("emiteconfigura", null, valores);
        return true;
    }

    public EmiteConfigura retornaEmiteConfiguraObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        EmiteConfigura emiteConfigura = new EmiteConfigura();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM emiteconfigura where codemitente = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldList = new ArrayList<>(Arrays.asList(EmiteConfigura.class.getDeclaredFields()));

        for (int f = 0; fieldList.size() != f; f++) {
            if (fieldList.get(f).getName().toLowerCase().equals("alteradoandroid")){
                fieldList.remove(f);
            }
            if (fieldList.get(f).getName().toLowerCase().equals("cadastroandroid")){
                fieldList.remove(f);
            }
            if (fieldList.get(f).getName().toLowerCase().equals("deletadooandroid")){
                fieldList.remove(f);
            }
        }

        for (int j = 0; cursor.getCount() != j; j++) {
            emiteConfigura = new EmiteConfigura();
            for (int f = 0; fieldList.size() != f; f++) {
                String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                String nomeCampo = fieldList.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retorno1 = getSetDinamico.insereField(fieldList.get(f), emiteConfigura, retorno);
                    emiteConfigura = (EmiteConfigura) retorno1;
                }
            }
        }
        db.close();
        return emiteConfigura;

    }
}
