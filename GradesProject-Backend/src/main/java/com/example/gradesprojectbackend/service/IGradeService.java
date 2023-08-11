package com.example.gradesprojectbackend.service;


import com.example.gradesprojectbackend.dto.GradeDTO;
import com.example.gradesprojectbackend.model.Grade;

public interface IGradeService extends ICRUD<Grade, Integer> {

    void calculateAverageAndStatus(GradeDTO dto) throws Exception;

}
