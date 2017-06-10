package com.example.jose.connectdrawer.cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jose.connectdrawer.banco.Banco;
import com.example.jose.connectdrawer.uteis.DadosBanco;
import com.example.jose.connectdrawer.uteis.GetSetDinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Jose on 18/05/2017.
 */

public class Cliente {

    private Long codigo;
    private String nomecliente;
    private String cpf;
    private Date datanasc;
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
    private Long codprofissao;
    private Long codcidade;
    private String responsavel;
    private String fone;
    private String obs;
    private String nume;
    private String email;
    private String pessoaauto;
    private Double limitecredito;
    private String pessoaauto1;
    private Double limitecredito1;
    private String pessoaauto2;
    private Double limitecredito2;
    private Double limitepessoal;
    private Long tipocliente;
    private String codvendedor;
    private Boolean simples;
    private String celular;
    private String fisju;
    private String conjuge;
    private String fretecli;
    private Long antecipacao;
    private Boolean etiquetas;
    private Boolean sistema;
    private Double vmanu;
    private Boolean recibo;
    private Long codigopgto;
    private String codrepresentante;
    private Date datacadastro;
    private Date dataalteracao;
    private Boolean liberalimite;
    private String fantasia;
    private String contatocobranca;
    private Boolean inativo;
    private Long clientetipo;
    private Long diacobranca;
    private Long diaparavencimento;
    private Boolean cadastroandroid;
    private Boolean deletadoandroid;
    private Boolean alteradoandroid;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
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

    public Long getCodprofissao() {
        return codprofissao;
    }

    public void setCodprofissao(Long codprofissao) {
        this.codprofissao = codprofissao;
    }

    public Long getCodcidade() {
        return codcidade;
    }

