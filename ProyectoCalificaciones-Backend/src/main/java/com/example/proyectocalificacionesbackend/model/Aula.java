package com.example.proyectocalificacionesbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAula;

    @Column(nullable = false)
    private Integer nivel;

    @Column(nullable = false, length = 1)
    private String seccion;

    @Column(nullable = false)
    private boolean enabled;
}
