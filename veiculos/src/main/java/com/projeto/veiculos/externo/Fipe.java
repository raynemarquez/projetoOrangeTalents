package com.projeto.veiculos.externo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://parallelum.com.br/fipe/api/v1/" , name = "fipe")
public interface Fipe {

            @GetMapping("{tipoVeiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{ano}")
            FipeResponseDto buscaDadosFipe(@PathVariable("tipoVeiculo") String tipoVeiculo,
                                           @PathVariable("codigoMarca") String codigoMarca,
                                           @PathVariable("codigoModelo") String codigoModelo,
                                           @PathVariable("ano") String ano);

}
