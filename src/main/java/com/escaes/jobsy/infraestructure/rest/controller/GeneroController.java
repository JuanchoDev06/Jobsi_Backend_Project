package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.genero.GeneroRequest;
import com.escaes.jobsy.application.dto.genero.GeneroResponse;
import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import com.escaes.jobsy.domain.model.Genero;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@Tag(name = "Generos", description = "Operaciones relacionadas con géneros")
public class GeneroController {

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;


    public GeneroController(GestionGenerosUseCase gestionGenerosUseCase, ListarGenerosUseCase listarGenerosUseCase) {
        this.gestionGenerosUseCase = gestionGenerosUseCase;
        this.listarGenerosUseCase = listarGenerosUseCase;
    }

    @PostMapping("/gender/create")
    public ResponseEntity<Map<String, Object>> crearGenero(@RequestBody GeneroRequest request) {

        Genero genero = new Genero(
                null,
                request.nombre()
        );

        gestionGenerosUseCase.crearGenero(genero);

        GeneroResponse response = new GeneroResponse(request.nombre());

        Map<String, Object> Generoresponse = Map.of(
                "data", response,
                "message", "Género creado exitosamente"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(Generoresponse);
    }
}
