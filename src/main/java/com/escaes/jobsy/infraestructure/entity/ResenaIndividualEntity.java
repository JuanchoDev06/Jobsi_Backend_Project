package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="RESENAS_INDIVIDUALES")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResenaIndividualEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESENA_INDIVIDUAL_ID")
    private Long id;

    @Column(name = "PUNTUACION_RESENA", nullable = false)
    private Integer puntuacionResena;

    @Column(name = "OPINIONES_RESENA", length = 1000)
    private String opinionesResena;

    @Column(name = "IMAGENES_RESENA", length = 500)
    private String imagenesResena; // puede ser una URL o JSON de URLs

    // Relación con el trabajo reseñado
    @ManyToOne
    @JoinColumn(name = "TRABAJO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESENA_TRABAJO"))
    private TrabajoEntity trabajo;

    // Usuario que evalúa
    @ManyToOne
    @JoinColumn(name = "EVALUADOR_USUARIO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESENA_EVALUADOR"))
    private UsuarioEntity evaluador;

    // Usuario que es evaluado
    @ManyToOne
    @JoinColumn(name = "EVALUADO_USUARIO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESENA_EVALUADO"))
    private UsuarioEntity evaluado;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}