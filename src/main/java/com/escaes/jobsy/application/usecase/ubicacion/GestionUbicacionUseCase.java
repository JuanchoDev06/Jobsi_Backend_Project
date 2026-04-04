package com.escaes.jobsy.application.usecase.ubicacion;

import com.escaes.jobsy.application.dto.ubicacion.UbicacionRequest;
import com.escaes.jobsy.domain.model.Ubicacion;
import com.escaes.jobsy.domain.repository.UbicacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GestionUbicacionUseCase {

    private final UbicacionRepository ubicacionRepository;


    public Ubicacion crear(UbicacionRequest request ) {
        return ubicacionRepository.create(Ubicacion.create(request));
    }

    public Boolean existePorNombre(UbicacionRequest request) {
        return ubicacionRepository.existsByNombre(request.nombre());
    }

    public Ubicacion buscarPorId (UUID id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion buscarPorNombre (UbicacionRequest request) {
        return ubicacionRepository.findByNombre(request.nombre());
    }

    public void eliminarPorId(UUID id) {
        ubicacionRepository.deleteById(id);
    }
    public void eliminarPorNombre(UbicacionRequest request) {
        ubicacionRepository.deleteByNombreUbicacion(request.nombre().trim().toLowerCase());
    }




}
