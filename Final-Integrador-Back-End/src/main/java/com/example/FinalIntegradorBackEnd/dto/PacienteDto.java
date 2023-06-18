package com.example.FinalIntegradorBackEnd.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class PacienteDto {

    private Integer id;
    private String nombre;
    private String apellido;


    public PacienteDto() {
    }

    public PacienteDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
