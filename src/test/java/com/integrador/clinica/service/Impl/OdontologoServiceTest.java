package com.integrador.clinica.service.Impl;

import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNoContentException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    @Test
    public void teatear_crear_un_odontologo() throws BadRequestException {
        //Arrange
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.nombre = "Maria";
        odontologoDto.apellido = "Martinez";
        odontologoDto.matricula = 123654;

        //Act
        OdontologoDto newOdontologo = odontologoService.crearOdontologo(odontologoDto);

        //Assert
        assertNotNull(newOdontologo);
    }

    @Test
    void buscar_odontologo_por_id() throws ResourceNotFoundException, BadRequestException {
        //Arrange
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.nombre = "Pedro";
        odontologoDto.apellido = "Picapiedra";
        odontologoDto.matricula = 789456;

        //Act
        OdontologoDto odontologoGuardado = odontologoService.crearOdontologo(odontologoDto);
        OdontologoDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(odontologoGuardado.getId());

        //Assert
        Assertions.assertTrue(odontologoEncontrado.getId() > 0);
    }

    @Test
    void eliminar_un_odontologo_buscado_por_id() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.nombre = "Bart";
        odontologoDto.apellido = "Simpson";
        odontologoDto.matricula = 123456;

        //Act
        OdontologoDto odontologoGuardado = odontologoService.crearOdontologo(odontologoDto);
        odontologoService.eliminarOdontologo(odontologoGuardado.getId());

        //Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(odontologoGuardado.getId()));
    }

    @Test
    void test_traer_todos_los_odontologos_da_lista_vacia() throws ResourceNoContentException, BadRequestException {
        odontologoService.crearOdontologo(new OdontologoDto());
        Set<OdontologoDto> odontologosDto = odontologoService.listarOdontologos();
        Assertions.assertTrue(odontologosDto.size() > 0);
    }
}