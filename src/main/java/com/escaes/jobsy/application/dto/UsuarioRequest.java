package com.escaes.jobsy.application.dto;

public record UsuarioRequest(
        Integer documento,
        String nombre,
        String email,
        String password
) {}

