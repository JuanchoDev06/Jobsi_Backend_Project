package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Table(name = "ESTADO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ESTADO_ID")
    private Long id;

    @Column(name="NOMBRE_ESTADO", nullable = false, unique = true, length = 50)
    private String nombreEstado;

    @Column(name="DESCRIPCION_ESTADO", length = 255)
    private String descripcionEstado;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<TrabajoEntity> trabajos;

}
