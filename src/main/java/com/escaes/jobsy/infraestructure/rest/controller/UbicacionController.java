package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.ubicacion.UbicacionRequest;
import com.escaes.jobsy.application.dto.ubicacion.UbicacionResponse;
import com.escaes.jobsy.application.usecase.ubicacion.GestionUbicacionUseCase;
import com.escaes.jobsy.application.usecase.ubicacion.ListarUbicacionUseCase;
import com.escaes.jobsy.infraestructure.mapper.UbicacionMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Tag(name = "Ubicaciones", description = "Operaciones relacionadas con ubicaciones enfocadas en trabajos")
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UbicacionController {

    private final GestionUbicacionUseCase  gestionUbicacionUseCase;

    private final ListarUbicacionUseCase listarUbicacionUseCase;

    private static final Logger LOG = Logger.getLogger(UbicacionController.class.getName());


    @PostMapping("/ubication")
    public ResponseEntity<UbicacionResponse>createUbicacion(@RequestBody UbicacionRequest ubicacionRequest){
        LOG.info("Solicitud de creación de ubicación con nombre: "+ ubicacionRequest.nombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UbicacionMapper
                        .domainToResponse(gestionUbicacionUseCase
                                .crear(ubicacionRequest)));
    }

    @GetMapping("/ubication")
    public ResponseEntity<List<UbicacionResponse>>listarUbicacion() {
        LOG.info("Solicitud obtener todas las ubicaciones");
        List<UbicacionResponse> responses = listarUbicacionUseCase
                .buscarTodos()
                .stream()
                .map(UbicacionMapper::domainToResponse).toList();
        return ResponseEntity.ok((responses));
    }

    @GetMapping("/lookups/ubication")
    public ResponseEntity<List<UbicacionResponse>>listarUbicacionMetaData(){
        LOG.info("Solicitud obtener todas las ubicaciones");
        List<UbicacionResponse>responses = listarUbicacionUseCase
                .buscarTodos()
                .stream()
                .map(UbicacionMapper::domainToResponse).toList();
        return ResponseEntity.ok((responses));
    }


    @GetMapping("/ubication/{name}")
    public ResponseEntity<UbicacionResponse>obtenerUbicacion(@PathVariable String name){
        LOG.info("Solicitud obtener ubicación con nombre: "+ name);
        UbicacionRequest ubicacionRequest = new UbicacionRequest(name);
        return ResponseEntity.ok(UbicacionMapper
                .domainToResponse(gestionUbicacionUseCase
                        .buscarPorNombre(ubicacionRequest)));
    }


    @DeleteMapping("/ubication/{name}")
    public ResponseEntity<UbicacionResponse> deleteUbicacion(@PathVariable String name){
        LOG.info("Solicitud eliminar ubicación con nombre: "+ name);
        UbicacionRequest ubicacionRequest = new UbicacionRequest(name);
        gestionUbicacionUseCase.eliminarPorNombre(ubicacionRequest);
        return ResponseEntity.noContent().build();
    }
}
