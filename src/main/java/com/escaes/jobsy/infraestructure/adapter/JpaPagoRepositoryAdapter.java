package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Pago;
import com.escaes.jobsy.domain.repository.PagoRepository;
import com.escaes.jobsy.infraestructure.jpa.SpringDataPagoRepository;
import com.escaes.jobsy.infraestructure.mapper.PagoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaPagoRepositoryAdapter implements PagoRepository {

    private final SpringDataPagoRepository  springDataPagoRepository;

    public JpaPagoRepositoryAdapter(SpringDataPagoRepository springDataPagoRepository) {
        this.springDataPagoRepository = springDataPagoRepository;
    }

    @Override
    public Optional<Pago> findById(UUID id) {
        return springDataPagoRepository.findById(id).map(PagoMapper::toDomain);
    }

    @Override
    public Optional<Pago> findByNombre(String nombrePago) {
        return springDataPagoRepository.findByNombreIgnoreCase(nombrePago).map(PagoMapper::toDomain);
    }

    @Override
    public void save(Pago pago) {
        springDataPagoRepository.save(PagoMapper.toEntity(pago));
    }

    @Override
    public void delete(Pago pago) {
        springDataPagoRepository.delete(PagoMapper.toEntity(pago));
    }

    @Override
    public List<Pago> findAll() {
        return springDataPagoRepository.findAll()
                .stream().map(PagoMapper::toDomain).collect(Collectors.toList());
    }
}
