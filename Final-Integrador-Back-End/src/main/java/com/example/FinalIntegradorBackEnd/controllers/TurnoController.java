package com.example.FinalIntegradorBackEnd.controllers;

import com.example.FinalIntegradorBackEnd.dto.TurnoDto;
import com.example.FinalIntegradorBackEnd.entities.Paciente;
import com.example.FinalIntegradorBackEnd.entities.Turno;
import com.example.FinalIntegradorBackEnd.services.PacienteService;
import com.example.FinalIntegradorBackEnd.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<String> respuesta = null;

        if(turnoService.registrarTurno(turno) != null){
            respuesta = ResponseEntity.ok("El turno fue registrado con éxito");
        } else{
            respuesta = ResponseEntity.internalServerError().body("No se pudo registrar el turno");
        }

        return respuesta;
    }

    @GetMapping("/listarTurno")
    public ResponseEntity<List<TurnoDto>> listarTurno(){
        return ResponseEntity.ok(turnoService.listarTurno());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurno(@PathVariable Integer id) {
        Optional turno = turnoService.buscarTurno(id);

        if (turno.isPresent()) {
            return ResponseEntity.ok((TurnoDto) turno.get());
        } else {
            return null;
        }

    }

    @PutMapping("/modificar")
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        ResponseEntity<Turno> response = null;

        if (turno.getId() != null && turnoService.buscarTurno(turno.getId()).isPresent()) {
            response = ResponseEntity.ok(turnoService.actualizarTurno(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (turnoService.buscarTurno(id).isPresent()) {
            turnoService.buscarTurno(id);
            response = ResponseEntity.ok("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

}
