package com.escaes.jobsy.application.dto.trabajo;


public record CrearTrabajoRequest(
        String descripcion,
        Double pago,
        String tipoPago,
        String ubicacion,
        String categoria
) {}