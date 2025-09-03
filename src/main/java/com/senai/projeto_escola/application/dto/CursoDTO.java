package com.senai.projeto_escola.application.dto;


import com.senai.projeto_escola.domain.entity.Curso;

public record CursoDTO(
        String titulo,
        int cargaHoraria
) {
    public static CursoDTO fromEntity(Curso curso){
        if (curso == null) return null;
        return new CursoDTO(
                curso.getTitulo(),
                curso.getCargaHoraria()
        );
    }

    public Curso toEntity(){
        Curso curso = new Curso();
        curso.setTitulo(this.titulo);
        curso.setCargaHoraria(this.cargaHoraria);
        return curso;
    }


}
