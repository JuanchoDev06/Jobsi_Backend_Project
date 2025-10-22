package com.escaes.jobsy.application.usecase.estado;

import com.escaes.jobsy.domain.model.Estado;
import com.escaes.jobsy.domain.repository.EstadoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarEstadosUseCase {

    private final EstadoRepository estadoRepository;

    public List<Estado>listarEstados(){
        return estadoRepository.findAll();
    }

    public Integer contarEstados(){
        return estadoRepository.findAll().size();
    }
}
