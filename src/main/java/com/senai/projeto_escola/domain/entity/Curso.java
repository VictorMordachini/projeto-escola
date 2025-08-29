package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Curso {

    private String titulo;

    private int cargaHoraria;
}
