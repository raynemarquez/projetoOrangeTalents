package com.projeto.veiculos.controller;

import com.projeto.veiculos.dto.VeiculoRequestDto;
import com.projeto.veiculos.dto.VeiculoResponseDto;
import com.projeto.veiculos.externo.Fipe;
import com.projeto.veiculos.externo.FipeResponseDto;
import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.model.Veiculo;
import com.projeto.veiculos.repository.UsuarioRepository;
import com.projeto.veiculos.repository.VeiculoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Fipe fipeService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> Adicionar(@Valid @RequestBody VeiculoRequestDto form, UriComponentsBuilder uriBuilder) {
        String numCombustivel = buscaNumCombustivel(form.getCombustivel());
        if (numCombustivel.equals("0")) return ResponseEntity.badRequest().body("Campo combustivel inválido");
        try {
            // buscar valor do carro na tabela FIPE, usando o servico
            FipeResponseDto dadosVeiculo = fipeService.buscaDadosFipe(form.getTipoVeiculo(),form.getMarca(),  form.getModelo(), form.getAno() + "-" + numCombustivel);
            Optional<Usuario> possivelUsuario = usuarioRepository.findById(form.getIdProprietario());
            if (possivelUsuario.isEmpty()) return ResponseEntity.badRequest().body("Não existe proprietário para o id : " + form.getIdProprietario());
            Veiculo veiculo = form.toVeiculo(dadosVeiculo.getValor(), possivelUsuario.get());
            VeiculoResponseDto veiculoResponse = new VeiculoResponseDto(veiculo);
            veiculoRepository.save(veiculo);

            URI uri = uriBuilder.path("/{id}").buildAndExpand(veiculo.getId()).toUri();
            return ResponseEntity.created(uri).body(veiculoResponse);
        }
        catch (FeignException.NotFound erro){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Url da Fipe não encontrada");
        }
    }

    @GetMapping
    public List<VeiculoResponseDto> listar(){
        List <Veiculo> veiculoList  = veiculoRepository.findAll();
        return veiculoList.stream().map(VeiculoResponseDto::new).collect(Collectors.toList());
    }

    // exemplo de url: "localhost:8080/veiculos/carros/marcas/5/modelos/24/anos/1998-3"
    @GetMapping("/{tipoVeiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{ano}")
    public ResponseEntity<FipeResponseDto> buscarVeiculo(@PathVariable String tipoVeiculo,
                                                         @PathVariable String codigoMarca,
                                                         @PathVariable String codigoModelo,
                                                         @PathVariable String ano) {

        FipeResponseDto fipe = fipeService.buscaDadosFipe(tipoVeiculo, codigoMarca, codigoModelo, ano );
        return fipe != null ? ResponseEntity.ok().body(fipe) : ResponseEntity.notFound().build();
    }

    private String buscaNumCombustivel(String nomeCombustivel) {
        String numCombustivel;
        switch (nomeCombustivel.toUpperCase(Locale.ROOT)){
            case "GASOLINA":
                numCombustivel="1";
                break;
            case "ETANOL":
                numCombustivel="2";
                break;
            case "DIESEL":
                numCombustivel="3";
                break;
            default:
                numCombustivel="0";
                break;
        }
        return  numCombustivel;
    }
}
