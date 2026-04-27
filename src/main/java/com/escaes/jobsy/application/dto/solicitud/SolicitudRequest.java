package com.escaes.jobsy.application.dto.solicitud;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SolicitudRequest(@Email String trabajadorEmail, @NotBlank UUID trabajo) {
}
