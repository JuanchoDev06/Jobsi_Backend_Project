package com.escaes.jobsy.application.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponse(
        String uuid,
        String nombre,
        String email,
        String rol
) {}