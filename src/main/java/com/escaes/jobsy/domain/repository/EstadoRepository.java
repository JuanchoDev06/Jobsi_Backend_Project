package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Estado;


import java.util.List;
import java.util.Optional;

public interface EstadoRepository {

    Optional<Estado> findById(Long id);
    Optional<Estado> findByNombre(String nombre);
    List<Estado> findAll();
    void save(Estado estado);
    void delete(Estado estado);
    void deleteById(Long id);
    void deleteByNombre(String nombre);


}
