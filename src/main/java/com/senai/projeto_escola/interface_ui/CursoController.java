package com.senai.projeto_escola.interface_ui;


import com.senai.projeto_escola.application.service.CursoService;
import com.senai.projeto_escola.domain.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/{id}")
    public Curso buscarCursoPorId(@PathVariable String id) {
        return cursoService.buscarCursoPorId(id);
    }

    @PostMapping
    public Curso salvarCurso(@RequestBody Curso curso) {
        return cursoService.salvarCurso(curso);
    }

    @PutMapping("/{id}")
    public Curso editarCurso(@PathVariable String id, @RequestBody Curso curso) {
        return cursoService.atualizarCurso(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable String id) {
        cursoService.deletarCurso(id);
    }


}
