package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.infraestructure.entity.TipoPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPagoRepository extends JpaRepository<TipoPagoEntity, UUID> {
    Optional<TipoPagoEntity> findByNombreIgnoreCase(String nombrePago);
}
