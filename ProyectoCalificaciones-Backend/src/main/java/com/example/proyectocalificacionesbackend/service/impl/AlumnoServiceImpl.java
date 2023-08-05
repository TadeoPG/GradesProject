package com.example.proyectocalificacionesbackend.service.impl;

import com.example.proyectocalificacionesbackend.model.Alumno;
import com.example.proyectocalificacionesbackend.repository.IAlumnoRepo;
import com.example.proyectocalificacionesbackend.repository.IGenericRepo;
import com.example.proyectocalificacionesbackend.service.IAlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl extends CRUDImpl<Alumno, Integer> implements IAlumnoService {

    private final IAlumnoRepo repo;

    @Override
    protected IGenericRepo<Alumno, Integer> getRepo() {
        return repo;
    }
}