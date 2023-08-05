package com.example.proyectocalificacionesbackend.service.impl;

import com.example.proyectocalificacionesbackend.model.Aula;
import com.example.proyectocalificacionesbackend.model.Curso;
import com.example.proyectocalificacionesbackend.repository.IAulaRepo;
import com.example.proyectocalificacionesbackend.repository.ICursoRepo;
import com.example.proyectocalificacionesbackend.repository.IGenericRepo;
import com.example.proyectocalificacionesbackend.service.IAulaService;
import com.example.proyectocalificacionesbackend.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AulaServiceImpl extends CRUDImpl<Aula, Integer> implements IAulaService {

    private final IAulaRepo repo;

    @Override
    protected IGenericRepo<Aula, Integer> getRepo() {
        return repo;
    }
}