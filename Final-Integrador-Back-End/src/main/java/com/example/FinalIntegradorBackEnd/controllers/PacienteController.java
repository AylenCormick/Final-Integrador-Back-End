package com.example.FinalIntegradorBackEnd.controllers;

import com.example.FinalIntegradorBackEnd.dto.PacienteDto;
import com.example.FinalIntegradorBackEnd.entities.Paciente;
import com.example.FinalIntegradorBackEnd.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<String> respuesta = null;

        if(pacienteService.registrarPaciente(paciente) != null){
            respuesta = ResponseEntity.ok("El paciente fue registrado con éxito");
        } else{
            respuesta = ResponseEntity.internalServerError().body("No se pudo registrar el paciente");
        }

        return respuesta;
    }

    @GetMapping("/listarPacientes")
    public ResponseEntity<List<PacienteDto>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPaciente(@PathVariable Integer id) {
        Optional paciente = pacienteService.buscarPaciente(id);

        if (paciente.isPresent()) {
            return ResponseEntity.ok((PacienteDto) paciente.get());
        } else {
            return null;
        }

    }

    @PutMapping("/modificar")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;

        if (paciente.getId() != null && pacienteService.buscarPaciente(paciente.getId()).isPresent()) {
            response = ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscarPaciente(id).isPresent()) {
            pacienteService.eliminarPaciente(id);
            response = ResponseEntity.ok("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}
