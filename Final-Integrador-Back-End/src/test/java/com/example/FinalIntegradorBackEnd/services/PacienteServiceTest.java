package com.example.FinalIntegradorBackEnd.services;

import com.example.FinalIntegradorBackEnd.dto.PacienteDto;
import com.example.FinalIntegradorBackEnd.entities.Domicilio;
import com.example.FinalIntegradorBackEnd.entities.Paciente;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    public void registrarPacientesTest() {
        Domicilio domicilio = new Domicilio("Av false", 456, "otra");
        Paciente paciente = new Paciente();
        paciente.setNombre("Aylen");
        paciente.setApellido("Cormick");
        String stg = pacienteService.registrarPaciente(paciente);
        Assertions.assertTrue((stg != null));
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

    @Test
    public void eliminarPacienteTest() {
        boolean ret = pacienteService.eliminarPaciente(2);
        Assertions.assertTrue(ret);
    }


}