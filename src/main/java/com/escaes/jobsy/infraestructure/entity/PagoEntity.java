package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "TBL_TIPO_PAGOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoEntity {
    @Id
    @Column(name = "Tipo_Pago_UUID",columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "Nombre_Pago")
    private String nombre;
}
