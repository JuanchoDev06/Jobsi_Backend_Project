package com.escaes.jobsy.application.dto;

public record UsuarioResponse(
        String uuid,
        String nombre,
        String email
) {}