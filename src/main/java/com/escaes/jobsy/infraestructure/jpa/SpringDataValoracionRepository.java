package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.infraestructure.persistence.entity.ValoracionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataValoracionRepository extends JpaRepository<ValoracionEntity, Long> {

    List<ValoracionEntity> findByTrabajoId(UUID trabajoId);
}
