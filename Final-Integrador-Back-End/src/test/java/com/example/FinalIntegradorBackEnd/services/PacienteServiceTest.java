package com.example.FinalIntegradorBackEnd.services;

import com.example.FinalIntegradorBackEnd.dto.PacienteDto;
import com.example.FinalIntegradorBackEnd.entities.Domicilio;
import com.example.FinalIntegradorBackEnd.entities.Odontologo;
import com.example.FinalIntegradorBackEnd.entities.Paciente;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Before
    public void registrarTurnosBefore() {

        ///// PACIENTE UNO /////

        Domicilio domicilioUno = new Domicilio("Calle falsa", 123, "Una");
        Paciente paciente = new Paciente();
        paciente.setNombre("Aylen");
        paciente.setApellido("Cormick");
        paciente.setDomicilio(domicilioUno);
        paciente.setDni("12345678");
        paciente.setFechaAlta(new Date(2023,6,30));
        pacienteService.registrarPaciente(paciente);

        ///// PACIENTE DOS /////

        Domicilio domicilioDos = new Domicilio("Av false", 456, "Otra");
        Paciente pacienteDos = new Paciente();
        pacienteDos.setNombre("Rocio");
        pacienteDos.setApellido("Silva");
        pacienteDos.setDomicilio(domicilioDos);
        pacienteDos.setDni("87654321");
        pacienteDos.setFechaAlta(new Date(2023,6,30));
        pacienteService.registrarPaciente(pacienteDos);

    }

    @Test
    public void registrarPacientesTest() {
        Domicilio domicilio = new Domicilio("Av false", 456, "otra");
        Paciente paciente = new Paciente();
        paciente.setNombre("Aylen");
        paciente.setApellido("Cormick");
        Paciente pc = pacienteService.registrarPaciente(paciente);

        Assertions.assertTrue((pc != null));
        Assertions.assertTrue(pc.getId() != null);
    }

    @Test
    public void buscarPacienteIdTest () {
        Optional<PacienteDto> p = pacienteService.buscarPaciente(1);

        Assertions.assertTrue(p.get().getId() == 1);
    }

    @Test
    public void listarPacientesTest () {
        List<PacienteDto> pacientes = pacienteService.listarPacientes();

        Assertions.assertTrue(pacientes.size() > 0);
    }

//    @Test
//    public void actualizarPacienteTest() {
//        Paciente pa = new Paciente();
//        pa.setNombre("Ayelen");
//        pa.setDomicilio(new Domicilio("Calle falsa", 123, "Una"));
//        pa.setDni("12345678");
//        pa.setFechaAlta(new Date(2023,6,30));
//        pa.setId(1);
//        pacienteService.actualizarPaciente(pa);
//
//        Assertions.assertTrue(pacienteService.buscarPaciente(1).get().getNombre() == "Ayelen");
//    }

    @Test
    public void eliminarPacienteTest() {
        boolean ret = pacienteService.eliminarPaciente(2);
        Assertions.assertTrue(ret);
    }


}