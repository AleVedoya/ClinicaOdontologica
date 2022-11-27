package com.integrador.clinica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.entities.Paciente;
import com.integrador.clinica.repository.PacienteRepository;
import com.integrador.clinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PacienteService implements IPacienteService {

    PacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente crearPaciente(PacienteDto pacienteDto) {
        Paciente newPaciente = mapper.convertValue(pacienteDto, Paciente.class);
        return pacienteRepository.save(newPaciente);
    }

    @Override
    public Optional<Paciente> buscarPaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            return paciente;
        }
        return Optional.empty(); //Hacer una Exception
    }

    @Override
    public Optional<Paciente> modificarPaciente(Long id, PacienteDto pacienteDto) {
        Optional<Paciente> paciente = buscarPaciente(id);
        if (paciente.isPresent()){
            Paciente pacienteModificado = mapper.convertValue(pacienteDto, Paciente.class);
            pacienteRepository.save(pacienteModificado);
        }
        return Optional.empty(); //Hacer una Exception
    }

    @Override
    public void eliminarPaciente(Long id) {
        Optional<Paciente> paciente = buscarPaciente(id);
        if (paciente.isPresent()){
            pacienteRepository.deleteById(id);
        }
    }

    @Override
    public List<Paciente> listarPaciente() {
        return pacienteRepository.findAll();
    }
}
