package com.integrador.clinica.dto;

import java.time.LocalDate;

public class PacienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaAlta;
    private DomicilioDto domicilio;

}
