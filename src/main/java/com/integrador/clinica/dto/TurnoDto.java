package com.integrador.clinica.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoDto {

    private Long id;
    @NotNull
    public LocalDate fecha;
    @NotNull
    public LocalTime hora;
    @NotNull
    public PacienteDto paciente;
    @NotNull
    public OdontologoDto odontologo;

    public Long getId() {
        return id;
    }


}
