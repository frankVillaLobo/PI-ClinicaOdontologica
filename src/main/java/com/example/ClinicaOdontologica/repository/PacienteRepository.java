package com.example.ClinicaOdontologica.repository;

import com.example.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

// No hay necesidad de anotarlo como un repo ya que heresa de la clase JpaRepo
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    // Aca van los metodos manuales
    // Dejamos este metodo para no repetir emails de pacientes
    Optional<Paciente> findByEmail(String email);
}
