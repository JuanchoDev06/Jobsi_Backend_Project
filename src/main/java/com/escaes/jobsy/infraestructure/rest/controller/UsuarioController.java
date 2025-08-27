package com.escaes.jobsy.infraestructure.rest.controller;


import com.escaes.jobsy.application.dto.usuario.UsuarioRequest;
import com.escaes.jobsy.application.dto.usuario.UsuarioResponse;
import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.rol.GestionRolesUseCase;
import com.escaes.jobsy.application.usecase.usuario.GestionUsuariosUseCase;
import com.escaes.jobsy.application.usecase.usuario.ListarUsuariosUseCase;
import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.model.Rol;
import com.escaes.jobsy.domain.model.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UsuarioController {

    private final GestionUsuariosUseCase gestionUsuariosUseCase;

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarUsuariosUseCase listarUsuariosUseCase;

    private final GestionRolesUseCase gestionRolesUseCase;

    public UsuarioController(GestionUsuariosUseCase gestionUsuariosUseCase, ListarUsuariosUseCase listarUsuariosUseCase,
                             GestionGenerosUseCase gestionGenerosUseCase, GestionRolesUseCase gestionRolesUseCase) {
        this.gestionRolesUseCase = gestionRolesUseCase;
        this.gestionGenerosUseCase = gestionGenerosUseCase;
        this.gestionUsuariosUseCase = gestionUsuariosUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
    }

    @PostMapping("/public/users/create")
    public ResponseEntity<Map<String, Object>>crearUsuario(@RequestBody UsuarioRequest request) {

        Genero genero= gestionGenerosUseCase.obtenerGeneroPorNombre(request.genero());

        Rol rol = gestionRolesUseCase.obtenerRolPorNombre(
                request.rol() != null ? request.rol() : "USER"
        );

        gestionUsuariosUseCase.crearUsuario(request,genero,rol);

        UsuarioResponse data= new UsuarioResponse(null, request.nombre(), request.email(), request.rol());

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", "Usuario creado exitosamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/admin/users/{documento}")
    public ResponseEntity<Usuario> obtenerPorDocumento(@PathVariable Integer documento) {
        Usuario usuario = gestionUsuariosUseCase.obtenerUsuarioPorDocumento(documento);


        return ResponseEntity.ok(usuario);
    }
}
