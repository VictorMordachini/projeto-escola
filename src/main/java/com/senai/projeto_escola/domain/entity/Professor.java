package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Professor extends Usuario{

    private List<String> turmas;

    private List<String> disciplinas;
}
