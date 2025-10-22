package com.escaes.jobsy.application.dto.estado;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EstadoResponse(String nombre) {
}
