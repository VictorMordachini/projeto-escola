package com.senai.projeto_escola.domain.repository;

import com.senai.projeto_escola.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository <Curso, String> {
}
