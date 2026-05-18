package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.ValoracionCategoria;

import java.util.List;
import java.util.Optional;

public interface ValoracionCategoriaRepository {

    void save(ValoracionCategoria categoria);

    Optional<ValoracionCategoria> findByNombre(String nombre);

    List<ValoracionCategoria> findAll();

    long count();
}
