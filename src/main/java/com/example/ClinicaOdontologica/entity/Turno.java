package com.example.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


public class Turno {
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;

}