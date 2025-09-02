package com.senai.projeto_escola.application.service;

import com.senai.projeto_escola.domain.entity.Aluno;
import com.senai.projeto_escola.domain.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(String id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizarAluno(String id, Aluno alunoAtualizado) {
        if (!alunoRepository.existsById(id))
            return null;
        alunoAtualizado.setId(id);
        return alunoRepository.save(alunoAtualizado);
    }

    public void deletarAluno(String id) {
        alunoRepository.deleteById(id);
    }
}
