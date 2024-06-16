package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    TurnoRepository turnoRepo;

    // guardarTurno
    public ResponseEntity<Turno> guardarTurno(Turno turno){
        return ResponseEntity.ok(turnoRepo.save(turno));
    }

    // actualizarTurno
    public ResponseEntity<Turno> actualizarTurno(Turno turno){
        return ResponseEntity.ok(turnoRepo.save(turno));
    }

    //buscarPorId
    public Optional<Turno> buscarTurnoPorId(Long id){
        return turnoRepo.findById(id);
    }


    //eliminarPaciente
    public void eliminarTurno(Long id){
        turnoRepo.deleteById(id);
    }

    //buscartodos
    public ResponseEntity<List<Turno>> buscarTurnoTodos(){
        return ResponseEntity.ok(turnoRepo.findAll());
    }
}
