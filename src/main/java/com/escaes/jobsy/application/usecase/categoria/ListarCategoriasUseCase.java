package com.escaes.jobsy.application.usecase.categoria;

import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.domain.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarCategoriasUseCase {

    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
