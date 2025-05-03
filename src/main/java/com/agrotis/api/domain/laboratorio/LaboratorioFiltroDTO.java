package com.agrotis.api.domain.laboratorio;

import java.time.Instant;

public class LaboratorioFiltroDTO {
    private Instant dataInicialInicio;
    private Instant dataInicialFim;
    private Instant dataFinalInicio;
    private Instant dataFinalFim;
    private String observacoes;
    private Integer minPessoas;

    public Instant getDataInicialInicio() {
        return dataInicialInicio;
    }

    public Instant getDataInicialFim() {
        return dataInicialFim;
    }

    public Instant getDataFinalInicio() {
        return dataFinalInicio;
    }

    public Instant getDataFinalFim() {
        return dataFinalFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Integer getMinPessoas() {
        return minPessoas;
    }

    public void setDataInicialInicio(Instant dataInicialInicio) {
        this.dataInicialInicio = dataInicialInicio;
    }

    public void setDataInicialFim(Instant dataInicialFim) {
        this.dataInicialFim = dataInicialFim;
    }

    public void setDataFinalInicio(Instant dataFinalInicio) {
        this.dataFinalInicio = dataFinalInicio;
    }

    public void setDataFinalFim(Instant dataFinalFim) {
        this.dataFinalFim = dataFinalFim;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setMinPessoas(Integer minPessoas) {
        this.minPessoas = minPessoas;
    }
}

