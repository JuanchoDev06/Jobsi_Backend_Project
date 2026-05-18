package com.escaes.jobsy.domain.model;

import com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud;

import java.time.LocalDateTime;
import java.util.UUID;

public record Solicitud(UUID id, Usuario trabajador, Trabajo trabajo, EstadoSolicitud estado,
                        LocalDateTime fechaCreacion, LocalDateTime fechaFinalizacion) {


    public static Solicitud crear(Usuario trabajador, Trabajo trabajo) {
        return new Solicitud(UUID.randomUUID(), trabajador, trabajo, EstadoSolicitud.PENDIENTE, null, null);
    }

}
