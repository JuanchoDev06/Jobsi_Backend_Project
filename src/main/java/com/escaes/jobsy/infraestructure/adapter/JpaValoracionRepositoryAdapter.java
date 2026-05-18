package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Valoracion;
import com.escaes.jobsy.domain.repository.ValoracionRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataValoracionRepository;
import com.escaes.jobsy.infraestructure.mapper.ValoracionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaValoracionRepositoryAdapter implements ValoracionRepository {

    private final SpringDataValoracionRepository springDataValoracionRepository;

    public JpaValoracionRepositoryAdapter(SpringDataValoracionRepository springDataValoracionRepository) {
        this.springDataValoracionRepository = springDataValoracionRepository;
    }

    @Override
    public void save(Valoracion valoracion) {
        springDataValoracionRepository.save(ValoracionMapper.toEntity(valoracion));
    }

    @Override
    public List<Valoracion> findByTrabajoId(UUID trabajoId) {
        return springDataValoracionRepository.findByTrabajoId(trabajoId).stream()
                .map(ValoracionMapper::toDomain)
                .collect(Collectors.toList());
    }
}
