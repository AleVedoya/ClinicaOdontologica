package com.integrador.clinica.controller;

import com.integrador.clinica.service.Impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;

public class OdontologoController {
    OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


}
