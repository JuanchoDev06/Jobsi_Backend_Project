package com.escaes.jobsy.config;

import com.escaes.jobsy.application.dto.usuario.UsuarioRequest;
import com.escaes.jobsy.application.usecase.categoria.GestionCategoriasUseCase;
import com.escaes.jobsy.application.usecase.categoria.ListarCategoriasUseCase;
import com.escaes.jobsy.application.usecase.estado.GestionEstadosUseCase;
import com.escaes.jobsy.application.usecase.estado.ListarEstadosUseCase;
import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import com.escaes.jobsy.application.usecase.rol.GestionRolesUseCase;
import com.escaes.jobsy.application.usecase.rol.ListarRolesUseCase;
import com.escaes.jobsy.application.usecase.trabajo.GestionTrabajosUseCase;
import com.escaes.jobsy.application.usecase.usuario.GestionUsuariosUseCase;
import com.escaes.jobsy.application.usecase.usuario.ListarUsuariosUseCase;
import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.model.Rol;
import com.escaes.jobsy.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;

    private final GestionRolesUseCase gestionRolesUseCase;

    private final ListarRolesUseCase listarRolesUseCase;

    private final GestionUsuariosUseCase  gestionUsuariosUseCase;

    private final ListarUsuariosUseCase  listarUsuariosUseCase;

    private final GestionEstadosUseCase gestionEstadosUseCase;

    private final ListarEstadosUseCase listarEstadosUseCase;

    private final GestionCategoriasUseCase gestionCategoriasUseCase;

    private final ListarCategoriasUseCase listarCategoriasUseCase;


    @Override
    public void run(String... args) throws Exception {
        initializeGeneros();
        initializeRoles();
        initializeAdmin();
        initializeEstados();
        initializeCategorias();
    }

    private void initializeGeneros() {
        if(listarGenerosUseCase.contarGeneros()==0){
            gestionGenerosUseCase.crearGenero(new Genero(null, "Masculino"));
            gestionGenerosUseCase.crearGenero(new Genero(null, "Femenino"));
            gestionGenerosUseCase.crearGenero(new Genero(null, "Alien"));
            gestionGenerosUseCase.crearGenero(new Genero(null, "Otro"));
            System.out.println("Generos creados");
        }

    }
    private void initializeRoles() {
       if (listarRolesUseCase.contarRoles()==0){
           gestionRolesUseCase.crearRol(new Rol(UUID.randomUUID(),"ADMIN",
                   "Administrador del sistema con acceso completo"));
           gestionRolesUseCase.crearRol(new Rol(UUID.randomUUID(),"USER",
                   "Usuario regular con acceso limitado"));

           System.out.println("Roles creados");
       }
    }
    private void initializeEstados(){
        if (listarEstadosUseCase.contarEstados()==0){
            gestionEstadosUseCase.crearEstado("PENDIENTE");
            gestionEstadosUseCase.crearEstado("FINALIZADO");
            gestionEstadosUseCase.crearEstado("ASIGNADO");
            gestionEstadosUseCase.crearEstado("CANCELADO");
            gestionEstadosUseCase.crearEstado("BLOQUEADO");
            System.out.println("Estados creados");
        }
    }
    private void initializeCategorias(){
        if (listarCategoriasUseCase.listarCategorias().isEmpty()){
            gestionCategoriasUseCase.crearCategoria("ASESORIAS");
            gestionCategoriasUseCase.crearCategoria("TAREAS");
            gestionCategoriasUseCase.crearCategoria("MATERIALES");
            gestionCategoriasUseCase.crearCategoria("ENTRENAMIENTOS");
            gestionCategoriasUseCase.crearCategoria("OTRO");
        }
    }
    private void initializeAdmin(){
        if(listarUsuariosUseCase.contarUsuarios()==0){
            Genero genero= gestionGenerosUseCase.obtenerGeneroPorNombre("Alien");
            Rol rol= gestionRolesUseCase.obtenerRolPorNombre("ADMIN");
            UsuarioRequest admin= new UsuarioRequest(1107834660,
                    "escaes","escaes@gmail.com","123",new Date(), "","");
            gestionUsuariosUseCase.crearUsuario(admin,genero,rol);
            System.out.println("Admin creado");
        }
    }
}
