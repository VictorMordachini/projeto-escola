package com.senai.projeto_escola.interface_ui;

import com.senai.projeto_escola.application.service.ProfessorService;
import com.senai.projeto_escola.domain.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listarProfessores(){
        return professorService.listarProfessores();
    }

    @GetMapping("/{id}")
    public Professor buscarProfessorPorID(@PathVariable String id){
        return professorService.buscarProfessorPorId(id);
    }

    @PostMapping
    public Professor salvarProfessor(@RequestBody Professor professor){
        return professorService.salvarProfessor(professor);
    }

    @PutMapping("/{id}")
    public Professor editarProfessor(@PathVariable String id ,@RequestBody Professor professor){
        return professorService.atualizarProfessor(id,professor);
    }
    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable String id){
        professorService.deletarProfessor(id);
    }
}
