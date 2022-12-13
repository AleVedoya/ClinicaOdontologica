package com.integrador.clinica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.entities.Paciente;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import com.integrador.clinica.repository.PacienteRepository;
import com.integrador.clinica.service.IPacienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOG = Logger.getLogger(PacienteService.class);

    PacienteRepository pacienteRepository;
    ObjectMapper mapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    @Override
    public PacienteDto crearPaciente(PacienteDto pacienteDto) throws BadRequestException {
        Paciente paciente = pacienteRepository.save(mapper.convertValue(pacienteDto, Paciente.class));
        if (paciente.getId() == null) {
            LOG.error("No se creó el paciente");
            throw new BadRequestException("No se pudo crear el paciente");
        } else if (paciente.getNombre() == null || paciente.getNombre().equals("") || paciente.getApellido() == null || paciente.getApellido().equals("")) {
            LOG.error("No se puede crear un paciente sin nombre y apellido");
        }
        LOG.info("Paciente creado");
        return mapper.convertValue(paciente, PacienteDto.class);
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NO se encontró el paciente con el id " + id));
        if (paciente.getId() == null || paciente.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
        } else {
            LOG.info("Se encontró el paciente con el id " + id);
            return mapper.convertValue(paciente, PacienteDto.class);
        }
        return null;
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public PacienteDto modificarPaciente(PacienteDto pacienteDto) throws ResourceNotFoundException {
        Paciente pacienteAActualizar = mapper.convertValue(pacienteDto, Paciente.class);
        if (pacienteAActualizar.getId() == null || pacienteAActualizar.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new ResourceNotFoundException("Debe ingresar un id válido");
        }
        LOG.info("Se modificó el paciente con el id " + pacienteAActualizar.getId());
        Paciente pacienteActualizado = pacienteRepository.save(pacienteAActualizar);
        return mapper.convertValue(pacienteActualizado, PacienteDto.class);
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        PacienteDto pacienteAEliminar = buscarPacientePorId(id);
        if (pacienteAEliminar.getId() == null || pacienteAEliminar.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new ResourceNotFoundException("Debe ingresar un id válido");
        }
        LOG.info("Se eliminó el paciente con el id " + id);
        pacienteRepository.deleteById(id);
    }

    @Override
    public Set<PacienteDto> listarPaciente() throws ResourceNoContentException {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDto> pacientesDto = new HashSet<>();
        if (pacientes.isEmpty()) {
            LOG.error("No hay pacientes para listar");
            throw new ResourceNoContentException("La lista de pacientes está vacía");
        }
        for (Paciente paciente : pacientes) {
            pacientesDto.add(mapper.convertValue(paciente, PacienteDto.class));
        }
        LOG.info("La lista de pacientes tiene " + pacientesDto.size() + " odontólogos");
        return pacientesDto;
    }
}
