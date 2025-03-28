package br.com.alura.domain.http;

import br.com.alura.service.http.SituacaoCadastral;

public class AgenciaHttp {
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private SituacaoCadastral situacaoCadastral;

    public AgenciaHttp(String nome, String razaoSocial, String cnpj, String situacaoCadastral) {
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacaoCadastral = SituacaoCadastral.valueOf(situacaoCadastral);
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public SituacaoCadastral getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

}
