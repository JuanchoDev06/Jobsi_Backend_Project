package com.escaes.jobsy.application.usecase.categoria;

import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCategoriasUseCase {

    private CategoriaRepository categoriaRepository;

    public ListarCategoriasUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
