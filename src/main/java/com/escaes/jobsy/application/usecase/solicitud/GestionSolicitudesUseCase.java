package com.escaes.jobsy.application.usecase.solicitud;

import com.escaes.jobsy.application.dto.solicitud.SolicitudRequest;
import com.escaes.jobsy.application.dto.solicitud.SolicitudValidada;
import com.escaes.jobsy.domain.model.Solicitud;
import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.SolicitudRepository;
import com.escaes.jobsy.domain.repository.TrabajoRepository;
import com.escaes.jobsy.domain.repository.UsuarioRepository;
import com.escaes.jobsy.infraestructure.rest.exception.BusinessExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GestionSolicitudesUseCase {

    private final SolicitudRepository solicitudRepository;

    private final UsuarioRepository usuarioRepository;

    private final TrabajoRepository trabajoRepository;

    private SolicitudValidada validateSolicitud (SolicitudRequest request) {

        if (request.trabajo()==null) {
            throw new BusinessExceptions.BadRequestException("El Id del trabajo no puede ser nulo");
        }
        if (request.trabajadorEmail().isBlank()) {
            throw new BusinessExceptions.BadRequestException("El Email del trabajador no puede ser nulo");
        }

        Usuario user = usuarioRepository.findByCorreo(request.trabajadorEmail())
                .orElseThrow(() -> new BusinessExceptions
                        .NotFoundException("Usuario no encontrado con correo: " + request.trabajadorEmail()));

        Trabajo job = trabajoRepository.findById(request.trabajo()).orElseThrow(()->new  BusinessExceptions
                .NotFoundException("Trabajo no encontrado"));

        if (solicitudRepository.existsByUsuarioCorreoAndTrabajoId(request.trabajadorEmail(), request.trabajo())) {
            throw new BusinessExceptions.ConflictException("Ya Aplicaste al trabajo seleccionado");
        }

       return new SolicitudValidada(user,job);
    }

    public void crearSolicitud(SolicitudRequest request) {
        SolicitudValidada validated= validateSolicitud(request);

        Solicitud solicitud = Solicitud.crear(validated.usuario(),validated.trabajo());

        solicitudRepository.save(solicitud);
    }
}
