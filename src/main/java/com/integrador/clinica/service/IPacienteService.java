package com.integrador.clinica.service;

import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.entities.Odontologo;
import com.integrador.clinica.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    Paciente crearPaciente(PacienteDto pacienteDto);
    Optional<Paciente> buscarPaciente(Long id);
    Optional<Paciente> modificarPaciente(Long id, PacienteDto pacienteDto);
    void eliminarPaciente(Long id);
    List<Paciente> listarPaciente();

}
