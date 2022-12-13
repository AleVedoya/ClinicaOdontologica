package com.integrador.clinica.service;

import com.integrador.clinica.dto.TurnoDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface ITurnoService {
    TurnoDto crearTurno(TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException;
    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;
    TurnoDto modificarTurno(TurnoDto turnoDto) throws ResourceNotFoundException;
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    Set<TurnoDto> listarTurnos() throws ResourceNotFoundException;


}
