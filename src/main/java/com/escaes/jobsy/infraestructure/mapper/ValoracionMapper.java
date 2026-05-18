package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Valoracion;
import com.escaes.jobsy.infraestructure.persistence.entity.ValoracionEntity;

public class ValoracionMapper {

    public static ValoracionEntity toEntity(Valoracion valoracion) {
        if (valoracion == null) {
            return null;
        }
        return ValoracionEntity.builder()
                .id(valoracion.id())
                .trabajo(TrabajoMapper.toEntity(valoracion.trabajo()))
                .categoria(ValoracionCategoriaMapper.toEntity(valoracion.categoria()))
                .puntuacion(valoracion.puntuacion())
                .comentario(valoracion.comentario())
                .build();
    }

    public static Valoracion toDomain(ValoracionEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Valoracion(
                entity.getId(),
                TrabajoMapper.toDomain(entity.getTrabajo()),
                ValoracionCategoriaMapper.toDomain(entity.getCategoria()),
                entity.getPuntuacion(),
                entity.getComentario());
    }
}
