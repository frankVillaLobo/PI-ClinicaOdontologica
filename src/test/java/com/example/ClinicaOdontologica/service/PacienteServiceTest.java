package com.example.ClinicaOdontologica.service;


import com.example.ClinicaOdontologica.entity.Domicilio;
import com.example.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
// Le digo a spring que voy a hacer un test de manera ordenada con las anotaciones
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    //metodo que necesito testear

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente = new Paciente("Frank","Villa","123", LocalDate.of(2024,8,11),
                new Domicilio("Avenida",123,"Teusaquillo","Bogota"),"frank@email.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPaciente(){
        Long id=1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPaciente(){
        Optional<Paciente> paciente = pacienteService.buscarPacientePorId(1L);
        paciente.get().setNombre("Pedro");
        pacienteService.actualizarPaciente(paciente.get());
        assertEquals("Pedro",paciente.get().getNombre());
    }

    @Test
    @Order(4)
    public void buscarTodos(){
        List<Paciente> pacientes = pacienteService.buscarPacienteTodos();
        assertEquals(1,pacientes.size());
    }

    @Test
    @Order(5)
    public void eliminarPaciente(){
        pacienteService.eliminarPaciente(1L);
        List<Paciente> pacientes = pacienteService.buscarPacienteTodos();
        assertEquals(0,pacientes.size());
    }
}
