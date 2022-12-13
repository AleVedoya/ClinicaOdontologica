package com.integrador.clinica.controller;

import com.integrador.clinica.dto.DomicilioDto;
import com.integrador.clinica.dto.OdontologoDto;
import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.dto.TurnoDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.exceptions.ResourceNotFoundException;
import com.integrador.clinica.service.Impl.OdontologoService;
import com.integrador.clinica.service.Impl.PacienteService;
import com.integrador.clinica.service.Impl.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoService turnoService;

    public void cargarDatos() throws BadRequestException, ResourceNotFoundException {
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
        turnoDto.paciente.domicilio.provincia = "Bs As";
        turnoDto.odontologo = new OdontologoDto();
        turnoDto.odontologo.nombre = "Bart";
        turnoDto.odontologo.apellido = "Simpson";
        turnoDto.odontologo.matricula = 123456;
        turnoDto.fecha = LocalDate.parse("2022-01-01");
        turnoDto.hora = LocalTime.parse("10:30:00");

        TurnoDto resuult = turnoService.crearTurno(turnoDto);

    }

    @Test
    public void listarTurnos() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/turnos/lista")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void registrarTurno() throws Exception {
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
        turnoDto.paciente.domicilio.provincia = "Bs As";
        turnoDto.odontologo = new OdontologoDto();
        turnoDto.odontologo.nombre = "Bart";
        turnoDto.odontologo.apellido = "Simpson";
        turnoDto.odontologo.matricula = 123456;
        turnoDto.fecha = LocalDate.parse("2022-01-01");
        turnoDto.hora = LocalTime.parse("10:30:00");

        TurnoDto result = turnoService.crearTurno(turnoDto);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/turnos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(result)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void eliminarTurno() throws Exception {
        this.cargarDatos();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/turnos/eliminar/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void buscarTurnoPorId() throws Exception {
        this.cargarDatos();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/buscar-por-id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }
}
