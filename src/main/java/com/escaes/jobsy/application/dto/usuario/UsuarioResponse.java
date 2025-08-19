package com.escaes.jobsy.application.dto.usuario;

public record UsuarioResponse(
        String uuid,
        String nombre,
        String email
) {}