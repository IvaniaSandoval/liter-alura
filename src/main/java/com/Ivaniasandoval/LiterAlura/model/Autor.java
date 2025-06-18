package com.Ivaniasandoval.LiterAlura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double añoDeNacimiento;
    private double añoDeFallecimiento;


    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.añoDeNacimiento = datosAutor.añoDeNacimiento();
        this.añoDeFallecimiento = datosAutor.añoDeFallecimiento();
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

    public double getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(double añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public double getAñoDeFallecimiento() {
        return añoDeFallecimiento;
    }

    public void setAñoDeFallecimiento(double añoDeFallecimiento) {
        this.añoDeFallecimiento = añoDeFallecimiento;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
