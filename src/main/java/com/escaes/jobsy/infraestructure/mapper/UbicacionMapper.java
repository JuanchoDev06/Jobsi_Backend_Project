package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.application.dto.ubicacion.UbicacionResponse;
import com.escaes.jobsy.domain.model.Ubicacion;
import com.escaes.jobsy.infraestructure.persistence.entity.UbicacionEntity;

public class UbicacionMapper {

    public static UbicacionEntity toEntity(Ubicacion ubicacion) {

        if (ubicacion == null) {
            return null;
        }

        return UbicacionEntity.builder()
                .id(ubicacion.id())
                .nombreUbicacion(ubicacion.nombre())
                .build();
    }

    public static Ubicacion toDomain(UbicacionEntity ubicacionEntity) {
        if (ubicacionEntity == null) {
            return null;
        }
        return new Ubicacion(
                ubicacionEntity.getId(),
                ubicacionEntity.getNombreUbicacion()
        );
    }

    public static UbicacionResponse domainToResponse(Ubicacion ubicacion) {
        if (ubicacion == null) {
            return null;
        }
        return new UbicacionResponse(ubicacion.nombre().toUpperCase());
    }
}
