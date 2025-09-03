package com.escaes.jobsy.application.usecase.trabajo;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.TrabajoRepository;
import com.escaes.jobsy.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class GestionTrabajosUseCase {

    private final TrabajoRepository trabajoRepository;

    private final UsuarioRepository usuarioRepository;

    public GestionTrabajosUseCase(TrabajoRepository trabajoRepository, UsuarioRepository usuarioRepository) {
        this.trabajoRepository = trabajoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public Trabajo crearTrabajo(CrearTrabajoRequest request, String solicitanteCorreo) {

        Usuario userSolcitante =usuarioRepository.findByCorreo(solicitanteCorreo)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        Trabajo trabajo= new Trabajo(
                UUID.randomUUID(),
                request.descripcion(),
                new Date(),
                request.pago(),
                request.ubicacion(),
                userSolcitante,
                null,
                null,
                null
        );
        trabajoRepository.save(trabajo);

        return trabajo;
    }

}
