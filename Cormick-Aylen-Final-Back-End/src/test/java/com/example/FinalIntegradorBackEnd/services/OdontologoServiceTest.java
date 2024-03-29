package com.example.FinalIntegradorBackEnd.services;

import com.example.FinalIntegradorBackEnd.dto.OdontologoDto;
import com.example.FinalIntegradorBackEnd.entities.Odontologo;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Before
    public void registrarOdontologosBefore() {
        Odontologo odontologoUno = new Odontologo("Aylen", "Cormick", "123456");
        odontologoService.registrarOdontologo(odontologoUno);

        Odontologo odontologoDos = new Odontologo("Rocio", "Silva", "654321");
        odontologoService.registrarOdontologo(odontologoDos);
    }

    @Test
    public void registrarOdontologoTest() {
        Odontologo odontologoUno = new Odontologo("Pepe", "Milanesa", "000111333");
        Odontologo od = odontologoService.registrarOdontologo(odontologoUno);

        Assertions.assertTrue((od != null));
        Assertions.assertTrue(od.getId() != null);
    }

    @Test
    public void buscarOdontologoIdTest () {
        Optional<OdontologoDto> o = odontologoService.buscarOdontologo(1);

        Assertions.assertTrue(o.get().getId() == 1);
    }


    @Test
    public void listarOdontologosTest () {
        List<OdontologoDto> odontologos = odontologoService.listarOdontologos();

        Assertions.assertTrue(odontologos.size() > 0);
    }

    @Test
    public void actualizarOdontologoTest() {
        Odontologo od = new Odontologo("Ayelen", "Cormick", "11111");
        od.setId(1);
        odontologoService.actualizarOdontologo(od);
        Assertions.assertTrue(od.getNombre() == "Ayelen");
    }

    @Test
    public void eliminarOdontologoTest() {
        boolean ret = odontologoService.eliminarOdontologo(2);
        Assertions.assertTrue(ret);
    }

}