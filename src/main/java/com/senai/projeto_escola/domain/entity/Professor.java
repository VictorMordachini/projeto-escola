package com.senai.projeto_escola.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Professor extends Usuario {

    @ElementCollection
    @CollectionTable(name = "professor_turmas",
            joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "turmas")
    private List<String> turmas;

    @ElementCollection
    @CollectionTable(name = "professor_disciplinas",
            joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "disciplinas")
    private List<String> disciplinas;
}
