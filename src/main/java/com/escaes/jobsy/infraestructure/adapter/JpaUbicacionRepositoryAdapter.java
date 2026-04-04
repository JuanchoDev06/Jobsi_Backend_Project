package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Ubicacion;
import com.escaes.jobsy.domain.repository.UbicacionRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataUbicacionRepository;
import com.escaes.jobsy.infraestructure.mapper.UbicacionMapper;
import com.escaes.jobsy.infraestructure.rest.exception.BusinessExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class JpaUbicacionRepositoryAdapter implements UbicacionRepository {


    private final SpringDataUbicacionRepository  springDataUbicacionRepository;


    @Override
    public Ubicacion create(Ubicacion ubicacion) {
        if(springDataUbicacionRepository.existsByNombreUbicacion(ubicacion.nombre())){
            throw new BusinessExceptions.ConflictException("Ubicación con nombre: "+ ubicacion.nombre()+"ya existe");
        }
        springDataUbicacionRepository.save(UbicacionMapper.toEntity(ubicacion));
        return ubicacion;
    }

    @Override
    public Boolean existsByNombre(String nombre) {
        return springDataUbicacionRepository.existsByNombreUbicacion(nombre);
    }

    @Override
    public Ubicacion findById(UUID id) {
        return springDataUbicacionRepository.findById(id)
                .map(UbicacionMapper::toDomain)
                .orElseThrow(()->new BusinessExceptions.NotFoundException("No existe el ubicación con el id: " + id));
    }

    @Override
    public Ubicacion findByNombre(String nombre) {
        return springDataUbicacionRepository.findByNombreUbicacionIgnoreCase(nombre.toLowerCase().trim())
                .map(UbicacionMapper::toDomain)
                .orElseThrow(()->new BusinessExceptions.NotFoundException("No existe el ubicación con el nombre: " + nombre));
    }

    @Override
    public List<Ubicacion> findAll() {
        return springDataUbicacionRepository.findAll()
                .stream()
                .map(UbicacionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return springDataUbicacionRepository.count();
    }

    @Override
    public void deleteById(UUID id) {
        Ubicacion ubicacion=findById(id);
        springDataUbicacionRepository.delete(UbicacionMapper.toEntity(ubicacion));
    }

    @Override
    public void deleteByNombreUbicacion(String nombre) {
        Ubicacion ubicacion=findByNombre(nombre);
        springDataUbicacionRepository.delete(UbicacionMapper.toEntity(ubicacion));
    }


}
