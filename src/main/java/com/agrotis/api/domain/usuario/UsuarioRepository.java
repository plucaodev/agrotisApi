package com.agrotis.api.domain.usuario;

import com.agrotis.api.domain.laboratorio.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    int countByLaboratorio(Laboratorio laboratorio);
}