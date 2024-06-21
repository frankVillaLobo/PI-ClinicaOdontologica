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
        String passwordSinCifrar = "frank";
        String passwordCifrada = passwordEncoder.encode(passwordSinCifrar);
        Usuario usuario = new Usuario("frank","frank","frank",passwordCifrada, UsuarioRole.ROLE_USER);
        System.out.println("password Cifrado: " + passwordCifrada);
        usuarioRepository.save(usuario);
    }
}
