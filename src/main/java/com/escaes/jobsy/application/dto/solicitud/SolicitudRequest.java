package com.escaes.jobsy.application.dto.solicitud;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SolicitudRequest( @NotBlank UUID trabajo) {
}
