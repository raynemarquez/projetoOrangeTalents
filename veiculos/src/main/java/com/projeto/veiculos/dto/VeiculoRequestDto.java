package com.projeto.veiculos.dto;

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
 //   @NotNull
 //   private Long codigoMarca;
    @NotBlank
    private String modelo;
 //   @NotBlank
 //   private String codigoModelo;
    @NotNull
    private Integer ano;
 //   @NotBlank
 //   private String codigoAno;
    //@NotBlank
    private String valor;
    @NotBlank
    private String combustivel;

    public Veiculo toVeiculo(){
        return new Veiculo(marca, modelo, ano, combustivel, valor);
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

    public String getValor() {
        return valor;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
}
