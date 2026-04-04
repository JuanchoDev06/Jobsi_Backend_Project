package com.escaes.jobsy.domain.model;

import com.escaes.jobsy.application.dto.ubicacion.UbicacionRequest;

import java.util.UUID;

public record Ubicacion(UUID id, String nombre) {


    public static Ubicacion create( UbicacionRequest request ) {

        return new Ubicacion(UUID.randomUUID(), request.nombre().toLowerCase().trim());
    }
}
