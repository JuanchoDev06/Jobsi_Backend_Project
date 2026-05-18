package com.escaes.jobsy.application.usecase.valoracion;

import com.escaes.jobsy.domain.model.ValoracionCategoria;
import com.escaes.jobsy.domain.repository.ValoracionCategoriaRepository;
import com.escaes.jobsy.infraestructure.rest.exception.BusinessExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestionValoracionCategoriasUseCase {

    private final ValoracionCategoriaRepository valoracionCategoriaRepository;

    public void crear(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new BusinessExceptions.BadRequestException(
                    "El nombre de la categoria de valoracion no puede ser vacio");
        }
        if (valoracionCategoriaRepository.findByNombre(nombre).isPresent()) {
            throw new BusinessExceptions.ConflictException(
                    "La categoria de valoracion ya existe: " + nombre);
        }
        valoracionCategoriaRepository.save(new ValoracionCategoria(null, nombre));
    }

    public long contar() {
        return valoracionCategoriaRepository.count();
    }

    public List<ValoracionCategoria> listar() {
        return valoracionCategoriaRepository.findAll();
    }

    public ValoracionCategoria obtenerPorNombre(String nombre) {
        return valoracionCategoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new BusinessExceptions.NotFoundException(
                        "Categoria de valoracion no encontrada: " + nombre));
    }
}
