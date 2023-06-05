package com.example.Final.Ingregrador.Back.End.models;

import java.time.LocalDate;

public class Administrador {

    private Integer id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    private LocalDate fechaAlta;


    // CONSTRUCTORES

    public Administrador() {
    }

    public Administrador(String nombre, String apellido, Domicilio domicilio, Integer dni, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }


    // GETTERS

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public Integer getDni() {
        return dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }


    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
