package com.projeto.veiculos.dto;

import com.projeto.veiculos.model.Usuario;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    @Email(message = "Digite um email válido")
    private String email;
    @NotBlank
    @CPF (message = "Digite um cpf válido")
    @Pattern(regexp = "^[0-9]{11}$", message = "Cpf deve conter apenas numeros")
    private String cpf;
    @NotNull @Past(message = "A data de nascimento deve ser anterior a data atual")
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
