package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.dto.TurnoDTO;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    TurnoRepository turnoRepo;

    // guardarTurno
    // Ajustamos para implementar el DTO
    public TurnoDTO guardarTurno(Turno turno){
        return turnoAturnoDTO(turnoRepo.save(turno));
    }
    // Metodo publico para transformar un turno a un turno DTO. es como un object mapper manual
    public TurnoDTO turnoAturnoDTO(Turno turno){
        TurnoDTO turnoDto = new TurnoDTO();
        turnoDto.setId(turnoDto.getId());
        turnoDto.setFecha(turnoDto.getFecha());
        turnoDto.setPacienteId(turno.getPaciente().getId());
        turnoDto.setOdontologoId(turno.getOdontologo().getId());
        return turnoDto;
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

    // Ajustamos para implementar el DTO
    //buscartodos
    public List<TurnoDTO> buscarTurnoTodos(){
        List<Turno> listaTurnos = turnoRepo.findAll();
        List<TurnoDTO> listaTurnoDTO = new ArrayList<>();
        for (Turno turno : listaTurnos){
            listaTurnoDTO.add(turnoAturnoDTO(turno));
        }
        return listaTurnoDTO;
    }
}
