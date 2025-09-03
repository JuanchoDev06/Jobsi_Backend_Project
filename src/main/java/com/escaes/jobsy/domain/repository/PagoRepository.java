package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Pago;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoRepository {

    Optional<Pago> findById(UUID id);
    Optional<Pago> findByNombre(String nombrePago);

    void save(Pago pago);
    void delete(Pago pago);

    List<Pago> findAll();
}
