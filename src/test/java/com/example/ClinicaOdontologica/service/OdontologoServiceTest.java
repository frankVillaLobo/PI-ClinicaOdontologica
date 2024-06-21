package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("M123","Fabio","Piedratita");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(1L,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPaciente(){
        Long id=1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologoPorId(1L);
        odontologo.get().setNombre("Manuel");
        odontologoService.actualizarOdontologo(odontologo.get());
        assertEquals("Manuel",odontologo.get().getNombre());
    }

    @Test
    @Order(4)
    public void buscarTodos(){
        List<Odontologo> odontologos = odontologoService.buscarOdontologoTodos();
        assertEquals(1,odontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        List<Odontologo> odontologos = odontologoService.buscarOdontologoTodos();
        assertEquals(0,odontologos.size());
    }
}
