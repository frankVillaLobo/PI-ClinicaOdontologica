package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    // guardarOdontologo
    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        /**Antes de guardar un nuevo odontologo verificamos que no se repitan las matriculas como en el caso de
         * los email de los pacientes**/
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(odontologo.getMatricula());
        if(odontologoBuscado.isPresent()){
            System.out.println("Matricula Encontrada");
            // por ahora devuelve el objeto tal cual se lo paso sin guardarlo en BD
            return ResponseEntity.ok(odontologo);
        }else{
            return odontologoService.guardarOdontologo(odontologo);
        }
    }

    // actulizarOdontologo
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }

    //buscarTodos
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologoTodos(){
        return odontologoService.buscarOdontologoTodos();
    }

    //buscarPorId
    @GetMapping("/{id}")
    public Optional<Odontologo> buscarOdontologoPorId(@PathVariable Long id){
        return odontologoService.buscarOdontologoPorId(id);
    }

    //eliminarOdontologos
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologPorId(@PathVariable Long id){
        // debo verificar que el odontologo existe para eliminarlo
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontolog eliminado con exito");
        }else{
            return ResponseEntity.ok("Odontologo no encontrado");
        }
    }
}
