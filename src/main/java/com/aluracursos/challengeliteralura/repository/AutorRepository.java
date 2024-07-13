package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String nombre);
    //Metodo JLPA
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND a.fechaFallecimiento >= :anio")
    List<Autor> listaAutoresVivosPorAnio(int anio);

}
