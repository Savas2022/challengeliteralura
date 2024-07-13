package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.models.Idioma;
import com.aluracursos.challengeliteralura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
        List<Libro> findByIdioma(Idioma idioma);
        Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
        //Metodo JLPA
        @Query("SELECT l FROM Libro l ORDER BY l.numeroDescargas DESC LIMIT 10")
        List<Libro> top10LibrosMasDescargados();

}
