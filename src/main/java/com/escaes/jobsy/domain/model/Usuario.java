package com.escaes.jobsy.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record Usuario(
        Long id,
        String nombre,
        String documento,
        String correo,
        String clave,
        String celular,
        LocalDate fechaNacimiento,
        Sexo sexo,
        Rol rol,
        List<Trabajo> trabajosPublicados,
        List<Trabajo> trabajosAsignados,
        Float calificacionPromedio,
        Integer calificacionConteo,
        Boolean bloqueado
) {}
