package com.example.gradesprojectbackend.dto;

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
public class StudentDTO {

    private Integer idStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 40)
    private String names;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 40)
    private String lastNames;

    private boolean enabled;

}