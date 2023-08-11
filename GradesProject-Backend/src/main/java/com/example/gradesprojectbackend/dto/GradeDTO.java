package com.example.gradesprojectbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GradeDTO {

    private Integer idGrade;

    @NotNull
    private StudentDTO student;

    @NotNull
    private CourseDTO course;

    @NotNull
    private ClassroomDTO classroom;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 3)
    private String bimester;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double exam1;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double exam2;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double homeworks;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Double finalExam;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double average;

    private boolean enabled;

}