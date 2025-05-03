package com.agrotis.api.controllers;

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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setDataInicial(usuarioDTO.getDataInicial());
        usuario.setDataFinal(usuarioDTO.getDataFinal());
        usuario.setObservacoes(usuarioDTO.getObservacoes());
        usuario.setInfosPropriedade(usuarioDTO.getInfosPropriedade().getNome(), usuario.getId());

        Laboratorio laboratorio;
        if (usuarioDTO.getLaboratorio().getId() != null && laboratorioRepository.existsById(usuarioDTO.getLaboratorio().getId())) {
            laboratorio = laboratorioRepository.findById(usuarioDTO.getLaboratorio().getId()).orElseThrow(() -> new EntityNotFoundException("Laboratório não encontrado"));
        } else {
            laboratorio = new Laboratorio();
            laboratorio.setNome(usuarioDTO.getLaboratorio().getNome());
            laboratorioRepository.save(laboratorio);
        }

        usuario.setLaboratorio(laboratorio);

        Propriedade propriedade = new Propriedade();
        propriedade.setNome(usuarioDTO.getInfosPropriedade().getNome());
        propriedadeRepository.save(propriedade);

        usuario.setInfosPropriedade(propriedade.getNome(), propriedade.getId());

        usuarioRepository.save(usuario);

        UsuarioDTO usuarioResponseDTO = new UsuarioDTO(
                usuario.getNome(),
                usuario.getDataInicial(),
                usuario.getDataFinal(),
                new PropriedadeDTO(propriedade.getId(), propriedade.getNome()),
                new LaboratorioDTO(usuario.getLaboratorio().getId(), usuario.getLaboratorio().getNome()),
                usuario.getObservacoes()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UsuarioUpdateDTO dados) {
        var usuario = usuarioRepository.getReferenceById(dados.getId());
        usuario.atualizarDados(dados);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
