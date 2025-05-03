package com.agrotis.api.domain.laboratorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    @Query(value = "SELECT l.* FROM laboratorio l " +
            "LEFT JOIN usuario u ON u.laboratorio_id = l.id " +
            "GROUP BY l.id " +
            "HAVING COUNT(u.id) >= :minPessoas " +
            "ORDER BY COUNT(u.id) DESC", nativeQuery = true)
    List<Laboratorio> filtrarLaboratorios(
            @Param("minPessoas") long minPessoas);


}



