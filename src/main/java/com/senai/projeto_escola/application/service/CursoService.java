package com.senai.projeto_escola.application.service;


import com.senai.projeto_escola.application.dto.CursoDTO;
import com.senai.projeto_escola.domain.entity.Curso;
import com.senai.projeto_escola.domain.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(CursoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public CursoDTO buscarCursoPorId(String id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        return CursoDTO.fromEntity(curso);
    }

    public CursoDTO salvarCurso(CursoDTO dto) {
        Curso salvo = cursoRepository.save(dto.toEntity());
        return CursoDTO.fromEntity(salvo);
    }

    public CursoDTO atualizarCurso(String id, CursoDTO dto) {
        Curso existente = cursoRepository.findById(id).orElse(null);

        existente.setTitulo(dto.titulo());
        existente.setCargaHoraria(dto.cargaHoraria());

        Curso atualizado = cursoRepository.save(existente);
        return CursoDTO.fromEntity(atualizado);
    }

    public void deletarCurso(String id) {
        cursoRepository.deleteById(id);
    }
}
