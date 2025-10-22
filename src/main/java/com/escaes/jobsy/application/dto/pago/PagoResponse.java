package com.escaes.jobsy.application.dto.pago;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PagoResponse(String nombre) {
}
