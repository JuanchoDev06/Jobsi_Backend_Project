package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.infraestructure.persistence.entity.ValoracionCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataValoracionCategoriaRepository extends JpaRepository<ValoracionCategoriaEntity, Long> {

    Optional<ValoracionCategoriaEntity> findByNombreIgnoreCase(String nombre);
}
