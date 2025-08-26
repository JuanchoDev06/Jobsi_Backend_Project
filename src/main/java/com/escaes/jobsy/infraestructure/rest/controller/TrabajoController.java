package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.application.dto.trabajo.TrabajoResponse;
import com.escaes.jobsy.application.usecase.trabajo.GestionTrabajosUseCase;
import com.escaes.jobsy.application.usecase.trabajo.ListarTrabajosUseCase;
import com.escaes.jobsy.domain.model.Trabajo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Trabajos",description = "Operaciones relacionadas con trabajos")
@RequestMapping("/v1")
public class TrabajoController {

    private final GestionTrabajosUseCase gestionTrabajosUseCase;

    private final ListarTrabajosUseCase  listarTrabajosUseCase;

    public TrabajoController(GestionTrabajosUseCase gestionTrabajosUseCase,  ListarTrabajosUseCase listarTrabajosUseCase) {
        this.gestionTrabajosUseCase = gestionTrabajosUseCase;
        this.listarTrabajosUseCase = listarTrabajosUseCase;
    }

    @PostMapping("/jobs/create")
    public ResponseEntity<TrabajoResponse> crearTrabajo(
            @RequestBody CrearTrabajoRequest request,
            Authentication authentication
    ) {
        // El correo lo obtenemos del token
        String solicitanteCorreo = authentication.getName();

        // Ejecutamos el caso de uso
        Trabajo trabajo = gestionTrabajosUseCase.crearTrabajo(request, solicitanteCorreo);

        // Convertimos a DTO de salida
        TrabajoResponse response = new TrabajoResponse(
                trabajo.id(),
                trabajo.descripcion(),
                trabajo.pago(),
                trabajo.ubicacion(),
                null,
                trabajo.categoria() != null ? trabajo.categoria().nombre() : null,
                trabajo.solicitante().correo(),
                trabajo.trabajador() != null ? trabajo.trabajador().correo() : null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/all-jobs")
    public ResponseEntity<List<TrabajoResponse>> obtenerTrabajos(){

        List<Trabajo> trabajos= listarTrabajosUseCase.listar();

        List<TrabajoResponse> responses = trabajos.stream()
                .map(trabajo -> new TrabajoResponse(
                        trabajo.id(),
                        trabajo.descripcion(),
                        trabajo.pago(),
                        trabajo.ubicacion(),
                        //trabajo.estado().toString()
                        null,
                        trabajo.categoria() != null ? trabajo.categoria().nombre() : null,
                        trabajo.solicitante() != null ? trabajo.solicitante().correo() : null,
                        trabajo.trabajador() != null ? trabajo.trabajador().correo() : null
                ))
                .toList();
        return ResponseEntity.ok(responses);

    }

}
