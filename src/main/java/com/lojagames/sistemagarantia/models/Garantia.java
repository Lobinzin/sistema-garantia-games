package com.lojagames.sistemagarantia.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Garantia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    private Integer tempoGarantiaMeses; // Ex: 12 (1 ano)
    private LocalDate dataExpiracao; // Calculada automaticamente

    @PrePersist
    public void calcularDataExpiracao() {
        this.dataExpiracao = venda.getDataVenda().plusMonths(tempoGarantiaMeses);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getTempoGarantiaMeses() {
        return tempoGarantiaMeses;
    }

    public void setTempoGarantiaMeses(Integer tempoGarantiaMeses) {
        this.tempoGarantiaMeses = tempoGarantiaMeses;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}