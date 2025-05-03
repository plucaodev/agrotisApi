package com.agrotis.api.domain.laboratorio;

import com.agrotis.api.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "laboratorio")
    private List<Usuario> usuarios = new ArrayList<>();

    public Laboratorio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Laboratorio() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {}

    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : null;
    }

    public int getQuantidadeUsuarios() {
        return usuarios != null ? usuarios.size() : 0;
    }

}

