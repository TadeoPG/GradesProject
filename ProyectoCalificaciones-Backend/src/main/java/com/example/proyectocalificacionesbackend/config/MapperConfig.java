package com.example.proyectocalificacionesbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("alumnoMapper")
    public ModelMapper alumnoMapper() {
        return new ModelMapper();
    }

    @Bean("aulaMapper")
    public ModelMapper aulaMapper() {
        return new ModelMapper();
    }

    @Bean("calificacionMapper")
    public ModelMapper calificacionMapper() {
        return new ModelMapper();
    }

    @Bean("cursoMapper")
    public ModelMapper cursoMapper() {
        return new ModelMapper();
    }
}
