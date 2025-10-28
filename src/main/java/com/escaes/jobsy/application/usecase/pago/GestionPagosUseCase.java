package com.escaes.jobsy.application.usecase.pago;

import com.escaes.jobsy.application.dto.pago.PagoRequest;
import com.escaes.jobsy.domain.model.TipoPago;
import com.escaes.jobsy.domain.repository.PagoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class GestionPagosUseCase {

    private final PagoRepository pagoRepository;


    public void crearTipoPago(PagoRequest request) {
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del tipo de pago no puede ser nulo o vacío");
        }
        if(pagoRepository.findByNombre(request.nombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un tipo de pago con el nombre proporcionado");
        }
        TipoPago nuevoPago = new TipoPago(UUID.randomUUID(), request.nombre());
        pagoRepository.save(nuevoPago);
    }

    public void updatePago(TipoPago pago) {
        if (pago.nombrePago() == null || pago.nombrePago().isBlank()) {
            throw new IllegalArgumentException("El nombre del tipo de pago no puede ser nulo o vacío");
        }
        if (pagoRepository.findById(pago.id()).isEmpty()) {
            throw new IllegalArgumentException("Tipo de pago no encontrado con el ID proporcionado");
        }
        pagoRepository.save(pago);
    }
    public TipoPago obtenerPagoPorId(UUID id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el tipo de pago con el ID proporcionado: "+ id));
    }
    public TipoPago obtenerPagoPorNombre(String nombre) {
        return pagoRepository.findByNombre(nombre)
                .orElseThrow(()->new IllegalArgumentException("No exite tipo de pago con el nombre proporcionado: "+ nombre));
    }

    public void eliminarPago(TipoPago pago) {
        if (pago.nombrePago() == null || pago.nombrePago().isBlank()) {
            throw new IllegalArgumentException("El nombre de tipo de pago no puede ser nulo");
        }
        if(pagoRepository.findById(pago.id()).isEmpty()) {
            throw new IllegalArgumentException("No existe el tipo de pago con el ID proporcionado");
        }
        pagoRepository.delete(pago);
    }

    public void eliminarPagoPorNombre(String nombre) {
        TipoPago pago= obtenerPagoPorNombre(nombre);
        pagoRepository.delete(pago);
    }
}
