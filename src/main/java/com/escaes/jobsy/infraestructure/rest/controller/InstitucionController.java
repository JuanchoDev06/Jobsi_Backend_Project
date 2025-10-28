package com.escaes.jobsy.infraestructure.rest.controller;

import com.escaes.jobsy.application.dto.institucion.InstitucionRequest;
import com.escaes.jobsy.application.dto.institucion.InstitucionResponse;
import com.escaes.jobsy.application.dto.institucion.ubicacion.Departamento;
import com.escaes.jobsy.application.dto.institucion.ubicacion.DepartamentoSimpleResponse;
import com.escaes.jobsy.application.dto.institucion.ubicacion.Municipio;
import com.escaes.jobsy.application.service.UbicacionService;
import com.escaes.jobsy.application.usecase.institucion.GestionInstitucionesUseCase;
import com.escaes.jobsy.application.usecase.institucion.ListarInstitucionesUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Instituciones",description = "Gestion de instituciones con sus respectivas ubicaciones")
@RequiredArgsConstructor
public class InstitucionController {

    private final ListarInstitucionesUseCase listarInstitucionesUseCase;

    private final GestionInstitucionesUseCase gestionInstitucionesUseCase;

    private final UbicacionService ubicacionService;

    // ------------------- Crear institución -------------------
    @PostMapping("/admin/create-institution")
    public ResponseEntity<InstitucionResponse> crearInstitucion(@RequestBody InstitucionRequest request) {
        gestionInstitucionesUseCase.crearInstitucion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InstitucionMapper.requestToResponse(request));
    }

    //Obtener departamentos y municipios relacionados en un JSON
    @GetMapping("/public/departments-and-municipalities")
    public ResponseEntity<List<Departamento>> listarDepartamentosMunicipios() {
        return ResponseEntity.ok(ubicacionService.listarDepartamentos());
    }

    //Obtener solo departamentos
    @GetMapping("/public/departments")
    public ResponseEntity<List<DepartamentoSimpleResponse>>listarDepartamentos() {
       List <Departamento> departamentos= ubicacionService.listarDepartamentos();
        return ResponseEntity.ok(departamentos.stream()
                .map(dep -> new DepartamentoSimpleResponse(dep.codigo(), dep.nombre()))
                .toList());
    }
    //Obtener municipios relacionados a un departamento
    @GetMapping("/public/departments/{code}/municipalities")
    public ResponseEntity<List<Municipio>> listarMunicipios(@PathVariable String code) {
        return ResponseEntity.ok(ubicacionService.municipiosPorDepartamento(code));
    }
    //Obtener instituciones asociadadas a departamento y municipio
    @GetMapping("/public/institutions/{departamentoCodigo}/{municipioCodigo}")
    public ResponseEntity<List<InstitucionResponse>> getInstituciones(
            @PathVariable String departamentoCodigo,
            @PathVariable String municipioCodigo) {

        String nombreDepartamento = ubicacionService.nombreDepartamento(departamentoCodigo);
        String nombreMunicipio = ubicacionService.nombreMunicipio(departamentoCodigo, municipioCodigo);

        List<Institucion> instituciones =
                listarInstitucionesUseCase.institucionesPorDepartamentoMunicipio(nombreDepartamento, nombreMunicipio);

        return ResponseEntity.ok(
                instituciones.stream()
                        .map(InstitucionMapper::entityToResponse)
                        .toList()
        );
    }
    //Obtener todas las instituciones registradas
    @GetMapping("/public/institutions-all")
    public ResponseEntity<List<InstitucionResponse>> getInstituciones() {
        return ResponseEntity.ok(listarInstitucionesUseCase.listarInstituciones().stream()
                .map(InstitucionMapper::entityToResponse).toList());
    }

}
