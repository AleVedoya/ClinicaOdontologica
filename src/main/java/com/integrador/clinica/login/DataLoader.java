package com.integrador.clinica.login;

import com.integrador.clinica.login.entities.Rol;
import com.integrador.clinica.login.entities.Usuario;
import com.integrador.clinica.login.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password_user = passwordEncoder.encode("ale2022");
        String password_admin = passwordEncoder.encode("fer2022");

        usuarioRepository.save(new Usuario("Ale", "ale@mail.com", "ale", password_user, Rol.ROLE_USER));
        usuarioRepository.save(new Usuario("Fer", "fer@mail.com", "fer", password_admin, Rol.ROLE_ADMIN));
    }
}