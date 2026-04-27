package com.escaes.jobsy.application.usecase.solicitud;

import com.escaes.jobsy.application.dto.solicitud.SolicitudResponse;
import com.escaes.jobsy.domain.repository.SolicitudRepository;
import com.escaes.jobsy.infraestructure.mapper.SolicitudMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarSolicitudesUseCase {

    private final SolicitudRepository solicitudRepository;

    public List<SolicitudResponse> listarSolicitudes(){
        return solicitudRepository.findAll()
                .stream()
                .map(SolicitudMapper::toResponse)
                .collect(Collectors.toList());
    }
}
