package com.example.proyectocalificacionesbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalificacionDTO {

    private Integer idCalificacion;

    @NotNull
    private AlumnoDTO alumno;

    @NotNull
    private CursoDTO curso;

    @NotNull
    private AulaDTO aula;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 3)
    private String bimestre;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double examen1;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double examen2;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double tareas;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double examenFinal;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String estado;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double promedio;

    private boolean enabled;

}