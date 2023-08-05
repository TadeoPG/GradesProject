package com.example.proyectocalificacionesbackend.service;


import com.example.proyectocalificacionesbackend.dto.CalificacionDTO;
import com.example.proyectocalificacionesbackend.model.Calificacion;

public interface ICalificacionService extends ICRUD<Calificacion, Integer> {

    void calcularPromedioYAprobacion(CalificacionDTO calificacion) throws Exception;

}
