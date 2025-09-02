package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "O título do curso não pode ser nulo, vazio ou apenas espaços em branco")
    @Size(min = 3, max = 100, message = "O título do curso deve ter entre 3 e 100 caracteres")
    private String titulo;

    @PositiveOrZero(message = "A carga horária deve ser maior ou igual a zero")
    private int cargaHoraria;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;
}
