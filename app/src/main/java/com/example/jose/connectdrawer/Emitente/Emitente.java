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

public class Emitente {


    Long codigoemitente;
    String serie;
    String cnpjemi;
    String cpfemi;
    String emitente;
    String fantasia;
    String endereco;
    String numero;
    String complemento;
    String bairro;
    String codmuni;
    String municipio;
    String uf;
    String cep;
    String codpais;
    String pais;
    String fone;
    String ieemi;
    String iesub;
    String imemi;
    String cnaeemi;
    String tributa;
    String certiemi;
    String licenca;
    String hambiente;
    String emailemi;
    String server;
    Long porta;
    Long autenticacao;
    String usuario;
    String senha;
    Long copiadanfe;
    String csosn;
    Double perapro;
    String user;
    Long modulo;
    Long cupomnfe;
    String portaimpre;
    String nporta;
    Boolean duplicata;
    String emailcontador;
    Boolean mecanica;
    String dadosobs;
    Boolean descpedidopor;
    Boolean descprodutopor;
    Boolean textologo;
    Boolean calprodutosimples;
    Boolean pedidoean;
    Boolean listainicio;
    String tipoimpfiscal;
    Boolean geracodigogrupo;
    Boolean ipipeso;
    Boolean usaleitor;
    Boolean calculajuro;
    Boolean carne;
    Long numerocaracter;
    Boolean emite;
    Boolean pedisimples;
    boolean entradaresumida;
    String tipoetiqueta;
    Boolean industria;
    Boolean mostratamanho;
    Boolean placacf;
    Boolean recibofiscal;
    Boolean precopedido;
    Boolean pedidofiscal;
    Boolean servicocupom;
    Long margeminferior;
    Boolean mostrarservico;
    Boolean orgaestoque;
    Boolean liberadatare;
    Boolean emitentepadrao;
    Boolean emitentenfce;
    String tokennfce;
    String pincerti;
    Boolean liberacaixa;
    Long viarecibo;
    Long viaduplicata;
    String codcedente;
    Long codpraca;
    String agencia;
    String conta;
    String unidadeatendimento;
    String nbanco;
    String nomebanco;
    Long codcanaltransmissao;
    String codcarteira;
    String tipoimpressaoboleto;
    Boolean postartitulo;
    String emissaoboleto;
    String diasprotesto;
    Double juropordiaatrazo;
    Double descontoboleto;
    String tipodesconto;
    String tipojuro;
    String postoagencia;
    Long juromes;
    String versaosistema;
    Long clientepedido;
    String fusohorario;
    Long numeparcela;
    Boolean naolancacaixa;
    Boolean dadosimpressora;
    Boolean produtocomprar;
    Boolean mostrafunrural;
    String codhistorico;
    Long lemitedesconto;
    Long limitedesconto;
    Boolean alterarvalorunitario;
    Boolean imprimeobs020;
    String senhareceber;
    Boolean emitentelaticinio;
    Boolean bloqueianota;
    Boolean horacaixa;
    Boolean ato;
    Boolean xml;
    String emaildados;
    Boolean dadospneus;
    Boolean produtocomposto;
    Boolean permitirestorno;
    Long diastravamento;
    Boolean emitesistemas;
    Boolean emitecapaboleto;
    String mensagemboleto;
    String notaservico;
    Boolean notaservicopm;
    Boolean duplicatapedido;
    String protestoautomatico;
    Boolean mensagempedido;
    Boolean entradacodigoforne;
    Boolean bloqueiamovimentacao;
    Boolean datasatrazadascaixa;
    Boolean chequebaixadata;
    Long codigoboleto;
    Boolean exibiraparelho;
    Boolean duplicatabematech;
    Boolean mostrarcustolista;
    Boolean listadeprecozero;
    Boolean exibirlogo;
    Long anobase;
    Boolean utilizarmarkup;
    Boolean aredondavenda;
    Boolean lancaapenascontaspagarentrada;
    Boolean carneboleto;
    Boolean lancavalorentrada;
    Boolean importaentradaean;
    Double valorfretepedido;
    Boolean pedidopoleto;
    String contacaixacheque;
    String contacaixaveiculos;
    Long taxacelic;
    Boolean pedidoautoprazo;
    Boolean usarbarrabalanca;
    String licencamdfe;
    String versaomdfe;
    String numerocopiacfe;
    Boolean baixacomposicaosaida;
    String vendedorpadrao;
    Boolean ocultardadosemitente;
    Long tipodeentrada;
    Boolean ocultaminimo;
    Boolean formatacodigo;
    Long numerocopiapedido;
    Boolean ocultarlogo;
    Boolean liberartotalprodupedido;
    Boolean mostraendepedido;
    Long taxaimpostolucro;
    Boolean alteraprecopgto;
    Long menucereais;
    String natentradapadrao;
    Boolean bloquearcondicional;
    Boolean abrirnfcevenda;
    Boolean movimentanotapedido;
    String licencacte;
    Long bancopadrao;
    Boolean boletoservico;
    String rntrcemperesa;
    String cpfmotorista;
    String nomemotorista;
    Boolean mostraprodutocomquantidade;
    Boolean acumularvendasinicio;
    Boolean naosomarquantinfe;
    Boolean imprimircapacarne;
    Long porcentagemavista;
    Long porcentagemprazo;
    String versaosistemaconnect;
    Boolean recalcularlucro;
    Boolean exibirmarcalista;
    Boolean filtralistapreco;
    Boolean bloquearclientecpf;
    Boolean usarvalormediocompra;
    Boolean enviarinformacoelivro;
    String numeroseapa;
    Boolean exibiroriginallista;
    Boolean ocultarprateleiralista;
    Boolean exibircodigofabricante;
    Boolean exibirdadoszanatta;
    Boolean exibirselosetiquetas;
    Boolean confirmarimpressaonfce;
    Boolean exibirdadospadrao;
    Boolean utilizarsalvamentoauto;
    Boolean exibirdadosfoco;
    Boolean emitirncfepedidosempre;
    Boolean exibirdadosbenevenuto;
    Boolean exibirdadoslaquent;
    String codigomovimento;
    Boolean exibirdadosalencastro;
    Boolean mantervalormaiorentrada;
    Boolean exibirdadosclauciane;
    Long bancopadraoentrada;
    Long profissaopadrao;
    Long pgtopadrao;
    Boolean exibirdadostruck;
    Boolean emitedanfea4;
    Boolean exibirdadoscrol;
    String formatadecimais;
    Boolean buscarvendedorcliente;
    Boolean exibirdadosstarfrio;
    Boolean exibirdadosedificare;
    Boolean pedidopallun;
    Boolean importaricmsentrada;
    Boolean duasviasnfceprazo;
    Boolean bloquearvendanegativa;
    Boolean exibirdadosidentita;
    String grupopadraoentrada;
    Boolean exibirdadoszepel;
    String contacaixapadraoentrada;
    Boolean exibirdataentrega;
    Boolean chamaimportaarquivos;
    Boolean exibirdadoskadini;
    Boolean exibirdadoselizandra;
    String emailextranotas;
    Long quantidadefixapedido;
    Boolean exibirdadosmannes;
    Boolean exibirdadosagrocampo;
    Boolean recalcularparcelanfe;
    Boolean exibirdadoslubritap;
    Boolean exibirdadosalger;
    Boolean visualizarboleto;
    Boolean exibirdadosstilo;
    Boolean exibirdadosgasparin;
    Boolean exibirdadosmarcolin;
    String nomecertificadonfse;
    Long aliquotaiss;
    String senhadesconto;
    String senhaparcela;
    String senhaproduto;

