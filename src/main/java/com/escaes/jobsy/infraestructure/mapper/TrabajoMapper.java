package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.infraestructure.entity.TrabajoEntity;

public class TrabajoMapper {

    /*
    Falta categorias y estado
     */

    public static Trabajo toDomain(TrabajoEntity trabajoEntity) {

        if(trabajoEntity == null) {
            return null;
        }
        return new Trabajo(
            trabajoEntity.getId(),
            trabajoEntity.getDescripcion(),
            trabajoEntity.getFechaPublicacion(),
            trabajoEntity.getPago(),
            trabajoEntity.getUbicacion(),
            trabajoEntity.getSolicitante() !=null ? UsuarioMapper.toDomainBasic(trabajoEntity.getSolicitante()): null,
            trabajoEntity.getTrabajador() != null ? UsuarioMapper.toDomainBasic(trabajoEntity.getTrabajador()): null,
                null,
                null
            //CategoriaMapper.toDomain(trabajoEntity.getCategoria()),
            //EstadoMapper.toDomain(trabajoEntity.getEstado())
        );
    }
    public static TrabajoEntity toEntity(Trabajo trabajo) {
        if(trabajo == null) {
            return null;
        }
        return new TrabajoEntity(
            trabajo.id(),
            trabajo.descripcion(),
            trabajo.fechaPublicacion(),
            trabajo.pago(),
            trabajo.ubicacion(),
            trabajo.solicitante() != null ? UsuarioMapper.toEntity(trabajo.solicitante()): null,
            trabajo.trabajador() != null ? UsuarioMapper.toEntity(trabajo.trabajador()): null
            //CategoriaMapper.toEntity(trabajo.categoria()),
            //EstadoMapper.toEntity(trabajo.estado())
        );
    }
}
