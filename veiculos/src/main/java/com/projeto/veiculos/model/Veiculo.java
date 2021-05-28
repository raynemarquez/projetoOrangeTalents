package com.projeto.veiculos.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
//    private String codigoMarca;
//    @Column(nullable = false)
    private String modelo;
//  @Column(nullable = false)
//  private String codigoModelo;
    @Column(nullable = false)
    private Integer ano;
//    @Column(nullable = false)
//    private String codigoAno;
    @Column(nullable = false)
    private String valor;
    @Column(nullable = false)
    private String combustivel;
    private String diaDoRodizio;
    @Transient
    private Boolean rodizioAtivo;
    @ManyToOne
    //@JoinColumn(name="proprietario_id", nullable=false)
    private Usuario proprietario;


    /** Hibernate Only
     *
     */
    @Deprecated
    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, Integer ano, String combustivel, String valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel= combustivel;
        this.valor = valor;
        diaDoRodizio = defineDiaRodizio(ano);
        rodizioAtivo= verificaRodizioAtivo();
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
    public Long getId() {
        return id;
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

    public String getDiaDoRodizio() {
        return diaDoRodizio;
    }

    public Boolean getRodizioAtivo() {
        return verificaRodizioAtivo();
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getValor() {
        return valor;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }
}
