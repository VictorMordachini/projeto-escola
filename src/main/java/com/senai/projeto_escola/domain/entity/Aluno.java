package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Aluno extends Usuario{

    private Curso curso;

    private String turma;
}
