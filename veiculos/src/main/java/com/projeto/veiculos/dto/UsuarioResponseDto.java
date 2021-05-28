package com.projeto.veiculos.dto;

import com.projeto.veiculos.model.Usuario;
import com.projeto.veiculos.model.Veiculo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioResponseDto {
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private List<VeiculoResponseDto> veiculos;


    public UsuarioResponseDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
        this.dataNascimento = usuario.getDataNascimento();
        this.veiculos = toVeiculoResponseDtoList(usuario.getVeiculos());
    }

    private List<VeiculoResponseDto> toVeiculoResponseDtoList(List<Veiculo> veiculos) {
        return veiculos.stream().map(VeiculoResponseDto::new).collect(Collectors.toList());
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

    public List<VeiculoResponseDto> getVeiculos() {
        return veiculos;
    }

}
