package com.escaes.jobsy.application.usecase.ubicacion;

import com.escaes.jobsy.domain.model.Ubicacion;
import com.escaes.jobsy.domain.repository.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ListarUbicacionUseCase {

    private final UbicacionRepository ubicacionRepository;


    public Long count(){
        return ubicacionRepository.count();
    }

    public List<Ubicacion> buscarTodos() {
        return ubicacionRepository.findAll();
    }

}
