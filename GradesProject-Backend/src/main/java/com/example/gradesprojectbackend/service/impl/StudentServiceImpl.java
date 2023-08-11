package com.example.gradesprojectbackend.service.impl;

import com.example.gradesprojectbackend.model.Student;
import com.example.gradesprojectbackend.repository.IGenericRepo;
import com.example.gradesprojectbackend.repository.IStudentRepo;
import com.example.gradesprojectbackend.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }
}