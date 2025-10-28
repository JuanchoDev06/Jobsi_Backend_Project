package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.infraestructure.entity.ResenaIndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataInstitucionRepository extends JpaRepository<ResenaIndividualEntity, UUID> {
    List<ResenaIndividualEntity>findByNombre(String nombre);
    List<ResenaIndividualEntity> findByDepartamento(String departamento);
    List<ResenaIndividualEntity> findByMunicipio(String municipio);
    List<ResenaIndividualEntity>findByDepartamentoAndMunicipio(String departamento, String municipio);
    List<ResenaIndividualEntity>findByNombreAndDepartamento(String nombre, String departamento);
    Optional <ResenaIndividualEntity> findByNombreAndDepartamentoAndMunicipioIgnoreCase(String nombre, String departamento, String municipio);
}
