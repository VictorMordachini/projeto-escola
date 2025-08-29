package com.senai.projeto_escola.interface_ui;

import com.senai.projeto_escola.domain.entity.Professor;
import com.senai.projeto_escola.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Professor getProfessorById(@PathVariable String id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable String id, @RequestBody Professor professorDetails) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        professor.setNome(professorDetails.getNome());
        professor.setCpf(professorDetails.getCpf());
        professor.setTurmas(professorDetails.getTurmas());
        professor.setDisciplinas(professorDetails.getDisciplinas());
        professor.setTipo(professorDetails.getTipo());

        return professorRepository.save(professor);

    }
}
