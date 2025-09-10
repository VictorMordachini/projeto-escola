package com.senai.projeto_escola.application.dto;

import com.senai.projeto_escola.domain.entity.Aluno;
import com.senai.projeto_escola.domain.entity.Curso;

public record AlunoDTO(
        String id,
        String nome,
        Long cpf,
        String idCurso,
        String turma
) {
    public static AlunoDTO fromEntity(Aluno aluno) {
        if (aluno == null) return null;
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getCurso() != null ? aluno.getCurso().getId() : null,
                aluno.getTurma()
        );
    }

    public Aluno toEntity(Curso curso) {
        Aluno aluno = new Aluno();
        aluno.setNome(this.nome);
        aluno.setCpf(this.cpf);
        aluno.setTipo("Aluno");
        aluno.setCurso(curso);
        aluno.setTurma(this.turma);
        return aluno;
    }

}
