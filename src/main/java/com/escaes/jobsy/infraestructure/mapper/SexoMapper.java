package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.application.dto.genero.GeneroRequest;
import com.escaes.jobsy.application.dto.genero.GeneroResponse;
import com.escaes.jobsy.domain.model.Sexo;
import com.escaes.jobsy.infraestructure.entity.GeneroEntity;
import com.escaes.jobsy.infraestructure.entity.SexoEntity;

public class SexoMapper {

    public static Sexo toDomain(SexoEntity generoEntity){

        if(generoEntity == null){
            return null;
        }
        return new Sexo(SexoEntity.getId(), generoEntity.getNombreSexo());
    }

    public static  GeneroEntity toEntity(Sexo domain){
        if(domain==null){
            return null;
        }

        return GeneroEntity.builder()
                .id(domain.id())
                .nombreGenero(domain.nombreGenero())
                .build();
    }
    public static GeneroResponse entityToResponse(Sexo genero){
        if(genero==null){
            return null;
        }
        return new GeneroResponse(
                genero.nombreGenero()!= null? genero.nombreGenero() : null
        );
    }
    public static GeneroResponse requestToResponse(GeneroRequest generoRequest){
        if(generoRequest==null){
            return null;
        }
        return new GeneroResponse(
                generoRequest.nombre()!= null? generoRequest.nombre() : null
        );
    }
}
