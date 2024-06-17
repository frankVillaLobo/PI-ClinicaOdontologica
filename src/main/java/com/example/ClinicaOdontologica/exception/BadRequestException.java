package com.example.ClinicaOdontologica.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String message){
        // Para cuando la peticion al servidor no cumple con algunparametro
        super(message);
    }
}
