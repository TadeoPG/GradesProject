package com.example.gradesprojectbackend.service.impl;

import com.example.gradesprojectbackend.model.Classroom;
import com.example.gradesprojectbackend.repository.IClassroomRepo;
import com.example.gradesprojectbackend.repository.IGenericRepo;
import com.example.gradesprojectbackend.service.IClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl extends CRUDImpl<Classroom, Integer> implements IClassroomService {

    private final IClassroomRepo repo;

    @Override
    protected IGenericRepo<Classroom, Integer> getRepo() {
        return repo;
    }
}