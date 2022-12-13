package com.integrador.clinica.dto;

import javax.validation.constraints.NotNull;

public class OdontologoDto {
    private Long id;
    @NotNull
    public String nombre;
    @NotNull
    public String apellido;
    public Integer matricula;

    public Long getId() {
        return id;
    }

}
