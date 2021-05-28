package com.projeto.veiculos.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[0-9]{11}$", message = "Cpf deve conter apenas numeros")
    private String cpf;
    @Column(nullable = false) @Past
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "proprietario")
    private List<Veiculo> veiculos;

    /**
     * Hibernate only
     */
    @Deprecated
    public Usuario() {
    }

    public Usuario(String nome, String email, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
