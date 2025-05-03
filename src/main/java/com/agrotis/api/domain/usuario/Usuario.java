package com.agrotis.api.domain.usuario;

import com.agrotis.api.domain.laboratorio.Laboratorio;
import com.agrotis.api.domain.laboratorio.LaboratorioRepository;
import com.agrotis.api.domain.propriedade.Propriedade;
import jakarta.persistence.*;

import java.time.Instant;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Instant dataInicial;
    private Instant dataFinal;

    @ManyToOne
    @JoinColumn(name = "propriedade_id")
    private Propriedade infosPropriedade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    private String observacoes;

    public String getNome() {
        return nome;
    }

    public Instant getDataInicial() {
        return dataInicial;
    }

    public Instant getDataFinal() {
        return dataFinal;
    }

    public Propriedade getInfosPropriedade() {
        return infosPropriedade;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setDataInicial(Instant dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Instant dataFinal) {
        this.dataFinal = dataFinal;
    }


    public void setInfosPropriedade(String nomePropriedade, Long idPropriedade) {
        if (this.infosPropriedade == null) {
            this.infosPropriedade = new Propriedade();
        }
        this.infosPropriedade.setId(idPropriedade);
        this.infosPropriedade.setNome(nomePropriedade);
    }

    public void setLaboratorio(Long laboratorioId, LaboratorioRepository laboratorioRepository) {
        Laboratorio laboratorio = laboratorioRepository.findById(laboratorioId)
                .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));

        this.laboratorio = laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void atualizarDados(UsuarioUpdateDTO dados) {
        if (dados.getNome() != null) {
            this.nome = dados.getNome();
        }

        if (dados.getDataInicial() != null) {
            this.dataInicial = dados.getDataInicial();
        }

        if (dados.getObservacoes() != null) {
            this.observacoes = dados.getObservacoes();
        }
        if (dados.getPropriedadeId() != null) {
            this.infosPropriedade.setId(dados.getPropriedadeId());
        }
    }

}