package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Aluno extends Usuario {

    @NotNull(message = "O curso não pode ser nulo")
    @ManyToOne
    private Curso curso;

    @NotBlank(message = "A turma não pode ser nula, vazia ou apenas espaços em branco")
    @Size(min = 1, max = 50, message = "A turma deve ter entre 1 e 50 caracteres")
    private String turma;
}
