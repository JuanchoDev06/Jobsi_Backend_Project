package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.solicitud.SolicitudRequest;
import com.escaes.jobsy.application.dto.solicitud.SolicitudResponse;
import com.escaes.jobsy.application.dto.solicitud.SolicitudUsuarioResponse;
import com.escaes.jobsy.application.usecase.solicitud.GestionSolicitudesUseCase;
import com.escaes.jobsy.application.usecase.solicitud.ListarSolicitudesUseCase;
import com.escaes.jobsy.infraestructure.mapper.SolicitudMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@Tag(name = "Solicitudes", description = "Operaciones relacionadas con solicitudes a trabajos")
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SolicitudesController {

    private final GestionSolicitudesUseCase gestionSolicitudesUseCase;

    private final ListarSolicitudesUseCase listarSolicitudesUseCase;

    private static final Logger LOG = Logger.getLogger(SolicitudesController.class.getName());

    @PostMapping("/requests")
    public ResponseEntity<SolicitudResponse> aplicarTrabajo(@RequestBody @Valid SolicitudRequest request,
            Authentication authentication) {

        // El correo lo obtenemos del token
        String trabajadorCorreo = authentication.getName();

        return ResponseEntity
                .ok(SolicitudMapper.toResponse(gestionSolicitudesUseCase.crearSolicitud(request, trabajadorCorreo)));
    }

    @GetMapping("/requests/job/{jobId}")
    public ResponseEntity<List<SolicitudUsuarioResponse>> aplicacionesTrabajoId(
            @PathVariable UUID jobId, Authentication authentication) {
        LOG.info("Listando solicitudes para trabajo con Id: " + jobId);

        // El correo lo obtenemos del token; solo el creador del trabajo puede consultarlas
        return ResponseEntity.ok(
                listarSolicitudesUseCase.listarSolicitudes(jobId, authentication.getName()));
    }

    @GetMapping("/requests-applied")
    public ResponseEntity<List<SolicitudResponse>> aplicacionesTrabajoCorreoUser(Authentication authentication) {
        LOG.info("Listando aplicaciones para usuario con correo: " + authentication.getName());

        // El correo lo obtenemos del token
        String trabajadorCorreo = authentication.getName();

        return ResponseEntity.ok(listarSolicitudesUseCase.listarSolicitudesPorUsuario(trabajadorCorreo));
    }

}
