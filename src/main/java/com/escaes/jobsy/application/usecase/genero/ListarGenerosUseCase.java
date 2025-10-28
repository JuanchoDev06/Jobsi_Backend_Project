package com.escaes.jobsy.application.usecase.genero;

import com.escaes.jobsy.domain.model.Sexo;
import com.escaes.jobsy.domain.repository.GeneroRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ListarGenerosUseCase {

    private final GeneroRepository generoRepository;

    public int contarGeneros() {
        return generoRepository.findAll().size();
    }

    public List<Sexo> listarGeneros() {
        return generoRepository.findAll();
    }



}
