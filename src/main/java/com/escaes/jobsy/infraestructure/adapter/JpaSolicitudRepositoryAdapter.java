package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Solicitud;
import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.SolicitudRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataSolicitudRepository;
import com.escaes.jobsy.infraestructure.mapper.SolicitudMapper;
import com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaSolicitudRepositoryAdapter implements SolicitudRepository {

    private final SpringDataSolicitudRepository springDataSolicitudRepository;

    @Override
    public Optional<Solicitud> findById(UUID id) {
        return springDataSolicitudRepository.findById(id)
                .map(SolicitudMapper::toDomain);
    }

    @Override
    public void save(Solicitud solicitud) {
        springDataSolicitudRepository.save(SolicitudMapper.toEntity(solicitud));
    }

    @Override
    public void delete(Solicitud solicitud) {

    }

    @Override
    public void deleteById(UUID id) {
        springDataSolicitudRepository.deleteById(id);
    }

    @Override
    public List<Solicitud> findAll() {
        return springDataSolicitudRepository
                .findAll()
                .stream()
                .map(SolicitudMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Solicitud> findByUsuario(Usuario usuario) {
        return springDataSolicitudRepository.findByUsuario(usuario)
                .stream()
                .map(SolicitudMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Solicitud> findByTrabajo(Trabajo trabajo) {
        return springDataSolicitudRepository.findByTrabajo(trabajo)
                .stream()
                .map(SolicitudMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Solicitud> findSolicitudesConUsuario(UUID trabajoId) {
        return springDataSolicitudRepository.findSolicitudesConUsuario(trabajoId)
                .stream()
                .map(SolicitudMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsByUsuarioCorreoAndTrabajoId(String correo, UUID trabajoId) {
        return springDataSolicitudRepository
                .existsByUsuarioCorreoAndTrabajoId(correo, trabajoId);
    }

    @Override
    public void actualizarEstado(UUID trabajoId, EstadoSolicitud estado) {
        springDataSolicitudRepository.actualizarEstado(trabajoId, estado);
    }

    @Override
    public void rechazarOtrasSolicitudes(UUID trabajoId, UUID solicitudId) {
        springDataSolicitudRepository.rechazarOtrasSolicitudes(trabajoId, solicitudId);
    }
}
