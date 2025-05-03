package com.agrotis.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.agrotis.api.controllers.UsuarioController;
import com.agrotis.api.domain.laboratorio.Laboratorio;
import com.agrotis.api.domain.laboratorio.LaboratorioDTO;
import com.agrotis.api.domain.laboratorio.LaboratorioRepository;
import com.agrotis.api.domain.propriedade.Propriedade;
import com.agrotis.api.domain.propriedade.PropriedadeDTO;
import com.agrotis.api.domain.propriedade.PropriedadeRepository;
import com.agrotis.api.domain.usuario.Usuario;
import com.agrotis.api.domain.usuario.UsuarioDTO;
import com.agrotis.api.domain.usuario.UsuarioRepository;
import com.agrotis.api.domain.usuario.UsuarioUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @Mock
    private PropriedadeRepository propriedadeRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Jon Doe");

        PropriedadeDTO propriedadeDTO = new PropriedadeDTO();
        propriedadeDTO.setNome("Fazenda X");
        usuarioDTO.setInfosPropriedade(propriedadeDTO);

        LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
        laboratorioDTO.setNome("Lab Teste");
        usuarioDTO.setLaboratorio(laboratorioDTO);

        usuarioDTO.setDataInicial(Instant.now());
        usuarioDTO.setDataFinal(Instant.now());

        Usuario usuario = new Usuario();
        usuario.setNome("Jon Doe");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(propriedadeRepository.save(any())).thenReturn(new Propriedade());
        when(laboratorioRepository.save(any())).thenReturn(new Laboratorio());

        ResponseEntity<UsuarioDTO> response = usuarioController.criarUsuario(usuarioDTO);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void obterTodosUsuariosTest() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("João");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria");

        List<Usuario> usuariosMock = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuariosMock);

        ResponseEntity<List<Usuario>> response = usuarioController.obterTodosUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("João", response.getBody().get(0).getNome());
        assertEquals("Maria", response.getBody().get(1).getNome());

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obterUsuarioExistentePorIdTest() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioController.obterUsuarioPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("João", response.getBody().getNome());

        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void obterUsuarioInexistentePorIdTest() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioController.obterUsuarioPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(usuarioRepository, times(1)).findById(99L);
    }

    @Test
    void atualizarUsuarioTest() {
        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
        dto.setId(1L);
        dto.setNome("Novo Nome");

        Usuario usuario = mock(Usuario.class);

        when(usuarioRepository.getReferenceById(1L)).thenReturn(usuario);

        ResponseEntity response = usuarioController.update(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioRepository, times(1)).getReferenceById(1L);
        verify(usuario, times(1)).atualizarDados(dto);
    }

    @Test
    void deletarUsuarioExistenteTest() {
        Long id = 1L;
        when(usuarioRepository.existsById(id)).thenReturn(true);

        ResponseEntity<Void> response = usuarioController.deletarUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioRepository, times(1)).existsById(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    void deletarUsuarioInexistenteTest() {
        Long id = 99L;
        when(usuarioRepository.existsById(id)).thenReturn(false);

        ResponseEntity<Void> response = usuarioController.deletarUsuario(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioRepository, times(1)).existsById(id);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

}

