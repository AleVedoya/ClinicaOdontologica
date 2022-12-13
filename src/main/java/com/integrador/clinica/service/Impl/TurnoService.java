package com.integrador.clinica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.clinica.controller.TurnoController;
import com.integrador.clinica.dto.TurnoDto;
import com.integrador.clinica.entities.Odontologo;
import com.integrador.clinica.entities.Paciente;
import com.integrador.clinica.entities.Turno;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import com.integrador.clinica.repository.TurnoRepository;
import com.integrador.clinica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOG = Logger.getLogger(TurnoController.class);

    TurnoRepository turnoRepository;
    ObjectMapper mapper;

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ObjectMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.mapper = mapper;
    }

    @Override
    public TurnoDto crearTurno(TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        Paciente paciente = pacienteService.buscarPorId(turnoDto.paciente.getId()).orElseThrow(()-> new ResourceNotFoundException("No existe el odontologo"));
        Odontologo odontologo = odontologoService.buscarPorId(turnoDto.odontologo.getId()).orElseThrow(()-> new ResourceNotFoundException("No existe el odontologo"));
        Turno nuevoTurno = new Turno(turnoDto.fecha, turnoDto.hora, paciente, odontologo);
        Turno turno = turnoRepository.save(nuevoTurno);
        LOG.info("Turno creado");
        return mapper.convertValue(turno, TurnoDto.class);
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el turno con el id " + id));
        if (turno.getId() == null || turno.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
        } else {
            LOG.info("Se encontró el turno con el id " + id);
        }
        return mapper.convertValue(turno, TurnoDto.class);
    }

    @Override
    public TurnoDto modificarTurno(TurnoDto turnoDto) throws ResourceNotFoundException {
        Turno turnoAActualizar = mapper.convertValue(turnoDto, Turno.class);
        if (turnoAActualizar.getId() == null || turnoAActualizar.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new ResourceNotFoundException("Debe ingresar un id válido");
        }
        LOG.info("Se modificó el turno con el id " + turnoAActualizar.getId());
        Turno turnoActualizado = turnoRepository.save(turnoAActualizar);
        return mapper.convertValue(turnoActualizado, TurnoDto.class);
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        TurnoDto turnoDto = buscarTurnoPorId(id);
        if (turnoDto.getId() == null || turnoDto.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new ResourceNotFoundException("Debe ingresar un id válido");
        }
        LOG.info("Se eliminó el turno con el id " + id);
        turnoRepository.deleteById(id);
    }

    @Override
    public Set<TurnoDto> listarTurnos() throws ResourceNotFoundException {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDto> turnosDto = new HashSet<>();
        if (turnos.isEmpty()) {
            LOG.error("No hay turnos para listar");
            throw new ResourceNotFoundException("La lista de turnos está vacía");
        }
        for (Turno turno : turnos) {
            turnosDto.add(mapper.convertValue(turno, TurnoDto.class));
        }
        LOG.info("La lista de turnos tiene " + turnosDto.size() + " turnos");
        return turnosDto;
    }
}
