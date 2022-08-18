package com.example.StefFood.dto;


import com.example.StefFood.modelo.Loja;

public class LojaDto {


    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;

    public LojaDto(Loja loja) {
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.endereco = loja.getEndereco();
        this.cnpj = loja.getCnpj();
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCnpj() {
        return cnpj;
    }
}
