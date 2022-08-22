package com.example.StefFood.form;

import com.example.StefFood.modelo.Cliente;
import com.example.StefFood.modelo.Loja;
import com.example.StefFood.repository.ClienteRepository;
import com.example.StefFood.repository.LojaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AtualizarCliente {
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

    public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
        Cliente cliente = clienteRepository.getOne(id);

        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setSenha(this.senha);

        return cliente;
    }
}
