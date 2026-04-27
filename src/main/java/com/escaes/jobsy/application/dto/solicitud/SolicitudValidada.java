package com.escaes.jobsy.application.dto.solicitud;

import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;

public record SolicitudValidada(Usuario usuario,
                                Trabajo trabajo) {

}
