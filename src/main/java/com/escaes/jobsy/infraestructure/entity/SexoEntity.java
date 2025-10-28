package com.escaes.jobsy.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="SEXO")
public class SexoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SEXO_ID")
    private Long id;

    @Column(name="NOMBRE_GENERO", nullable = false, unique = true, length = 30)
    private String nombreSexo;

    //Relación provisional
    @OneToMany(mappedBy = "sexo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Evita ciclos infinitos si se usa REST
    private List<UsuarioEntity> usuarios;


}
