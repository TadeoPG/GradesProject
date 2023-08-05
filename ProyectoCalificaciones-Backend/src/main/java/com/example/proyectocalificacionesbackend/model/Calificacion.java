package com.example.proyectocalificacionesbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Calificacion {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;

    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false, foreignKey = @ForeignKey(name = "FK_Calificacion_Alumno"))
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "FK_Calificacion_Curso"))
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_aula", nullable = false, foreignKey = @ForeignKey(name = "FK_Calificacion_Aula"))
    private Aula aula;

    @Column(nullable = false, length = 10)
    private String bimestre;

    @Column(nullable = false)
    @Check(constraints = "examen1 BETWEEN 0 AND 20", name = "Examen1Range")
    private Double examen1;

    @Column(nullable = false)
    @Check(constraints = "examen2 BETWEEN 0 AND 20", name = "Examen2Range")
    private Double examen2;

    @Column(nullable = false)
    @Check(constraints = "tareas BETWEEN 0 AND 20", name = "TareasRange")
    private Double tareas;

    @Column(nullable = false)
    @Check(constraints = "examen_final BETWEEN 0 AND 20", name = "ExamenFinalRange")
    private Double examenFinal;

    @Column(nullable = false, length = 40)
    private String estado;

    @Column(nullable = false)
    @Check(constraints = "promedio BETWEEN 0 AND 20", name = "PromedioRange")
    private Double promedio;

    @Column(nullable = false)
    private boolean enabled;
}
