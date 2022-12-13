package com.integrador.clinica.service.Impl;

import com.integrador.clinica.dto.DomicilioDto;
import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Test
    void crear_un_paciente() throws BadRequestException {
        //Arrange
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.nombre = "Pablo";
        pacienteDto.apellido = "Marmol";
        pacienteDto.dni = 203132456;
        pacienteDto.fechaAlta = LocalDate.parse("2022-12-05");
        pacienteDto.domicilio = new DomicilioDto();
        pacienteDto.domicilio.calle = "Colon";
        pacienteDto.domicilio.numero = 2325;
        pacienteDto.domicilio.localidad = "MKar del Plata";
        pacienteDto.domicilio.provincia = "Bs Assertions";

        //Act
        PacienteDto nuevoPaciente = pacienteService.crearPaciente(pacienteDto);

        //Assert
        assertNotNull(nuevoPaciente);
    }

    @Test
    void buscar_un_paciente_por_su_id() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.nombre = "Pablo";
        pacienteDto.apellido = "Marmol";
        pacienteDto.dni = 203132456;
        pacienteDto.fechaAlta = LocalDate.parse("2022-12-05");
        pacienteDto.domicilio = new DomicilioDto();
        pacienteDto.domicilio.calle = "Colon";
        pacienteDto.domicilio.numero = 2325;
        pacienteDto.domicilio.localidad = "MKar del Plata";
        pacienteDto.domicilio.provincia = "Bs Assertions";

        //Act
        PacienteDto nuevoPaciente = pacienteService.crearPaciente(pacienteDto);
        PacienteDto pacienteencontrador = pacienteService.buscarPacientePorId(nuevoPaciente.getId());

        //Assert
        assertNotNull(pacienteencontrador);
    }

    @Test
    void eliminar_paciente_buscado_por_id() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.nombre = "Pablo";
        pacienteDto.apellido = "Marmol";
        pacienteDto.dni = 203132456;
        pacienteDto.fechaAlta = LocalDate.parse("2022-12-05");
        pacienteDto.domicilio = new DomicilioDto();
        pacienteDto.domicilio.calle = "Colon";
        pacienteDto.domicilio.numero = 2325;
        pacienteDto.domicilio.localidad = "MKar del Plata";
        pacienteDto.domicilio.provincia = "Bs Assertions";

        //Act
        PacienteDto pacienteGuardado = pacienteService.crearPaciente(pacienteDto);
        pacienteService.eliminarPaciente(pacienteGuardado.getId());

        //Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(pacienteGuardado.getId()));
    }

    @Test
    void listar_todos_los_paciente() throws BadRequestException, ResourceNoContentException {
        pacienteService.crearPaciente(new PacienteDto());
        Set<PacienteDto> pacienteDtos = pacienteService.listarPaciente();
        Assertions.assertTrue(pacienteDtos.size() > 0);
    }
}