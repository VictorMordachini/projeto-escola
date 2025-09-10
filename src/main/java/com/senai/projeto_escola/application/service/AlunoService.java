package com.senai.projeto_escola.application.service;

import com.senai.projeto_escola.application.dto.AlunoDTO;
import com.senai.projeto_escola.domain.entity.Aluno;
import com.senai.projeto_escola.domain.entity.Curso;
import com.senai.projeto_escola.domain.repository.AlunoRepository;
import com.senai.projeto_escola.domain.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public List<AlunoDTO> listarAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public AlunoDTO buscarAlunoPorId(String id) {
        return alunoRepository.findById(id)
                .map(AlunoDTO::fromEntity)
                .orElse(null);
    }

    public AlunoDTO salvarAluno(AlunoDTO dto) {
        Optional<Curso> cursoOpt = cursoRepository.findById(dto.idCurso());
        Aluno entidade = dto.toEntity(cursoOpt.get());
        Aluno salvo = alunoRepository.save(entidade);
        return AlunoDTO.fromEntity(salvo);
    }

    public AlunoDTO atualizarAluno(String id, AlunoDTO dto) {
        Optional<Aluno> alunoExistenteOpt = alunoRepository.findById(id);
        if (alunoExistenteOpt.isEmpty()) return null;

        Aluno existente = alunoExistenteOpt.get();
        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setTipo("Aluno");
        existente.setTurma(dto.turma());

        if (dto.idCurso() != null) {
            Optional<Curso> cursoOpt = cursoRepository.findById(dto.idCurso());
            cursoOpt.ifPresent(existente::setCurso);
        } else {
            existente.setCurso(null);
        }

        Aluno atualizado = alunoRepository.save(existente);
        return AlunoDTO.fromEntity(atualizado);
    }

    public void deletarAluno(String id) {
        alunoRepository.deleteById(id);
    }
}
