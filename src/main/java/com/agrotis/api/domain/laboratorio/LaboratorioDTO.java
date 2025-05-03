package com.agrotis.api.domain.laboratorio;

public class LaboratorioDTO {
    private Long id;
    private String nome;

    public LaboratorioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public LaboratorioDTO() {

    }

    public Laboratorio convertToEntity(LaboratorioDTO laboratorioDTO) {
        if (laboratorioDTO == null) {
            return null;
        }

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(laboratorioDTO.getId());
        laboratorio.setNome(laboratorioDTO.getNome());

        return laboratorio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
