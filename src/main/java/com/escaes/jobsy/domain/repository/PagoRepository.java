package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.TipoPago;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoRepository {

    Optional<TipoPago> findById(UUID id);
    Optional<TipoPago> findByNombre(String nombrePago);

    void save(TipoPago pago);
    void delete(TipoPago pago);

    List<TipoPago> findAll();
}
