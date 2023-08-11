package com.example.gradesprojectbackend.service.impl;

import com.example.gradesprojectbackend.dto.GradeDTO;
import com.example.gradesprojectbackend.model.Grade;
import com.example.gradesprojectbackend.repository.IGenericRepo;
import com.example.gradesprojectbackend.repository.IGradeRepo;
import com.example.gradesprojectbackend.service.IGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl extends CRUDImpl<Grade, Integer> implements IGradeService {

    private final IGradeRepo repo;

    @Override
    protected IGenericRepo<Grade, Integer> getRepo() {
        return repo;
    }

    @Override
    public void calculateAverageAndStatus(GradeDTO dto) throws Exception {
        double average = (dto.getExam1() + dto.getExam2() + dto.getHomeworks() + dto.getFinalExam()) / 4;
        dto.setAverage(average);
        dto.setStatus(average >= 11 ? "PASSED" : "FAILED");
    }
}