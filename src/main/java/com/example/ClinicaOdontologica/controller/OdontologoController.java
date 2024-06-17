package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.exception.BadRequestException;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo)throws BadRequestException {
        /**Antes de guardar un nuevo odontologo verificamos que no se repitan las matriculas como en el caso de
         * los email de los pacientes**/
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(odontologo.getMatricula());
        if(odontologoBuscado.isPresent()){
            throw new BadRequestException("La matricula ya registrada, revise nuevamente");
        }else{
            return odontologoService.guardarOdontologo(odontologo);
        }
    }

    // actulizarOdontologo
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo)throws ResourceNotFoundException {
        // Se busca primero para veficar que existe
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            return odontologoService.actualizarOdontologo(odontologo);
        }else{
            throw new ResourceNotFoundException("No existe el odontologo");
        }
    }

    //buscarTodos
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologoTodos()throws ResourceNotFoundException{
        try{
            return odontologoService.buscarOdontologoTodos();
        }catch(Exception e){
            throw new ResourceNotFoundException("No se encuentran odonotologos registrados");
        }

    }

    //buscarPorId
    @GetMapping("/{id}")
    public Optional<Odontologo> buscarOdontologoPorId(@PathVariable Long id)throws ResourceNotFoundException{
        try{
            return odontologoService.buscarOdontologoPorId(id);
        }catch(Exception e){
            throw new ResourceNotFoundException("No se encontro al Odontologo: "+ id);
        }

    }

    //eliminarOdontologos
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologPorId(@PathVariable Long id)throws ResourceNotFoundException{
        // debo verificar que el odontologo existe para eliminarlo
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontolog eliminado con exito");
        }else{
            throw new ResourceNotFoundException("No se encontro al odontologo: "+ id);
        }
    }
}
