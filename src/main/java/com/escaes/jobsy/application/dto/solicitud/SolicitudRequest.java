package com.escaes.jobsy.application.dto.solicitud;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SolicitudRequest(@NotNull UUID trabajo) {
}
