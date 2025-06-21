package com.Ivaniasandoval.LiterAlura.principal;

import com.Ivaniasandoval.LiterAlura.model.*;
import com.Ivaniasandoval.LiterAlura.repository.LibroRepository;
import com.Ivaniasandoval.LiterAlura.service.ConsumoAPI;
import com.Ivaniasandoval.LiterAlura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();

    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    private static final Map<String, String> idiomasDisponibles = Map.of(
            "es", "Español",
            "en", "Inglés",
            "fr", "Francés",
            "pt", "Portugués"

    );

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir 
                    """;
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            String opcionStr = teclado.nextLine();
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

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
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrendo la aplicación");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }
    }


    private DatosLibros getDatosLibro() {
        System.out.println("Escribe el titulo del libro que deceas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        //return datos;
        Optional<DatosLibros> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro Encontrado ");
            System.out.println(libroBuscado.get());
            return libroBuscado.get();
        } else {
            System.out.println("Libro no encontrado");
            return null;
        }

    }

    private void buscarLibro() {
        DatosLibros datos = getDatosLibro();
        if (datos != null) {
            boolean yaExiste = this.repositorio.findByTituloIgnoreCase(datos.titulo()).isPresent();

            if (yaExiste) {
                System.out.println("No se puede registrar un libro más de una vez.");
            } else {
                Libro libro = new Libro(datos);
                repositorio.save(libro);
                System.out.println("Libro registrado correctamente.");
            }
        }

    }

    private void listarLibros() {
        //datosLibros.forEach(System.out::println);
        List<Libro> libros = repositorio.findAll();


        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutores() {
        Set<String> autoresRegistrados = new HashSet<>();
        List<Libro> libros = repositorio.findAll();

        for (Libro libro : libros) {
            for (Autor autor : libro.getAutores()) {
                autoresRegistrados.add(autor.getNombre());
            }
        }

        if (autoresRegistrados.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            autoresRegistrados.forEach(System.out::println);
        }
    }


    private void listarAutoresVivos() {

        System.out.print("Ingresa el año que deseas consultar: ");
        int añoBuscado = teclado.nextInt();

        Set<String> autoresVivos = new HashSet<>();
        List<Libro> libros = repositorio.findAll();

        for (Libro libro : libros) {
            for (Autor autor : libro.getAutores()) {
                if (autor.getAñoDeNacimiento() <= añoBuscado && autor.getAñoDeFallecimiento() > añoBuscado) {
                    autoresVivos.add(autor.getNombre());
                }
            }
        }

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + añoBuscado);
        } else {
            System.out.println("Autores vivos en el año " + añoBuscado + ":");
            autoresVivos.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {

        System.out.println("\nIngrese el código de idioma para buscar los libros:");

        // Mostrar los idiomas disponibles dinámicamente
        idiomasDisponibles.forEach((codigo, nombre) ->
                System.out.println(codigo + " - " + nombre)
        );

        String codigoIdioma = teclado.nextLine().trim().toLowerCase();


        // Solo permitimos es y en porque así pide tu maestro
        Set<String> idiomasDisponibles = Set.of("es", "en", "fr", "pt");

        while (!idiomasDisponibles.contains(codigoIdioma)) {
            System.out.println("Código de idioma inválido. Solo puede ser 'es', 'en', 'fr' o 'pt'");
            codigoIdioma = teclado.nextLine().trim().toLowerCase();
        }

        List<Libro> libros = repositorio.findAll();

        String finalCodigoIdioma = codigoIdioma;
        List<Libro> librosFiltrados = libros.stream()
                .filter(libro -> libro.getIdiomas().contains(finalCodigoIdioma))
                .collect(Collectors.toList());

        if (librosFiltrados.isEmpty()) {
            System.out.println("No hay libros registrados en " + (codigoIdioma) + ".");
        } else {
            librosFiltrados.forEach(libro -> {
                System.out.println("----- LIBRO -----");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.print("Autores: ");
                libro.getAutores().forEach(autor -> System.out.print(autor.getNombre() + ", "));
                System.out.println();
                System.out.println("Número de Descargas: " + libro.getNumeroDeDescargas());
                System.out.println("-----------------");
            });
        }
    }


}















