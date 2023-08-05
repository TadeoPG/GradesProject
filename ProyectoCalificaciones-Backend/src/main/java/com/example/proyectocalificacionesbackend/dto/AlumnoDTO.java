package com.example.proyectocalificacionesbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlumnoDTO {

    private Integer idAlumno;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 40)
    private String nombres;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 40)
    private String apellidos;

    private boolean enabled;

}