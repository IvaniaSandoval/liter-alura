package com.Ivaniasandoval.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    private Double numeroDeDescargas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "libro_id") // clave foránea en la tabla Autor
    private List<Autor> autores;

    public Libro() {
    }

    // Constructor desde DatosLibros
    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.autores = datosLibros.autor()
                .stream()
                .map(Autor::new)  //
                .toList();
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

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "----- LIBRO -----\n" +
                //"id=" + id +
                " titulo = " + titulo + '\'' + '\n' +
                " idiomas = " + idiomas + '\n' +
                " autores = " + autores + '\n' +
                " numeroDeDescargas = " + numeroDeDescargas + '\n' +
                "-----------------";
    }
}



