package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.estado.EstadoRequest;
import com.escaes.jobsy.application.dto.estado.EstadoResponse;
import com.escaes.jobsy.application.usecase.estado.GestionEstadosUseCase;
import com.escaes.jobsy.application.usecase.estado.ListarEstadosUseCase;
import com.escaes.jobsy.domain.model.Estado;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="Estados", description = "Gestion de estados de trabajos(ADMIN)")
@RequestMapping("/v1")
public class EstadoController {

    private final GestionEstadosUseCase gestionEstadosUseCase;

    private final ListarEstadosUseCase listarEstadosUseCase;

    public EstadoController(GestionEstadosUseCase gestionEstadosUseCase
            ,ListarEstadosUseCase listarEstadosUseCase){
        this.gestionEstadosUseCase=gestionEstadosUseCase;
        this.listarEstadosUseCase=listarEstadosUseCase;
    }

    @PostMapping("/admin/create/state")
    public ResponseEntity<Map<String,Object>> createEstado(EstadoRequest request){

        gestionEstadosUseCase.crearEstado(request.nombre());

        Map<String,Object> response = new HashMap<>();
        response.put("data",request.nombre());
        response.put("message","Estado guardado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/admin/state/{name}")
    public ResponseEntity<EstadoResponse> getEstado(@PathVariable String name){
        Estado estado = gestionEstadosUseCase.obtenerEstadoPorNombre(name);
        return ResponseEntity.ok(new EstadoResponse(estado.nombre()));
    }

    @GetMapping("/admin/state-all")
    public ResponseEntity<List<EstadoResponse>> getAllEstado(){

        return ResponseEntity.ok(listarEstadosUseCase.listarEstados().stream().map(
                estado -> new EstadoResponse(
                        estado.nombre())).toList());
    }
}
