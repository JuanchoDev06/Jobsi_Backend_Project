package com.escaes.jobsy.domain.model;

public record Valoracion(Long id, Trabajo trabajo, ValoracionCategoria categoria,
                         Integer puntuacion, String comentario) {
}
