package br.com.alura.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rua;
    private String logradouro;
    private String complemento;
    private Integer numero;

    public Endereco() {

    }

    public Endereco(Integer id, String rua, String logradouro, String complemento, Integer numero) {
        this.id = id;
        this.rua = rua;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getId() {
        return this.id;
    }

    public String getRua() {
        return this.rua;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public Integer getNumero() {
        return this.numero;
    }
}
