package com.example.gradesprojectbackend.controller;

import com.example.gradesprojectbackend.dto.GradeDTO;
import com.example.gradesprojectbackend.model.Grade;
import com.example.gradesprojectbackend.service.IGradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final IGradeService service;

    @Qualifier("gradeMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<GradeDTO>> readAll() throws Exception {
        List<GradeDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Grade obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<GradeDTO> create(@Valid @RequestBody GradeDTO dto) throws Exception {
        dto.setEnabled(true);
        service.calculateAverageAndStatus(dto);
        Grade obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody GradeDTO dto) throws Exception {
        dto.setIdGrade(id);
        Grade obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private GradeDTO convertToDto(Grade entity) {
        return mapper.map(entity, GradeDTO.class);
    }

    private Grade convertToEntity(GradeDTO dto) {
        return mapper.map(dto, Grade.class);
    }

}