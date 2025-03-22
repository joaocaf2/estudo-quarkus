package br.com.alura.domain;

import br.com.alura.service.http.SituacaoCadastral;
import jakarta.persistence.*;

@Entity
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    private String cnpj;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

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
