package com.example.jose.connectdrawer.DadosEnvio;

public class DadosEnvio {
    String tipodocumento;
    String emaildestinatario;
    String assunto;
    String mensagem;
    String arquivo;

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getEmaildestinatario() {
        return emaildestinatario;
    }

    public void setEmaildestinatario(String emaildestinatario) {
        this.emaildestinatario = emaildestinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
