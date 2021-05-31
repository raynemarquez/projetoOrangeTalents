package com.projeto.veiculos.externo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class FipeResponseDto {
    /*
        Valor: "R$ 99.197,00"
        Marca: "VW - VolksWagen"
        Modelo: "AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut"
        AnoModelo: 2014
        Combustivel: "Diesel"
        CodigoFipe: "005340-6"
        MesReferencia: "maio de 2021 "
        TipoVeiculo: 1
        SiglaCombustivel: "D"
     */
    @JsonAlias("Valor")
    private String valor;
    @JsonAlias("Marca")
    private String marca;
    @JsonAlias("Modelo")
    private String modelo;
    @JsonAlias("AnoModelo")
    private String anoModelo;
    @JsonAlias("Combustivel")
    private String combustivel;
    @JsonAlias("CodigoFipe")
    private String codigoFipe;
    @JsonAlias("MesReferencia")
    private String mesReferencia;
    @JsonAlias("TipoVeiculo")
    private String tipoVeiculo;
    @JsonAlias("SiglaCombustivel")
    private String siglaCombustivel;

    public String getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnoModelo() {return anoModelo;}

    public String getCombustivel() {
        return combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getSiglaCombustivel() {
        return siglaCombustivel;
    }

}
