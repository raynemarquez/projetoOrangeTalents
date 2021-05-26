package com.projeto.veiculos.servico;

public class FipeResponse {
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

    private Double valor;
    private String marca;
    private String modelo;
    private String AnoModelo;
    private String combustivel;
    private String codigoFipe;
    private String mesRef;
    private Integer tipoVeiculo;
    private String siglaCumbustivel;

    public Double getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnoModelo() {
        return AnoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public String getMesRef() {
        return mesRef;
    }

    public Integer getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getSiglaCumbustivel() {
        return siglaCumbustivel;
    }
}
