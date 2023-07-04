package com.example.FinalIntegradorBackEnd;

import com.example.FinalIntegradorBackEnd.entities.Domicilio;
import com.example.FinalIntegradorBackEnd.entities.Odontologo;
import com.example.FinalIntegradorBackEnd.entities.Paciente;
import com.example.FinalIntegradorBackEnd.entities.Turno;
import com.example.FinalIntegradorBackEnd.services.OdontologoService;
import com.example.FinalIntegradorBackEnd.services.PacienteService;
import com.example.FinalIntegradorBackEnd.services.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Av Santa fe", 444, "CABA");
        Paciente p = pacienteService.registrarPaciente(new Paciente("Santiago", "Paz", "88888888", domicilio, new java.sql.Date(2023,10,20)));
        Odontologo o = odontologoService.registrarOdontologo(new Odontologo("Santiago", "Paz", "3455647"));
        turnoService.registrarTurno(new Turno(pacienteService.registrarPaciente(p),odontologoService.registrarOdontologo(o),new java.sql.Date(2023,10,20)));
    }

    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/listarTurno").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
