package com.escaes.jobsy.application.usecase.genero;

import com.escaes.jobsy.application.dto.genero.GeneroRequest;
import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.domain.repository.GeneroRepository;
import org.springframework.stereotype.Service;

@Service
public class GestionGenerosUseCase {

    private final GeneroRepository generoRepository;

    public GestionGenerosUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public void crearGenero(GeneroRequest request) {

        if (request == null || request.nombre().isBlank()) {
            throw new IllegalArgumentException("El género no puede ser nulo o vacío");
        }
        if (generoRepository.findByNombre(request.nombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un género con el nombre proporcionado");
        }
        Genero genero= new Genero(
                null, request.nombre()
        );
        generoRepository.save(genero);
    }
    public Genero obtenerGeneroPorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado con el ID proporcionado"));
    }

    public Genero obtenerGeneroPorNombre(String nombre) {
        return generoRepository.findByNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado con el nombre proporcionado"));
    }

    public void eliminarGeneroPorId(Long id) {
        if (generoRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Género no encontrado con el ID proporcionado");
        }
        generoRepository.deleteById(id);
    }
    public void eliminarGeneroPorNombre(String nombre) {
        if (generoRepository.findByNombre(nombre).isEmpty()) {
            throw new IllegalArgumentException("Género no encontrado con el nombre proporcionado");
        }
        generoRepository.deleteByNombre(nombre);
    }
}
