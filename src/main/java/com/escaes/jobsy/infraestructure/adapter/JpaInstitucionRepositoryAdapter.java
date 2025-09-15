package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Institucion;
import com.escaes.jobsy.domain.repository.InstitucionRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataInstitucionRepository;
import com.escaes.jobsy.infraestructure.mapper.InstitucionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaInstitucionRepositoryAdapter implements InstitucionRepository {

    private final SpringDataInstitucionRepository institucionRepository;

    public JpaInstitucionRepositoryAdapter(SpringDataInstitucionRepository
                                                   institucionRepository) {
        this.institucionRepository = institucionRepository;
    }

    @Override
    public void save(Institucion institucion) {
        institucionRepository.save(InstitucionMapper.toEntity(institucion));
    }

    @Override
    public Optional<Institucion> findById(UUID id) {
        return institucionRepository.findById(id).map(InstitucionMapper::toDomain);
    }

    @Override
    public List<Institucion> findByNombre(String nombre) {
        return institucionRepository.findByNombre(nombre).stream().map(InstitucionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Institucion> findAll() {
        return institucionRepository.findAll().stream().map(InstitucionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Institucion> findByDepartamento(String departamento) {
        return institucionRepository.findByDepartamento(departamento).stream().map(InstitucionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Institucion> findByMunicipio(String municipio) {
        return institucionRepository.findByMunicipio(municipio).stream().map(InstitucionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Institucion> findByDepartamentoAndMunicipio(String departamento, String municipio) {
        return institucionRepository.findByDepartamentoAndMunicipio(departamento,municipio).stream()
                .map(InstitucionMapper::toDomain)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<Institucion>findByNombreAndDepartamentoAndMuncipio(String nombre,
                                                                       String departamento,String municipio){
        return institucionRepository.findByNombreAndDepartamentoAndMunicipioIgnoreCase(nombre,departamento,municipio)
                .map(InstitucionMapper::toDomain);
    }

    @Override
    public List<Institucion> findByNombreAndDepartamento(String nombre, String departamento) {
        return institucionRepository.findByNombreAndDepartamento(nombre,departamento).stream()
                .map(InstitucionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Institucion institucion) {
        institucionRepository.delete(InstitucionMapper.toEntity(institucion));
    }
}
