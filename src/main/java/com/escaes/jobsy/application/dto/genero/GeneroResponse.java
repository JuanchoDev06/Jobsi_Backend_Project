package com.escaes.jobsy.application.dto.genero;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GeneroResponse(String nombre) {
}
