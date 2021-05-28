package com.projeto.veiculos.controller;

import com.projeto.veiculos.dto.MarcasModelosDto;
import com.projeto.veiculos.dto.VeiculoRequestDto;
import com.projeto.veiculos.externo.Combustivel;
import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.model.Veiculo;
import com.projeto.veiculos.repository.UsuarioRepository;
import com.projeto.veiculos.repository.VeiculoRepository;
import com.projeto.veiculos.servico.Fipe;
import com.projeto.veiculos.servico.FipeResponse;
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

    @Autowired
    private Fipe fipeService;

    private String numCombustivel;

    @PostMapping
    public ResponseEntity<?> Adicionar(@Valid @RequestBody VeiculoRequestDto form, UriComponentsBuilder uriBuilder) {

        numCombustivel = buscaNumCombustivel(form.getCombustivel());

        /*
         * buscar valor FIPE
         */

        System.out.println(Combustivel.GASOLINA);
        FipeResponse dadosVeidulo = fipeService.buscaDadosFipe(form.getTipoVeiculo(),form.getMarca(), form.getModelo(), form.getAno() + "-" + numCombustivel);

        Optional<Usuario> possivelUsuario = usuarioRepository.findById(form.getIdProprietario());
        if (possivelUsuario.isEmpty()) return ResponseEntity.badRequest().body("Não existe proprietário para o id : " + form.getIdProprietario());
        Veiculo veiculo = form.toVeiculo(dadosVeidulo.getValor(), possivelUsuario.get());

        veiculoRepository.save(veiculo);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).body(veiculo);
    }

    private String buscaNumCombustivel(String nomeCombustivel) {
        String nCombustivel;
        switch (nomeCombustivel){
            case "Gasolina":
                nCombustivel="1";
                break;
            case "Etanol":
                nCombustivel="2";
                break;
            case "Diesel":
                nCombustivel="3";
                break;
            default:
                nCombustivel="0";
                break;
        }
        return  nCombustivel;
    }

    @GetMapping
    public List<Veiculo> listar(){
        return veiculoRepository.findAll();
    }

    /*
    @GetMapping("/{tipoVeiculo}")
    public ResponseEntity<List<MarcasDto>> getTipoVeiculo(@PathVariable String tipoVeiculo) {

        List<MarcasDto> fipe = fipeService.buscaDadosFipe(tipoVeiculo);

        return fipe != null ? ResponseEntity.ok().body(fipe) : ResponseEntity.notFound().build();
    }

     */

    @GetMapping("/{tipoVeiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{ano}")
    public ResponseEntity<FipeResponse> getVeiculo( @PathVariable String tipoVeiculo,
                                                           @PathVariable String codigoMarca,
                                                           @PathVariable String codigoModelo,
                                                           @PathVariable String ano) {
        System.out.println(tipoVeiculo + codigoMarca + codigoModelo + ano);
        FipeResponse fipe = fipeService.buscaDadosFipe(tipoVeiculo, codigoMarca, codigoModelo, ano );
        return fipe != null ? ResponseEntity.ok().body(fipe) : ResponseEntity.notFound().build();
    }
}
