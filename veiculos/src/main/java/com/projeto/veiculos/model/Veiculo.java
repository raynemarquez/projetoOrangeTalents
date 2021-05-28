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
    @ManyToOne
    //@JoinColumn(name="proprietario_id", nullable=false)
    private Usuario proprietario;


    /** Hibernate Only
     *
     */
    @Deprecated
    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, Integer ano, String combustivel, String valor, Usuario usuario) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel= combustivel;
        this.valor = valor;
        proprietario = usuario;
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

    public String getCombustivel() {
        return combustivel;
    }

    public String getValor() {
        return valor;
    }

}
