package com.example.proyectocalificacionesbackend.service.impl;

import com.example.proyectocalificacionesbackend.model.Alumno;
import com.example.proyectocalificacionesbackend.model.Curso;
import com.example.proyectocalificacionesbackend.repository.IAlumnoRepo;
import com.example.proyectocalificacionesbackend.repository.ICursoRepo;
import com.example.proyectocalificacionesbackend.repository.IGenericRepo;
import com.example.proyectocalificacionesbackend.service.IAlumnoService;
import com.example.proyectocalificacionesbackend.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo repo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }
}