package com.Ivaniasandoval.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDeDescargas) {

    @Override
    public String toString() {
        return "----- LIBRO -----\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Idioma: " + idiomas + "\n" +
                "Numero de descargas: " + numeroDeDescargas + "\n" +
                "-----------------";
    }



}
