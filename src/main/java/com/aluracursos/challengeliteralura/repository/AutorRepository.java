package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> finByNombreContainsIgnoreCase(String nombre);

}
