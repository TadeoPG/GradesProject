package com.example.gradesprojectbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
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
public class ClassroomDTO {

    private Integer idClassroom;

    @Min(value = 1)
    private Integer level;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 3)
    private String section;

    private boolean enabled;

}