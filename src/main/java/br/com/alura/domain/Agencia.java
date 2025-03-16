package br.com.alura.domain;

import br.com.alura.service.http.SituacaoCadastral;

public class Agencia {
    private Integer id;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private SituacaoCadastral situacaoCadastral;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public SituacaoCadastral getSituacaoCadastral() {
        return situacaoCadastral;
    }

}
