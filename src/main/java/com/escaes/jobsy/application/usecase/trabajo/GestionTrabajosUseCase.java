package com.escaes.jobsy.application.usecase.trabajo;

import com.escaes.jobsy.application.dto.trabajo.CrearTrabajoRequest;
import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.domain.model.Estado;
import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.CategoriaRepository;
import com.escaes.jobsy.domain.repository.EstadoRepository;
import com.escaes.jobsy.domain.repository.TrabajoRepository;
import com.escaes.jobsy.domain.repository.UsuarioRepository;
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

    public Trabajo crearTrabajo(CrearTrabajoRequest request, String solicitanteCorreo) {

        Usuario userSolcitante =usuarioRepository.findByCorreo(solicitanteCorreo)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        Categoria categoria = categoriaRepository.findByNombre(request.categoria())
                .orElseThrow(()->new RuntimeException("Categoria no encontrado"));

        Estado estado= estadoRepository.findByNombre("PENDIENTE")
                .orElseThrow(()-> new RuntimeException("Estado no encontrado"));

        Trabajo trabajo= new Trabajo(
                UUID.randomUUID(),
                request.descripcion(),
                new Date(),
                request.pago(),
                request.ubicacion(),
                userSolcitante,
                null,
                categoria,
                estado
        );
        trabajoRepository.save(trabajo);

        return trabajo;
    }

}
