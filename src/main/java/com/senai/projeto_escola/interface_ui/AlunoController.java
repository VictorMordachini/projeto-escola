package com.senai.projeto_escola.interface_ui;

import com.senai.projeto_escola.domain.entity.Aluno;
import com.senai.projeto_escola.domain.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable String id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable String id, @RequestBody Aluno alunoDetails) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.setNome(alunoDetails.getNome());
        aluno.setCpf(alunoDetails.getCpf());
        aluno.setCurso(alunoDetails.getCurso());
        aluno.setTurma(alunoDetails.getTurma());
        aluno.setTipo(alunoDetails.getTipo());

        return alunoRepository.save(aluno);

    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable String id) {
        alunoRepository.deleteById(id);
    }

}
