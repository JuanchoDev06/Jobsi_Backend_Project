package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Categoria;
import com.escaes.jobsy.domain.repository.CategoriaRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataCategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCategoriaRepositoryAdapter implements CategoriaRepository {

    private final SpringDataCategoriaRepository springDataCategoriaRepository;

    public JpaCategoriaRepositoryAdapter(SpringDataCategoriaRepository springDataCategoriaRepository) {
        this.springDataCategoriaRepository = springDataCategoriaRepository;
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return springDataCategoriaRepository.findById(id).map(CategoriaMapper::toDomain);
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre) {
        return springDataCategoriaRepository.findByNombreIgnoreCase(nombre).map(CategoriaMapper::toDomain);
    }

    @Override
    public List<Categoria> findAll() {
        return springDataCategoriaRepository.findAll().stream()
                .map(CategoriaMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void save(Categoria categoria) {
        springDataCategoriaRepository.save(CategoriaMapper.toEntity(categoria));
    }

    @Override
    public void delete(Categoria categoria) {
        springDataCategoriaRepository.delete(CategoriaMapper.toEntity(categoria));
    }

    @Override
    public void deleteById(Long id) {
        springDataCategoriaRepository.deleteById(id);

    }

    @Override
    public void deleteByNombre(String nombre) {
        springDataCategoriaRepository.deleteByNombreIgnoreCase(nombre);

    }
}
