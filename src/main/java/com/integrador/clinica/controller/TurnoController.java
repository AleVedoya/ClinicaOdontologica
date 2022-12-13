package com.integrador.clinica.controller;

import com.integrador.clinica.dto.TurnoDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import com.integrador.clinica.service.Impl.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger LOG = Logger.getLogger(TurnoController.class);

    TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/create")
    public ResponseEntity<TurnoDto> crearNuevoTurno(@RequestBody TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        if (turnoDto.fecha == null || turnoDto.hora == null ){
            LOG.error("No se puede crear un turno sin fecha y hora");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Creando turno con el id " + turnoDto.getId());
        return ResponseEntity.ok(turnoService.crearTurno(turnoDto));
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        if (id == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            TurnoDto turnoEncontrado = turnoService.buscarTurnoPorId(id);
            LOG.info("Se encontró el turno con el id  " + turnoEncontrado.getId());
            return ResponseEntity.ok(turnoEncontrado);
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<TurnoDto> modificarTurnoPorId(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException {
        if (turnoDto.getId() == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Se actualizó el turno con el id " + turnoDto.getId());
        return ResponseEntity.ok(turnoService.modificarTurno(turnoDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        if (id == null) {
            LOG.info("No se encontró el turno con el id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOG.info("Se eliminó el turno con el id " + id);
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el turno con el id " + id);
    }

    @GetMapping("/lista")
    public ResponseEntity<Collection<TurnoDto>> getTodos() throws ResourceNotFoundException {
        Set<TurnoDto> listaDeTurnos = turnoService.listarTurnos();
        if (listaDeTurnos.isEmpty()) {
            LOG.info("No hay ningùn turno registrado");
        }
        LOG.info("se encontraron " + listaDeTurnos.size() + " turnos");
        return ResponseEntity.ok(listaDeTurnos);
    }
}
