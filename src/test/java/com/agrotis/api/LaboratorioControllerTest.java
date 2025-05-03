package com.agrotis.api;

import com.agrotis.api.controllers.LaboratorioController;
import com.agrotis.api.domain.laboratorio.Laboratorio;
import com.agrotis.api.domain.laboratorio.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratorioControllerTest {

    @InjectMocks
    private LaboratorioController laboratorioController;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarLaboratorio() {
        Laboratorio lab = new Laboratorio();
        lab.setNome("Lab Teste");

        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(lab);

        ResponseEntity<Laboratorio> response = laboratorioController.criarLaboratorio(lab);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("LAB TESTE", response.getBody().getNome());
    }

    @Test
    void deveObterTodosLaboratorios() {
        Laboratorio l1 = new Laboratorio(); l1.setNome("L1");
        Laboratorio l2 = new Laboratorio(); l2.setNome("L2");

        when(laboratorioRepository.findAll()).thenReturn(List.of(l1, l2));

        ResponseEntity<List<Laboratorio>> response = laboratorioController.obterTodosLaboratorios();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void deveObterLaboratorioPorId() {
        Laboratorio lab = new Laboratorio(); lab.setId(1L); lab.setNome("L1");

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(lab));

        ResponseEntity<Laboratorio> response = laboratorioController.obterLaboratorioPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("L1", response.getBody().getNome());
    }

    @Test
    void deveRetornarNotFoundSeNaoExistirLaboratorio() {
        when(laboratorioRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Laboratorio> response = laboratorioController.obterLaboratorioPorId(99L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deveAtualizarLaboratorio() {
        Laboratorio labExistente = new Laboratorio();
        labExistente.setId(1L);
        labExistente.setNome("Antigo");

        Laboratorio labParaAtualizar = new Laboratorio();
        labParaAtualizar.setId(1L);
        labParaAtualizar.setNome("Atualizado");

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(labExistente)); // Simula que o laboratório existe
        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(labExistente); // Simula o salvamento bem-sucedido do laboratório atualizado

        ResponseEntity<Laboratorio> response = laboratorioController.atualizarLaboratorio(1L, labParaAtualizar);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("ATUALIZADO", response.getBody().getNome()); // Verifica se o nome foi atualizado corretamente
        verify(laboratorioRepository, times(1)).save(labExistente); // Verifica se o método save foi chamado
    }


    @Test
    void deveDeletarLaboratorio() {
        when(laboratorioRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = laboratorioController.deletarLaboratorio(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(laboratorioRepository).deleteById(1L);
    }

    @Test
    void deveFiltrarLaboratorios() {
        Laboratorio lab = new Laboratorio();
        lab.setNome("Filtrado");
        lab.setId(1L);

        when(laboratorioRepository.filtrarLaboratorios(anyLong()))
                .thenReturn(Collections.singletonList(lab));

        ResponseEntity<List<Laboratorio>> response = laboratorioController.filtrarLaboratorios(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals("FILTRADO", response.getBody().get(0).getNome());
    }
}
