package com.escaes.jobsy.domain.repository;

import com.escaes.jobsy.domain.model.Trabajo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrabajoRepository {

    Optional<Trabajo> findById(UUID id);
    Optional<Trabajo> findByNombre(String nombre);
    void save(Trabajo trabajo);
    void deleteById(UUID id);
    void delete(Trabajo trabajo);
    List<Trabajo> findAll();
    List<Trabajo> findBySolicitanteCorreo(String solicitanteCorreo);
    List<Trabajo> findByTrabajadorCorreo(String TrabajadorCorreo);
    List<Trabajo>findByEstado(String estado);
    List<Trabajo>findByCategoria(String categoria);
    List<Trabajo>findByCategoriaAndEstado(String categoria, String estado);

    /*
     * Consultas para solicitantes
     */
    List<Trabajo> findBySolicitanteCorreoAndEstado(String correo, String estado);
    List<Trabajo> findBySolicitanteCorreoAndCategoria(String correo, String categoria);
    List<Trabajo> findBySolicitanteCorreoAndCategoriaAndEstado(String correo, String categoria, String estado);

    /*
     * Consultas para trabajadores
     */
    List<Trabajo> findByTrabajadorCorreoAndEstado(String correo, String estado);
    List<Trabajo> findByTrabajadorCorreoAndCategoria(String correo, String categoria);
    List<Trabajo> findByTrabajadorCorreoAndCategoriaAndEstado(String correo, String categoria, String estado);

}
