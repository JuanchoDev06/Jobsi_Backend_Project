package com.escaes.jobsy.application.usecase.genero;

import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListarGenerosUseCase {

    private final GeneroRepository generoRepository;
    public ListarGenerosUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public int contarGeneros() {
        return generoRepository.findAll().size();
    }

    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }



}
