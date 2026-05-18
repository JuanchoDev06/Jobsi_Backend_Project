package com.escaes.jobsy.application.dto.solicitud;

import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud;

import java.time.LocalDateTime;
import java.util.UUID;

public record SolicitudResponse(UUID id, Usuario trabajador, Trabajo trabajo, EstadoSolicitud estado, LocalDateTime fechaCreacion) {
}
