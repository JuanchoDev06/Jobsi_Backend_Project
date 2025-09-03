package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.categoria.CategoriaRequest;
import com.escaes.jobsy.application.dto.categoria.CatergoriaResponse;
import com.escaes.jobsy.application.usecase.categoria.GestionCategoriasUseCase;
import com.escaes.jobsy.application.usecase.categoria.ListarCategoriasUseCase;
import com.escaes.jobsy.domain.model.Categoria;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Categorias", description = "Operaciones de gestion de categorias (ADMIN)")
@RequestMapping("/v1")
public class CategoriaController {

    private final GestionCategoriasUseCase gestionCategoriasUseCase;

    private final ListarCategoriasUseCase  listarCategoriasUseCase;

    public CategoriaController(GestionCategoriasUseCase gestionCategoriasUseCase,
                               ListarCategoriasUseCase  listarCategoriasUseCase) {
        this.gestionCategoriasUseCase = gestionCategoriasUseCase;
        this.listarCategoriasUseCase = listarCategoriasUseCase;
    }
    @GetMapping("/admin/category-all")
    public ResponseEntity<List<CatergoriaResponse>> listarCategorias(){

        return ResponseEntity.ok(listarCategoriasUseCase.listarCategorias().stream()
                .map(categoria -> new CatergoriaResponse(categoria.nombre()

        )).toList());
    }
    @GetMapping("/admin/category/{name}")
    public ResponseEntity<CatergoriaResponse> getCategoria(@PathVariable String name){
        Categoria categoria = gestionCategoriasUseCase.buscarCategoriaPorNombre(name);
        return ResponseEntity.ok(new CatergoriaResponse(categoria.nombre()));
    }

    @PostMapping("/admin/category/create")
    public ResponseEntity<Map<String, String>> createCategoria(CategoriaRequest request){

        gestionCategoriasUseCase.crearCategoria(request.nombre());

        Map<String,String> response = new HashMap<>();
        response.put("message", "Categoria guardada correctamente");
        response.put("data", request.nombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
