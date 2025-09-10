package com.senai.projeto_escola.application.dto;

import com.senai.projeto_escola.domain.entity.Professor;

import java.util.ArrayList;
import java.util.List;

public record ProfessorDTO(
        String id,
        String nome,
        Long cpf,
        List<String> turmas,
        List<String> disciplinas
) {
    public static ProfessorDTO fromEntity(Professor professor) {
        if (professor == null) return null;
        return new ProfessorDTO(
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                professor.getTurmas() != null ? professor.getTurmas() : List.of(),
                professor.getDisciplinas() != null ? professor.getDisciplinas() : List.of()
        );
    }

    public Professor toEntity() {
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setCpf(this.cpf);
        professor.setTipo("Professor");
        professor.setTurmas(this.turmas != null ? new ArrayList<>(this.turmas) : new ArrayList<>());
        professor.setDisciplinas(this.disciplinas != null ? new ArrayList<>(this.disciplinas) : new ArrayList<>());
        return professor;
    }

}
