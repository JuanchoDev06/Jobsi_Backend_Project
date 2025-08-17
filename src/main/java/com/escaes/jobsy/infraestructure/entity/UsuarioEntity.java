package com.escaes.jobsy.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "TBL_USUARIOS")
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @Column(name="Usuario_UUID",columnDefinition = "CHAR(36)")
    private String id;

    @Column(name="Nombre", nullable = false)
    private String nombre;

    @Column(name="Documento", unique = true, nullable = false)
    private Integer documento;

    @Column(name="Correo", unique = true, nullable = false)
    private String correo;

    @Column(name="Contraseña", nullable = false)
    private String clave;

    @Column(name="Bloqueado", nullable = false)
    private Boolean bloqueado=false;

    @Column(name="Fecha_Nacimiento", nullable = true)
    private Date fechaNacimiento;

}
