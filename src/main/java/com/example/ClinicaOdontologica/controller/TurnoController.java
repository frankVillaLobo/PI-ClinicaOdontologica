package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.dto.TurnoDTO;
import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.exception.BadRequestException;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.service.OdontologoService;
import com.example.ClinicaOdontologica.service.PacienteService;
import com.example.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    // Ojo que este tiene mas atributos dentro de si y tiene que acceder tanto a paciente como a odontologo
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public TurnoDTO guardarTurno(@RequestBody Turno turno)throws BadRequestException {
        // Verifica que los pacientes existan
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if(odontologoBuscado.isPresent() && pacienteBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return turnoService.guardarTurno(turno);
        }else{
            // En caso de que no esten registrado antes se crean tanto el paciente como
            throw new BadRequestException("Verifique la informacion");
            // por ahora devuelve el objeto tal cual se lo paso sin guardarlo en BD
        }
    }

    // Ajustamos este metodo para implementar el DTO
    @GetMapping("/{id}")
    public Optional<Turno> buscarTurnoPorId(@PathVariable Long id)throws ResourceNotFoundException {
        try{
           return turnoService.buscarTurnoPorId(id);
        }catch(Exception e){
            throw new ResourceNotFoundException("No se encontro al turno con el ID: "+ id);
        }

    }
    // Ajustamos este metodo para implementar el DTO
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTurnoTodos()throws ResourceNotFoundException{
        try{
            return turnoService.buscarTurnoTodos();
        }catch(Exception e){
            throw new ResourceNotFoundException("No se encuentran pacientes registrados");
        }
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno)throws BadRequestException{
        //necesitamos validar si existe el turno o no
        Optional<Turno> turnoBuscado= turnoService.buscarTurnoPorId(turno.getId());
        if(turnoBuscado.isPresent()){
            //Lo elimino y luego lo creo otra vez
            return turnoService.actualizarTurno(turno);
        }else{
            // Si no lo encuentra lo devuelvo como objeto
            throw new BadRequestException("NO hay un Turno con esas caracteristicas");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id)throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado= turnoService.buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con exito");
        }else{
            throw new ResourceNotFoundException("No se encontro al turno con ID: "+ id);
        }
    }

}
