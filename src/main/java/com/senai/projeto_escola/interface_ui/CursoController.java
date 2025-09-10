package com.senai.projeto_escola.interface_ui;


import com.senai.projeto_escola.application.dto.CursoDTO;
import com.senai.projeto_escola.application.service.CursoService;
import com.senai.projeto_escola.domain.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<CursoDTO> listarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/{id}")
    public CursoDTO buscarCursoPorId(@PathVariable String id) {
        return cursoService.buscarCursoPorId(id);
    }

    @PostMapping
    public CursoDTO salvarCurso(@RequestBody CursoDTO cursoDTO) {
        return cursoService.salvarCurso(cursoDTO);
    }

    @PutMapping("/{id}")
    public CursoDTO editarCurso(@PathVariable String id, @RequestBody CursoDTO cursoDTO) {
        return cursoService.atualizarCurso(id, cursoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable String id) {
        cursoService.deletarCurso(id);
    }


}
