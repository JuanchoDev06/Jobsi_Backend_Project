package com.escaes.jobsy.infraestructure.adapter;

import com.escaes.jobsy.domain.model.Sexo;
import com.escaes.jobsy.domain.repository.GeneroRepository;
import com.escaes.jobsy.infraestructure.entity.GeneroEntity;
import com.escaes.jobsy.infraestructure.jpa.SpringDataGeneroRepository;
import com.escaes.jobsy.infraestructure.mapper.SexoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaGeneroRepositoryAdapter implements GeneroRepository {

    private final SpringDataGeneroRepository springDataGeneroRepository;

    public JpaGeneroRepositoryAdapter(SpringDataGeneroRepository springDataGeneroRepository) {
        this.springDataGeneroRepository = springDataGeneroRepository;
    }


    @Override
    public void save(Sexo genero) {

        GeneroEntity generoEntity= new GeneroEntity();
        generoEntity.setNombreGenero(genero.nombreGenero());
        springDataGeneroRepository.save(generoEntity);

    }

    @Override
    public Optional<Sexo> findById(Long id) {
        Optional<GeneroEntity> generoEntityOptional = springDataGeneroRepository.findById(id);
        if (generoEntityOptional.isPresent()) {
            GeneroEntity generoEntity = generoEntityOptional.get();
            return Optional.of(SexoMapper.toDomain(generoEntity));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Sexo> findByNombre(String nombre) {
        Optional<GeneroEntity>generoEntityOptional= springDataGeneroRepository.findByNombreGenero(nombre);
        if(generoEntityOptional.isPresent()){
            GeneroEntity generoEntity = generoEntityOptional.get();
            return Optional.of(SexoMapper.toDomain(generoEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<Sexo> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByNombre(String nombre) {

    }

    @Override
    public void delete(String genero) {

    }
}
