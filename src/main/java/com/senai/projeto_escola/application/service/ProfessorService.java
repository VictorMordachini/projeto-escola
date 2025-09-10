package com.senai.projeto_escola.application.service;


import com.senai.projeto_escola.application.dto.ProfessorDTO;
import com.senai.projeto_escola.domain.entity.Professor;
import com.senai.projeto_escola.domain.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional(readOnly = true)
    public List<ProfessorDTO> listarProfessores() {
        return professorRepository.findAll()
                .stream()
                .map(ProfessorDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProfessorDTO buscarProfessorPorId(String id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        return ProfessorDTO.fromEntity(professor);
    }

    public ProfessorDTO salvarProfessor(ProfessorDTO dto) {
        Professor entidade = dto.toEntity();
        Professor salvo = professorRepository.save(entidade);
        return ProfessorDTO.fromEntity(salvo);
    }

    public ProfessorDTO atualizarProfessor(String id, ProfessorDTO dto) {
        Professor existente = professorRepository.findById(id).orElse(null);

        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setTipo("Professor");
        existente.setTurmas(new ArrayList<>(dto.turmas()));
        existente.setDisciplinas(new ArrayList<>(dto.disciplinas()));

        Professor atualizado = professorRepository.save(existente);
        return ProfessorDTO.fromEntity(atualizado);
    }

    public void deletarProfessor(String id) {
        professorRepository.deleteById(id);
    }


}
