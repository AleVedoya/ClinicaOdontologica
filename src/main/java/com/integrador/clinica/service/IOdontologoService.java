package com.integrador.clinica.service;

import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.entities.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    Odontologo crearOdontologo(OdontologoDto odontologoDto);
    Optional<Odontologo> buscarOdontologo(Long id);
    Optional<Odontologo> modificarOdontologo(Long id, OdontologoDto odontologoDto);
    void eliminarOdontologo(Long id);
    List<Odontologo> listarOdontologos();
}
