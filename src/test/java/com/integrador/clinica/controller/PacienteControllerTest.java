package com.integrador.clinica.controller;

import com.integrador.clinica.dto.PacienteDto;
import com.integrador.clinica.exceptions.BadRequestException;
import com.integrador.clinica.service.Impl.PacienteService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PacienteService pacienteService;

    public Object cargarDataSet() throws BadRequestException {
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.nombre = "Pedro";
        pacienteDto.apellido = "Picapiedra";
        pacienteDto.dni = 123654789;
        pacienteDto.fechaAlta = LocalDate.parse("2022-02-03");

        return pacienteService.crearPaciente(pacienteDto);
    }

    @Test
    public void registrarPaciente() throws Exception {
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.nombre = "Pedro";
        pacienteDto.apellido = "Picapiedra";
        pacienteDto.dni = 123654789;
        pacienteDto.fechaAlta = LocalDate.parse("2022-02-03");

        PacienteDto result = pacienteService.crearPaciente(pacienteDto);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/pacientes/create")
                        .content(JsonUtil.asJsonString(result))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void eliminarPaciente() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/pacientes/eliminar/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void listarPacientes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/pacientes/lista")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void buscarPacientePorId() throws Exception {
        this.cargarDataSet();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/buscar-por-id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }
}