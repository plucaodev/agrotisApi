package com.agrotis.api.controllers;

import com.agrotis.api.domain.laboratorio.Laboratorio;
import com.agrotis.api.domain.laboratorio.LaboratorioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @PostMapping
    public ResponseEntity<Laboratorio> criarLaboratorio(@RequestBody Laboratorio laboratorio) {
        Laboratorio novoLaboratorio = laboratorioRepository.save(laboratorio);
        return new ResponseEntity<>(novoLaboratorio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Laboratorio>> obterTodosLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return new ResponseEntity<>(laboratorios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> obterLaboratorioPorId(@PathVariable Long id) {
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
        return laboratorio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Laboratorio>> filtrarLaboratorios(
            @RequestParam(value = "minPessoas", required = false) Long minPessoas) {

        List<Laboratorio> laboratorios = laboratorioRepository.filtrarLaboratorios(minPessoas);

        return laboratorios.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                new ResponseEntity<>(laboratorios, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Laboratorio> atualizarLaboratorio(@PathVariable Long id, @RequestBody Laboratorio laboratorio) {
        Optional<Laboratorio> laboratorioExistenteOptional = laboratorioRepository.findById(id);
        if (!laboratorioExistenteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Laboratorio laboratorioExistente = laboratorioExistenteOptional.get();

        laboratorioExistente.setNome(laboratorio.getNome());

        laboratorioRepository.save(laboratorioExistente);

        return ResponseEntity.ok(laboratorioExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLaboratorio(@PathVariable Long id) {
        if (!laboratorioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        laboratorioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
