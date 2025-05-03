package com.agrotis.api.domain.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class UsuarioUpdateDTO {
    private Long id;
    private String nome;
    private Instant dataInicial;
    private Instant dataFinal;
    private Long propriedadeId;
    private String observacoes;

    public UsuarioUpdateDTO(
            @JsonProperty("nome") String nome,
            @JsonProperty("observacoes") String observacoes,
            @JsonProperty("dataInicial") Instant dataInicial,
            @JsonProperty("dataFinal") Instant dataFinal) {
        this.nome = nome;
        this.observacoes = observacoes;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public UsuarioUpdateDTO(Usuario usuario) {
    }

    public UsuarioUpdateDTO(UsuarioUpdateDTO usuarioUpdateDTO) {}

    public UsuarioUpdateDTO() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Instant getDataInicial() {
        return dataInicial;
    }

    public Instant getDataFinal() {
        return dataFinal;
    }

    public Long getPropriedadeId() {
        return propriedadeId;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicial(Instant dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Instant dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setPropriedadeId(Long propriedadeId) {
        this.propriedadeId = propriedadeId;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
