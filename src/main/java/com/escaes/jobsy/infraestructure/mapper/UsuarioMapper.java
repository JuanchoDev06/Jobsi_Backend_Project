package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.infraestructure.entity.UsuarioEntity;

import java.util.UUID;

public class UsuarioMapper {

    public static Usuario toDomain(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }
        return new Usuario(
                UUID.fromString( usuarioEntity.getId()),
                usuarioEntity.getNombre(),
                usuarioEntity.getDocumento(),
                usuarioEntity.getCorreo(),
                usuarioEntity.getClave(),
                usuarioEntity.getBloqueado(),
                usuarioEntity.getFechaNacimiento(),
                null,
                null,
                null
                //usuarioEntity.getGenero(),
                //usuarioEntity.getRol(),
                //usuarioEntity.getTrabajos()
        );
    }
    public static UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioEntity.builder()
                .id(usuario.id() != null ? usuario.id().toString() : UUID.randomUUID().toString())
                .nombre(usuario.nombre())
                .documento(usuario.documento())
                .correo(usuario.correo())
                .clave(usuario.clave())
                .bloqueado(usuario.bloqueado())
                .fechaNacimiento(usuario.fechaNacimiento())
                //.genero(usuario.getGenero())
                //.rol(usuario.getRol())
                //.trabajos(usuario.getTrabajos())
                .build();
    }
}
