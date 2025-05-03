package com.agrotis.api.domain.usuario;

import com.agrotis.api.domain.laboratorio.LaboratorioDTO;
import com.agrotis.api.domain.propriedade.Propriedade;
import com.agrotis.api.domain.propriedade.PropriedadeDTO;

import java.time.Instant;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private Instant dataInicial;
    private Instant dataFinal;
    private PropriedadeDTO infosPropriedade;
    private LaboratorioDTO laboratorio;
    private String observacoes;

    public UsuarioDTO(String nome, Instant dataInicial, Instant dataFinal, PropriedadeDTO infosPropriedade, LaboratorioDTO laboratorio, String observacoes) {
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.infosPropriedade = infosPropriedade;
        this.laboratorio = laboratorio;
        this.observacoes = observacoes;
    }

    public UsuarioDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instant getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Instant dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Instant getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Instant dataFinal) {
        this.dataFinal = dataFinal;
    }

    public PropriedadeDTO getInfosPropriedade() {
        return infosPropriedade;
    }

    public void setInfosPropriedade(PropriedadeDTO infosPropriedade) {
        this.infosPropriedade = infosPropriedade;
    }

    public LaboratorioDTO getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLaboratorioNome(String laboratorioNome) {
        this.laboratorio.setNome(laboratorioNome);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

