package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        //Validamos que no este registrado el email
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(paciente.getEmail());
        if(pacienteBuscado.isPresent()){
            // por ahora devuelve el objeto tal cual se lo paso sin guardarlo en BD
            return ResponseEntity.ok(paciente);
        }
        return pacienteService.guardarPaciente(paciente);
    }



    // actulizarPaciente
    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

    //buscarTodos
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacienteTodos(){
        return pacienteService.buscarPacienteTodos();
    }

    //buscarPorId
    @GetMapping("/{id}")
    public Optional<Paciente> buscarPacientePorId(@PathVariable Long id){
        return pacienteService.buscarPacientePorId(id);
    }

    //eliminarPaciente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacientePorId(@PathVariable Long id){
        // debo verificar que el paciente existe para eliminarlo
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con exito");
        }else{
            return ResponseEntity.ok("Paciente no encontrado");
        }
    }


}
