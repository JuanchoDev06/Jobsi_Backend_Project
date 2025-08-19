package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository {

    void save(String genero);
    Optional<Genero> findById(Long id);
    Optional<Genero> findByNombre(String nombre);
    List<Genero>findAll();
    void deleteById(Long id);
    void deleteByNombre(String nombre);
    void delete(String genero);
}
