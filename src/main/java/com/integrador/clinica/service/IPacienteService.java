package com.integrador.clinica.service;

import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface IPacienteService {

    PacienteDto crearPaciente(PacienteDto pacienteDto) throws BadRequestException, ResourceNotFoundException;
    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;
    PacienteDto modificarPaciente(PacienteDto pacienteDto) throws ResourceNotFoundException;
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
    Set<PacienteDto> listarPaciente() throws ResourceNoContentException;
}
