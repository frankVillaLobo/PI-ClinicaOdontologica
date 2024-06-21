package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepo;

    //guardar
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepo.save(paciente);
    }

    //actualizar
    public ResponseEntity<Paciente> actualizarPaciente(Paciente paciente){
        return ResponseEntity.ok(pacienteRepo.save(paciente));
    }

    //buscarPorId
    public Optional<Paciente> buscarPacientePorId(Long id){
        return pacienteRepo.findById(id);
    }

    //buscarPorEmail
    public Optional<Paciente> buscarPacientePorEmail(String email){
        return pacienteRepo.findByEmail(email);
    }

    //eliminarPaciente
    public void eliminarPaciente(Long id){
        pacienteRepo.deleteById(id);
    }

    //buscartodos
    public List<Paciente> buscarPacienteTodos(){
        return (pacienteRepo.findAll());
    }

}
