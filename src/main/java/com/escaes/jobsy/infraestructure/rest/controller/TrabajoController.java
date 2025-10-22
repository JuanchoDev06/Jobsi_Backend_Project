package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.application.dto.trabajo.TrabajoResponse;
import com.escaes.jobsy.application.usecase.trabajo.GestionTrabajosUseCase;
import com.escaes.jobsy.application.usecase.trabajo.ListarTrabajosUseCase;
import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.infraestructure.mapper.TrabajoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Trabajos",description = "Operaciones relacionadas con trabajos")
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TrabajoController {

    private final GestionTrabajosUseCase gestionTrabajosUseCase;

    private final ListarTrabajosUseCase  listarTrabajosUseCase;

    @PostMapping("/jobs/create")
    public ResponseEntity<TrabajoResponse> crearTrabajo(
            @RequestBody CrearTrabajoRequest request,
            Authentication authentication
    ) {
        // El correo lo obtenemos del token
        String solicitanteCorreo = authentication.getName();

        // Ejecutamos el caso de uso
        Trabajo trabajo = gestionTrabajosUseCase.crearTrabajo(request, solicitanteCorreo);


        return ResponseEntity.status(HttpStatus.CREATED).body(TrabajoMapper.requestToResponse(request,solicitanteCorreo));
    }

    @GetMapping("/public/all-jobs")
    public ResponseEntity<List<TrabajoResponse>> obtenerTrabajos(){

        // Ejecutamos el caso de uso
        List<Trabajo> trabajos= listarTrabajosUseCase.listar();

        List<TrabajoResponse> responses = trabajos.stream()
                .map(TrabajoMapper::entityToResponse
                )
                .toList();
        return ResponseEntity.ok(responses);

    }

//    @GetMapping("/jobs/my-jobs")
//    public ResponseEntity<List<TrabajoResponse>> obtenerTrabajosMyJobs(){}

}
