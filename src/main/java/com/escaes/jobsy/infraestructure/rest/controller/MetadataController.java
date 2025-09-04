package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.categoria.CatergoriaResponse;
import com.escaes.jobsy.application.dto.estado.EstadoResponse;
import com.escaes.jobsy.application.dto.genero.GeneroResponse;
import com.escaes.jobsy.application.dto.pago.PagoResponse;
import com.escaes.jobsy.application.usecase.categoria.ListarCategoriasUseCase;
import com.escaes.jobsy.application.usecase.estado.ListarEstadosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import com.escaes.jobsy.application.usecase.pago.ListarPagosUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name="Metadata", description = "Metadatos para alimentar otros Enpoints")
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MetadataController {

    private final ListarCategoriasUseCase listarCategoriasUseCase;

    private final ListarEstadosUseCase listarEstadosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;

    private final ListarPagosUseCase listarPagosUseCase;


    @GetMapping("/lookups/category-all")
    public ResponseEntity<List<CatergoriaResponse>> listarCategoriasMetaData(){

        return ResponseEntity.ok(listarCategoriasUseCase.listarCategorias().stream()
                .map(categoria -> new CatergoriaResponse(categoria.nombre()
                )).toList());
    }


    @GetMapping("/lookups/state-all")
    public ResponseEntity<List<EstadoResponse>> getAllEstadoMetaData(){

        return ResponseEntity.ok(listarEstadosUseCase.listarEstados().stream().map(
                estado -> new EstadoResponse(
                        estado.nombre())).toList());
    }


    @GetMapping("/lookups/gender-all")
    public ResponseEntity<List<GeneroResponse>> getAllGenerosMetaData(){
        return ResponseEntity.ok(listarGenerosUseCase.listarGeneros()
                .stream().map(genero->
                new GeneroResponse(genero.nombreGenero())
        ).toList());
    }

    @GetMapping("/lookups/payment-all")
    public ResponseEntity<List<PagoResponse>> getAllPagosMetaData(){
        return ResponseEntity.ok(listarPagosUseCase.listar().stream().map(pago->new PagoResponse(
                pago.nombrePago()
        )).toList());
    }
}
