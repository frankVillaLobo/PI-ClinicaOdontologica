package com.example.ClinicaOdontologica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class PasswordEncoder {
    //Codifica y descodifica las contrasenhas empleadas por el sistema
    //el bean se usa para que cuando necesito inyectarlo en algun lado que lo haga
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
