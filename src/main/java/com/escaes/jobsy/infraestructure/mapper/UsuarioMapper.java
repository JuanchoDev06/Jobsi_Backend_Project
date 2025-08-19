package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.infraestructure.entity.UsuarioEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UsuarioMapper {

    private final GeneroMapper generoMapper;


    public UsuarioMapper(GeneroMapper generoMapper) {
        this.generoMapper = generoMapper;
    }

    public static Usuario toDomain(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getNombre(),
                usuarioEntity.getDocumento(),
                usuarioEntity.getCorreo(),
                usuarioEntity.getClave(),
                usuarioEntity.getBloqueado(),
                usuarioEntity.getFechaNacimiento(),
                usuarioEntity.getGenero() != null ? GeneroMapper.toDomain(usuarioEntity.getGenero()) : null,
                usuarioEntity.getRol() != null ? RolMapper.toDomain(usuarioEntity.getRol()) : null ,
                null
                //usuarioEntity.getRol(),
                //usuarioEntity.getTrabajos()
        );
    }
    public static UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioEntity.builder()
                .id(usuario.id() != null ? usuario.id() : UUID.randomUUID())
                .nombre(usuario.nombre())
                .documento(usuario.documento())
                .correo(usuario.correo())
                .clave(usuario.clave())
                .bloqueado(usuario.bloqueado())
                .fechaNacimiento(usuario.fechaNacimiento())
                .genero(usuario.genero() != null ? GeneroMapper.toEntity(usuario.genero()) : null)
                .rol(usuario.rol() != null ? RolMapper.toEntity(usuario.rol()) : null)
                //.trabajos(usuario.getTrabajos())
                .build();
    }
}
