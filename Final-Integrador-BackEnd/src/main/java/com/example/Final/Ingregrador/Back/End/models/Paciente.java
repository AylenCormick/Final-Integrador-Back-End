package com.example.Final.Ingregrador.Back.End.models;

import java.sql.Date;

public class Paciente {

    private Integer id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    private Date fechaAlta;


    // CONSTRUCTORES

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, Domicilio domicilio, Integer dni, Date fechaAlta) {
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

    public Date getFechaAlta() {
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

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    // TOSTRING

    public String domicilioToString() {
        return domicilio.getCalle() + " " + domicilio.getNumero() + " " + domicilio.getLocalidad() + " " + domicilio.getProvincia();
    }


}
