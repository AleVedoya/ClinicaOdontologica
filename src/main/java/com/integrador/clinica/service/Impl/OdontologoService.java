package com.integrador.clinica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.entities.Odontologo;
import com.integrador.clinica.repository.OdontologoRepository;
import com.integrador.clinica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OdontologoService implements IOdontologoService {

    OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo crearOdontologo(OdontologoDto odontologoDto) {
        Odontologo newOdontologo = mapper.convertValue(odontologoDto, Odontologo.class);
        return odontologoRepository.save(newOdontologo);
    }

    @Override
    public Optional<Odontologo> buscarOdontologo(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            return odontologo;
        }
        return Optional.empty(); //Hacer una Exception
    }

    @Override
    public Optional<Odontologo> modificarOdontologo(Long id, OdontologoDto odontologoDto) {
        Optional<Odontologo> odontologo = buscarOdontologo(id);
        if (odontologo.isPresent()) {
            Odontologo odontologoModificado = mapper.convertValue(odontologoDto, Odontologo.class);
            odontologoRepository.save(odontologoModificado);
        }
        return Optional.empty(); //Hacer una Exception
    }

    @Override
    public void eliminarOdontologo(Long id) {
        Optional<Odontologo> odontologo = buscarOdontologo(id);
        if (odontologo.isPresent()) {
            odontologoRepository.deleteById(id);
        }
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }
}
