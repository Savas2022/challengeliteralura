package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.models.*;
import com.aluracursos.challengeliteralura.repository.AutorRepository;
import com.aluracursos.challengeliteralura.repository.LibroRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    6 - Top 10 libros más descargados
                    7 - Libro más descargado y menos descargado
                    0 - Salir
                    ****************************
                    """;
            System.out.println(menu);
            System.out.println("Eliga una opcion del menu: ");
            while (!teclado.hasNextInt()) {
                System.out.println("Formato inválido, ingrese un número que este disponible en el menú!");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

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
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    top10Libros();
                    break;
                case 7:
                    libroMasYMenosDescargados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    //Metodo buscar libro mediante la api(titulo o parte del titulo)
    private DatosBusqueda getBusqueda(){
        System.out.println("Escribe el libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();
        try {
            var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
            System.out.println(json);
            //return conversor.obtenerDatos(json,DatosBusqueda.class);
            DatosBusqueda datos = conversor.obtenerDatos(json, DatosBusqueda.class);
            return datos;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Por favor, ingrese un nombre de libro válido.");
            return getBusqueda();
        }
    }

    private void buscarLibro(){
        DatosBusqueda datosBusqueda=getBusqueda();
        if (datosBusqueda!=null&&!datosBusqueda.resultado().isEmpty()){
            DatosLibro primerLibro= datosBusqueda.resultado().get(0);

            Libro libro= new Libro(primerLibro);
            System.out.println("*****Libro*****");
            System.out.println(libro);
            System.out.println("----------------");

            Optional<Libro> libroExiste=repositoryLibro.findByTituloContainsIgnoreCase(libro.getTitulo());
            if (libroExiste.isPresent()){
                System.out.println("El libro ya se encuentra registrado");
            }else{
                if (!primerLibro.autor().isEmpty()){
                    DatosAutor autor=primerLibro.autor().get(0);
                    Autor autorNuevo= new Autor(autor);
                    Optional<Autor> autorExiste= repositoryAutor.findByNombre(autorNuevo.getNombre());
                    if (autorExiste.isPresent()){
                        Autor autorAux=autorExiste.get();
                        libro.setAutor(autorAux);
                        repositoryLibro.save(libro);
                    }else{
                        Autor autorNew= repositoryAutor.save(autorNuevo);
                        libro.setAutor(autorNew);
                        repositoryLibro.save(libro);
                    }
                    Integer numeroDescargas=libro.getNumeroDescargas() !=null? libro.getNumeroDescargas() : 0;
                    System.out.println("--------Libro--------");
                    System.out.printf("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargas: %s%n",
                            libro.getTitulo(), autorNuevo.getNombre(), libro.getIdioma(), libro.getNumeroDescargas());
                    System.out.println("---------------------------\n");
                }else{
                    System.out.println("Sin autor");
                }
            }

        }else{
            System.out.println("Libro no encontrado");
        }
    }

    private void listarLibros(){
        libros=repositoryLibro.findAll();
        libros.stream().forEach(System.out::println);
    }

    private void listarAutores(){
        autors=repositoryAutor.findAll();
        autors.stream().forEach(System.out::println);
    }

    private void listarAutoresVivosPorAnio(){
        System.out.println("Ingrese el año a buscar de los autores vivos entre dicho año: ");
        var anio=teclado.nextInt();
        autors=repositoryAutor.listaAutoresVivosPorAnio(anio);
        autors.stream().forEach(System.out::println);
    }

    private List<Libro> datosBusquedaLenguaje(String idioma){
        var idiomaAux = Idioma.fromString(idioma);
        System.out.println("Lenguaje buscado: " + idiomaAux);

        List<Libro> libroPorIdioma = repositoryLibro.findByIdioma(idiomaAux);
        return libroPorIdioma;
    }

    private void listarLibrosPorIdioma(){
        System.out.println("Ingrese el idioma que desea buscar: ");
        var opcion = -1;
        while (opcion != 0) {
            var menuIdioma = """
                    1. en - Ingles
                    2. es - Español
                    3. fr - Francés
                    4. pt - Portugués
                    
                    0. Volver a Las opciones anteriores
                    """;
            System.out.println(menuIdioma);
            System.out.println("Eliga una opcion del menu: ");
            while (!teclado.hasNextInt()) {
                System.out.println("Formato inválido, ingrese un número que esté disponible en el menú");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    List<Libro> librosEnIngles = datosBusquedaLenguaje("[en]");
                    librosEnIngles.forEach(System.out::println);
                    break;
                case 2:
                    List<Libro> librosEnEspanol = datosBusquedaLenguaje("[es]");
                    librosEnEspanol.forEach(System.out::println);
                    break;
                case 3:
                    List<Libro> librosEnFrances = datosBusquedaLenguaje("[fr]");
                    librosEnFrances.forEach(System.out::println);
                    break;
                case 4:
                    List<Libro> librosEnPortugues = datosBusquedaLenguaje("[pt]");
                    librosEnPortugues.forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Ningún idioma seleccionado");
            }
        }

    }

    private void top10Libros(){
        var topLibros=repositoryLibro.top10LibrosMasDescargados();
        topLibros.stream().forEach(System.out::println);
    }

    private void libroMasYMenosDescargados(){
        libros=repositoryLibro.findAll();
        //Metodo estadisticos, aqui filtramos los libros con numero de descarga mayor a 0
        IntSummaryStatistics est= libros.stream()
                .filter(l->l.getNumeroDescargas()>0)
                .collect(Collectors.summarizingInt(Libro::getNumeroDescargas));
        var libroMasDescargado = libros.stream()
                .filter(l->l.getNumeroDescargas() == est.getMax())
                .findFirst()
                .orElse(null);
        var librosMenosDescargado = libros.stream()
                .filter(l->l.getNumeroDescargas() == est.getMin())
                .findFirst()
                .orElse(null);
        System.out.println("*************************************************");
        System.out.printf("%nLibro más descargado: %s%nNúmero de descargas: " +
                        "%d%n%nLibro menos descargado: %s%nNúmero de descargas: " +
                        "%d%n%n",libroMasDescargado.getTitulo(),est.getMax(),
                librosMenosDescargado.getTitulo(),est.getMin());
        System.out.println("*************************************************");

    }

}
