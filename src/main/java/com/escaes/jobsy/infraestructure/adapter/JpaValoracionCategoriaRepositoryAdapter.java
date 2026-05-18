package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.ValoracionCategoria;
import com.escaes.jobsy.domain.repository.ValoracionCategoriaRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataValoracionCategoriaRepository;
import com.escaes.jobsy.infraestructure.mapper.ValoracionCategoriaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaValoracionCategoriaRepositoryAdapter implements ValoracionCategoriaRepository {

    private final SpringDataValoracionCategoriaRepository springDataValoracionCategoriaRepository;

    public JpaValoracionCategoriaRepositoryAdapter(
            SpringDataValoracionCategoriaRepository springDataValoracionCategoriaRepository) {
        this.springDataValoracionCategoriaRepository = springDataValoracionCategoriaRepository;
    }

    @Override
    public void save(ValoracionCategoria categoria) {
        springDataValoracionCategoriaRepository.save(ValoracionCategoriaMapper.toEntity(categoria));
    }

    @Override
    public Optional<ValoracionCategoria> findByNombre(String nombre) {
        return springDataValoracionCategoriaRepository.findByNombreIgnoreCase(nombre)
                .map(ValoracionCategoriaMapper::toDomain);
    }

    @Override
    public List<ValoracionCategoria> findAll() {
        return springDataValoracionCategoriaRepository.findAll().stream()
                .map(ValoracionCategoriaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return springDataValoracionCategoriaRepository.count();
    }
}
