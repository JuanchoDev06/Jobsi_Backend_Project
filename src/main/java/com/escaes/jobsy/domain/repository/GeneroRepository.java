package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Sexo;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository {

    void save(Sexo genero);
    Optional<Sexo> findById(Long id);
    Optional<Sexo> findByNombre(String nombre);
    List<Sexo>findAll();
    void deleteById(Long id);
    void deleteByNombre(String nombre);
    void delete(String genero);
}
