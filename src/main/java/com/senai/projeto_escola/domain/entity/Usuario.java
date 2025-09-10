package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data // getters, setters, construtor (completo e vazio), toString etc
@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Nome não pode ser nulo")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    private Long cpf;

    @NotBlank(message = "Tipo não pode ser nulo")
    private String tipo;

}
