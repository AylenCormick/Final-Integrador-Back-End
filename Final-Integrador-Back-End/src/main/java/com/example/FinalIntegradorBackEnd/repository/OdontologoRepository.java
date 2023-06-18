package com.example.FinalIntegradorBackEnd.repository;

import com.example.FinalIntegradorBackEnd.entities.Odontologo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
