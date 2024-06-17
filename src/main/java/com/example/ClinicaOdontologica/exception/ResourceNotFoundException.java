package com.example.ClinicaOdontologica.exception;

public class ResourceNotFoundException extends Exception{
    public  ResourceNotFoundException(String message){
        // Este es para los que son de busqueda se pasa por throws
        super(message);
    }
}
