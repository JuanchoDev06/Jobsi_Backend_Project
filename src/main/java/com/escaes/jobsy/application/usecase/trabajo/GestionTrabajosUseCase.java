package com.escaes.jobsy.application.usecase.trabajo;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.domain.model.*;
import com.escaes.jobsy.domain.repository.*;
import com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud;
import com.escaes.jobsy.infraestructure.rest.exception.BusinessExceptions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class GestionTrabajosUseCase {

    private final TrabajoRepository trabajoRepository;

    private final UsuarioRepository usuarioRepository;

    private final EstadoRepository estadoRepository;

    private final CategoriaRepository categoriaRepository;

    private final PagoRepository pagoRepository;

    private final UbicacionRepository ubicacionRepository;

    private final SolicitudRepository solicitudRepository;

    /*
     * Eventualmente, delimitar cuantos trabajos activos puede crear un usuario
     * */
    private void validateTrabajo(CrearTrabajoRequest request, String solicitanteCorreo) {
        if (request == null) {
            throw new IllegalArgumentException("El trabajo no puede ser nulo");
        }
        if (solicitanteCorreo == null || solicitanteCorreo.isEmpty()) {
            throw new BusinessExceptions.BadRequestException("El solicitante no puede ser nulo");
        }
        if (request.pago() <= 0) {
            throw new BusinessExceptions.BadRequestException("El pago debe ser un valor positivo");
        }
        if (request.titulo().isEmpty() || request.titulo().isBlank()) {
            throw new BusinessExceptions.BadRequestException("El titulo no puede ser nulo o vacio");
        }
        if (request.ubicacion().isEmpty()) {
            throw new BusinessExceptions.BadRequestException("La ubicacion no puede ser nulo o vacio");
        }
    }

    public Trabajo crearTrabajo(CrearTrabajoRequest request, String solicitanteCorreo) {

        validateTrabajo(request, solicitanteCorreo);
        Usuario userSolcitante = usuarioRepository.findByCorreo(solicitanteCorreo)
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Usuario no encontrado"));

        Categoria categoria = categoriaRepository.findByNombre(request.categoria())
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Categoria no encontrado"));

        Estado estado = estadoRepository.findByNombre("PENDIENTE")
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Estado no encontrado"));

        Pago tipoPago = pagoRepository.findByNombre(request.tipoPago())
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Tipo de pago no encontrado"));

        Ubicacion ubicacion=  ubicacionRepository.findByNombre(request.ubicacion());

        Trabajo trabajo = Trabajo.crear(request, userSolcitante,ubicacion, null, categoria, estado, tipoPago);

        trabajoRepository.save(trabajo);

        return trabajo;
    }

    public Trabajo obtenerTrabajoPorId(UUID id) {
        return trabajoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trabajo no encontrado"));
    }

    /*
     *Eventualmente, limitar que un usuario no pueda asignarse mas de X trabajos al mismo tiempo
     * */
    @Transactional
    public Trabajo asignarTrabajo(UUID trabajoId, UUID solicitudId) {

        Trabajo trabajo = trabajoRepository
                .findById(trabajoId)
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Trabajo no encontrado"));

        if (!"PENDIENTE".equals(trabajo.estado().nombre())) {
            throw new BusinessExceptions.BadRequestException("Trabajo no disponible para asignarse");
        }

        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Solicitud no encontrada"));

        if (!solicitud.trabajo().id().equals(trabajoId)) {
            throw new BusinessExceptions.BadRequestException("La solicitud no pertenece al trabajo");
        }

        Usuario trabajador = solicitud.trabajador();

        if (trabajador.correo().equals(trabajo.solicitante().correo())) {
            throw new BusinessExceptions.BadRequestException("No puedes asignarte tu propio trabajo");
        }

        Estado estadoAsignado = estadoRepository.findByNombre("ASIGNADO")
                .orElseThrow(() -> new BusinessExceptions.NotFoundException("Estado 'ASIGNADO' no existe"));

        Trabajo updated = new Trabajo(
                trabajo.id(),
                trabajo.titulo(),
                trabajo.descripcion(),
                trabajo.fechaPublicacion(),
                trabajo.pago(),
                trabajo.ubicacion(),
                trabajo.solicitante(),
                trabajador,
                trabajo.categoria(),
                estadoAsignado,
                trabajo.tipoPago()
        );

        trabajoRepository.save(updated);

        solicitudRepository.actualizarEstado(solicitudId, EstadoSolicitud.ACEPTADA);
        solicitudRepository.rechazarOtrasSolicitudes(trabajoId, solicitudId);

        return updated;
    }

    public void eliminarTrabajoPorIdYUsuarioCorreoSolicitante(UUID id, String solicitanteCorreo) {
        Trabajo trabajo = obtenerTrabajoPorId(id);
        if (!trabajo.solicitante().correo().equals(solicitanteCorreo)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar este trabajo");
        }
        trabajoRepository.deleteById(id);
    }
    public void eliminarAplicacionATrabajoCorreoTrabajador(UUID id, String trabajadorCorreo){
        Trabajo trabajo = obtenerTrabajoPorId(id);

        Estado estadoPendiente = estadoRepository.findByNombre("PENDIENTE")
                .orElseThrow(() -> new IllegalArgumentException("Estado 'PENDIENTE' no existe"));

        Optional<Trabajo> job = trabajoRepository
                .findByTrabajadorCorreoAndEstado(trabajadorCorreo, "ASIGNADO")
                .stream()
                .filter(t -> t.id().equals(trabajo.id()))
                .findFirst();

        if (job.isEmpty()) {
            throw new BusinessExceptions.NotFoundException(
                    "No se puede quitar aplicación a trabajo ya que no está en tus trabajos con estado asignado"
            );
        }
        Trabajo trabajoActualizado = new Trabajo(
                job.get().id(),
                job.get().titulo(),
                job.get().descripcion(),
                job.get().fechaPublicacion(),
                job.get().pago(),
                job.get().ubicacion(),
                job.get().solicitante(),
                null,
                job.get().categoria(),
                estadoPendiente,
                job.get().tipoPago()
        );

        trabajoRepository.save(trabajoActualizado);
    }

    public void abandonarTrabajoPorIdYUsuarioCorreoTrabajador(UUID jobId, String correoTrabajador) {
        Trabajo trabajo = trabajoRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado"));

        if (trabajo.trabajador() == null) {
            throw new RuntimeException("El trabajo no tiene trabajador asignado");
        }

        if (!trabajo.trabajador().correo().equals(correoTrabajador)) {
            throw new RuntimeException("No puedes abandonar un trabajo que no te pertenece");
        }

        // Se cambia el estado a PENDIENTE
        Estado estadoPendiente = estadoRepository.findByNombre("PENDIENTE")
                .orElseThrow(() -> new IllegalArgumentException("Estado 'PENDIENTE' no existe"));

        Trabajo updated = new Trabajo(
                trabajo.id(),
                trabajo.titulo(),
                trabajo.descripcion(),
                trabajo.fechaPublicacion(),
                trabajo.pago(),
                trabajo.ubicacion(),
                trabajo.solicitante(),
                null,        // quitar trabajador
                trabajo.categoria(),
                estadoPendiente,      // volver a pendiente
                trabajo.tipoPago()
        );

        trabajoRepository.save(updated);
    }


}
