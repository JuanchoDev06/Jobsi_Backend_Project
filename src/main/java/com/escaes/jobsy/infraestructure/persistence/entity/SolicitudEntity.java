package com.escaes.jobsy.infraestructure.persistence.entity;
import com.escaes.jobsy.infraestructure.persistence.enums.EstadoSolicitud;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "SOLICITUDES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitudEntity {

    @Id
    @Column(name="Solicitud_Id",columnDefinition = "uuid")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "Usuario_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_SOLICITUD"))
    UsuarioEntity usuario;

    @ManyToOne()
    @JoinColumn(name="Trabajo_ID",nullable = false,foreignKey = @ForeignKey(name = "FK_TRABAJO_SOLICITUD"))
    TrabajoEntity trabajo;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @CreationTimestamp
    private LocalDateTime fechaSolicitud;

    @UpdateTimestamp
    private LocalDateTime fechaModificacion;

}
