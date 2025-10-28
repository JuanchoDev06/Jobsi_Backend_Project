package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.estado.EstadoRequest;
import com.escaes.jobsy.application.dto.estado.EstadoResponse;
import com.escaes.jobsy.application.usecase.estado.GestionEstadosUseCase;
import com.escaes.jobsy.application.usecase.estado.ListarEstadosUseCase;
import com.escaes.jobsy.domain.model.Estado;
import com.escaes.jobsy.infraestructure.mapper.EstadoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Tag(name="Estados", description = "Gestion de estados de trabajos(ADMIN)")
@RequestMapping("/v1")
@RequiredArgsConstructor
public class EstadoController {

    private final GestionEstadosUseCase gestionEstadosUseCase;

    private final ListarEstadosUseCase listarEstadosUseCase;


    @PostMapping("/admin/create/state")
    public ResponseEntity<EstadoResponse> createEstado(@RequestBody EstadoRequest request){
        gestionEstadosUseCase.crearEstado(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EstadoMapper.requestToResponse(request));
    }

    @GetMapping("/admin/state/{name}")
    public ResponseEntity<EstadoResponse> getEstado(@PathVariable String name){
        Estado estado = gestionEstadosUseCase.obtenerEstadoPorNombre(name);
        return ResponseEntity.ok(EstadoMapper.entityToResponse(estado));
    }

    @GetMapping("/admin/state-all")
    public ResponseEntity<List<EstadoResponse>> getAllEstado(){
        return ResponseEntity.ok(listarEstadosUseCase.listarEstados().stream().map(
                EstadoMapper::entityToResponse).toList());
    }
}
