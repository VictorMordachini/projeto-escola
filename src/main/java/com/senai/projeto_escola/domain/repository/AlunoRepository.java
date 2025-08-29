package com.senai.projeto_escola.domain.repository;

import com.senai.projeto_escola.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository <Aluno, String> {
}
