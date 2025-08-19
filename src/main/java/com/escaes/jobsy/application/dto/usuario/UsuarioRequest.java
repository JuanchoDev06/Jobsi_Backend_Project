package com.escaes.jobsy.application.dto.usuario;

public record UsuarioRequest(
        Integer documento,
        String nombre,
        String email,
        String password
) {}

