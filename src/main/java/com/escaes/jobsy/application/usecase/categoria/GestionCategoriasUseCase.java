package com.escaes.jobsy.application.usecase.categoria;

import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class GestionCategoriasUseCase {

    private final CategoriaRepository categoriaRepository;

    public GestionCategoriasUseCase(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscarCategoriaPorNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        return categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("No existe categoría con nombre: " + nombre));
    }
    public Categoria buscarCategoriaPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        return categoriaRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No existe categoria con id:  "+id));
    }

    public void crearCategoria(String nombreCategoria){
        if(categoriaRepository.findByNombre(nombreCategoria).isPresent()){
            throw new IllegalArgumentException("El categoria ya existe en el sistema");
        }
        Categoria categoria= new Categoria(
                null,
                nombreCategoria
        );
        categoriaRepository.save(categoria);
    }
    public void actualizarCategoria(String nombreCategoria, String nuevaCategoria){
        if (nombreCategoria == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        Categoria categoria=categoriaRepository.findByNombre(nombreCategoria)
                .orElseThrow(()->new IllegalArgumentException("No existe categoria con nombre: "+nombreCategoria));

        Categoria actualizado= new Categoria(
                categoria.id(), nuevaCategoria
        );
        categoriaRepository.save(actualizado);
    }

    public void eliminarCategoriaPorId(Long id){
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        categoriaRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No existe categoria con id:  "+id));
        categoriaRepository.deleteById(id);
    }
    public void eliminarCategoriaPorNombre(String NombreCategoria){
        if (NombreCategoria == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        categoriaRepository.findByNombre(NombreCategoria).
                orElseThrow(()->new IllegalArgumentException("No existe categoria con id:  "+NombreCategoria));
        categoriaRepository.deleteByNombre(NombreCategoria);
    }
}
