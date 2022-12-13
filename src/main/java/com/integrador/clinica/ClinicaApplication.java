package com.integrador.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.*;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {

		PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		SpringApplication.run(ClinicaApplication.class, args);
	}
}
