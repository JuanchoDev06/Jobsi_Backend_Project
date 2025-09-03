package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.application.dto.trabajo.TrabajoResponse;
import com.escaes.jobsy.application.usecase.trabajo.GestionTrabajosUseCase;
import com.escaes.jobsy.application.usecase.trabajo.ListarTrabajosUseCase;
import com.escaes.jobsy.domain.model.Trabajo;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public class TrabajoController {

    private final GestionTrabajosUseCase gestionTrabajosUseCase;

    private final ListarTrabajosUseCase  listarTrabajosUseCase;

    public TrabajoController(GestionTrabajosUseCase gestionTrabajosUseCase,  ListarTrabajosUseCase listarTrabajosUseCase) {
        this.gestionTrabajosUseCase = gestionTrabajosUseCase;
        this.listarTrabajosUseCase = listarTrabajosUseCase;
    }

    @PostMapping("/jobs/create")
    public ResponseEntity<Map<String, Object>> crearTrabajo(
            @RequestBody CrearTrabajoRequest request,
            Authentication authentication
    ) {
        // El correo lo obtenemos del token
        String solicitanteCorreo = authentication.getName();

        // Ejecutamos el caso de uso
        Trabajo trabajo = gestionTrabajosUseCase.crearTrabajo(request, solicitanteCorreo);

        // Convertimos a DTO de salida
        TrabajoResponse data = new TrabajoResponse(
                trabajo.id(),
                trabajo.descripcion(),
                trabajo.pago(),
                trabajo.ubicacion(),
                trabajo.estado() !=null ? trabajo.estado().nombre() : null,
                trabajo.categoria() != null ? trabajo.categoria().nombre() : null,
                trabajo.solicitante().correo(),
                trabajo.trabajador() != null ? trabajo.trabajador().correo() : null
        );
        Map<String, Object> response= new HashMap<>();
        response.put("data", data);
        response.put("message","Trabajo creado con exito");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/public/all-jobs")
    public ResponseEntity<List<TrabajoResponse>> obtenerTrabajos(){

        List<Trabajo> trabajos= listarTrabajosUseCase.listar();

        List<TrabajoResponse> responses = trabajos.stream()
                .map(trabajo -> new TrabajoResponse(
                        null,
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

//    @GetMapping("/jobs/my-jobs")
//    public ResponseEntity<List<TrabajoResponse>> obtenerTrabajosMyJobs(){}

}
