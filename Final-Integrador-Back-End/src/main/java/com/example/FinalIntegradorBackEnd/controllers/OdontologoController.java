package com.example.FinalIntegradorBackEnd.controllers;

import com.example.FinalIntegradorBackEnd.dto.OdontologoDto;
import com.example.FinalIntegradorBackEnd.entities.Odontologo;
import com.example.FinalIntegradorBackEnd.entities.Paciente;
import com.example.FinalIntegradorBackEnd.services.OdontologoService;
import com.example.FinalIntegradorBackEnd.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity<String> respuesta = null;

        if(odontologoService.registrarOdontologo(odontologo) != null){
            respuesta = ResponseEntity.ok("El odontologo fue registrado con éxito");
        } else{
            respuesta = ResponseEntity.internalServerError().body("No se pudo registrar el odontologo");
        }

        return respuesta;
    }

    @GetMapping("/listarOdontologos")
    public ResponseEntity<List<OdontologoDto>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologo(@PathVariable Integer id) {
        Optional odontologo = odontologoService.buscarOdontologo(id);

        if (odontologo.isPresent()) {
            return ResponseEntity.ok((OdontologoDto) odontologo.get());
        } else {
            return null;
        }

    }

    @PutMapping("/modificar")
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> response = null;

        if (odontologo.getId() != null && odontologoService.buscarOdontologo(odontologo.getId()).isPresent()) {
            response = ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (odontologoService.buscarOdontologo(id).isPresent()) {
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.ok("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

}
