package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Ubicacion;

import java.util.List;
import java.util.UUID;

public interface UbicacionRepository {

    Ubicacion create(Ubicacion ubicacion);

    Boolean existsByNombre(String nombre);
    Ubicacion findById(UUID id);
    Ubicacion findByNombre(String nombre);

    List<Ubicacion> findAll();

    Long count();

    void deleteById(UUID id);
    void deleteByNombreUbicacion(String nombre);
}
