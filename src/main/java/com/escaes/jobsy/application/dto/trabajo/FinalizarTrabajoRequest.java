package com.escaes.jobsy.application.dto.trabajo;

import java.util.List;

/**
 * Cuerpo de la peticion para finalizar un trabajo: el solicitante envia una
 * puntuacion (1-5) por cada categoria de valoracion, mas un comentario opcional.
 */
public record FinalizarTrabajoRequest(List<PuntuacionCategoria> puntuaciones, String comentario) {

    public record PuntuacionCategoria(String categoria, Integer puntuacion) {
    }
}
