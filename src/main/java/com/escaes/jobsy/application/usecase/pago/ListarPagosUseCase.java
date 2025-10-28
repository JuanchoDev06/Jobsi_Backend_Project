package com.escaes.jobsy.application.usecase.pago;

import com.escaes.jobsy.domain.model.TipoPago;
import com.escaes.jobsy.domain.repository.PagoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarPagosUseCase {

    private final PagoRepository pagoRepository;

    public Integer contarTipoDePagos(){
        return pagoRepository.findAll().size();
    }
    public List<TipoPago> listar() {
        return pagoRepository.findAll();
    }
}
