package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.ValoracionCategoria;
import com.escaes.jobsy.infraestructure.persistence.entity.ValoracionCategoriaEntity;

public class ValoracionCategoriaMapper {

    public static ValoracionCategoria toDomain(ValoracionCategoriaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ValoracionCategoria(entity.getId(), entity.getNombre());
    }

    public static ValoracionCategoriaEntity toEntity(ValoracionCategoria categoria) {
        if (categoria == null) {
            return null;
        }
        return ValoracionCategoriaEntity.builder()
                .id(categoria.id())
                .nombre(categoria.nombre())
                .build();
    }
}
