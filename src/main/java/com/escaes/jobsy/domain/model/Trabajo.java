package com.escaes.jobsy.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Trabajo(
        Long id,
        String titulo,
        String descripcion,
        LocalDate fecha,
        String ubicacion,
        Estado estado,
        TipoPago tipoPago,
        Usuario publicadoPor,
        Usuario asignadoA,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
