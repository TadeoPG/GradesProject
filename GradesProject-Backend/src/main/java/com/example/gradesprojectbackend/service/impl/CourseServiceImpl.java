package com.example.gradesprojectbackend.service.impl;

import com.example.gradesprojectbackend.model.Course;
import com.example.gradesprojectbackend.repository.ICourseRepo;
import com.example.gradesprojectbackend.repository.IGenericRepo;
import com.example.gradesprojectbackend.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {

    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}