package com.projeto.veiculos.dto;

import com.projeto.veiculos.model.Usuario;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    @Email(message = "Digite um email válido")
    private String email;
    @NotBlank
    @CPF (message = "Digite um cpf válido")
    private String cpf;
    @NotNull
    private LocalDate dataNascimento;

    public Usuario toUsuario(){
        return new Usuario(nome, email, cpf, dataNascimento);
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
}
