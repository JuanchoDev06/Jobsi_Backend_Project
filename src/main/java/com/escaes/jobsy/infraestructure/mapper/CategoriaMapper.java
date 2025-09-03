package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.infraestructure.entity.CategoriaEntity;

public class CategoriaMapper {

    public static Categoria toDomain(CategoriaEntity entity){

        if(entity==null){
            return null;
        }
        return new Categoria(
                entity.getId(), entity.getNombre()
        );
    }
    public static CategoriaEntity toEntity(Categoria categoria){
        if(categoria==null){
            return null;
        }
        return new CategoriaEntity(
                categoria.id(),categoria.nombre()
        );
    }
}
