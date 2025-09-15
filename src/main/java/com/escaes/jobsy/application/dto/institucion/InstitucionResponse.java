package com.escaes.jobsy.application.dto.institucion;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InstitucionResponse(UUID id,String nombre, String departamento, String municipio) {
}
