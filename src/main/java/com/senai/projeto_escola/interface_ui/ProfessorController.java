package com.senai.projeto_escola.interface_ui;

import com.senai.projeto_escola.application.dto.ProfessorDTO;
import com.senai.projeto_escola.application.service.ProfessorService;
import com.senai.projeto_escola.domain.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<ProfessorDTO> listarProfessores(){
        return professorService.listarProfessores();
    }

    @GetMapping("/{id}")
    public ProfessorDTO buscarProfessorPorID(@PathVariable String id){
        return professorService.buscarProfessorPorId(id);
    }

    @PostMapping
    public ProfessorDTO salvarProfessor(@RequestBody ProfessorDTO professorDTO){
        return professorService.salvarProfessor(professorDTO);
    }

    @PutMapping("/{id}")
    public ProfessorDTO editarProfessor(@PathVariable String id ,@RequestBody ProfessorDTO professorDTO){
        return professorService.atualizarProfessor(id,professorDTO);
    }
    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable String id){
        professorService.deletarProfessor(id);
    }
}
