package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Solicitud;
import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudRepository {

    Optional<Solicitud> findById(UUID id);

    void save(Solicitud solicitud);

    void delete(Solicitud solicitud);

    void deleteById(UUID id);

    List<Solicitud> findAll();

    List<Solicitud>findByUsuario(Usuario usuario);

    List<Solicitud>findByTrabajo(Trabajo trabajo);

    List<Solicitud>findSolicitudesConUsuario(UUID trabajoId);

    Boolean existsByUsuarioCorreoAndTrabajoId(String usuarioDocumento, UUID trabajoId);


}
