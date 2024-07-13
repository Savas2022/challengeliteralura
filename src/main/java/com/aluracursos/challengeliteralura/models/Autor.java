package com.aluracursos.challengeliteralura.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="autors")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){

    }

    public Autor(DatosAutor datosAutor){
        this.nombre=datosAutor.nombre();
        this.fechaNacimiento=datosAutor.fechaNacimiento();
        this.fechaFallecimiento=datosAutor.fechaFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return  "-----AUTOR-----" +
                "\nNombre: " + nombre +
                "\nFecha de nacimiento: " + fechaNacimiento +
                "\nFecha de muerte: " + fechaFallecimiento +
                "\nLibros: " + this.libros.stream().map(Libro::getTitulo).collect(Collectors.toList())+
                "\n------------------\n";
    }
}
