package com.integrador.clinica.service.Impl;

import com.integrador.clinica.dto.DomicilioDto;
import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.dto.TurnoDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;

    @Test
    void crear_nuevo_turno() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.paciente = new PacienteDto();
        turnoDto.paciente.nombre = "Pablo";
        turnoDto.paciente.apellido = "Marmol";
        turnoDto.paciente.dni = 203132456;
        turnoDto.paciente.fechaAlta = LocalDate.parse("2022-12-05");
        turnoDto.paciente.domicilio = new DomicilioDto();
        turnoDto.paciente.domicilio.calle = "Colon";
        turnoDto.paciente.domicilio.numero = 2325;
        turnoDto.paciente.domicilio.localidad = "Mar del Plata";
        turnoDto.paciente.domicilio.provincia = "Bs Assertions";
        turnoDto.odontologo = new OdontologoDto();
        turnoDto.odontologo.nombre = "Bart";
        turnoDto.odontologo.apellido = "Simpson";
        turnoDto.odontologo.matricula = 123456;
        turnoDto.fecha = LocalDate.parse("2022-01-01");
        turnoDto.hora = LocalTime.parse("10:30:00");

        //Act
        TurnoDto nuevoTurno = turnoService.crearTurno(turnoDto);

        //Assert
        assertNotNull(nuevoTurno);
    }

    @Test
    void buscar_un_turno_por_su_id() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.paciente = new PacienteDto();
        turnoDto.odontologo = new OdontologoDto();
        turnoDto.paciente.nombre = "Pablo";
        turnoDto.paciente.apellido = "Marmol";
        turnoDto.paciente.dni = 203132456;
        turnoDto.paciente.fechaAlta = LocalDate.parse("2022-12-05");
        turnoDto.paciente.domicilio = new DomicilioDto();
        turnoDto.paciente.domicilio.calle = "Colon";
        turnoDto.paciente.domicilio.numero = 2325;
        turnoDto.paciente.domicilio.localidad = "Mar del Plata";
        turnoDto.paciente.domicilio.provincia = "Bs Assertions";
        turnoDto.odontologo.nombre = "Bart";
        turnoDto.odontologo.apellido = "Simpson";
        turnoDto.odontologo.matricula = 123456;
        turnoDto.fecha = LocalDate.parse("2022-01-01");
        turnoDto.hora = LocalTime.parse("10:30:00");


        //Act
        TurnoDto nuevoTurno = turnoService.crearTurno(turnoDto);
        TurnoDto turnoEncontrado = turnoService.buscarTurnoPorId(nuevoTurno.getId());

        //Assert
        assertNotNull(turnoEncontrado);
    }

    @Test
    void eliminar_paciente_buscado_por_id() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.paciente = new PacienteDto();
        turnoDto.odontologo = new OdontologoDto();
        turnoDto.paciente.nombre = "Pablo";
        turnoDto.paciente.apellido = "Marmol";
        turnoDto.paciente.dni = 203132456;
        turnoDto.paciente.fechaAlta = LocalDate.parse("2022-12-05");
        turnoDto.paciente.domicilio = new DomicilioDto();
        turnoDto.paciente.domicilio.calle = "Colon";
        turnoDto.paciente.domicilio.numero = 2325;
        turnoDto.paciente.domicilio.localidad = "Mar del Plata";
        turnoDto.paciente.domicilio.provincia = "Bs Assertions";
        turnoDto.odontologo.nombre = "Bart";
        turnoDto.odontologo.apellido = "Simpson";
        turnoDto.odontologo.matricula = 123456;
        turnoDto.fecha = LocalDate.parse("2022-01-01");
        turnoDto.hora = LocalTime.parse("10:30:00");

        //Act
        TurnoDto nuevoTurno = turnoService.crearTurno(turnoDto);
        turnoService.eliminarTurno(nuevoTurno.getId());

        //Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(nuevoTurno.getId()));
    }

    @Test
    void listar_todos_los_turnos() throws BadRequestException, ResourceNotFoundException {
        turnoService.crearTurno(new TurnoDto());
        Set<TurnoDto> turnoDtos = turnoService.listarTurnos();
        Assertions.assertTrue(turnoDtos.size() > 0);
    }
}