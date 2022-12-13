package com.integrador.clinica.repository;

import com.integrador.clinica.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Optional<Odontologo> buscarOdontologoPorMatricula(Integer matricula);
}
