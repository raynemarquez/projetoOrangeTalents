package com.projeto.veiculos.controller;

import com.projeto.veiculos.dto.UsuarioRequestDto;
import com.projeto.veiculos.dto.VeiculoRequestDto;
import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.model.Veiculo;
import com.projeto.veiculos.repository.UsuarioRepository;
import com.projeto.veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> Adicionar(@Valid @RequestBody VeiculoRequestDto form, UriComponentsBuilder uriBuilder) {
        Veiculo veiculo = form.toVeiculo();
        Optional<Usuario> possivelUsuario = usuarioRepository.findById(form.getIdProprietario());
        if (possivelUsuario.isPresent()){
            veiculo.setProprietario(possivelUsuario.get());
        }
        else{
            return ResponseEntity.badRequest().body("Não existe proprietário para o id : " + form.getIdProprietario());
        }

        veiculoRepository.save(veiculo);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).body(veiculo);
    }

    @GetMapping
    public List<Veiculo> listar(){
        return veiculoRepository.findAll();
    }


}
