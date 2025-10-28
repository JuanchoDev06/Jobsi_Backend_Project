package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="TRABAJOS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrabajoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TRABAJO_ID")
    private Long id;

    @Column(name="TITULO_TRABAJO", nullable = false, length = 150)
    private String tituloTrabajo;

    @Column(name="DESCRIPCION_TRABAJO", nullable = false, length = 500)
    private String descripcionTrabajo;

    @Column(name="FECHA_TRABAJO", nullable = false)
    private Date fechaTrabajo;

    @Column(name="UBICACION_TRABAJO", nullable = false, length = 255)
    private String ubicacion;

    //Estado del trabajo
    @ManyToOne
    @JoinColumn(name = "ESTADO_TRABAJO", nullable = false, foreignKey = @ForeignKey(name = "FK_TRABAJO_ESTADO"))
    private EstadoEntity estado;

    //Tipo de pago, transferencia, efectivo, nequi, otro)
    @ManyToOne
    @JoinColumn(name="TIPO_PAGO_TRABAJO", nullable = false, foreignKey = @ForeignKey(name = "FK_TRABAJO_TIPO_PAGO"))
    private TipoPagoEntity tipoPago;

    //Usuario que publicó el Job
    @ManyToOne
    @JoinColumn(name="POSTED_BY_USER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_TRABAJO_USUARIO_POSTED"))
    private UsuarioEntity publicadoPor;

    // Usuario asignado al trabajo
    @ManyToOne
    @JoinColumn(name="ASSIGNED_TO_USER_ID", nullable = false,  foreignKey = @ForeignKey(name = "FK_TRABAJO_USUARIO_ASSIGNED"))
    private UsuarioEntity asignadoPara;

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

    @OneToMany(mappedBy = "trabajo", fetch = FetchType.LAZY)
    private List<ResenaIndividualEntity> resenas;


}
