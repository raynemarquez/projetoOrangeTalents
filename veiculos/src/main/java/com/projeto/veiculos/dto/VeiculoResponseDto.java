package com.projeto.veiculos.dto;

import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.model.Veiculo;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class VeiculoResponseDto {
    private String marca;
    private String modelo;
    private Integer ano;
    private String valor;
    private String combustivel;
    private String diaDoRodizio;
    private Boolean rodizioAtivo;

    public VeiculoResponseDto(Veiculo veiculo) {
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getAno();
        this.valor = veiculo.getValor();
        this.combustivel = veiculo.getCombustivel();
        this.diaDoRodizio = defineDiaRodizio(ano);
        this.rodizioAtivo = verificaRodizioAtivo();
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

    public String getValor() {
        return valor;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getDiaDoRodizio() {
        return diaDoRodizio;
    }

    public Boolean getRodizioAtivo() {
        return rodizioAtivo;
    }

    private String defineDiaRodizio(Integer ano) {
        if (ano.toString().endsWith("0") || ano.toString().endsWith("1")  )   {
            diaDoRodizio = DayOfWeek.MONDAY.toString();
        }
        else if (ano.toString().endsWith("2") || ano.toString().endsWith("3")  )   {
            diaDoRodizio = DayOfWeek.TUESDAY.toString();
        }
        else if (ano.toString().endsWith("4") || ano.toString().endsWith("5")  )   {
            diaDoRodizio = DayOfWeek.WEDNESDAY.toString();
        }
        else if (ano.toString().endsWith("6") || ano.toString().endsWith("7")  )   {
            diaDoRodizio = DayOfWeek.THURSDAY.toString();
        }
        else if (ano.toString().endsWith("8") || ano.toString().endsWith("9")  )   {
            diaDoRodizio = DayOfWeek.FRIDAY.toString();
        }
        return diaDoRodizio;
    }

    private Boolean verificaRodizioAtivo(){
        return diaDoRodizio.equals(LocalDate.now().getDayOfWeek().toString());
    }
}
