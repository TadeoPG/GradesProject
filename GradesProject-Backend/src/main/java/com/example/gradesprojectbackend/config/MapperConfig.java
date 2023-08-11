package com.example.gradesprojectbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("studentMapper")
    public ModelMapper studentMapper() {
        return new ModelMapper();
    }

    @Bean("classroomMapper")
    public ModelMapper aulaMapper() {
        return new ModelMapper();
    }

    @Bean("gradeMapper")
    public ModelMapper calificacionMapper() {
        return new ModelMapper();
    }

    @Bean("courseMapper")
    public ModelMapper cursoMapper() {
        return new ModelMapper();
    }
}
