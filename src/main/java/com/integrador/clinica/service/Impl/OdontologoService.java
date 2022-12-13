package com.integrador.clinica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.entities.Odontologo;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import com.integrador.clinica.repository.OdontologoRepository;
import com.integrador.clinica.service.IOdontologoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOG = Logger.getLogger(OdontologoService.class);

    OdontologoRepository odontologoRepository;
    ObjectMapper mapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public OdontologoDto crearOdontologo(OdontologoDto odontologoDto) throws BadRequestException {
        Odontologo odontologo = odontologoRepository.save(mapper.convertValue(odontologoDto, Odontologo.class));
        if (odontologo.getId() == null) {
            LOG.error("No se creó el odontólogo");
            throw new BadRequestException("No se pudo crear el odontologo");
        } else if (odontologo.getNombre() == null || odontologo.getNombre().equals("") || odontologo.getApellido() == null || odontologo.getApellido().equals("")) {
            LOG.error("No se puede crear un odontologo sin nombre y apellido");
        }
        LOG.info("Odontólogo creado");
        return mapper.convertValue(odontologo, OdontologoDto.class);
    }

    public Optional<Odontologo> buscarPorId(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el odontólogo con el id " + id));
        if (odontologo.getId() == null || odontologo.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
        } else {
            LOG.info("Se encontró el odontólogo con el id " + id);
            return mapper.convertValue(odontologo, OdontologoDto.class);
        }
        return null;
    }

    public OdontologoDto buscarPorMatricula(Integer matricula) throws ResourceNoContentException {
        Optional<Odontologo> odontologo = odontologoRepository.buscarOdontologoPorMatricula(matricula);
        if (odontologo.isEmpty()) {
            LOG.error("No se encontró el odontólogo con la matrícula " + matricula);
            throw new ResourceNoContentException("No se encontró el odontólogo con la matrícula " + matricula);
        } else {
            LOG.info("Se encontró el odontólogo con la matrícula " + matricula);
            return mapper.convertValue(odontologo, OdontologoDto.class);
        }
    }

    @Override
    public OdontologoDto modificarOdontologo(OdontologoDto odontologoDto) throws ResourceNotFoundException {
        Odontologo odontologoAModificar = mapper.convertValue(odontologoDto, Odontologo.class);
        if (odontologoAModificar.getId() == null || odontologoAModificar.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new ResourceNotFoundException("Debe ingresar un id válido");
        }
        LOG.info("Se modificó el odontólogo con el id " + odontologoAModificar.getId());
        Odontologo odontologoModificado = odontologoRepository.save(odontologoAModificar);
        return mapper.convertValue(odontologoModificado, OdontologoDto.class);
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        OdontologoDto odontologoAEliminar = buscarOdontologoPorId(id);
        if (odontologoAEliminar.getId() == null || odontologoAEliminar.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new ResourceNotFoundException("Debe ingresar un id válido");
        }
        LOG.info("Se eliminó el odontólogo con el id " + id);
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<OdontologoDto> listarOdontologos() throws ResourceNoContentException {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDto> odontologosDto = new HashSet<>();
        if (odontologos.isEmpty()) {
            LOG.error("No hay odontólogos para listar");
            throw new ResourceNoContentException("La lista de odontólogos está vacía");
        }
        for (Odontologo odontologo : odontologos) {
            odontologosDto.add(mapper.convertValue(odontologo, OdontologoDto.class));
        }
        LOG.info("La lista de odontólogos tiene " + odontologosDto.size() + " odontólogos");
        return odontologosDto;
    }
}
