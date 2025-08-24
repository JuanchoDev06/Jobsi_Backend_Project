package com.escaes.jobsy.infraestructure.jpa;


import com.escaes.jobsy.infraestructure.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataGeneroRepository extends JpaRepository<GeneroEntity, Long> {
    Optional<GeneroEntity> findByNombreGenero(String nombre);
}
