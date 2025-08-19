package com.escaes.jobsy.infraestructure.rest.controller;


import com.escaes.jobsy.application.dto.usuario.UsuarioRequest;
import com.escaes.jobsy.application.usecase.usuario.GestionUsuariosUseCase;
import com.escaes.jobsy.application.usecase.usuario.ListarUsuariosUseCase;
import com.escaes.jobsy.domain.model.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UsuarioController {

    private final GestionUsuariosUseCase gestionUsuariosUseCase;

    private final ListarUsuariosUseCase listarUsuariosUseCase;

    public UsuarioController(GestionUsuariosUseCase gestionUsuariosUseCase, ListarUsuariosUseCase listarUsuariosUseCase) {
        this.gestionUsuariosUseCase = gestionUsuariosUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>>crearUsuario(@RequestBody UsuarioRequest request) {

        Usuario usuario = new Usuario(
                UUID.randomUUID(),
                request.nombre(),
                request.documento(),
                request.email(),
                request.password(),
                false,
                null,
                null,
                null,
                null
        );
        gestionUsuariosUseCase.crearUsuario(usuario);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuario creado exitosamente");
        response.put("data", usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{documento}")
    public ResponseEntity<Usuario> obtenerPorDocumento(@PathVariable Integer documento) {
        Usuario usuario = gestionUsuariosUseCase.obtenerUsuarioPorDocumento(documento);


        return ResponseEntity.ok(usuario);
    }
}
