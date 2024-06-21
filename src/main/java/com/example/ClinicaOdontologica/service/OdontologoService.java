package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.repository.OdontologoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    OdontologoRepository odontologoRepo;

    //guardar
    @Transactional
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepo.save(odontologo);
    }

    //actualizar
    public ResponseEntity<Odontologo> actualizarOdontologo(Odontologo odontologo){
        return ResponseEntity.ok(odontologoRepo.save(odontologo));
    }

    //buscarPorId

    @Transactional
    public Optional<Odontologo> buscarOdontologoPorId(Long id){
        return odontologoRepo.findById(id);
    }


    //eliminar
    public void eliminarOdontologo(Long id){
        odontologoRepo.deleteById(id);
    }

    //buscartodos
    public List<Odontologo> buscarOdontologoTodos(){
        return odontologoRepo.findAll();
    }

    //buscarPorMatricula
    public Optional<Odontologo> buscarOdontologoPorMatricula(String matricula){
        return odontologoRepo.findByMatricula(matricula);
    }
}
