package com.example.Final.Ingregrador.Back.End.models;

public class Domicilio {

    private Integer id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;


    // CONSTRUCTOR

    public Domicilio(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    // GETTERS


    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }
}
