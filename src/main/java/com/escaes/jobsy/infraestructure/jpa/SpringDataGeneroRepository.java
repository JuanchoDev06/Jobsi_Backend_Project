package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.domain.model.Genero;
import com.escaes.jobsy.infraestructure.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringDataGeneroRepository extends JpaRepository<GeneroEntity, Long> {
}
