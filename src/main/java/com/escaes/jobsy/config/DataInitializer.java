package com.escaes.jobsy.config;

import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import com.escaes.jobsy.application.usecase.rol.GestionRolesUseCase;
import com.escaes.jobsy.application.usecase.rol.ListarRolesUseCase;
import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.model.Rol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;

    private final GestionRolesUseCase gestionRolesUseCase;

    private final ListarRolesUseCase listarRolesUseCase;

    public DataInitializer(GestionGenerosUseCase gestionGenerosUseCase,
                           ListarGenerosUseCase listarGenerosUseCase,GestionRolesUseCase gestionRolesUseCase,
                           ListarRolesUseCase listarRolesUseCase) {
        this.gestionGenerosUseCase = gestionGenerosUseCase;
        this.listarGenerosUseCase = listarGenerosUseCase;
        this.gestionRolesUseCase = gestionRolesUseCase;
        this.listarRolesUseCase = listarRolesUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeGeneros();
        initializeRoles();
    }

    private void initializeGeneros() {
        if(listarGenerosUseCase.contarGeneros()==0){
            gestionGenerosUseCase.crearGenero(new Genero(null, "Masculino"));
            gestionGenerosUseCase.crearGenero(new Genero(null, "Femenino"));
            gestionGenerosUseCase.crearGenero(new Genero(null, "Alien"));
            gestionGenerosUseCase.crearGenero(new Genero(null, "Otro"));
        }

    }
    private void initializeRoles() {
       if (listarRolesUseCase.contarRoles()==0){
           gestionRolesUseCase.crearRol(new Rol(UUID.randomUUID(),"ADMIN",
                   "Administrador del sistema con acceso completo"));
           gestionRolesUseCase.crearRol(new Rol(UUID.randomUUID(),"USER",
                   "Usuario regular con acceso limitado"));
       }
    }
}
