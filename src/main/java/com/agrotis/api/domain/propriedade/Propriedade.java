package com.agrotis.api.domain.propriedade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Propriedade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Propriedade(Long id, String nome) {
    }

    public Propriedade() {
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
