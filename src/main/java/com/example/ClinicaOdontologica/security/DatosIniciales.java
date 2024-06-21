package com.example.ClinicaOdontologica.security;

import com.example.ClinicaOdontologica.entity.Usuario;
import com.example.ClinicaOdontologica.entity.UsuarioRole;
import com.example.ClinicaOdontologica.repository.UsuarioRepository;
import com.example.ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passwordSinCifrar1 = "frank";
        String passwordSinCifrarAdmin = "admin";
        String passwordCifrada1 = passwordEncoder.encode(passwordSinCifrar1);
        String passwordCifradaAdmin = passwordEncoder.encode(passwordSinCifrarAdmin);
        Usuario usuario1 = new Usuario("frank","frank","frank@correo.com",passwordCifrada1, UsuarioRole.ROLE_USER);
        Usuario usuarioAdmin = new Usuario("admin","admin","admin@correo.com",passwordCifradaAdmin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuarioAdmin);
    }
}
