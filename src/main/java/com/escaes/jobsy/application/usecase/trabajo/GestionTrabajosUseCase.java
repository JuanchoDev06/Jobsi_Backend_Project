package com.escaes.jobsy.application.usecase.trabajo;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.domain.model.*;
import com.escaes.jobsy.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GestionTrabajosUseCase {

    private final TrabajoRepository trabajoRepository;

    private final UsuarioRepository usuarioRepository;

    private final EstadoRepository estadoRepository;

    private final CategoriaRepository categoriaRepository;

    private final PagoRepository  pagoRepository;

    public Trabajo crearTrabajo(CrearTrabajoRequest request, String solicitanteCorreo) {

        Usuario userSolcitante =usuarioRepository.findByCorreo(solicitanteCorreo)
                .orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));

        Categoria categoria = categoriaRepository.findByNombre(request.categoria())
                .orElseThrow(()->new IllegalArgumentException("Categoria no encontrado"));

        Estado estado= estadoRepository.findByNombre("PENDIENTE")
                .orElseThrow(()-> new IllegalArgumentException("Estado no encontrado"));

        TipoPago tipoPago= pagoRepository.findByNombre(request.tipoPago())
                .orElseThrow(()->new IllegalArgumentException("Tipo de pago no encontrado"));

        Trabajo trabajo= new Trabajo(
                UUID.randomUUID(),
                request.descripcion(),
                new Date(),
                request.pago(),
                request.ubicacion(),
                userSolcitante,
                null,
                categoria,
                estado,
                tipoPago
        );
        trabajoRepository.save(trabajo);

        return trabajo;
    }

}
