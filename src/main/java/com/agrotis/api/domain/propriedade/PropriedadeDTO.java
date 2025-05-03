package com.agrotis.api.domain.propriedade;

public class PropriedadeDTO {
    private Long id;
    private String nome;

    public PropriedadeDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public PropriedadeDTO() {

    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
