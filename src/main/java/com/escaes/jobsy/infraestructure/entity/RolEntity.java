package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="ROLES")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private Long id;

    @Column(name = "NOMBRE_ROL", nullable = false, unique = true, length = 50)
    private String nombreRol;

    @Column(name="DESCRIPCION_ROL", length = 255)
    private String descripcionRol;
}
