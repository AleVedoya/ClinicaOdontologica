package com.integrador.clinica.controller;

import com.integrador.clinica.dto.OdontologoDto;

import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import com.integrador.clinica.service.Impl.OdontologoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger LOG = Logger.getLogger(OdontologoController.class);

    OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/create")
    public ResponseEntity<OdontologoDto> crearNuevoOdontologo(@Valid @RequestBody OdontologoDto odontologoDto) throws BadRequestException {
        if (odontologoDto.nombre == null || odontologoDto.nombre.equals(" ") || odontologoDto.apellido == null || odontologoDto.apellido.equals(" ")) {
            LOG.error("No se puede crear un odontologo con datos vacios o nulos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Creando Odontologo: " + odontologoDto.nombre + " " + odontologoDto.apellido);
        return ResponseEntity.ok(odontologoService.crearOdontologo(odontologoDto));
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        if (id == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            OdontologoDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);
            LOG.info("Se encontró el odontologo con el id " + id);
            return ResponseEntity.ok(odontologoEncontrado);
        }
    }

    @GetMapping("/buscar-por-matricula/{matricula}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorMatricula(@PathVariable Integer matricula) throws ResourceNoContentException {
        OdontologoDto odontologoEncontrado = odontologoService.buscarPorMatricula(matricula);
        if (matricula == null) {
            LOG.info("No se encontró el odontologo con la matricula " + matricula);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOG.info("Se encontró el odontologo con la matricula " + matricula);
        return ResponseEntity.ok(odontologoEncontrado);
    }

    @PutMapping("/modificar")
    public ResponseEntity<OdontologoDto> modificarOdontologoPorId(@RequestBody OdontologoDto odontologoDto) throws ResourceNotFoundException {
        if (odontologoDto.getId() == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Se actualizó el odontologo con el id " + odontologoDto.getId());
        return ResponseEntity.ok(odontologoService.modificarOdontologo(odontologoDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        if (id == null) {
            LOG.info("No se encontró el odontologo con el id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOG.info("Se eliminó el odontologo con el id " + id);
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el odontologo con el id " + id);
    }

    @GetMapping("/lista")
    public ResponseEntity<Collection<OdontologoDto>> getTodos() throws ResourceNoContentException {
        Set<OdontologoDto> listaDeOdontologos = odontologoService.listarOdontologos();
        if (listaDeOdontologos.isEmpty()) {
            LOG.info("No hay ningùn odontologo registrado");
        }
        LOG.info("Se encontraron " + listaDeOdontologos.size() + " odontologos");
        return ResponseEntity.ok(listaDeOdontologos);
    }
}