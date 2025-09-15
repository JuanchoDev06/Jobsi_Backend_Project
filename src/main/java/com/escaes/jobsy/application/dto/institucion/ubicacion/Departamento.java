package com.escaes.jobsy.application.dto.institucion.ubicacion;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Departamento(String codigo, String nombre, List<Municipio> municipios) {
}
