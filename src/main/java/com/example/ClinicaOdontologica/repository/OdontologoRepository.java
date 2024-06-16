package com.example.ClinicaOdontologica.repository;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
    // Aca van los metodo manuales
    // Nos aseguramos primero que no haya odontologos con esa matricula en el sistema
    Optional<Odontologo> findByMatricula(String matricula);
}
