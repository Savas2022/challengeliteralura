package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.models.Autor;
import com.aluracursos.challengeliteralura.models.Libro;
import com.aluracursos.challengeliteralura.repository.AutorRepository;
import com.aluracursos.challengeliteralura.repository.LibroRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado=new Scanner(System.in);
    private ConsumoAPI consumoAPI=new ConsumoAPI();
    private final String URL_BASE="https://gutendex.com/books/?search=";
    private ConvierteDatos conversor=new ConvierteDatos();
    private LibroRepository repositoryLibro;
    private AutorRepository repositoryAutor;
    private List<Autor> autors;
    private List<Libro> libros;

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor){
        this.repositoryLibro=repositoryLibro;
        this.repositoryAutor=repositoryAutor;
    }

    public void menu(){
        var opcion = -1;
        while(opcion!=0){
            var menu = """
                    ****************************
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma 
                    0 - Salir
                    ****************************
                    """;
            System.out.println(menu);
            System.out.println("Eliga una opcion del menu: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                /*
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosMedianteAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
               */
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
            

        }
    }


}
