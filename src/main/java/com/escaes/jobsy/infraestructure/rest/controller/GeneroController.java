package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.genero.GeneroRequest;
import com.escaes.jobsy.application.dto.genero.GeneroResponse;
import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.infraestructure.mapper.GeneroMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@Tag(name = "Generos", description = "Operaciones relacionadas con géneros")
@RequiredArgsConstructor
public class GeneroController {

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;

    @PostMapping("/admin/gender/create")
    public ResponseEntity<GeneroResponse> crearGenero(@RequestBody GeneroRequest request) {
        gestionGenerosUseCase.crearGenero(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GeneroMapper.requestToResponse(request));
    }
}
