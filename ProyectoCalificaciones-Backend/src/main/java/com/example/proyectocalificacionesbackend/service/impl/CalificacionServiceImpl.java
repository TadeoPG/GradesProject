package com.example.proyectocalificacionesbackend.service.impl;

import com.example.proyectocalificacionesbackend.dto.CalificacionDTO;
import com.example.proyectocalificacionesbackend.model.Aula;
import com.example.proyectocalificacionesbackend.model.Calificacion;
import com.example.proyectocalificacionesbackend.repository.IAulaRepo;
import com.example.proyectocalificacionesbackend.repository.ICalificacionRepo;
import com.example.proyectocalificacionesbackend.repository.IGenericRepo;
import com.example.proyectocalificacionesbackend.service.IAulaService;
import com.example.proyectocalificacionesbackend.service.ICalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl extends CRUDImpl<Calificacion, Integer> implements ICalificacionService {

    private final ICalificacionRepo repo;

    @Override
    protected IGenericRepo<Calificacion, Integer> getRepo() {
        return repo;
    }

    @Override
    public void calcularPromedioYAprobacion(CalificacionDTO calificacion) throws Exception {
        double promedio = (calificacion.getExamen1() + calificacion.getExamen2() + calificacion.getTareas() + calificacion.getExamenFinal()) / 4;
        calificacion.setPromedio(promedio);
        calificacion.setEstado(promedio >= 11 ? "APROBADO": "DESAPROBADO");
    }
}