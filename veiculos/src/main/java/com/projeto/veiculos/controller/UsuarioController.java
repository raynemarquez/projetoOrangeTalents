package com.projeto.veiculos.controller;

import com.projeto.veiculos.dto.UsuarioRequestDto;
import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> Adicionar(@Valid @RequestBody UsuarioRequestDto form, UriComponentsBuilder uriBuilder){
        Usuario usuario = form.toUsuario();
        if (usuarioRepository.existsByCpf(usuario.getCpf())){
            return ResponseEntity.badRequest().body("Ja existe um cara com esse cpf");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            return ResponseEntity.badRequest().body("Ja existe um cara com esse email");
        }
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }


}
