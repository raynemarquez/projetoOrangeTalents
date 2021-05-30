package com.projeto.veiculos.controller;

import com.projeto.veiculos.dto.UsuarioRequestDto;
import com.projeto.veiculos.dto.UsuarioResponseDto;
import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> Adicionar(@Valid @RequestBody UsuarioRequestDto form, UriComponentsBuilder uriBuilder){

        if(!form.getCpf().matches("^[0-9]{11}$")){
            return ResponseEntity.badRequest().body("CPF deverá conter somente numeros");
        }

        Usuario usuario = form.toUsuario();
        if (usuarioRepository.existsByCpf(usuario.getCpf())){
            return ResponseEntity.badRequest().body("Já existe esse CPF cadastrado");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            return ResponseEntity.badRequest().body("Ja existe esse email cadastrado");
        }

        UsuarioResponseDto usuarioResponse = new UsuarioResponseDto(usuario);
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioResponse);
    }

    @GetMapping
    public List<UsuarioResponseDto> listar(){
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return usuarioList.stream().map(UsuarioResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity <UsuarioResponseDto> listarPorId(@PathVariable Long id){
        Optional <Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            UsuarioResponseDto usuarioResponse = new UsuarioResponseDto(usuario.get());
            return ResponseEntity.ok(usuarioResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
