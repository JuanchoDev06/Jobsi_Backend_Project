package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Estado;
import com.escaes.jobsy.infraestructure.entity.EstadoEntity;

public class EstadoMapper {

    public static Estado toDomain(EstadoEntity estadoEntity) {
        if  (estadoEntity == null) {
            return null;
        }
        return new Estado(
                estadoEntity.getId(), estadoEntity.getNombre()
        );
    }
    public static EstadoEntity toEntity(Estado estado) {
        if  (estado == null) {
            return null;
        }
        return new EstadoEntity(
                estado.id(),
                estado.nombre()
        );
    }
}
