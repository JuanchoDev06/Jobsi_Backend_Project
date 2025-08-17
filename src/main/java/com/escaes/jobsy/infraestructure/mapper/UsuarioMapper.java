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
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuario.id() != null ? usuario.id().toString() : UUID.randomUUID().toString());
        usuarioEntity.setNombre(usuario.nombre());
        usuarioEntity.setDocumento(usuario.documento());
        usuarioEntity.setCorreo(usuario.correo());
        usuarioEntity.setClave(usuario.clave());
        usuarioEntity.setBloqueado(usuario.bloqueado());
        usuarioEntity.setFechaNacimiento(usuario.fechaNacimiento());
        //usuarioEntity.setGenero(usuario.genero());
        //usuarioEntity.setRol(usuario.rol());
        //usuarioEntity.setTrabajos(usuario.trabajos());
        return usuarioEntity;
    }
}
