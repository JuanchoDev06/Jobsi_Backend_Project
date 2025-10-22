package com.escaes.jobsy.application.usecase.genero;

import com.escaes.jobsy.domain.model.Genero;
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

    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }



}
