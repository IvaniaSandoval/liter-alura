package com.Ivaniasandoval.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor (
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") double añoDeNacimiento,
        @JsonAlias("death_year") double  añoDeFallecimiento
        ) {

        @Override
        public String toString() {
                return nombre ;
        }
}
