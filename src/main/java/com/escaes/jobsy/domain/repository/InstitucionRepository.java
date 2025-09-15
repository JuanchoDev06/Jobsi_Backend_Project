package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Institucion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstitucionRepository {

    void save(Institucion institucion);
    Optional<Institucion> findById(UUID id);
    List<Institucion> findByNombre(String nombre);

    List<Institucion> findAll();
    List<Institucion> findByDepartamento(String departamento);
    List<Institucion> findByMunicipio(String municipio);
    List<Institucion> findByDepartamentoAndMunicipio(String departamento,String municipio);
    List<Institucion> findByNombreAndDepartamento(String nombre,String departamento);
    Optional<Institucion>findByNombreAndDepartamentoAndMuncipio(String nombre,String departamento,String municipio);

    void delete(Institucion institucion);

}
