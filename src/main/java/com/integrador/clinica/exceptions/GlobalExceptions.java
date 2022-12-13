package com.integrador.clinica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    private static final Logger LOG = Logger.getLogger(GlobalExceptions.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> throwErrorRecursoNoEncontrado(ResourceNotFoundException ex){
        LOG.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> throwErrorSolicitudIncorrecta(BadRequestException ex){
        LOG.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNoContentException.class)
    public ResponseEntity<String> throwErrorSinContenido(ResourceNoContentException ex){
        LOG.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }
}