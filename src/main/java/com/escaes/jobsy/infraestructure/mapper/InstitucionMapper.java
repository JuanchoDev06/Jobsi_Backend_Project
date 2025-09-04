package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Institucion;
import com.escaes.jobsy.infraestructure.entity.InstitucionEntity;

public class InstitucionMapper {

    public static Institucion toDomain(InstitucionEntity institucionEntity) {
        if (institucionEntity == null) {
            return null;
        }
        return new Institucion(
                institucionEntity.getId(), institucionEntity.getNombre(),
                institucionEntity.getDepartamento(),  institucionEntity.getMunicipio()
        );
    }
    public static InstitucionEntity toEntity(Institucion institucion) {
        if (institucion == null) {
            return null;
        }
        return new InstitucionEntity(
                institucion.id(), institucion.nombre(), institucion.departamento(),
                institucion.municipio()
        );
    }
}
