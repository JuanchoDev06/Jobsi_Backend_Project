package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.model.Rol;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolRepository {
    Optional<Rol>findById(UUID id);
    Optional<Rol> findByNombre(String nombre);
    List<Rol>findAll();
    void save(Rol rol);
    void deleteById(UUID id);
    void deleteByNombre(String nombre);
    void delete(Rol rol);

}

