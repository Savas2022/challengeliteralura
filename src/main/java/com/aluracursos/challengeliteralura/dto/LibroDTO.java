package com.aluracursos.challengeliteralura.dto;

import com.aluracursos.challengeliteralura.models.Idioma;

public record LibroDTO(
        Long id,
        String titulo,
        Idioma idioma,
        Integer numeroDescargas ) {

}
