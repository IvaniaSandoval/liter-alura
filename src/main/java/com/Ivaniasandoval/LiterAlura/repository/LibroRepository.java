package com.Ivaniasandoval.LiterAlura.repository;

import com.Ivaniasandoval.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdiomasContaining(String idioma);
}
