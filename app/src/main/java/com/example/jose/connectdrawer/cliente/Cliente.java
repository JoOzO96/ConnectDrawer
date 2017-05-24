package com.example.jose.connectdrawer.cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jose.connectdrawer.banco.Banco;

import java.util.Date;

/**
 * Created by Jose on 18/05/2017.
 */

public class Cliente {

    private Long codigo;
    private String nomeCliente;
    private String cpf;
    private Date dataNasc;
    private String endereco;
    private String posicao;
    private String pai;
    private String mae;
    private String bairro;
    private String cep;
    private String identidade;
    private String trabalho;
    private String telefone;
    private String fonetrab;
    private String cgc;
    private String incest;
    private String enderecotrab;
    private Long codProfissao;
    private Long codCidade;
    private String responsavel;
    private String fone;
    private String obs;
    private String nume;
    private String email;
    private String pessoaAuto;
    private Double limiteCredito;
    private String pessoaAuto1;
    private Double limiteCredito1;
    private String pessoaAuto2;
    private Double limiteCredito2;
    private Double limitePessoal;
    private Long tipoCliente;
    private String codVendedor;
    private Boolean simples;
    private String celular;
    private String fisJu;
    private String conjuge;
    private String freteCli;
    private Long antecipacao;
    private Boolean etiquetas;
    private Boolean sistema;
    private Double vmanu;
    private Boolean recibo;
    private Long codigoPgto;
    private String codRepresentante;
    private Date dataCadastro;
    private Date dataAlteracao;
    private Boolean liberaLimite;
    private String fantasia;
    private String contatoCobranca;
    private Boolean inativo;
    private Long clienteTipo;
    private Long diaCobranca;
    private Long diaParaVencimento;
    private Boolean cadastroAndroid;
    private Boolean deletadoAndroid;
    private Boolean alteradoAndroid;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(String trabalho) {
        this.trabalho = trabalho;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFonetrab() {
        return fonetrab;
    }

    public void setFonetrab(String fonetrab) {
        this.fonetrab = fonetrab;
    }

    public String getCgc() {
        return cgc;
    }

    public void setCgc(String cgc) {
        this.cgc = cgc;
    }

    public String getIncest() {
        return incest;
    }

    public void setIncest(String incest) {
        this.incest = incest;
    }

    public String getEnderecotrab() {
        return enderecotrab;
    }

    public void setEnderecotrab(String enderecotrab) {
        this.enderecotrab = enderecotrab;
    }

    public Long getCodProfissao() {
        return codProfissao;
    }

    public void setCodProfissao(Long codProfissao) {
        this.codProfissao = codProfissao;
    }

    public Long getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Long codCidade) {
        this.codCidade = codCidade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPessoaAuto() {
        return pessoaAuto;
    }

    public void setPessoaAuto(String pessoaAuto) {
        this.pessoaAuto = pessoaAuto;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getPessoaAuto1() {
        return pessoaAuto1;
    }

    public void setPessoaAuto1(String pessoaAuto1) {
        this.pessoaAuto1 = pessoaAuto1;
    }

    public Double getLimiteCredito1() {
        return limiteCredito1;
    }

    public void setLimiteCredito1(Double limiteCredito1) {
        this.limiteCredito1 = limiteCredito1;
    }

    public String getPessoaAuto2() {
        return pessoaAuto2;
    }

    public void setPessoaAuto2(String pessoaAuto2) {
        this.pessoaAuto2 = pessoaAuto2;
    }

    public Double getLimiteCredito2() {
        return limiteCredito2;
    }

    public void setLimiteCredito2(Double limiteCredito2) {
        this.limiteCredito2 = limiteCredito2;
    }

    public Double getLimitePessoal() {
        return limitePessoal;
    }

    public void setLimitePessoal(Double limitePessoal) {
        this.limitePessoal = limitePessoal;
    }

    public Long getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Long tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public Boolean getSimples() {
        return simples;
    }

    public void setSimples(Boolean simples) {
        this.simples = simples;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFisJu() {
        return fisJu;
    }

    public void setFisJu(String fisJu) {
        this.fisJu = fisJu;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public String getFreteCli() {
        return freteCli;
    }

    public void setFreteCli(String freteCli) {
        this.freteCli = freteCli;
    }

    public Long getAntecipacao() {
        return antecipacao;
    }

    public void setAntecipacao(Long antecipacao) {
        this.antecipacao = antecipacao;
    }

    public Boolean getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Boolean etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Boolean getSistema() {
        return sistema;
    }

    public void setSistema(Boolean sistema) {
        this.sistema = sistema;
    }

    public Double getVmanu() {
        return vmanu;
    }

    public void setVmanu(Double vmanu) {
        this.vmanu = vmanu;
    }

    public Boolean getRecibo() {
        return recibo;
    }

    public void setRecibo(Boolean recibo) {
        this.recibo = recibo;
    }

    public Long getCodigoPgto() {
        return codigoPgto;
    }

    public void setCodigoPgto(Long codigoPgto) {
        this.codigoPgto = codigoPgto;
    }

    public String getCodRepresentante() {
        return codRepresentante;
    }

    public void setCodRepresentante(String codRepresentante) {
        this.codRepresentante = codRepresentante;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Boolean getLiberaLimite() {
        return liberaLimite;
    }

    public void setLiberaLimite(Boolean liberaLimite) {
        this.liberaLimite = liberaLimite;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getContatoCobranca() {
        return contatoCobranca;
    }

    public void setContatoCobranca(String contatoCobranca) {
        this.contatoCobranca = contatoCobranca;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Long getClienteTipo() {
        return clienteTipo;
    }

    public void setClienteTipo(Long clienteTipo) {
        this.clienteTipo = clienteTipo;
    }

    public Long getDiaCobranca() {
        return diaCobranca;
    }

    public void setDiaCobranca(Long diaCobranca) {
        this.diaCobranca = diaCobranca;
    }

    public Long getDiaParaVencimento() {
        return diaParaVencimento;
    }

    public void setDiaParaVencimento(Long diaParaVencimento) {
        this.diaParaVencimento = diaParaVencimento;
    }

    public Boolean getCadastroAndroid() {
        return cadastroAndroid;
    }

    public void setCadastroAndroid(Boolean cadastroAndroid) {
        this.cadastroAndroid = cadastroAndroid;
    }

    public Boolean getDeletadoAndroid() {
        return deletadoAndroid;
    }

    public void setDeletadoAndroid(Boolean deletadoAndroid) {
        this.deletadoAndroid = deletadoAndroid;
    }

    public Boolean getAlteradoAndroid() {
        return alteradoAndroid;
    }

    public void setAlteradoAndroid(Boolean alteradoAndroid) {
        this.alteradoAndroid = alteradoAndroid;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return codigo + " - " + nomeCliente;
    }


    public boolean insereDados(Context context, Long codigo, String nomeCliente, String cpf, Date dataNasc, String endereco, String posicao, String pai, String mae, String bairro, String cep, String identidade, String trabalho, String telefone, String fonetrab, String cgc, String incest, String enderecotrab, Long codProfissao, Long codCidade, String responsavel, String fone, String obs, String nume, String email, String pessoaAuto, Double limiteCredito, String pessoaAuto1, Double limiteCredito1, String pessoaAuto2, Double limiteCredito2, Double limitePessoal, Long tipoCliente, String codVendedor, Boolean simples, String celular, String fisJu, String conjuge, String freteCli, Long antecipacao, Boolean etiquetas, Boolean sistema, Double vmanu, Boolean recibo, Long codigoPgto, String codRepresentante, Date dataCadastro, Date dataAlteracao, Boolean liberaLimite, String fantasia, String contatoCobranca, Boolean inativo, Long clienteTipo, Long diaCobranca, Long diaParaVencimento) {
        Banco myDb = new Banco(context);
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        valores.put("codigo", codigo);
        valores.put("nomeCliente", nomeCliente);
        valores.put("cpf", cpf);
        valores.put("dataNasc", dataNasc.getTime());
        valores.put("endereco", endereco);
        valores.put("posicao", posicao);
        valores.put("pai", pai);
        valores.put("mae", mae);
        valores.put("bairro", bairro);
        valores.put("cep", cep);
        valores.put("identidade", identidade);
        valores.put("trabalho", trabalho);
        valores.put("telefone", telefone);
        valores.put("fonetrab", fonetrab);
        valores.put("cgc", cgc);
        valores.put("incest", incest);
        valores.put("enderecotrab", enderecotrab);
        valores.put("codProfissao", codProfissao);
        valores.put("codCidade", codCidade);
        valores.put("responsavel", responsavel);
        valores.put("fone", fone);
        valores.put("obs", obs);
        valores.put("nume", nume);
        valores.put("email", email);
        valores.put("pessoaAuto", pessoaAuto);
        valores.put("limiteCredito", limiteCredito);
        valores.put("pessoaAuto1", pessoaAuto1);
        valores.put("limiteCredito1", limiteCredito1);
        valores.put("pessoaAuto2", pessoaAuto2);
        valores.put("limiteCredito2", limiteCredito2);
        valores.put("limitePessoal", limitePessoal);
        valores.put("tipoCliente", tipoCliente);
        valores.put("codVendedor", codVendedor);
        valores.put("simples", simples);
        valores.put("celular", celular);
        valores.put("fisJu", fisJu);
        valores.put("conjuge", conjuge);
        valores.put("freteCli", freteCli);
        valores.put("antecipacao", antecipacao);
        valores.put("etiquetas", etiquetas);
        valores.put("sistema", sistema);
        valores.put("vmanu", vmanu);
        valores.put("recibo", recibo);
        valores.put("codigoPgto", codigoPgto);
        valores.put("codRepresentante", codRepresentante);
        valores.put("dataCadastro", dataCadastro.getTime());
        valores.put("dataAlteracao", dataAlteracao.getTime());
        valores.put("liberaLimite", liberaLimite);
        valores.put("fantasia", fantasia);
        valores.put("contatoCobranca", contatoCobranca);
        valores.put("inativo", inativo);
        valores.put("clienteTipo", clienteTipo);
        valores.put("diaCobranca", diaCobranca);
        valores.put("diaParaVencimento", diaParaVencimento);

        long result = db.insert("cliente", null, valores);
        db.close();
        valores.clear();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cliente retornaClienteFiltrado(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cliente where codigo = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }

        Cliente cliente = new Cliente();
        cliente.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
        cliente.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
        cliente.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
        cliente.setCgc(cursor.getString(cursor.getColumnIndex("cgc")));
        cliente.setDataNasc(new Date(cursor.getLong(cursor.getColumnIndex("dataNasc"))));
        cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
        cliente.setPosicao(cursor.getString(cursor.getColumnIndex("posicao")));
        cliente.setPai(cursor.getString(cursor.getColumnIndex("pai")));
        cliente.setMae(cursor.getString(cursor.getColumnIndex("mae")));
        cliente.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
        cliente.setCep(cursor.getString(cursor.getColumnIndex("cep")));
        cliente.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
        cliente.setCep(cursor.getString(cursor.getColumnIndex("cep")));
        cliente.setIdentidade(cursor.getString(cursor.getColumnIndex("identidade")));
        cliente.setTrabalho(cursor.getString(cursor.getColumnIndex("trabalho")));
        cliente.setEnderecotrab(cursor.getString(cursor.getColumnIndex("enderecotrab")));
        cliente.setCodProfissao(cursor.getLong(cursor.getColumnIndex("codProfissao")));
        cliente.setCodCidade(cursor.getLong(cursor.getColumnIndex("codCidade")));
        cliente.setResponsavel(cursor.getString(cursor.getColumnIndex("responsavel")));
        cliente.setFone(cursor.getString(cursor.getColumnIndex("fone")));
        cliente.setObs(cursor.getString(cursor.getColumnIndex("obs")));
        cliente.setNume(cursor.getString(cursor.getColumnIndex("nume")));
        cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        cliente.setPessoaAuto(cursor.getString(cursor.getColumnIndex("pessoaAuto")));
        cliente.setLimiteCredito(cursor.getDouble(cursor.getColumnIndex("limiteCredito")));
        cliente.setPessoaAuto1(cursor.getString(cursor.getColumnIndex("pessoaAuto1")));
        cliente.setLimiteCredito1(cursor.getDouble(cursor.getColumnIndex("limiteCredito1")));
        cliente.setPessoaAuto2(cursor.getString(cursor.getColumnIndex("pessoaAuto2")));
        cliente.setLimiteCredito2(cursor.getDouble(cursor.getColumnIndex("limiteCredito2")));
        cliente.setLimitePessoal(cursor.getDouble(cursor.getColumnIndex("limitePessoal")));
        cliente.setLimitePessoal(cursor.getDouble(cursor.getColumnIndex("tipoCliente")));
        cliente.setCodVendedor(cursor.getString(cursor.getColumnIndex("codVendedor")));
        cliente.setSimples(cursor.getInt(cursor.getColumnIndex("simples")) == 1);
        cliente.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
        cliente.setIncest(cursor.getString(cursor.getColumnIndex("incest")));
        cliente.setFisJu(cursor.getString(cursor.getColumnIndex("fisJu")));
        cliente.setFonetrab(cursor.getString(cursor.getColumnIndex("fonetrab")));
        cliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
        cliente.setConjuge(cursor.getString(cursor.getColumnIndex("conjuge")));
        cliente.setFreteCli(cursor.getString(cursor.getColumnIndex("freteCli")));
        cliente.setAntecipacao(cursor.getLong(cursor.getColumnIndex("antecipacao")));
        cliente.setEtiquetas(cursor.getInt(cursor.getColumnIndex("etiquetas")) == 1);
        cliente.setSistema(cursor.getInt(cursor.getColumnIndex("sistema")) == 1);
        cliente.setRecibo(cursor.getInt(cursor.getColumnIndex("recibo")) == 1);
        cliente.setVmanu(cursor.getDouble(cursor.getColumnIndex("vmanu")));
        cliente.setCodigoPgto(cursor.getLong(cursor.getColumnIndex("codigoPgto")));
        cliente.setCodRepresentante(cursor.getString(cursor.getColumnIndex("codRepresentante")));
        cliente.setDataCadastro(new Date(cursor.getLong(cursor.getColumnIndex("dataCadastro"))));
        cliente.setDataAlteracao(new Date(cursor.getLong(cursor.getColumnIndex("dataAlteracao"))));
        cliente.setLiberaLimite(cursor.getInt(cursor.getColumnIndex("liberaLimite")) == 1);
        cliente.setFantasia(cursor.getString(cursor.getColumnIndex("fantasia")));
        cliente.setContatoCobranca(cursor.getString(cursor.getColumnIndex("contatoCobranca")));
        cliente.setInativo(cursor.getInt(cursor.getColumnIndex("inativo")) == 1);
        cliente.setClienteTipo(cursor.getLong(cursor.getColumnIndex("clienteTipo")));
        cliente.setDiaCobranca(cursor.getLong(cursor.getColumnIndex("diaCobranca")));
        cliente.setDiaParaVencimento(cursor.getLong(cursor.getColumnIndex("diaParaVencimento")));
        cliente.setCadastroAndroid(cursor.getInt(cursor.getColumnIndex("cadastroAndroid")) == 1);
        cliente.setDeletadoAndroid(cursor.getInt(cursor.getColumnIndex("deletadoAndroid")) == 1);
        cliente.setAlteradoAndroid(cursor.getInt(cursor.getColumnIndex("alteradoAndroid")) == 1);
        db.close();

        return cliente;
    }

    public Cursor retornaClienteFiltradoCursor(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cliente where codigo = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaCliente(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cliente", null);
        Log.i("CURSOR", "" + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cliente() {
    }

    public Boolean cadastraCliente(Context context, Long codigo, String nomeCliente, String cpf, Long dataNasc, String endereco, String posicao, String pai, String mae, String bairro, String cep, String identidade, String trabalho, String telefone, String fonetrab, String cgc, String incest, String enderecotrab, Long codProfissao, Long codCidade, String responsavel, String fone, String obs, String nume, String email, String pessoaAuto, Double limiteCredito, String pessoaAuto1, Double limiteCredito1, String pessoaAuto2, Double limiteCredito2, Double limitePessoal, Long tipoCliente, String codVendedor, Boolean simples, String celular, String fisJu, String conjuge, String freteCli, Long antecipacao, Boolean etiquetas, Boolean sistema, Double vmanu, Boolean recibo, Long codigoPgto, String codRepresentante, Long dataCadastro, Long dataAlteracao, Boolean liberaLimite, String fantasia, String contatoCobranca, Boolean inativo, Long clienteTipo, Long diaCobranca, Long diaParaVencimento, Boolean cadastroAndroid, Boolean deletadoAndroid, Boolean alteradoAndroid) {
        Banco myDb = new Banco(context);
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        valores.put("codigo", codigo);
        valores.put("nomeCliente", nomeCliente);
        valores.put("cpf", cpf);
        //valores.put("dataNasc", dataNasc.toString());
        valores.put("endereco", endereco);
        valores.put("posicao", posicao);
        valores.put("pai", pai);
        valores.put("mae", mae);
        valores.put("bairro", bairro);
        valores.put("cep", cep);
        valores.put("identidade", identidade);
        valores.put("trabalho", trabalho);
        valores.put("telefone", telefone);
        valores.put("fonetrab", fonetrab);
        valores.put("cgc", cgc);
        valores.put("incest", incest);
        valores.put("enderecotrab", enderecotrab);
        valores.put("codProfissao", codProfissao);
        valores.put("codCidade", codCidade);
        valores.put("responsavel", responsavel);
        valores.put("fone", fone);
        valores.put("obs", obs);
        valores.put("nume", nume);
        valores.put("email", email);
        valores.put("pessoaAuto", pessoaAuto);
        valores.put("limiteCredito", limiteCredito);
        valores.put("pessoaAuto1", pessoaAuto1);
        valores.put("limiteCredito1", limiteCredito1);
        valores.put("pessoaAuto2", pessoaAuto2);
        valores.put("limiteCredito2", limiteCredito2);
        valores.put("limitePessoal", limitePessoal);
        valores.put("tipoCliente", tipoCliente);
        valores.put("codVendedor", codVendedor);
        valores.put("simples", simples);
        valores.put("celular", celular);
        valores.put("fisJu", fisJu);
        valores.put("conjuge", conjuge);
        valores.put("freteCli", freteCli);
        valores.put("antecipacao", antecipacao);
        valores.put("etiquetas", etiquetas);
        valores.put("sistema", sistema);
        valores.put("vmanu", vmanu);
        valores.put("recibo", recibo);
        valores.put("codigoPgto", codigoPgto);
        valores.put("codRepresentante", codRepresentante);
        //valores.put("dataCadastro", dataCadastro.toString());
        //valores.put("dataAlteracao", dataAlteracao.toString());
        valores.put("liberaLimite", liberaLimite);
        valores.put("fantasia", fantasia);
        valores.put("contatoCobranca", contatoCobranca);
        valores.put("inativo", inativo);
        valores.put("clienteTipo", clienteTipo);
        valores.put("diaCobranca", diaCobranca);
        valores.put("diaParaVencimento", diaParaVencimento);
        valores.put("cadastroAndroid", cadastroAndroid);
        valores.put("deletadoAndroid", deletadoAndroid);
        valores.put("alteradoAndroid", alteradoAndroid);

        long result = db.insert("cliente", null, valores);
        db.close();
        valores.clear();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean alterarCliente(Context context, Long codigo, String nomeCliente, String cpf, Date dataNasc, String endereco, String posicao, String pai, String mae, String bairro, String cep, String identidade, String trabalho, String telefone, String fonetrab, String cgc, String incest, String enderecotrab, Long codProfissao, Long codCidade, String responsavel, String fone, String obs, String nume, String email, String pessoaAuto, Double limiteCredito, String pessoaAuto1, Double limiteCredito1, String pessoaAuto2, Double limiteCredito2, Double limitePessoal, Long tipoCliente, String codVendedor, Boolean simples, String celular, String fisJu, String conjuge, String freteCli, Long antecipacao, Boolean etiquetas, Boolean sistema, Double vmanu, Boolean recibo, Long codigoPgto, String codRepresentante, Date dataCadastro, Date dataAlteracao, Boolean liberaLimite, String fantasia, String contatoCobranca, Boolean inativo, Long clienteTipo, Long diaCobranca, Long diaParaVencimento, Boolean cadastroAndroid, Boolean deletadoAndroid, Boolean alteradoAndroid) {
        Banco myDb = new Banco(context);
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        valores.put("codigo", codigo);
        valores.put("nomeCliente", nomeCliente);
        valores.put("cpf", cpf);
        valores.put("dataNasc", dataNasc.getTime());
        valores.put("endereco", endereco);
        valores.put("posicao", posicao);
        valores.put("pai", pai);
        valores.put("mae", mae);
        valores.put("bairro", bairro);
        valores.put("cep", cep);
        valores.put("identidade", identidade);
        valores.put("trabalho", trabalho);
        valores.put("telefone", telefone);
        valores.put("fonetrab", fonetrab);
        valores.put("cgc", cgc);
        valores.put("incest", incest);
        valores.put("enderecotrab", enderecotrab);
        valores.put("codProfissao", codProfissao);
        valores.put("codCidade", codCidade);
        valores.put("responsavel", responsavel);
        valores.put("fone", fone);
        valores.put("obs", obs);
        valores.put("nume", nume);
        valores.put("email", email);
        valores.put("pessoaAuto", pessoaAuto);
        valores.put("limiteCredito", limiteCredito);
        valores.put("pessoaAuto1", pessoaAuto1);
        valores.put("limiteCredito1", limiteCredito1);
        valores.put("pessoaAuto2", pessoaAuto2);
        valores.put("limiteCredito2", limiteCredito2);
        valores.put("limitePessoal", limitePessoal);
        valores.put("tipoCliente", tipoCliente);
        valores.put("codVendedor", codVendedor);
        valores.put("simples", simples);
        valores.put("celular", celular);
        valores.put("fisJu", fisJu);
        valores.put("conjuge", conjuge);
        valores.put("freteCli", freteCli);
        valores.put("antecipacao", antecipacao);
        valores.put("etiquetas", etiquetas);
        valores.put("sistema", sistema);
        valores.put("vmanu", vmanu);
        valores.put("recibo", recibo);
        valores.put("codigoPgto", codigoPgto);
        valores.put("codRepresentante", codRepresentante);
        valores.put("dataCadastro", dataCadastro.getTime());
        valores.put("dataAlteracao", dataAlteracao.getTime());
        valores.put("liberaLimite", liberaLimite);
        valores.put("fantasia", fantasia);
        valores.put("contatoCobranca", contatoCobranca);
        valores.put("inativo", inativo);
        valores.put("clienteTipo", clienteTipo);
        valores.put("diaCobranca", diaCobranca);
        valores.put("diaParaVencimento", diaParaVencimento);
        valores.put("cadastroAndroid", cadastroAndroid);
        valores.put("deletadoAndroid", deletadoAndroid);
        valores.put("alteradoAndroid", alteradoAndroid);

        long result = db.insert("cliente", null, valores);
        db.close();
        valores.clear();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean deletaCliente(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        long result = db.delete("cliente", "codigo = " + codigo, null);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cliente(Long codigo, String nomeCliente, String cpf, Date dataNasc, String endereco, String posicao, String pai, String mae, String bairro, String cep, String identidade, String trabalho, String telefone, String fonetrab, String cgc, String incest, String enderecotrab, Long codProfissao, Long codCidade, String responsavel, String fone, String obs, String nume, String email, String pessoaAuto, Double limiteCredito, String pessoaAuto1, Double limiteCredito1, String pessoaAuto2, Double limiteCredito2, Double limitePessoal, Long tipoCliente, String codVendedor, Boolean simples, String celular, String fisJu, String conjuge, String freteCli, Long antecipacao, Boolean etiquetas, Boolean sistema, Double vmanu, Boolean recibo, Long codigoPgto, String codRepresentante, Date dataCadastro, Date dataAlteracao, Boolean liberaLimite, String fantasia, String contatoCobranca, Boolean inativo, Long clienteTipo, Long diaCobranca, Long diaParaVencimento, Boolean cadastroAndroid, Boolean deletadoAndroid, Boolean alteradoAndroid) {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.posicao = posicao;
        this.pai = pai;
        this.mae = mae;
        this.bairro = bairro;
        this.cep = cep;
        this.identidade = identidade;
        this.trabalho = trabalho;
        this.telefone = telefone;
        this.fonetrab = fonetrab;
        this.cgc = cgc;
        this.incest = incest;
        this.enderecotrab = enderecotrab;
        this.codProfissao = codProfissao;
        this.codCidade = codCidade;
        this.responsavel = responsavel;
        this.fone = fone;
        this.obs = obs;
        this.nume = nume;
        this.email = email;
        this.pessoaAuto = pessoaAuto;
        this.limiteCredito = limiteCredito;
        this.pessoaAuto1 = pessoaAuto1;
        this.limiteCredito1 = limiteCredito1;
        this.pessoaAuto2 = pessoaAuto2;
        this.limiteCredito2 = limiteCredito2;
        this.limitePessoal = limitePessoal;
        this.tipoCliente = tipoCliente;
        this.codVendedor = codVendedor;
        this.simples = simples;
        this.celular = celular;
        this.fisJu = fisJu;
        this.conjuge = conjuge;
        this.freteCli = freteCli;
        this.antecipacao = antecipacao;
        this.etiquetas = etiquetas;
        this.sistema = sistema;
        this.vmanu = vmanu;
        this.recibo = recibo;
        this.codigoPgto = codigoPgto;
        this.codRepresentante = codRepresentante;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
        this.liberaLimite = liberaLimite;
        this.fantasia = fantasia;
        this.contatoCobranca = contatoCobranca;
        this.inativo = inativo;
        this.clienteTipo = clienteTipo;
        this.diaCobranca = diaCobranca;
        this.diaParaVencimento = diaParaVencimento;
        this.cadastroAndroid = cadastroAndroid;
        this.deletadoAndroid = deletadoAndroid;
        this.alteradoAndroid = alteradoAndroid;
    }
}
