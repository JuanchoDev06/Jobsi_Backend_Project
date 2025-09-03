package com.escaes.jobsy.application.usecase.pago;

import com.escaes.jobsy.domain.model.Pago;
import com.escaes.jobsy.domain.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPagosUseCase {

    private final PagoRepository pagoRepository;

    public ListarPagosUseCase(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }
    public Integer contarTipoDePagos(){
        return pagoRepository.findAll().size();
    }
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }
}
