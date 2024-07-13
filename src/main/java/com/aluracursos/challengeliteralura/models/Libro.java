package com.aluracursos.challengeliteralura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)//que no se repita el titulo
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id",nullable = false)
    private Autor autor;
    private Idioma idioma;
    private  Integer numeroDescargas;

    public Libro(){

    }
    public Libro(DatosLibro datosLibro){
        this.titulo=datosLibro.titulo();
        this.idioma=Idioma.fromString(datosLibro.idioma().toString().split(",")[0].trim());
        this.numeroDescargas=datosLibro.numeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? autor.getNombre() : "Autor desconocido";
        return  "-----LIBRO-----" +
                "\nTitulo:" + titulo +
                "\nAutor: " + nombreAutor+
                "\nIdioma: " + idioma +
                "\nDescargas: " + numeroDescargas +
                "\n------------------\n";
    }
}
