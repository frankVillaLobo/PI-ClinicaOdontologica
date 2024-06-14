package com.example.ClinicaOdontologica.repository;

import com.example.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
    // Aca van los metodo manuales
}
