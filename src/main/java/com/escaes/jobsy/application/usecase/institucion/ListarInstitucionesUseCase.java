package com.escaes.jobsy.application.usecase.institucion;

import com.escaes.jobsy.domain.model.Institucion;
import com.escaes.jobsy.domain.repository.InstitucionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarInstitucionesUseCase {

    private final InstitucionRepository institucionRepository;

    public ListarInstitucionesUseCase(InstitucionRepository institucionRepository) {
        this.institucionRepository = institucionRepository;
    }

    public List<Institucion> listarInstituciones(){
        return institucionRepository.findAll();
    }
    public Integer contarInstituciones(){
        return institucionRepository.findAll().size();
    }

    public List<Institucion>institucionPorNombre(String nombre){
        return institucionRepository.findByNombre(nombre);
    }

    public List<Institucion>institucionesPorDepartamento(String departamento){
        return institucionRepository.findByDepartamento(departamento);
    }

    public List<Institucion>institucionesPorDepartamentoMunicipio(String departamento,String municipio){
        return institucionRepository.findByDepartamentoAndMunicipio(departamento, municipio).stream()
                .filter(i -> departamento == null || i.departamento().equalsIgnoreCase(departamento))
                .filter(i -> municipio == null || i.municipio().equalsIgnoreCase(municipio))
                .toList();
    }
    public List<Institucion>institucionesPorNombreYDepartamento(String nombre, String departamento){
        return institucionRepository.findByNombreAndDepartamento(nombre,departamento);
    }
}
