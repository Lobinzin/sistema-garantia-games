package com.lojagames.sistemagarantia.models;  // Note o pacote declarado aqui

import jakarta.persistence.*;

@Entity  // Indica que esta classe é uma entidade JPA
public class Cliente {
    @Id  // Define o campo como chave primária
    private Long cpf;

    @Column(nullable = false)  // Configura a coluna no banco
    private String nome;

    private Long telefone;

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}