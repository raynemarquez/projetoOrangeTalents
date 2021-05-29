package com.projeto.veiculos.dto;

import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.model.Veiculo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VeiculoRequestDto {

    @NotNull
    private Long idProprietario;
    @NotBlank
    private String tipoVeiculo;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotNull
    private Integer ano;
    @NotBlank
    private String combustivel;

    public Veiculo toVeiculo(String valor, Usuario usuario){
        return new Veiculo(marca, modelo, ano, combustivel, valor, usuario);
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

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
}
