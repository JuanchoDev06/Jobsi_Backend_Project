package com.escaes.jobsy.application.dto.usuario;

import java.util.Date;

public record UsuarioRequest(
        Integer documento,
        String nombre,
        String email,
        String password,
        Date fechaNacimiento,
        String genero,
        String rol
) {}

