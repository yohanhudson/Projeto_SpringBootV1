package com.example.StefFood.form;


import com.example.StefFood.modelo.Cliente;
import com.example.StefFood.repository.ClienteRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ClienteForm {

    @NotNull @Length(min = 5)
    private String nome;
    @NotNull @Length(min = 5)
    private String email;
    @NotNull @Length(min = 5)
    private String senha;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente converter(ClienteRepository clienteRepository) {
        Cliente cliente = clienteRepository.findByNome(nome);
        return new Cliente(nome, email, senha);
    }
}
