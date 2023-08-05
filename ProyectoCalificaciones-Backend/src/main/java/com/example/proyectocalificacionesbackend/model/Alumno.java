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
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAlumno;

    @Column(nullable = false, length = 40)
    private String nombres;

    @Column(nullable = false, length = 40)
    private String apellidos;

    @Column(nullable = false)
    private boolean enabled;
}
