package com.senai.projeto_escola.interface_ui;

import com.senai.projeto_escola.application.dto.AlunoDTO;
import com.senai.projeto_escola.application.service.AlunoService;
import com.senai.projeto_escola.domain.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<AlunoDTO> listarAlunos(){
        return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    public AlunoDTO buscarAlunoPorID(@PathVariable String id){
        return alunoService.buscarAlunoPorId(id);
    }

    @PostMapping
    public AlunoDTO salvarAluno(@RequestBody AlunoDTO alunoDTO){
        return alunoService.salvarAluno(alunoDTO);
    }

    @PutMapping("/{id}")
    public AlunoDTO editarAluno(@PathVariable String id ,@RequestBody AlunoDTO alunoDTO){
        return alunoService.atualizarAluno(id,alunoDTO);
    }
    @DeleteMapping("/{id}")
    public void deletarAluno(@PathVariable String id){
        alunoService.deletarAluno(id);
    }

}
