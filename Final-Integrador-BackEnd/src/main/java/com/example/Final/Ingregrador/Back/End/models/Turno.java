package com.example.Final.Ingregrador.Back.End.models;

import java.sql.Timestamp;

public class Turno {

    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Timestamp fechaTurno;


    // CONSTRUCTOR

    public Turno(Paciente paciente, Odontologo odontologo, Timestamp fechaTurno) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaTurno = fechaTurno;
    }


    // GETTERS

    public Integer getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public Timestamp getFechaTurno() {
        return fechaTurno;
    }


    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setFechaTurno(Timestamp fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
}
