package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table (name = "USUARIOS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USUARIO_ID")
    private Long id;

    @Column(name="CORREO_USUARIO", unique = true, nullable = false, length = 150)
    private String correoUsuario;

    @Column(name="DOCUMENTO_USUARIO", unique = true, nullable = false, length = 20)
    private String documentoUsuario;

    @Column(name="NOMBRE_USUARIO", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name="CONTRASENA_USUARIO", nullable = false, length = 255)
    private String contrasenaUsuario;

    @Column(name = "CELULAR_USUARIO", nullable = false, length = 20)
    private String celularUsuario;

    @ManyToOne
    @JoinColumn(name="SEXO_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_SEXO"))
    private SexoEntity sexo;

    @ManyToOne
    @JoinColumn(name="ROL_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_ROL"))
    private RolEntity rol;

    @Column(name = "FECHA_NACIMIENTO_USUARIO", nullable = false)
    private LocalDate fechaNacimientoUsuario;

    @Column(name = "CALIFICACION_PROMEDIO")
    private Float calificacionPromedio = 0f;

    @Column(name = "CALIFICACION_CONTEO")
    private Integer calificacionConteo = 0;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "publicadoPor", fetch = FetchType.LAZY)
    private List<TrabajoEntity> trabajosPublicados;

    @OneToMany(mappedBy = "asignadoA", fetch = FetchType.LAZY)
    private List<TrabajoEntity> trabajosAsignados;

    @OneToMany(mappedBy = "evaluador", fetch = FetchType.LAZY)
    private List<ResenaIndividualEntity> resenasHechas;

    @OneToMany(mappedBy = "evaluado", fetch = FetchType.LAZY)
    private List<ResenaIndividualEntity> resenasRecibidas;
}
