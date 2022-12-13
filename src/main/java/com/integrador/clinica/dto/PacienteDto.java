package com.integrador.clinica.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PacienteDto {

    private Long id;
    @NotNull
    public String nombre;
    @NotNull
    public String apellido;
    public int dni;
    public LocalDate fechaAlta;
    public DomicilioDto domicilio;

    public Long getId() {
        return id;
    }
}
