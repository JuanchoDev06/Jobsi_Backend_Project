package com.escaes.jobsy.application.dto.auth;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JwtResponse(String token) {
}
