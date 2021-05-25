package com.projeto.veiculos.dto;

import com.projeto.veiculos.model.Veiculo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VeiculoRequestDto {

    @NotNull
    private Long idProprietario;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotNull
    private Integer ano;

    public Veiculo toVeiculo(){
        return new Veiculo(marca, modelo, ano);
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }
}
