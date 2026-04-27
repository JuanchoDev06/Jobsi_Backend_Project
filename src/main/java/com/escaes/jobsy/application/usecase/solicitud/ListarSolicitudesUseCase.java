package com.escaes.jobsy.application.usecase.solicitud;

import com.escaes.jobsy.application.dto.solicitud.SolicitudResponse;
import com.escaes.jobsy.application.dto.solicitud.SolicitudUsuarioResponse;
import com.escaes.jobsy.application.usecase.usuario.GestionUsuariosUseCase;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.SolicitudRepository;
import com.escaes.jobsy.infraestructure.mapper.SolicitudMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarSolicitudesUseCase {

    private final SolicitudRepository solicitudRepository;

    private final GestionUsuariosUseCase  gestionUsuariosUseCase;

    public List<SolicitudResponse> listarSolicitudes(){
        return solicitudRepository.findAll()
                .stream()
                .map(SolicitudMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<SolicitudUsuarioResponse> listarSolicitudes(UUID trabajoId){
        return solicitudRepository.findSolicitudesConUsuario(trabajoId)
                .stream()
                .map(SolicitudMapper::toUserResponse)
                .toList();
    }

    public List<SolicitudResponse>listarSolicitudesPorUsuario(String correoUsuario){
        Usuario user= gestionUsuariosUseCase.obtenerUsuarioPorCorreo(correoUsuario);
        return solicitudRepository.findByUsuario(user)
                .stream()
                .map(SolicitudMapper::toResponse)
                .collect(Collectors.toList());
    }
}
