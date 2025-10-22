package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.categoria.CategoriaRequest;
import com.escaes.jobsy.application.dto.categoria.CatergoriaResponse;
import com.escaes.jobsy.application.usecase.categoria.GestionCategoriasUseCase;
import com.escaes.jobsy.application.usecase.categoria.ListarCategoriasUseCase;
import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.infraestructure.mapper.CategoriaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Categorias", description = "Operaciones de gestion de categorias (ADMIN)")
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CategoriaController {

    private final GestionCategoriasUseCase gestionCategoriasUseCase;

    private final ListarCategoriasUseCase  listarCategoriasUseCase;

    @GetMapping("/admin/category-all")
    public ResponseEntity<List<CatergoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(listarCategoriasUseCase.listarCategorias().stream()
                .map(CategoriaMapper::entityToResponse).toList());
    }
    @GetMapping("/admin/category/{name}")
    public ResponseEntity<CatergoriaResponse> getCategoria(@PathVariable String name){
        Categoria categoria = gestionCategoriasUseCase.buscarCategoriaPorNombre(name);
        return ResponseEntity.ok(new CatergoriaResponse(categoria.nombre()));
    }

    @PostMapping("/admin/category/create")
    public ResponseEntity<CatergoriaResponse> createCategoria(@RequestBody CategoriaRequest request){
        gestionCategoriasUseCase.crearCategoria(request.nombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaMapper.requestToResponse(request));
    }

}
