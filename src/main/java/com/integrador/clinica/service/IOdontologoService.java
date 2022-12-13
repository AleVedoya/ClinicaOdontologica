package com.integrador.clinica.service;

import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface IOdontologoService {

    OdontologoDto crearOdontologo(OdontologoDto odontologoDto) throws BadRequestException;
    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;
    OdontologoDto modificarOdontologo(OdontologoDto odontologoDto) throws Exception;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    Set<OdontologoDto> listarOdontologos() throws ResourceNotFoundException, ResourceNoContentException;
}
