package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="TBL_TRABAJOS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrabajoEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name="Descripcion", nullable = false)
    private String descripcion;

    @Column(name="Fecha_Publicacion", nullable = false)
    private Date fechaPublicacion;

    @Column(name="Pago", nullable = false)
    private Double pago;

    @Column(name="Ubicacion", nullable = false)
    private String ubicacion;

    @ManyToOne()
    @JoinColumn(name="Solicitante_UUID", nullable = false, foreignKey = @ForeignKey(name = "FK_TRABAJO_SOLICITANTE"))
    private UsuarioEntity solicitante;

    @ManyToOne()
    @JoinColumn(name="Trabajador_UUID", nullable = true, foreignKey = @ForeignKey(name = "FK_TRABAJO_TRABAJADOR"))
    private UsuarioEntity trabajador;


    @ManyToOne
    @JoinColumn(name = "Estados_AIID",nullable = false,foreignKey = @ForeignKey(name = "FK_TRABAJO_ESTADOS"))
    private EstadoEntity estado;

    @ManyToOne
    @JoinColumn(name = "Categorias_AIID",nullable = false, foreignKey =@ForeignKey(name = "FK_TRABAJO_CATEGORIAS"))
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "Tipos_De_Pago_UUID",nullable = true,foreignKey = @ForeignKey(name = "FK_TRABAJO_TIPO_PAGO"))
    private PagoEntity tipoPago;
}
