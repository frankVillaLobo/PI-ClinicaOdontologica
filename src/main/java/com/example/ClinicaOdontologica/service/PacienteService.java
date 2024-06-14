package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepo;

    //guardar
    public ResponseEntity<Paciente> guardarPaciente(Paciente paciente){
        return ResponseEntity.ok(pacienteRepo.save(paciente));
    }

    //actualizar
    public ResponseEntity<Paciente> actualizarPaciente(Paciente paciente){
        return ResponseEntity.ok(pacienteRepo.save(paciente));
    }

    //buscarPorId
    public ResponseEntity<Optional<Paciente>> buscarPacientePorId(Long id){
        return ResponseEntity.ok(pacienteRepo.findById(id));
    }

    //buscarPorEmail
    public ResponseEntity<Optional<Paciente>> buscarPacientePorEmail(String email){
        return ResponseEntity.ok(pacienteRepo.findByEmail(email));
    }

    //eliminarPaciente
    public void eliminarPaciente(Long id){
        pacienteRepo.deleteById(id);
    }

    //buscartodos
    public ResponseEntity<List<Paciente>> buscarPacienteTodos(Long id){
        return ResponseEntity.ok(pacienteRepo.findById(id));
    }

}
