package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TIPO_PAGO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoPagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TIPO_PAGO_ID")
    private Long id;

    @Column(name = "NOMBRE_PAGO", nullable = false, unique = true, length = 50)
    private String nombrePago;

    @OneToMany(mappedBy = "tipoPago", fetch = FetchType.LAZY)
    private List<TrabajoEntity> trabajos;

}
