package com.escaes.jobsy.infraestructure.persistence.criteria;

import com.escaes.jobsy.infraestructure.persistence.entity.TrabajoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Búsqueda dinámica de trabajos construida "a mano" con la JPA Criteria API
 * ({@link EntityManager} + {@link CriteriaBuilder}). Cada filtro nulo se omite,
 * de modo que la consulta solo incluye los WHERE/JOIN realmente necesarios.
 */
@Repository
public class TrabajoCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TrabajoEntity> buscar(String titulo, String categoria, String estado,
            String ubicacion, String tipoPago, Double pagoMin, Double pagoMax,
            String solicitanteCorreo, int size, int page) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrabajoEntity> cq = cb.createQuery(TrabajoEntity.class);
        Root<TrabajoEntity> root = cq.from(TrabajoEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (titulo != null && !titulo.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%"));
        }
        if (categoria != null && !categoria.isBlank()) {
            predicates.add(cb.equal(root.join("categoria").get("nombre"), categoria));
        }
        if (estado != null && !estado.isBlank()) {
            predicates.add(cb.equal(root.join("estado").get("nombre"), estado));
        }
        if (ubicacion != null && !ubicacion.isBlank()) {
            predicates.add(cb.equal(root.join("ubicacion").get("nombreUbicacion"), ubicacion));
        }
        if (tipoPago != null && !tipoPago.isBlank()) {
            predicates.add(cb.equal(root.join("tipoPago").get("nombre"), tipoPago));
        }
        if (pagoMin != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("pago"), pagoMin));
        }
        if (pagoMax != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("pago"), pagoMax));
        }
        if (solicitanteCorreo != null && !solicitanteCorreo.isBlank()) {
            predicates.add(cb.equal(root.join("solicitante").get("correo"), solicitanteCorreo));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cq)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}
