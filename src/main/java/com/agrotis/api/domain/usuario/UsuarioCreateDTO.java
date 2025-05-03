package com.agrotis.api.domain.usuario;

import java.time.Instant;

public class UsuarioCreateDTO {
    private String nome;
    private Instant dataInicial;
    private Instant dataFinal;
    private Long propriedadeId;
    private Long laboratorioId;
    private String observacoes;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Instant getDataInicial() { return dataInicial; }
    public void setDataInicial(Instant dataInicial) { this.dataInicial = dataInicial; }

    public Instant getDataFinal() { return dataFinal; }
    public void setDataFinal(Instant dataFinal) { this.dataFinal = dataFinal; }

    public Long getPropriedadeId() { return propriedadeId; }
    public void setPropriedadeId(Long propriedadeId) { this.propriedadeId = propriedadeId; }

    public Long getLaboratorioId() { return laboratorioId; }
    public void setLaboratorioId(Long laboratorioId) { this.laboratorioId = laboratorioId; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
