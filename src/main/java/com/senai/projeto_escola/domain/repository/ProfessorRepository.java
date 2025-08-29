package com.senai.projeto_escola.domain.repository;

import com.senai.projeto_escola.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
