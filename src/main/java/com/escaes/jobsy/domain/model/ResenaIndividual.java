package com.escaes.jobsy.domain.model;

import java.time.LocalDateTime;

public record ResenaIndividual(
        Long id,
        Integer puntuacion,
        String opiniones,
        String imagenes,
        Trabajo trabajo,
        Usuario evaluador,
        Usuario evaluado,
        LocalDateTime fechaCreacion
) {}
