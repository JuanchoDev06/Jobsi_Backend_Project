package com.escaes.jobsy.config;

import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import com.escaes.jobsy.application.usecase.rol.GestionRolesUseCase;
import com.escaes.jobsy.application.usecase.rol.ListarRolesUseCase;
import com.escaes.jobsy.application.usecase.trabajo.GestionTrabajosUseCase;
import com.escaes.jobsy.application.usecase.usuario.GestionUsuariosUseCase;
import com.escaes.jobsy.application.usecase.usuario.ListarUsuariosUseCase;
import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.model.Rol;
import com.escaes.jobsy.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;

    private final GestionRolesUseCase gestionRolesUseCase;

    private final ListarRolesUseCase listarRolesUseCase;

    private final GestionUsuariosUseCase  gestionUsuariosUseCase;

    private final ListarUsuariosUseCase  listarUsuariosUseCase;

    public DataInitializer(GestionGenerosUseCase gestionGenerosUseCase,
                           ListarGenerosUseCase listarGenerosUseCase, GestionRolesUseCase gestionRolesUseCase,
                           ListarRolesUseCase listarRolesUseCase, GestionUsuariosUseCase gestionUsuariosUseCase,
                           ListarUsuariosUseCase listarUsuariosUseCase) {
        this.gestionGenerosUseCase = gestionGenerosUseCase;
        this.listarGenerosUseCase = listarGenerosUseCase;
        this.gestionRolesUseCase = gestionRolesUseCase;
        this.listarRolesUseCase = listarRolesUseCase;
        this.gestionUsuariosUseCase = gestionUsuariosUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeGeneros();
        initializeRoles();
        initializeAdmin();
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
    private void initializeAdmin(){
        if(listarUsuariosUseCase.contarUsuarios()==0){
            Genero genero= gestionGenerosUseCase.obtenerGeneroPorNombre("Alien");
            Rol rol= gestionRolesUseCase.obtenerRolPorNombre("ADMIN");
            gestionUsuariosUseCase.crearUsuario(new Usuario(
                    UUID.randomUUID(),"escaes",1107834660,
                    "escaes@gmail.com","123",false,
                    new Date(), genero,rol, List.of(),List.of()
            ));
            System.out.println("Admin creado");
        }
    }
}
