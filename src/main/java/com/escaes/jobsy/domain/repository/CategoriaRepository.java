package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Optional<Categoria>findById(Long id);
    Optional<Categoria> findByNombre(String nombre);
    List<Categoria> findAll();
    void save(Categoria categoria);
    void delete(Categoria categoria);
    void deleteById(Long id);
    void deleteByNombre(String nombre);
}
