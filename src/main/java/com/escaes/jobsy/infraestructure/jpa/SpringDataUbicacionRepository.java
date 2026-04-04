package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.infraestructure.persistence.entity.UbicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUbicacionRepository extends JpaRepository<UbicacionEntity,UUID> {

    Boolean existsByNombreUbicacion(String nombre);

    Optional<UbicacionEntity> findByNombreUbicacionIgnoreCase(String nombre);

}