    public void setCodcidade(Long codcidade) {
        this.codcidade = codcidade;
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

    public String getPessoaauto() {
        return pessoaauto;
    }

    public void setPessoaauto(String pessoaauto) {
        this.pessoaauto = pessoaauto;
    }

    public Double getLimitecredito() {
        return limitecredito;
    }

    public void setLimitecredito(Double limitecredito) {
        this.limitecredito = limitecredito;
    }

    public String getPessoaauto1() {
        return pessoaauto1;
    }

    public void setPessoaauto1(String pessoaauto1) {
        this.pessoaauto1 = pessoaauto1;
    }

    public Double getLimitecredito1() {
        return limitecredito1;
    }

    public void setLimitecredito1(Double limitecredito1) {
        this.limitecredito1 = limitecredito1;
    }

    public String getPessoaauto2() {
        return pessoaauto2;
    }

    public void setPessoaauto2(String pessoaauto2) {
        this.pessoaauto2 = pessoaauto2;
    }

    public Double getLimitecredito2() {
        return limitecredito2;
    }

    public void setLimitecredito2(Double limitecredito2) {
        this.limitecredito2 = limitecredito2;
    }

    public Double getLimitepessoal() {
        return limitepessoal;
    }

    public void setLimitepessoal(Double limitepessoal) {
        this.limitepessoal = limitepessoal;
    }

    public Long getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(Long tipocliente) {
        this.tipocliente = tipocliente;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
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

    public String getFisju() {
        return fisju;
    }

    public void setFisju(String fisju) {
        this.fisju = fisju;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public String getFretecli() {
        return fretecli;
    }

    public void setFretecli(String fretecli) {
        this.fretecli = fretecli;
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

    public Long getCodigopgto() {
        return codigopgto;
    }

    public void setCodigopgto(Long codigopgto) {
        this.codigopgto = codigopgto;
    }

    public String getCodrepresentante() {
        return codrepresentante;
    }

    public void setCodrepresentante(String codrepresentante) {
        this.codrepresentante = codrepresentante;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Date getDataalteracao() {
        return dataalteracao;
    }

    public void setDataalteracao(Date dataalteracao) {
        this.dataalteracao = dataalteracao;
    }

    public Boolean getLiberalimite() {
        return liberalimite;
    }

    public void setLiberalimite(Boolean liberalimite) {
        this.liberalimite = liberalimite;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getContatocobranca() {
        return contatocobranca;
    }

    public void setContatocobranca(String contatocobranca) {
        this.contatocobranca = contatocobranca;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Long getClientetipo() {
        return clientetipo;
    }

    public void setClientetipo(Long clientetipo) {
        this.clientetipo = clientetipo;
    }

    public Long getDiacobranca() {
        return diacobranca;
    }

    public void setDiacobranca(Long diacobranca) {
        this.diacobranca = diacobranca;
    }

    public Long getDiaparavencimento() {
        return diaparavencimento;
    }

    public void setDiaparavencimento(Long diaparavencimento) {
        this.diaparavencimento = diaparavencimento;
    }

    public Boolean getCadastroandroid() {
        return cadastroandroid;
    }

    public void setCadastroandroid(Boolean cadastroandroid) {
        this.cadastroandroid = cadastroandroid;
    }

    public Boolean getDeletadoandroid() {
        return deletadoandroid;
    }

    public void setDeletadoandroid(Boolean deletadoandroid) {
        this.deletadoandroid = deletadoandroid;
    }

    public Boolean getAlteradoandroid() {
        return alteradoandroid;
    }

    public void setAlteradoandroid(Boolean alteradoandroid) {
        this.alteradoandroid = alteradoandroid;
    }

    @Override
    public String toString() {
        return codigo + " - " + nomecliente;
    }


    public boolean insereDados(Context context, Cliente cliente) {
        Banco myDb = new Banco(context);
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        valores.put("codigo", cliente.getCodigo());
        valores.put("nomecliente", cliente.getNomecliente());
        valores.put("cpf", cliente.getCpf());
        if (cliente.getDatanasc() != null) {
            valores.put("datanasc", cliente.getDatanasc().getTime());
        }
        valores.put("endereco", cliente.getEndereco());
        valores.put("posicao", cliente.getPosicao());
        valores.put("pai", cliente.getPai());
        valores.put("mae", cliente.getMae());
        valores.put("bairro", cliente.getBairro());
        valores.put("cep", cliente.getCep());
        valores.put("identidade", cliente.getIdentidade());
        valores.put("trabalho", cliente.getTrabalho());
        valores.put("telefone", cliente.getTelefone());
        valores.put("fonetrab", cliente.getFonetrab());
        valores.put("cgc", cliente.getCgc());
        valores.put("incest", cliente.getIncest());
        valores.put("enderecotrab", cliente.getEnderecotrab());
        valores.put("codprofissao", cliente.getCodprofissao());
        valores.put("codcidade", cliente.getCodcidade());
        valores.put("responsavel", cliente.getResponsavel());
        valores.put("fone", cliente.getFone());
        valores.put("obs", cliente.getObs());
        valores.put("nume", cliente.getNume());
        valores.put("email", cliente.getEmail());
        valores.put("pessoaauto", cliente.getPessoaauto());
        valores.put("limitecredito", cliente.getLimitecredito());
        valores.put("pessoaauto1", cliente.getPessoaauto1());
        valores.put("limitecredito1", cliente.getLimitecredito1());
        valores.put("pessoaauto2", cliente.getPessoaauto2());
        valores.put("limitecredito2", cliente.getLimitecredito2());
        valores.put("limitepessoal", cliente.getLimitepessoal());
        valores.put("tipocliente", cliente.getTipocliente());
        valores.put("codvendedor", cliente.getCodvendedor());
        valores.put("simples", cliente.getSimples());
        valores.put("celular", cliente.getCelular());
        valores.put("fisju", cliente.getFisju());
        valores.put("conjuge", cliente.getConjuge());
        valores.put("fretecli", cliente.getFretecli());
        valores.put("antecipacao", cliente.getAntecipacao());
        valores.put("etiquetas", cliente.getEtiquetas());
        valores.put("sistema", cliente.getSistema());
        valores.put("vmanu", cliente.getVmanu());
        valores.put("recibo", cliente.getRecibo());
        valores.put("codigopgto", cliente.getCodigopgto());
        valores.put("codrepresentante", cliente.getCodrepresentante());
        if (cliente.getDatacadastro() != null) {
            valores.put("datacadastro", cliente.getDatacadastro().getTime());
        }
        if (cliente.getDataalteracao() != null) {
            valores.put("dataalteracao", cliente.getDataalteracao().getTime());
        }
        valores.put("liberalimite", cliente.getLiberalimite());
        valores.put("fantasia",cliente.getFantasia());
        valores.put("contatocobranca", cliente.getContatocobranca());
        valores.put("inativo",cliente.getInativo());
        valores.put("clientetipo", cliente.getClientetipo());
        valores.put("diacobranca",cliente.getDiacobranca());
        valores.put("diaparavencimento", cliente.getDiaparavencimento());

        long result = db.insert("cliente", null, valores);
        db.close();
        valores.clear();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor retornaClienteFiltrado(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cliente where codigo = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
//        GetSetDinamico getSetDinamico = new GetSetDinamico();
//        Cliente cliente = new Cliente();
//        List<Field> fieldList = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
//
//        for (int i = 0; fieldList.size() != i; i++) {
//            Object ret = getSetDinamico.setValorObjetoCursor(fieldList.get(i), cliente, cursor);
//            cliente = (Cliente) ret;
//        }
//        cliente.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
//        cliente.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomecliente")));
//        cliente.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
//        cliente.setCgc(cursor.getString(cursor.getColumnIndex("cgc")));
//        cliente.setDataNasc(new Date(cursor.getLong(cursor.getColumnIndex("datanasc"))));
//        cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
//        cliente.setPosicao(cursor.getString(cursor.getColumnIndex("posicao")));
//        cliente.setPai(cursor.getString(cursor.getColumnIndex("pai")));
//        cliente.setMae(cursor.getString(cursor.getColumnIndex("mae")));
//        cliente.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
//        cliente.setCep(cursor.getString(cursor.getColumnIndex("cep")));
//        cliente.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
//        cliente.setCep(cursor.getString(cursor.getColumnIndex("cep")));
//        cliente.setIdentidade(cursor.getString(cursor.getColumnIndex("identidade")));
//        cliente.setTrabalho(cursor.getString(cursor.getColumnIndex("trabalho")));
//        cliente.setEnderecotrab(cursor.getString(cursor.getColumnIndex("enderecotrab")));
//        cliente.setCodProfissao(cursor.getLong(cursor.getColumnIndex("codprofissao")));
//        cliente.setCodCidade(cursor.getLong(cursor.getColumnIndex("codcidade")));
//        cliente.setResponsavel(cursor.getString(cursor.getColumnIndex("responsavel")));
//        cliente.setFone(cursor.getString(cursor.getColumnIndex("fone")));
//        cliente.setObs(cursor.getString(cursor.getColumnIndex("obs")));
//        cliente.setNume(cursor.getString(cursor.getColumnIndex("nume")));
//        cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
//        cliente.setPessoaAuto(cursor.getString(cursor.getColumnIndex("pessoaauto")));
//        cliente.setLimiteCredito(cursor.getDouble(cursor.getColumnIndex("limitecredito")));
//        cliente.setPessoaAuto1(cursor.getString(cursor.getColumnIndex("pessoaauto1")));
//        cliente.setLimiteCredito1(cursor.getDouble(cursor.getColumnIndex("limitecredito1")));
//        cliente.setPessoaAuto2(cursor.getString(cursor.getColumnIndex("pessoaauto2")));
//        cliente.setLimiteCredito2(cursor.getDouble(cursor.getColumnIndex("limitecredito2")));
//        cliente.setLimitePessoal(cursor.getDouble(cursor.getColumnIndex("limitepessoal")));
//        cliente.setLimitePessoal(cursor.getDouble(cursor.getColumnIndex("tipocliente")));
//        cliente.setCodVendedor(cursor.getString(cursor.getColumnIndex("codvendedor")));
//        cliente.setSimples(cursor.getInt(cursor.getColumnIndex("simples")) == 1);
//        cliente.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
//        cliente.setIncest(cursor.getString(cursor.getColumnIndex("incest")));
//        cliente.setFisJu(cursor.getString(cursor.getColumnIndex("fisju")));
//        cliente.setFonetrab(cursor.getString(cursor.getColumnIndex("fonetrab")));
//        cliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
//        cliente.setConjuge(cursor.getString(cursor.getColumnIndex("conjuge")));
//        cliente.setFreteCli(cursor.getString(cursor.getColumnIndex("fretecli")));
//        cliente.setAntecipacao(cursor.getLong(cursor.getColumnIndex("antecipacao")));
//        cliente.setEtiquetas(cursor.getInt(cursor.getColumnIndex("etiquetas")) == 1);
//        cliente.setSistema(cursor.getInt(cursor.getColumnIndex("sistema")) == 1);
//        cliente.setRecibo(cursor.getInt(cursor.getColumnIndex("recibo")) == 1);
//        cliente.setVmanu(cursor.getDouble(cursor.getColumnIndex("vmanu")));
//        cliente.setCodigoPgto(cursor.getLong(cursor.getColumnIndex("codigopgto")));
//        cliente.setCodRepresentante(cursor.getString(cursor.getColumnIndex("codrepresentante")));
//        cliente.setDataCadastro(new Date(cursor.getLong(cursor.getColumnIndex("datacadastro"))));
//        cliente.setDataAlteracao(new Date(cursor.getLong(cursor.getColumnIndex("dataalteracao"))));
//        cliente.setLiberaLimite(cursor.getInt(cursor.getColumnIndex("liberalimite")) == 1);
//        cliente.setFantasia(cursor.getString(cursor.getColumnIndex("fantasia")));
//        cliente.setContatoCobranca(cursor.getString(cursor.getColumnIndex("contatocobranca")));
//        cliente.setInativo(cursor.getInt(cursor.getColumnIndex("inativo")) == 1);
//        cliente.setClienteTipo(cursor.getLong(cursor.getColumnIndex("clientetipo")));
//        cliente.setDiaCobranca(cursor.getLong(cursor.getColumnIndex("diacobranca")));
//        cliente.setDiaParaVencimento(cursor.getLong(cursor.getColumnIndex("diaparavencimento")));
//        cliente.setCadastroAndroid(cursor.getInt(cursor.getColumnIndex("cadastroandroid")) == 1);
//        cliente.setDeletadoAndroid(cursor.getInt(cursor.getColumnIndex("deletadoandroid")) == 1);
//        cliente.setAlteradoAndroid(cursor.getInt(cursor.getColumnIndex("alteradoandroid")) == 1);
        //db.close();

        //return cliente;
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
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cliente() {
    }

    public Boolean cadastraCliente(Context context, Cliente cliente){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(cliente.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){
            dadosBanco.insereValoresContent(fieldList.get(i), cliente);
        }




        return true;
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
        valores.put("nomecliente", nomeCliente);
        valores.put("cpf", cpf);
        valores.put("datanasc", dataNasc.getTime());
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
        valores.put("codprofissao", codProfissao);
        valores.put("codcidade", codCidade);
        valores.put("responsavel", responsavel);
        valores.put("fone", fone);
        valores.put("obs", obs);
        valores.put("nume", nume);
        valores.put("email", email);
        valores.put("pessoaauto", pessoaAuto);
        valores.put("limitecredito", limiteCredito);
        valores.put("pessoaauto1", pessoaAuto1);
        valores.put("limitecredito1", limiteCredito1);
        valores.put("pessoaauto2", pessoaAuto2);
        valores.put("limitecredito2", limiteCredito2);
        valores.put("limitepessoal", limitePessoal);
        valores.put("tipocliente", tipoCliente);
        valores.put("codvendedor", codVendedor);
        valores.put("simples", simples);
        valores.put("celular", celular);
        valores.put("fisju", fisJu);
        valores.put("conjuge", conjuge);
        valores.put("freteCli", freteCli);
        valores.put("antecipacao", antecipacao);
        valores.put("etiquetas", etiquetas);
        valores.put("sistema", sistema);
        valores.put("vmanu", vmanu);
        valores.put("recibo", recibo);
        valores.put("codigopgto", codigoPgto);
        valores.put("codrepresentante", codRepresentante);
        valores.put("datacadastro", dataCadastro.getTime());
        valores.put("dataalteracao", dataAlteracao.getTime());
        valores.put("liberalimite", liberaLimite);
        valores.put("fantasia", fantasia);
        valores.put("contatocobranca", contatoCobranca);
        valores.put("inativo", inativo);
        valores.put("clientetipo", clienteTipo);
        valores.put("diacobranca", diaCobranca);
        valores.put("diaparavencimento", diaParaVencimento);
        valores.put("cadastroandroid", cadastroAndroid);
        valores.put("deletadoandroid", deletadoAndroid);
        valores.put("alteradoandroid", alteradoAndroid);

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
}
