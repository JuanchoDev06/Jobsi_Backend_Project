package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Estado;
import com.escaes.jobsy.domain.repository.EstadoRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataEstadoRepository;
import com.escaes.jobsy.infraestructure.mapper.EstadoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaEstadoRepositoryAdapter implements EstadoRepository {

    private final SpringDataEstadoRepository springDataEstadoRepository;

    public JpaEstadoRepositoryAdapter(SpringDataEstadoRepository springDataEstadoRepository) {
        this.springDataEstadoRepository = springDataEstadoRepository;
    }

    @Override
    public Optional<Estado> findById(Long id) {
        return springDataEstadoRepository.findById(id).map(EstadoMapper::toDomain);
    }

    @Override
    public Optional<Estado> findByNombre(String nombre) {
        return springDataEstadoRepository.findByNombreIgnoreCase(nombre).map(EstadoMapper::toDomain);
    }

    @Override
    public List<Estado> findAll() {
        return springDataEstadoRepository.findAll().stream()
                .map(EstadoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void save(Estado estado) {
        springDataEstadoRepository.save(EstadoMapper.toEntity(estado));

    }

    @Override
    public void delete(Estado estado) {
        springDataEstadoRepository.delete(EstadoMapper.toEntity(estado));
    }

    @Override
    public void deleteById(Long id) {
        springDataEstadoRepository.deleteById(id);
    }

    @Override
    public void deleteByNombre(String nombre) {
        springDataEstadoRepository.deleteByNombreIgnoreCase(nombre);
    }
}
