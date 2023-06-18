package com.example.FinalIntegradorBackEnd.repository;

import com.example.FinalIntegradorBackEnd.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
