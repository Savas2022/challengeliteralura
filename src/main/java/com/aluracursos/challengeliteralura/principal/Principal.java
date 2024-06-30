package com.aluracursos.challengeliteralura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner teclado=new Scanner(System.in);

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
            System.out.println("Eliga una opcion: ");
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
