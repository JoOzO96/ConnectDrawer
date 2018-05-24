package com.example.jose.connectdrawer.Usuario;

/**
 * Created by Jose on 27/03/2018.
 */

public class Usuario {
    private String usuario;
    private String senha;
    private Long empresa;


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

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }
}
