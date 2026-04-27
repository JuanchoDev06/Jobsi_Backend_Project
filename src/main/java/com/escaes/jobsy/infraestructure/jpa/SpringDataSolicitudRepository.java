package com.escaes.jobsy.infraestructure.jpa;

import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.infraestructure.persistence.entity.SolicitudEntity;
import com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataSolicitudRepository extends JpaRepository<SolicitudEntity, UUID> {

    @Query("""
            SELECT s FROM SolicitudEntity s
            JOIN FETCH s.usuario
            WHERE s.trabajo.id = :trabajoId
            """)
    List<SolicitudEntity> findSolicitudesConUsuario(UUID trabajoId);

    @Modifying
    @Query("""
    UPDATE SolicitudEntity s
    SET s.estado = :estado
    WHERE s.id = :solicitudId
""")
    void actualizarEstado(@Param("solicitudId") UUID solicitudId,
                          @Param("estado") EstadoSolicitud estado);

    @Modifying
    @Query("""
                UPDATE SolicitudEntity s
                SET s.estado = com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud.RECHAZADA
                WHERE s.trabajo.id = :trabajoId
                AND s.id <> :solicitudId
            """)
    void rechazarOtrasSolicitudes(@Param("trabajoId") UUID trabajoId,
                                  @Param("solicitudId") UUID solicitudId);

    boolean existsByUsuarioCorreoAndTrabajoId(String correo, UUID trabajoId);

    List<SolicitudEntity> findByUsuario(Usuario usuario);

    List<SolicitudEntity> findByTrabajo(Trabajo trabajo);


}
