package com.senai.projeto_escola.application.service;


import com.senai.projeto_escola.domain.entity.Curso;
import com.senai.projeto_escola.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso buscarCursoPorId(String id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizarCurso(String id, Curso cursoAtualizado) {
        if (!cursoRepository.existsById(id))
            return null;
        cursoAtualizado.setId(id);
        return cursoRepository.save(cursoAtualizado);
    }

    public void deletarCurso(String id) {
        cursoRepository.deleteById(id);
    }
}
