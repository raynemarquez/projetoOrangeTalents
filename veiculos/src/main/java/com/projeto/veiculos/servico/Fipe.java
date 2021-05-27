package com.projeto.veiculos.servico;

import com.projeto.veiculos.dto.MarcasDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url= "https://parallelum.com.br/fipe/api/v1/" , name = "fipe")
public interface Fipe {

            /*
            @GetMapping("{tipoVeiculo}/marcas")
            List<MarcasDto> buscaDadosFipe(@PathVariable("tipoVeiculo") String tipoVeiculo);
            */


            @GetMapping("{tipoVeiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{ano}")
            FipeResponse buscaDadosFipe(@PathVariable("tipoVeiculo") String tipoVeiculo,
                                           @PathVariable("codigoMarca") String codigoMarca,
                                           @PathVariable("codigoModelo") String codigoModelo,
                                           @PathVariable("ano") String ano);

}