    public Long getCodigoemitente() {
        return codigoemitente;
    }

    public void setCodigoemitente(Long codigoemitente) {
        this.codigoemitente = codigoemitente;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCnpjemi() {
        return cnpjemi;
    }

    public void setCnpjemi(String cnpjemi) {
        this.cnpjemi = cnpjemi;
    }

    public String getCpfemi() {
        return cpfemi;
    }

    public void setCpfemi(String cpfemi) {
        this.cpfemi = cpfemi;
    }

    public String getEmitente() {
        return emitente;
    }

    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodmuni() {
        return codmuni;
    }

    public void setCodmuni(String codmuni) {
        this.codmuni = codmuni;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCodpais() {
        return codpais;
    }

    public void setCodpais(String codpais) {
        this.codpais = codpais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getIeemi() {
        return ieemi;
    }

    public void setIeemi(String ieemi) {
        this.ieemi = ieemi;
    }

    public String getIesub() {
        return iesub;
    }

    public void setIesub(String iesub) {
        this.iesub = iesub;
    }

    public String getImemi() {
        return imemi;
    }

    public void setImemi(String imemi) {
        this.imemi = imemi;
    }

    public String getCnaeemi() {
        return cnaeemi;
    }

    public void setCnaeemi(String cnaeemi) {
        this.cnaeemi = cnaeemi;
    }

    public String getTributa() {
        return tributa;
    }

    public void setTributa(String tributa) {
        this.tributa = tributa;
    }

    public String getCertiemi() {
        return certiemi;
    }

    public void setCertiemi(String certiemi) {
        this.certiemi = certiemi;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    public String getHambiente() {
        return hambiente;
    }

    public void setHambiente(String hambiente) {
        this.hambiente = hambiente;
    }

    public String getEmailemi() {
        return emailemi;
    }

    public void setEmailemi(String emailemi) {
        this.emailemi = emailemi;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Long getPorta() {
        return porta;
    }

    public void setPorta(Long porta) {
        this.porta = porta;
    }

    public Long getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(Long autenticacao) {
        this.autenticacao = autenticacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getCopiadanfe() {
        return copiadanfe;
    }

    public void setCopiadanfe(Long copiadanfe) {
        this.copiadanfe = copiadanfe;
    }

    public String getCsosn() {
        return csosn;
    }

    public void setCsosn(String csosn) {
        this.csosn = csosn;
    }

    public Double getPerapro() {
        return perapro;
    }

    public void setPerapro(Double perapro) {
        this.perapro = perapro;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getModulo() {
        return modulo;
    }

    public void setModulo(Long modulo) {
        this.modulo = modulo;
    }

    public Long getCupomnfe() {
        return cupomnfe;
    }

    public void setCupomnfe(Long cupomnfe) {
        this.cupomnfe = cupomnfe;
    }

    public String getPortaimpre() {
        return portaimpre;
    }

    public void setPortaimpre(String portaimpre) {
        this.portaimpre = portaimpre;
    }

    public String getNporta() {
        return nporta;
    }

    public void setNporta(String nporta) {
        this.nporta = nporta;
    }

    public Boolean getDuplicata() {
        return duplicata;
    }

    public void setDuplicata(Boolean duplicata) {
        this.duplicata = duplicata;
    }

    public String getEmailcontador() {
        return emailcontador;
    }

    public void setEmailcontador(String emailcontador) {
        this.emailcontador = emailcontador;
    }

    public Boolean getMecanica() {
        return mecanica;
    }

    public void setMecanica(Boolean mecanica) {
        this.mecanica = mecanica;
    }

    public String getDadosobs() {
        return dadosobs;
    }

    public void setDadosobs(String dadosobs) {
        this.dadosobs = dadosobs;
    }

    public Boolean getDescpedidopor() {
        return descpedidopor;
    }

    public void setDescpedidopor(Boolean descpedidopor) {
        this.descpedidopor = descpedidopor;
    }

    public Boolean getDescprodutopor() {
        return descprodutopor;
    }

    public void setDescprodutopor(Boolean descprodutopor) {
        this.descprodutopor = descprodutopor;
    }

    public Boolean getTextologo() {
        return textologo;
    }

    public void setTextologo(Boolean textologo) {
        this.textologo = textologo;
    }

    public Boolean getCalprodutosimples() {
        return calprodutosimples;
    }

    public void setCalprodutosimples(Boolean calprodutosimples) {
        this.calprodutosimples = calprodutosimples;
    }

    public Boolean getPedidoean() {
        return pedidoean;
    }

    public void setPedidoean(Boolean pedidoean) {
        this.pedidoean = pedidoean;
    }

    public Boolean getListainicio() {
        return listainicio;
    }

    public void setListainicio(Boolean listainicio) {
        this.listainicio = listainicio;
    }

    public String getTipoimpfiscal() {
        return tipoimpfiscal;
    }

    public void setTipoimpfiscal(String tipoimpfiscal) {
        this.tipoimpfiscal = tipoimpfiscal;
    }

    public Boolean getGeracodigogrupo() {
        return geracodigogrupo;
    }

    public void setGeracodigogrupo(Boolean geracodigogrupo) {
        this.geracodigogrupo = geracodigogrupo;
    }

    public Boolean getIpipeso() {
        return ipipeso;
    }

    public void setIpipeso(Boolean ipipeso) {
        this.ipipeso = ipipeso;
    }

    public Boolean getUsaleitor() {
        return usaleitor;
    }

    public void setUsaleitor(Boolean usaleitor) {
        this.usaleitor = usaleitor;
    }

    public Boolean getCalculajuro() {
        return calculajuro;
    }

    public void setCalculajuro(Boolean calculajuro) {
        this.calculajuro = calculajuro;
    }

    public Boolean getCarne() {
        return carne;
    }

    public void setCarne(Boolean carne) {
        this.carne = carne;
    }

    public Long getNumerocaracter() {
        return numerocaracter;
    }

    public void setNumerocaracter(Long numerocaracter) {
        this.numerocaracter = numerocaracter;
    }

    public Boolean getEmite() {
        return emite;
    }

    public void setEmite(Boolean emite) {
        this.emite = emite;
    }

    public Boolean getPedisimples() {
        return pedisimples;
    }

    public void setPedisimples(Boolean pedisimples) {
        this.pedisimples = pedisimples;
    }

    public Boolean  getEntradaresumida() {
        return entradaresumida;
    }

    public void setEntradaresumida(Boolean entradaresumida) {
        this.entradaresumida = entradaresumida;
    }

    public String getTipoetiqueta() {
        return tipoetiqueta;
    }

    public void setTipoetiqueta(String tipoetiqueta) {
        this.tipoetiqueta = tipoetiqueta;
    }

    public Boolean getIndustria() {
        return industria;
    }

    public void setIndustria(Boolean industria) {
        this.industria = industria;
    }

    public Boolean getMostratamanho() {
        return mostratamanho;
    }

    public void setMostratamanho(Boolean mostratamanho) {
        this.mostratamanho = mostratamanho;
    }

    public Boolean getPlacacf() {
        return placacf;
    }

    public void setPlacacf(Boolean placacf) {
        this.placacf = placacf;
    }

    public Boolean getRecibofiscal() {
        return recibofiscal;
    }

    public void setRecibofiscal(Boolean recibofiscal) {
        this.recibofiscal = recibofiscal;
    }

    public Boolean getPrecopedido() {
        return precopedido;
    }

    public void setPrecopedido(Boolean precopedido) {
        this.precopedido = precopedido;
    }

    public Boolean getPedidofiscal() {
        return pedidofiscal;
    }

    public void setPedidofiscal(Boolean pedidofiscal) {
        this.pedidofiscal = pedidofiscal;
    }

    public Boolean getServicocupom() {
        return servicocupom;
    }

    public void setServicocupom(Boolean servicocupom) {
        this.servicocupom = servicocupom;
    }

    public Long getMargeminferior() {
        return margeminferior;
    }

    public void setMargeminferior(Long margeminferior) {
        this.margeminferior = margeminferior;
    }

    public Boolean getMostrarservico() {
        return mostrarservico;
    }

    public void setMostrarservico(Boolean mostrarservico) {
        this.mostrarservico = mostrarservico;
    }

    public Boolean getOrgaestoque() {
        return orgaestoque;
    }

    public void setOrgaestoque(Boolean orgaestoque) {
        this.orgaestoque = orgaestoque;
    }

    public Boolean getLiberadatare() {
        return liberadatare;
    }

    public void setLiberadatare(Boolean liberadatare) {
        this.liberadatare = liberadatare;
    }

    public Boolean getEmitentepadrao() {
        return emitentepadrao;
    }

    public void setEmitentepadrao(Boolean emitentepadrao) {
        this.emitentepadrao = emitentepadrao;
    }

    public Boolean getEmitentenfce() {
        return emitentenfce;
    }

    public void setEmitentenfce(Boolean emitentenfce) {
        this.emitentenfce = emitentenfce;
    }

    public String getTokennfce() {
        return tokennfce;
    }

    public void setTokennfce(String tokennfce) {
        this.tokennfce = tokennfce;
    }

    public String getPincerti() {
        return pincerti;
    }

    public void setPincerti(String pincerti) {
        this.pincerti = pincerti;
    }

    public Boolean getLiberacaixa() {
        return liberacaixa;
    }

    public void setLiberacaixa(Boolean liberacaixa) {
        this.liberacaixa = liberacaixa;
    }

    public Long getViarecibo() {
        return viarecibo;
    }

    public void setViarecibo(Long viarecibo) {
        this.viarecibo = viarecibo;
    }

    public Long getViaduplicata() {
        return viaduplicata;
    }

    public void setViaduplicata(Long viaduplicata) {
        this.viaduplicata = viaduplicata;
    }

    public String getCodcedente() {
        return codcedente;
    }

    public void setCodcedente(String codcedente) {
        this.codcedente = codcedente;
    }

    public Long getCodpraca() {
        return codpraca;
    }

    public void setCodpraca(Long codpraca) {
        this.codpraca = codpraca;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getUnidadeatendimento() {
        return unidadeatendimento;
    }

    public void setUnidadeatendimento(String unidadeatendimento) {
        this.unidadeatendimento = unidadeatendimento;
    }

    public String getNbanco() {
        return nbanco;
    }

    public void setNbanco(String nbanco) {
        this.nbanco = nbanco;
    }

    public String getNomebanco() {
        return nomebanco;
    }

    public void setNomebanco(String nomebanco) {
        this.nomebanco = nomebanco;
    }

    public Long getCodcanaltransmissao() {
        return codcanaltransmissao;
    }

    public void setCodcanaltransmissao(Long codcanaltransmissao) {
        this.codcanaltransmissao = codcanaltransmissao;
    }

    public String getCodcarteira() {
        return codcarteira;
    }

    public void setCodcarteira(String codcarteira) {
        this.codcarteira = codcarteira;
    }

    public String getTipoimpressaoboleto() {
        return tipoimpressaoboleto;
    }

    public void setTipoimpressaoboleto(String tipoimpressaoboleto) {
        this.tipoimpressaoboleto = tipoimpressaoboleto;
    }

    public Boolean getPostartitulo() {
        return postartitulo;
    }

    public void setPostartitulo(Boolean postartitulo) {
        this.postartitulo = postartitulo;
    }

    public String getEmissaoboleto() {
        return emissaoboleto;
    }

    public void setEmissaoboleto(String emissaoboleto) {
        this.emissaoboleto = emissaoboleto;
    }

    public String getDiasprotesto() {
        return diasprotesto;
    }

    public void setDiasprotesto(String diasprotesto) {
        this.diasprotesto = diasprotesto;
    }

    public Double getJuropordiaatrazo() {
        return juropordiaatrazo;
    }

    public void setJuropordiaatrazo(Double juropordiaatrazo) {
        this.juropordiaatrazo = juropordiaatrazo;
    }

    public Double getDescontoboleto() {
        return descontoboleto;
    }

    public void setDescontoboleto(Double descontoboleto) {
        this.descontoboleto = descontoboleto;
    }

    public String getTipodesconto() {
        return tipodesconto;
    }

    public void setTipodesconto(String tipodesconto) {
        this.tipodesconto = tipodesconto;
    }

    public String getTipojuro() {
        return tipojuro;
    }

    public void setTipojuro(String tipojuro) {
        this.tipojuro = tipojuro;
    }

    public String getPostoagencia() {
        return postoagencia;
    }

    public void setPostoagencia(String postoagencia) {
        this.postoagencia = postoagencia;
    }

    public Long getJuromes() {
        return juromes;
    }

    public void setJuromes(Long juromes) {
        this.juromes = juromes;
    }

    public String getVersaosistema() {
        return versaosistema;
    }

    public void setVersaosistema(String versaosistema) {
        this.versaosistema = versaosistema;
    }

    public Long getClientepedido() {
        return clientepedido;
    }

    public void setClientepedido(Long clientepedido) {
        this.clientepedido = clientepedido;
    }

    public String getFusohorario() {
        return fusohorario;
    }

    public void setFusohorario(String fusohorario) {
        this.fusohorario = fusohorario;
    }

    public Long getNumeparcela() {
        return numeparcela;
    }

    public void setNumeparcela(Long numeparcela) {
        this.numeparcela = numeparcela;
    }

    public Boolean getNaolancacaixa() {
        return naolancacaixa;
    }

    public void setNaolancacaixa(Boolean naolancacaixa) {
        this.naolancacaixa = naolancacaixa;
    }

    public Boolean getDadosimpressora() {
        return dadosimpressora;
    }

    public void setDadosimpressora(Boolean dadosimpressora) {
        this.dadosimpressora = dadosimpressora;
    }

    public Boolean getProdutocomprar() {
        return produtocomprar;
    }

    public void setProdutocomprar(Boolean produtocomprar) {
        this.produtocomprar = produtocomprar;
    }

    public Boolean getMostrafunrural() {
        return mostrafunrural;
    }

    public void setMostrafunrural(Boolean mostrafunrural) {
        this.mostrafunrural = mostrafunrural;
    }

    public String getCodhistorico() {
        return codhistorico;
    }

    public void setCodhistorico(String codhistorico) {
        this.codhistorico = codhistorico;
    }

    public Long getLemitedesconto() {
        return lemitedesconto;
    }

    public void setLemitedesconto(Long lemitedesconto) {
        this.lemitedesconto = lemitedesconto;
    }

    public Long getLimitedesconto() {
        return limitedesconto;
    }

    public void setLimitedesconto(Long limitedesconto) {
        this.limitedesconto = limitedesconto;
    }

    public Boolean getAlterarvalorunitario() {
        return alterarvalorunitario;
    }

    public void setAlterarvalorunitario(Boolean alterarvalorunitario) {
        this.alterarvalorunitario = alterarvalorunitario;
    }

    public Boolean getImprimeobs020() {
        return imprimeobs020;
    }

    public void setImprimeobs020(Boolean imprimeobs020) {
        this.imprimeobs020 = imprimeobs020;
    }

    public String getSenhareceber() {
        return senhareceber;
    }

    public void setSenhareceber(String senhareceber) {
        this.senhareceber = senhareceber;
    }

    public Boolean getEmitentelaticinio() {
        return emitentelaticinio;
    }

    public void setEmitentelaticinio(Boolean emitentelaticinio) {
        this.emitentelaticinio = emitentelaticinio;
    }

    public Boolean getBloqueianota() {
        return bloqueianota;
    }

    public void setBloqueianota(Boolean bloqueianota) {
        this.bloqueianota = bloqueianota;
    }

    public Boolean getHoracaixa() {
        return horacaixa;
    }

    public void setHoracaixa(Boolean horacaixa) {
        this.horacaixa = horacaixa;
    }

    public Boolean getAto() {
        return ato;
    }

    public void setAto(Boolean ato) {
        this.ato = ato;
    }

    public Boolean getXml() {
        return xml;
    }

    public void setXml(Boolean xml) {
        this.xml = xml;
    }

    public String getEmaildados() {
        return emaildados;
    }

    public void setEmaildados(String emaildados) {
        this.emaildados = emaildados;
    }

    public Boolean getDadospneus() {
        return dadospneus;
    }

    public void setDadospneus(Boolean dadospneus) {
        this.dadospneus = dadospneus;
    }

    public Boolean getProdutocomposto() {
        return produtocomposto;
    }

    public void setProdutocomposto(Boolean produtocomposto) {
        this.produtocomposto = produtocomposto;
    }

    public Boolean getPermitirestorno() {
        return permitirestorno;
    }

    public void setPermitirestorno(Boolean permitirestorno) {
        this.permitirestorno = permitirestorno;
    }

    public Long getDiastravamento() {
        return diastravamento;
    }

    public void setDiastravamento(Long diastravamento) {
        this.diastravamento = diastravamento;
    }

    public Boolean getEmitesistemas() {
        return emitesistemas;
    }

    public void setEmitesistemas(Boolean emitesistemas) {
        this.emitesistemas = emitesistemas;
    }

    public Boolean getEmitecapaboleto() {
        return emitecapaboleto;
    }

    public void setEmitecapaboleto(Boolean emitecapaboleto) {
        this.emitecapaboleto = emitecapaboleto;
    }

    public String getMensagemboleto() {
        return mensagemboleto;
    }

    public void setMensagemboleto(String mensagemboleto) {
        this.mensagemboleto = mensagemboleto;
    }

    public String getNotaservico() {
        return notaservico;
    }

    public void setNotaservico(String notaservico) {
        this.notaservico = notaservico;
    }

    public Boolean getNotaservicopm() {
        return notaservicopm;
    }

    public void setNotaservicopm(Boolean notaservicopm) {
        this.notaservicopm = notaservicopm;
    }

    public Boolean getDuplicatapedido() {
        return duplicatapedido;
    }

    public void setDuplicatapedido(Boolean duplicatapedido) {
        this.duplicatapedido = duplicatapedido;
    }

    public String getProtestoautomatico() {
        return protestoautomatico;
    }

    public void setProtestoautomatico(String protestoautomatico) {
        this.protestoautomatico = protestoautomatico;
    }

    public Boolean getMensagempedido() {
        return mensagempedido;
    }

    public void setMensagempedido(Boolean mensagempedido) {
        this.mensagempedido = mensagempedido;
    }

    public Boolean getEntradacodigoforne() {
        return entradacodigoforne;
    }

    public void setEntradacodigoforne(Boolean entradacodigoforne) {
        this.entradacodigoforne = entradacodigoforne;
    }

    public Boolean getBloqueiamovimentacao() {
        return bloqueiamovimentacao;
    }

    public void setBloqueiamovimentacao(Boolean bloqueiamovimentacao) {
        this.bloqueiamovimentacao = bloqueiamovimentacao;
    }

    public Boolean getDatasatrazadascaixa() {
        return datasatrazadascaixa;
    }

    public void setDatasatrazadascaixa(Boolean datasatrazadascaixa) {
        this.datasatrazadascaixa = datasatrazadascaixa;
    }

    public Boolean getChequebaixadata() {
        return chequebaixadata;
    }

    public void setChequebaixadata(Boolean chequebaixadata) {
        this.chequebaixadata = chequebaixadata;
    }

    public Long getCodigoboleto() {
        return codigoboleto;
    }

    public void setCodigoboleto(Long codigoboleto) {
        this.codigoboleto = codigoboleto;
    }

    public Boolean getExibiraparelho() {
        return exibiraparelho;
    }

    public void setExibiraparelho(Boolean exibiraparelho) {
        this.exibiraparelho = exibiraparelho;
    }

    public Boolean getDuplicatabematech() {
        return duplicatabematech;
    }

    public void setDuplicatabematech(Boolean duplicatabematech) {
        this.duplicatabematech = duplicatabematech;
    }

    public Boolean getMostrarcustolista() {
        return mostrarcustolista;
    }

    public void setMostrarcustolista(Boolean mostrarcustolista) {
        this.mostrarcustolista = mostrarcustolista;
    }

    public Boolean getListadeprecozero() {
        return listadeprecozero;
    }

    public void setListadeprecozero(Boolean listadeprecozero) {
        this.listadeprecozero = listadeprecozero;
    }

    public Boolean getExibirlogo() {
        return exibirlogo;
    }

    public void setExibirlogo(Boolean exibirlogo) {
        this.exibirlogo = exibirlogo;
    }

    public Long getAnobase() {
        return anobase;
    }

    public void setAnobase(Long anobase) {
        this.anobase = anobase;
    }

    public Boolean getUtilizarmarkup() {
        return utilizarmarkup;
    }

    public void setUtilizarmarkup(Boolean utilizarmarkup) {
        this.utilizarmarkup = utilizarmarkup;
    }

    public Boolean getAredondavenda() {
        return aredondavenda;
    }

    public void setAredondavenda(Boolean aredondavenda) {
        this.aredondavenda = aredondavenda;
    }

    public Boolean getLancaapenascontaspagarentrada() {
        return lancaapenascontaspagarentrada;
    }

    public void setLancaapenascontaspagarentrada(Boolean lancaapenascontaspagarentrada) {
        this.lancaapenascontaspagarentrada = lancaapenascontaspagarentrada;
    }

    public Boolean getCarneboleto() {
        return carneboleto;
    }

    public void setCarneboleto(Boolean carneboleto) {
        this.carneboleto = carneboleto;
    }

    public Boolean getLancavalorentrada() {
        return lancavalorentrada;
    }

    public void setLancavalorentrada(Boolean lancavalorentrada) {
        this.lancavalorentrada = lancavalorentrada;
    }

    public Boolean getImportaentradaean() {
        return importaentradaean;
    }

    public void setImportaentradaean(Boolean importaentradaean) {
        this.importaentradaean = importaentradaean;
    }

    public Double getValorfretepedido() {
        return valorfretepedido;
    }

    public void setValorfretepedido(Double valorfretepedido) {
        this.valorfretepedido = valorfretepedido;
    }

    public Boolean getPedidopoleto() {
        return pedidopoleto;
    }

    public void setPedidopoleto(Boolean pedidopoleto) {
        this.pedidopoleto = pedidopoleto;
    }

    public String getContacaixacheque() {
        return contacaixacheque;
    }

    public void setContacaixacheque(String contacaixacheque) {
        this.contacaixacheque = contacaixacheque;
    }

    public String getContacaixaveiculos() {
        return contacaixaveiculos;
    }

    public void setContacaixaveiculos(String contacaixaveiculos) {
        this.contacaixaveiculos = contacaixaveiculos;
    }

    public Long getTaxacelic() {
        return taxacelic;
    }

    public void setTaxacelic(Long taxacelic) {
        this.taxacelic = taxacelic;
    }

    public Boolean getPedidoautoprazo() {
        return pedidoautoprazo;
    }

    public void setPedidoautoprazo(Boolean pedidoautoprazo) {
        this.pedidoautoprazo = pedidoautoprazo;
    }

    public Boolean getUsarbarrabalanca() {
        return usarbarrabalanca;
    }

    public void setUsarbarrabalanca(Boolean usarbarrabalanca) {
        this.usarbarrabalanca = usarbarrabalanca;
    }

    public String getLicencamdfe() {
        return licencamdfe;
    }

    public void setLicencamdfe(String licencamdfe) {
        this.licencamdfe = licencamdfe;
    }

    public String getVersaomdfe() {
        return versaomdfe;
    }

    public void setVersaomdfe(String versaomdfe) {
        this.versaomdfe = versaomdfe;
    }

    public String getNumerocopiacfe() {
        return numerocopiacfe;
    }

    public void setNumerocopiacfe(String numerocopiacfe) {
        this.numerocopiacfe = numerocopiacfe;
    }

    public Boolean getBaixacomposicaosaida() {
        return baixacomposicaosaida;
    }

    public void setBaixacomposicaosaida(Boolean baixacomposicaosaida) {
        this.baixacomposicaosaida = baixacomposicaosaida;
    }

    public String getVendedorpadrao() {
        return vendedorpadrao;
    }

    public void setVendedorpadrao(String vendedorpadrao) {
        this.vendedorpadrao = vendedorpadrao;
    }

    public Boolean getOcultardadosemitente() {
        return ocultardadosemitente;
    }

    public void setOcultardadosemitente(Boolean ocultardadosemitente) {
        this.ocultardadosemitente = ocultardadosemitente;
    }

    public Long getTipodeentrada() {
        return tipodeentrada;
    }

    public void setTipodeentrada(Long tipodeentrada) {
        this.tipodeentrada = tipodeentrada;
    }

    public Boolean getOcultaminimo() {
        return ocultaminimo;
    }

    public void setOcultaminimo(Boolean ocultaminimo) {
        this.ocultaminimo = ocultaminimo;
    }

    public Boolean getFormatacodigo() {
        return formatacodigo;
    }

    public void setFormatacodigo(Boolean formatacodigo) {
        this.formatacodigo = formatacodigo;
    }

    public Long getNumerocopiapedido() {
        return numerocopiapedido;
    }

    public void setNumerocopiapedido(Long numerocopiapedido) {
        this.numerocopiapedido = numerocopiapedido;
    }

    public Boolean getOcultarlogo() {
        return ocultarlogo;
    }

    public void setOcultarlogo(Boolean ocultarlogo) {
        this.ocultarlogo = ocultarlogo;
    }

    public Boolean getLiberartotalprodupedido() {
        return liberartotalprodupedido;
    }

    public void setLiberartotalprodupedido(Boolean liberartotalprodupedido) {
        this.liberartotalprodupedido = liberartotalprodupedido;
    }

    public Boolean getMostraendepedido() {
        return mostraendepedido;
    }

    public void setMostraendepedido(Boolean mostraendepedido) {
        this.mostraendepedido = mostraendepedido;
    }

    public Long getTaxaimpostolucro() {
        return taxaimpostolucro;
    }

    public void setTaxaimpostolucro(Long taxaimpostolucro) {
        this.taxaimpostolucro = taxaimpostolucro;
    }

    public Boolean getAlteraprecopgto() {
        return alteraprecopgto;
    }

    public void setAlteraprecopgto(Boolean alteraprecopgto) {
        this.alteraprecopgto = alteraprecopgto;
    }

    public Long getMenucereais() {
        return menucereais;
    }

    public void setMenucereais(Long menucereais) {
        this.menucereais = menucereais;
    }

    public String getNatentradapadrao() {
        return natentradapadrao;
    }

    public void setNatentradapadrao(String natentradapadrao) {
        this.natentradapadrao = natentradapadrao;
    }

    public Boolean getBloquearcondicional() {
        return bloquearcondicional;
    }

    public void setBloquearcondicional(Boolean bloquearcondicional) {
        this.bloquearcondicional = bloquearcondicional;
    }

    public Boolean getAbrirnfcevenda() {
        return abrirnfcevenda;
    }

    public void setAbrirnfcevenda(Boolean abrirnfcevenda) {
        this.abrirnfcevenda = abrirnfcevenda;
    }

    public Boolean getMovimentanotapedido() {
        return movimentanotapedido;
    }

    public void setMovimentanotapedido(Boolean movimentanotapedido) {
        this.movimentanotapedido = movimentanotapedido;
    }

    public String getLicencacte() {
        return licencacte;
    }

    public void setLicencacte(String licencacte) {
        this.licencacte = licencacte;
    }

    public Long getBancopadrao() {
        return bancopadrao;
    }

    public void setBancopadrao(Long bancopadrao) {
        this.bancopadrao = bancopadrao;
    }

    public Boolean getBoletoservico() {
        return boletoservico;
    }

    public void setBoletoservico(Boolean boletoservico) {
        this.boletoservico = boletoservico;
    }

    public String getRntrcemperesa() {
        return rntrcemperesa;
    }

    public void setRntrcemperesa(String rntrcemperesa) {
        this.rntrcemperesa = rntrcemperesa;
    }

    public String getCpfmotorista() {
        return cpfmotorista;
    }

    public void setCpfmotorista(String cpfmotorista) {
        this.cpfmotorista = cpfmotorista;
    }

    public String getNomemotorista() {
        return nomemotorista;
    }

    public void setNomemotorista(String nomemotorista) {
        this.nomemotorista = nomemotorista;
    }

    public Boolean getMostraprodutocomquantidade() {
        return mostraprodutocomquantidade;
    }

    public void setMostraprodutocomquantidade(Boolean mostraprodutocomquantidade) {
        this.mostraprodutocomquantidade = mostraprodutocomquantidade;
    }

    public Boolean getAcumularvendasinicio() {
        return acumularvendasinicio;
    }

    public void setAcumularvendasinicio(Boolean acumularvendasinicio) {
        this.acumularvendasinicio = acumularvendasinicio;
    }

    public Boolean getNaosomarquantinfe() {
        return naosomarquantinfe;
    }

    public void setNaosomarquantinfe(Boolean naosomarquantinfe) {
        this.naosomarquantinfe = naosomarquantinfe;
    }

    public Boolean getImprimircapacarne() {
        return imprimircapacarne;
    }

    public void setImprimircapacarne(Boolean imprimircapacarne) {
        this.imprimircapacarne = imprimircapacarne;
    }

    public Long getPorcentagemavista() {
        return porcentagemavista;
    }

    public void setPorcentagemavista(Long porcentagemavista) {
        this.porcentagemavista = porcentagemavista;
    }

    public Long getPorcentagemprazo() {
        return porcentagemprazo;
    }

    public void setPorcentagemprazo(Long porcentagemprazo) {
        this.porcentagemprazo = porcentagemprazo;
    }

    public String getVersaosistemaconnect() {
        return versaosistemaconnect;
    }

    public void setVersaosistemaconnect(String versaosistemaconnect) {
        this.versaosistemaconnect = versaosistemaconnect;
    }

    public Boolean getRecalcularlucro() {
        return recalcularlucro;
    }

    public void setRecalcularlucro(Boolean recalcularlucro) {
        this.recalcularlucro = recalcularlucro;
    }

    public Boolean getExibirmarcalista() {
        return exibirmarcalista;
    }

    public void setExibirmarcalista(Boolean exibirmarcalista) {
        this.exibirmarcalista = exibirmarcalista;
    }

    public Boolean getFiltralistapreco() {
        return filtralistapreco;
    }

    public void setFiltralistapreco(Boolean filtralistapreco) {
        this.filtralistapreco = filtralistapreco;
    }

    public Boolean getBloquearclientecpf() {
        return bloquearclientecpf;
    }

    public void setBloquearclientecpf(Boolean bloquearclientecpf) {
        this.bloquearclientecpf = bloquearclientecpf;
    }

    public Boolean getUsarvalormediocompra() {
        return usarvalormediocompra;
    }

    public void setUsarvalormediocompra(Boolean usarvalormediocompra) {
        this.usarvalormediocompra = usarvalormediocompra;
    }

    public Boolean getEnviarinformacoelivro() {
        return enviarinformacoelivro;
    }

    public void setEnviarinformacoelivro(Boolean enviarinformacoelivro) {
        this.enviarinformacoelivro = enviarinformacoelivro;
    }

    public String getNumeroseapa() {
        return numeroseapa;
    }

    public void setNumeroseapa(String numeroseapa) {
        this.numeroseapa = numeroseapa;
    }

    public Boolean getExibiroriginallista() {
        return exibiroriginallista;
    }

    public void setExibiroriginallista(Boolean exibiroriginallista) {
        this.exibiroriginallista = exibiroriginallista;
    }

    public Boolean getOcultarprateleiralista() {
        return ocultarprateleiralista;
    }

    public void setOcultarprateleiralista(Boolean ocultarprateleiralista) {
        this.ocultarprateleiralista = ocultarprateleiralista;
    }

    public Boolean getExibircodigofabricante() {
        return exibircodigofabricante;
    }

    public void setExibircodigofabricante(Boolean exibircodigofabricante) {
        this.exibircodigofabricante = exibircodigofabricante;
    }

    public Boolean getExibirdadoszanatta() {
        return exibirdadoszanatta;
    }

    public void setExibirdadoszanatta(Boolean exibirdadoszanatta) {
        this.exibirdadoszanatta = exibirdadoszanatta;
    }

    public Boolean getExibirselosetiquetas() {
        return exibirselosetiquetas;
    }

    public void setExibirselosetiquetas(Boolean exibirselosetiquetas) {
        this.exibirselosetiquetas = exibirselosetiquetas;
    }

    public Boolean getConfirmarimpressaonfce() {
        return confirmarimpressaonfce;
    }

    public void setConfirmarimpressaonfce(Boolean confirmarimpressaonfce) {
        this.confirmarimpressaonfce = confirmarimpressaonfce;
    }

    public Boolean getExibirdadospadrao() {
        return exibirdadospadrao;
    }

    public void setExibirdadospadrao(Boolean exibirdadospadrao) {
        this.exibirdadospadrao = exibirdadospadrao;
    }

    public Boolean getUtilizarsalvamentoauto() {
        return utilizarsalvamentoauto;
    }

    public void setUtilizarsalvamentoauto(Boolean utilizarsalvamentoauto) {
        this.utilizarsalvamentoauto = utilizarsalvamentoauto;
    }

    public Boolean getExibirdadosfoco() {
        return exibirdadosfoco;
    }

    public void setExibirdadosfoco(Boolean exibirdadosfoco) {
        this.exibirdadosfoco = exibirdadosfoco;
    }

    public Boolean getEmitirncfepedidosempre() {
        return emitirncfepedidosempre;
    }

    public void setEmitirncfepedidosempre(Boolean emitirncfepedidosempre) {
        this.emitirncfepedidosempre = emitirncfepedidosempre;
    }

    public Boolean getExibirdadosbenevenuto() {
        return exibirdadosbenevenuto;
    }

    public void setExibirdadosbenevenuto(Boolean exibirdadosbenevenuto) {
        this.exibirdadosbenevenuto = exibirdadosbenevenuto;
    }

    public Boolean getExibirdadoslaquent() {
        return exibirdadoslaquent;
    }

    public void setExibirdadoslaquent(Boolean exibirdadoslaquent) {
        this.exibirdadoslaquent = exibirdadoslaquent;
    }

    public String getCodigomovimento() {
        return codigomovimento;
    }

    public void setCodigomovimento(String codigomovimento) {
        this.codigomovimento = codigomovimento;
    }

    public Boolean getExibirdadosalencastro() {
        return exibirdadosalencastro;
    }

    public void setExibirdadosalencastro(Boolean exibirdadosalencastro) {
        this.exibirdadosalencastro = exibirdadosalencastro;
    }

    public Boolean getMantervalormaiorentrada() {
        return mantervalormaiorentrada;
    }

    public void setMantervalormaiorentrada(Boolean mantervalormaiorentrada) {
        this.mantervalormaiorentrada = mantervalormaiorentrada;
    }

    public Boolean getExibirdadosclauciane() {
        return exibirdadosclauciane;
    }

    public void setExibirdadosclauciane(Boolean exibirdadosclauciane) {
        this.exibirdadosclauciane = exibirdadosclauciane;
    }

    public Long getBancopadraoentrada() {
        return bancopadraoentrada;
    }

    public void setBancopadraoentrada(Long bancopadraoentrada) {
        this.bancopadraoentrada = bancopadraoentrada;
    }

    public Long getProfissaopadrao() {
        return profissaopadrao;
    }

    public void setProfissaopadrao(Long profissaopadrao) {
        this.profissaopadrao = profissaopadrao;
    }

    public Long getPgtopadrao() {
        return pgtopadrao;
    }

    public void setPgtopadrao(Long pgtopadrao) {
        this.pgtopadrao = pgtopadrao;
    }

    public Boolean getExibirdadostruck() {
        return exibirdadostruck;
    }

    public void setExibirdadostruck(Boolean exibirdadostruck) {
        this.exibirdadostruck = exibirdadostruck;
    }

    public Boolean getEmitedanfea4() {
        return emitedanfea4;
    }

    public void setEmitedanfea4(Boolean emitedanfea4) {
        this.emitedanfea4 = emitedanfea4;
    }

    public Boolean getExibirdadoscrol() {
        return exibirdadoscrol;
    }

    public void setExibirdadoscrol(Boolean exibirdadoscrol) {
        this.exibirdadoscrol = exibirdadoscrol;
    }

    public String getFormatadecimais() {
        return formatadecimais;
    }

    public void setFormatadecimais(String formatadecimais) {
        this.formatadecimais = formatadecimais;
    }

    public Boolean getBuscarvendedorcliente() {
        return buscarvendedorcliente;
    }

    public void setBuscarvendedorcliente(Boolean buscarvendedorcliente) {
        this.buscarvendedorcliente = buscarvendedorcliente;
    }

    public Boolean getExibirdadosstarfrio() {
        return exibirdadosstarfrio;
    }

    public void setExibirdadosstarfrio(Boolean exibirdadosstarfrio) {
        this.exibirdadosstarfrio = exibirdadosstarfrio;
    }

    public Boolean getExibirdadosedificare() {
        return exibirdadosedificare;
    }

    public void setExibirdadosedificare(Boolean exibirdadosedificare) {
        this.exibirdadosedificare = exibirdadosedificare;
    }

    public Boolean getPedidopallun() {
        return pedidopallun;
    }

    public void setPedidopallun(Boolean pedidopallun) {
        this.pedidopallun = pedidopallun;
    }

    public Boolean getImportaricmsentrada() {
        return importaricmsentrada;
    }

    public void setImportaricmsentrada(Boolean importaricmsentrada) {
        this.importaricmsentrada = importaricmsentrada;
    }

    public Boolean getDuasviasnfceprazo() {
        return duasviasnfceprazo;
    }

    public void setDuasviasnfceprazo(Boolean duasviasnfceprazo) {
        this.duasviasnfceprazo = duasviasnfceprazo;
    }

    public Boolean getBloquearvendanegativa() {
        return bloquearvendanegativa;
    }

    public void setBloquearvendanegativa(Boolean bloquearvendanegativa) {
        this.bloquearvendanegativa = bloquearvendanegativa;
    }

    public Boolean getExibirdadosidentita() {
        return exibirdadosidentita;
    }

    public void setExibirdadosidentita(Boolean exibirdadosidentita) {
        this.exibirdadosidentita = exibirdadosidentita;
    }

    public String getGrupopadraoentrada() {
        return grupopadraoentrada;
    }

    public void setGrupopadraoentrada(String grupopadraoentrada) {
        this.grupopadraoentrada = grupopadraoentrada;
    }

    public Boolean getExibirdadoszepel() {
        return exibirdadoszepel;
    }

    public void setExibirdadoszepel(Boolean exibirdadoszepel) {
        this.exibirdadoszepel = exibirdadoszepel;
    }

    public String getContacaixapadraoentrada() {
        return contacaixapadraoentrada;
    }

    public void setContacaixapadraoentrada(String contacaixapadraoentrada) {
        this.contacaixapadraoentrada = contacaixapadraoentrada;
    }

    public Boolean getExibirdataentrega() {
        return exibirdataentrega;
    }

    public void setExibirdataentrega(Boolean exibirdataentrega) {
        this.exibirdataentrega = exibirdataentrega;
    }

    public Boolean getChamaimportaarquivos() {
        return chamaimportaarquivos;
    }

    public void setChamaimportaarquivos(Boolean chamaimportaarquivos) {
        this.chamaimportaarquivos = chamaimportaarquivos;
    }

    public Boolean getExibirdadoskadini() {
        return exibirdadoskadini;
    }

    public void setExibirdadoskadini(Boolean exibirdadoskadini) {
        this.exibirdadoskadini = exibirdadoskadini;
    }

    public Boolean getExibirdadoselizandra() {
        return exibirdadoselizandra;
    }

    public void setExibirdadoselizandra(Boolean exibirdadoselizandra) {
        this.exibirdadoselizandra = exibirdadoselizandra;
    }

    public String getEmailextranotas() {
        return emailextranotas;
    }

    public void setEmailextranotas(String emailextranotas) {
        this.emailextranotas = emailextranotas;
    }

    public Long getQuantidadefixapedido() {
        return quantidadefixapedido;
    }

    public void setQuantidadefixapedido(Long quantidadefixapedido) {
        this.quantidadefixapedido = quantidadefixapedido;
    }

    public Boolean getExibirdadosmannes() {
        return exibirdadosmannes;
    }

    public void setExibirdadosmannes(Boolean exibirdadosmannes) {
        this.exibirdadosmannes = exibirdadosmannes;
    }

    public Boolean getExibirdadosagrocampo() {
        return exibirdadosagrocampo;
    }

    public void setExibirdadosagrocampo(Boolean exibirdadosagrocampo) {
        this.exibirdadosagrocampo = exibirdadosagrocampo;
    }

    public Boolean getRecalcularparcelanfe() {
        return recalcularparcelanfe;
    }

    public void setRecalcularparcelanfe(Boolean recalcularparcelanfe) {
        this.recalcularparcelanfe = recalcularparcelanfe;
    }

    public Boolean getExibirdadoslubritap() {
        return exibirdadoslubritap;
    }

    public void setExibirdadoslubritap(Boolean exibirdadoslubritap) {
        this.exibirdadoslubritap = exibirdadoslubritap;
    }

    public Boolean getExibirdadosalger() {
        return exibirdadosalger;
    }

    public void setExibirdadosalger(Boolean exibirdadosalger) {
        this.exibirdadosalger = exibirdadosalger;
    }

    public Boolean getVisualizarboleto() {
        return visualizarboleto;
    }

    public void setVisualizarboleto(Boolean visualizarboleto) {
        this.visualizarboleto = visualizarboleto;
    }

    public Boolean getExibirdadosstilo() {
        return exibirdadosstilo;
    }

    public void setExibirdadosstilo(Boolean exibirdadosstilo) {
        this.exibirdadosstilo = exibirdadosstilo;
    }

    public Boolean getExibirdadosgasparin() {
        return exibirdadosgasparin;
    }

    public void setExibirdadosgasparin(Boolean exibirdadosgasparin) {
        this.exibirdadosgasparin = exibirdadosgasparin;
    }

    public Boolean getExibirdadosmarcolin() {
        return exibirdadosmarcolin;
    }

    public void setExibirdadosmarcolin(Boolean exibirdadosmarcolin) {
        this.exibirdadosmarcolin = exibirdadosmarcolin;
    }

    public String getNomecertificadonfse() {
        return nomecertificadonfse;
    }

    public void setNomecertificadonfse(String nomecertificadonfse) {
        this.nomecertificadonfse = nomecertificadonfse;
    }

    public Long getAliquotaiss() {
        return aliquotaiss;
    }

    public void setAliquotaiss(Long aliquotaiss) {
        this.aliquotaiss = aliquotaiss;
    }

    public String getSenhadesconto() {
        return senhadesconto;
    }

    public void setSenhadesconto(String senhadesconto) {
        this.senhadesconto = senhadesconto;
    }

    public String getSenhaparcela() {
        return senhaparcela;
    }

    public void setSenhaparcela(String senhaparcela) {
        this.senhaparcela = senhaparcela;
    }

    public String getSenhaproduto() {
        return senhaproduto;
    }

    public void setSenhaproduto(String senhaproduto) {
        this.senhaproduto = senhaproduto;
    }

    public Cursor retornaEmitenteFiltradoCursor(Context context, Long codigoemitente) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM emitente where codigoemitente = " + codigoemitente, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Emitente retornaEmitenteObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        Emitente emitente = new Emitente();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM emitente where codigoemitente = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldList = new ArrayList<>(Arrays.asList(Emitente.class.getDeclaredFields()));

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
           emitente = new Emitente();
            for (int f = 0; fieldList.size() != f; f++) {
                String tipo = getSetDinamico.retornaTipoCampo(fieldList.get(f));
                String nomeCampo = fieldList.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retCliente = getSetDinamico.insereField(fieldList.get(f), emitente, retorno);
                    emitente = (Emitente) retCliente;
                }
            }
        }
        db.close();
        return emitente;

    }

    public Boolean cadastraEmitente(Context context, Emitente emitente) {
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(emitente.getClass().getDeclaredFields()));

        for (int i = 0; fieldList.size() != i; i++) {
            valores = dadosBanco.insereValoresContent(fieldList.get(i), emitente, valores);
        }

        if (valores.get("codigoemitente") == null) {
            db.insert("emitente", null, valores);
            db.close();
            valores.clear();
            return true;
        } else {
            Cursor cursor = emitente.retornaEmitenteFiltradoCursor(context, Long.parseLong(valores.get("codigoemitente").toString()));

            if (cursor.getCount() > 0) {
                long retorno = db.update("emitente", valores, "codigoemitente = " + valores.get("codigoemitente").toString(), null);
                db.close();
                valores.clear();
                return retorno != -1;
            } else {
                db.insert("emitente", null, valores);
                db.close();
                valores.clear();
                return true;
            }

        }
    }

}
