package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.exception.BadRequestException;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    // guardarPaciente
    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente)  throws BadRequestException {
        //Validamos que no este registrado el email
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(paciente.getEmail());
        if(pacienteBuscado.isPresent()){
            // por ahora devuelve el objeto tal cual se lo paso sin guardarlo en BD
            throw new BadRequestException("Ya se encuetra registrado el correo en el sistema, intente con otro");
        }
        return pacienteService.guardarPaciente(paciente);
    }



    // actulizarPaciente
    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        //antes de seguir se mira que exista el paciente a modificar
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            return pacienteService.actualizarPaciente(paciente);
        }else{
            throw new BadRequestException("NO hay un paciente con esas caracteristicas");
        }
    }

    //buscarTodos
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacienteTodos()throws ResourceNotFoundException{
        try{
            return ResponseEntity.ok(pacienteService.buscarPacienteTodos());
        }catch(Exception e){
            throw new ResourceNotFoundException("No se encuentran pacientes registrados");
        }
    }

    //buscarPorId
    @GetMapping("/{id}")
    public Optional<Paciente> buscarPacientePorId(@PathVariable Long id)throws ResourceNotFoundException{
        try{
            return pacienteService.buscarPacientePorId(id);
        }catch(Exception e){
            throw new ResourceNotFoundException("No se encontro al paciente: "+ id);
        }
    }

    //eliminarPaciente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacientePorId(@PathVariable Long id)throws ResourceNotFoundException{
        // debo verificar que el paciente existe para eliminarlo
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con exito");
        }else{
            throw new ResourceNotFoundException("No se encontro al paciente: "+ id);
        }
    }


}
