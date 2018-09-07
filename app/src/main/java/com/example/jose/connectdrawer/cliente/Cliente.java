package com.example.jose.connectdrawer.cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


    public Cliente() {
    }

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

//    public boolean insereDados(Context context, Cliente cliente) {
////        Banco myDb = new Banco(context);
////        ContentValues valores = new ContentValues();
////        SQLiteDatabase db = myDb.getWritableDatabase();
////        valores.put("codigo", cliente.getCodigo());
////        valores.put("nomecliente", cliente.getNomecliente());
////        valores.put("cpf", cliente.getCpf());
////        if (cliente.getDatanasc() != null) {
////            valores.put("datanasc", cliente.getDatanasc().getTime());
////        }
////        valores.put("endereco", cliente.getEndereco());
////        valores.put("posicao", cliente.getPosicao());
////        valores.put("pai", cliente.getPai());
////        valores.put("mae", cliente.getMae());
////        valores.put("bairro", cliente.getBairro());
////        valores.put("cep", cliente.getCep());
////        valores.put("identidade", cliente.getIdentidade());
////        valores.put("trabalho", cliente.getTrabalho());
////        valores.put("telefone", cliente.getTelefone());
////        valores.put("fonetrab", cliente.getFonetrab());
////        valores.put("cgc", cliente.getCgc());
////        valores.put("incest", cliente.getIncest());
////        valores.put("enderecotrab", cliente.getEnderecotrab());
////        valores.put("codprofissao", cliente.getCodprofissao());
////        valores.put("codcidade", cliente.getCodcidade());
////        valores.put("responsavel", cliente.getResponsavel());
////        valores.put("fone", cliente.getFone());
////        valores.put("obs", cliente.getObs());
////        valores.put("nume", cliente.getNume());
////        valores.put("email", cliente.getEmail());
////        valores.put("pessoaauto", cliente.getPessoaauto());
////        valores.put("limitecredito", cliente.getLimitecredito());
////        valores.put("pessoaauto1", cliente.getPessoaauto1());
////        valores.put("limitecredito1", cliente.getLimitecredito1());
////        valores.put("pessoaauto2", cliente.getPessoaauto2());
////        valores.put("limitecredito2", cliente.getLimitecredito2());
////        valores.put("limitepessoal", cliente.getLimitepessoal());
////        valores.put("tipocliente", cliente.getTipocliente());
////        valores.put("codvendedor", cliente.getCodvendedor());
////        valores.put("simples", cliente.getSimples());
////        valores.put("celular", cliente.getCelular());
////        valores.put("fisju", cliente.getFisju());
////        valores.put("conjuge", cliente.getConjuge());
////        valores.put("fretecli", cliente.getFretecli());
////        valores.put("antecipacao", cliente.getAntecipacao());
////        valores.put("etiquetas", cliente.getEtiquetas());
////        valores.put("sistema", cliente.getSistema());
////        valores.put("vmanu", cliente.getVmanu());
////        valores.put("recibo", cliente.getRecibo());
////        valores.put("codigopgto", cliente.getCodigopgto());
////        valores.put("codrepresentante", cliente.getCodrepresentante());
////        if (cliente.getDatacadastro() != null) {
////            valores.put("datacadastro", cliente.getDatacadastro().getTime());
////        }
////        if (cliente.getDataalteracao() != null) {
////            valores.put("dataalteracao", cliente.getDataalteracao().getTime());
////        }
////        valores.put("liberalimite", cliente.getLiberalimite());
////        valores.put("fantasia",cliente.getFantasia());
////        valores.put("contatocobranca", cliente.getContatocobranca());
////        valores.put("inativo",cliente.getInativo());
////        valores.put("clientetipo", cliente.getClientetipo());
////        valores.put("diacobranca",cliente.getDiacobranca());
////        valores.put("diaparavencimento", cliente.getDiaparavencimento());
//
//
//        Banco myDb = new Banco(context);
//        Long retorno = 0L;
//        DadosBanco dadosBanco = new DadosBanco();
//        ContentValues valores = new ContentValues();
//        SQLiteDatabase db = myDb.getWritableDatabase();
//        List<Field> fieldList = new ArrayList<>(Arrays.asList(cliente.getClass().getDeclaredFields()));
//
//        for (int i = 0; fieldList.size() != i; i++) {
//            valores = dadosBanco.insereValoresContent(fieldList.get(i), cliente, valores);
//        }
//
//        if (valores.get("codigo") == null) {
//            retorno = retornaMaiorCod(context);
//            retorno = retorno + 1;
//            valores.remove("codigo");
//            valores.remove("cadastroandroid");
//            valores.put("pedido", retorno);
//            valores.put("cadastroandroid", true);
//            retorno =db.insert("cliente", null, valores);
//            db.close();
//            valores.clear();
//            return retorno != -1;
//        } else {
//            valores.remove("alteradoandroid");
//            valores.put("alteradoandroid", true);
//            int ret = db.update("cliente", valores, "codigo= " + valores.get("codigo").toString(), null);
//            db.close();
//            valores.clear();
//            return ret != -1;
//        }
//    }

    public Cursor retornaClienteFiltrado(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cliente where codigo = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
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

    public Cursor retornaCliente(SQLiteDatabase db) {

        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cliente order by nomecliente", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
//        db.close();
        return cursor;
    }

    public Long retornaMaiorCod(Context context) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  rowid _id,  max(codigo) from cliente", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(1);
        } else {
            return 0L;
        }
    }

    public Boolean cadastraCliente(Context context, Cliente cliente){
        Banco myDb = new Banco(context);
        DadosBanco dadosBanco = new DadosBanco();
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = myDb.getWritableDatabase();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(cliente.getClass().getDeclaredFields()));

        for (int i = 0 ; fieldList.size() != i ; i++){

            valores = dadosBanco.insereValoresContent(fieldList.get(i), cliente, valores);
        }

        if (valores.get("codigo") == null){
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
            Cursor cursor = cliente.retornaClienteFiltradaCursor(context, Long.parseLong(valores.get("codigo").toString()));

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

    public Boolean deletaCliente(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getWritableDatabase();
        long result = db.delete("cliente", "codigo = " + codigo, null);
        db.close();
        return result != -1;
    }

    //                           CUIDADO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public Cursor retornaClienteFiltradaCursor(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT codigo FROM cliente where codigo = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cliente retornaClienteObjeto(Context context, Long codigo) {
        Banco myDb = new Banco(context);
        Cliente cliente = new Cliente();
        GetSetDinamico getSetDinamico = new GetSetDinamico();
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid _id,* FROM cliente where codigo = " + codigo, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        List<Field> fieldListCliente = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
        for (int j = 0; cursor.getCount() != j; j++) {
            Cliente cliente1 = new Cliente();

            for (int f = 0; fieldListCliente.size() != f; f++) {

                String tipo = getSetDinamico.retornaTipoCampo(fieldListCliente.get(f));
                String nomeCampo = fieldListCliente.get(f).getName().toLowerCase();
                Object retorno = getSetDinamico.retornaValorCursor(tipo, nomeCampo, cursor);
                if (retorno != null) {
                    Object retCliente = getSetDinamico.insereField(fieldListCliente.get(f), cliente1, retorno);
                    cliente1 = (Cliente) retCliente;
                }
            }
            cliente = cliente1;
        }
        db.close();
        return cliente;

    }



    public Cursor retornaClienteAlteradaAndroid(Context context, String tipo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cliente where " + tipo + " = 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaCursorRawQUery(Context context, String query) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraCidadeCliente(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("codcidade", codigoServidor);
        int retorno = db.update("cliente", values, "codcidade = " + codigoAndroid, null);
        values.clear();
    }


    public void alteraPedidoCliente(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("codcliente", codigoServidor);
        int retorno = db.update("pedido", values, "codcliente = " + codigoAndroid, null);
        values.clear();
    }

    public void alteraCodCliente(Context context, Long codigoAndroid, Long codigoServidor) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("codigo", codigoServidor);
        int retorno = db.update("cliente", values, "codigo = " + codigoAndroid, null);

    }


    public void removeClienteAlteradaAndroid(Context context, String campo) {
        Banco myDb = new Banco(context);
        SQLiteDatabase db = myDb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(campo,"0");
        int retorno = db.update("cliente", values, campo + " = 1", null);

    }
}
