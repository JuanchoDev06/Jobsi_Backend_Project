package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Valoracion;

import java.util.List;
import java.util.UUID;

public interface ValoracionRepository {

    void save(Valoracion valoracion);

    List<Valoracion> findByTrabajoId(UUID trabajoId);
}
