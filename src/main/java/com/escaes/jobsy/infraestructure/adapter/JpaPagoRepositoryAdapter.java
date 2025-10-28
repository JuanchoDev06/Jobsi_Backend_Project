package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.TipoPago;
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
    public Optional<TipoPago> findById(UUID id) {
        return springDataPagoRepository.findById(id).map(PagoMapper::toDomain);
    }

    @Override
    public Optional<TipoPago> findByNombre(String nombrePago) {
        return springDataPagoRepository.findByNombreIgnoreCase(nombrePago).map(PagoMapper::toDomain);
    }

    @Override
    public void save(TipoPago pago) {
        springDataPagoRepository.save(PagoMapper.toEntity(pago));
    }

    @Override
    public void delete(TipoPago pago) {
        springDataPagoRepository.delete(PagoMapper.toEntity(pago));
    }

    @Override
    public List<TipoPago> findAll() {
        return springDataPagoRepository.findAll()
                .stream().map(PagoMapper::toDomain).collect(Collectors.toList());
    }
}
