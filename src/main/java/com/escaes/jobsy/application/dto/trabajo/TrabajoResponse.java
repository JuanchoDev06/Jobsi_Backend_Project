package com.escaes.jobsy.application.dto.trabajo;

import java.util.UUID;

public record TrabajoResponse(
        UUID id,
        String descripcion,
        Double pago,
        String ubicacion,
        String estado,
        String categoria,
        String solicitanteCorreo,
        String trabajadorCorreo
) {}