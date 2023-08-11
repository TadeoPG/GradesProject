package com.example.gradesprojectbackend.controller;

import com.example.gradesprojectbackend.dto.StudentDTO;
import com.example.gradesprojectbackend.model.Student;
import com.example.gradesprojectbackend.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;

    @Qualifier("studentMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception {
        List<StudentDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Student obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception {
        dto.setEnabled(true);
        Student obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody StudentDTO dto) throws Exception {
        dto.setIdStudent(id);
        Student obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private StudentDTO convertToDto(Student entity) {
        return mapper.map(entity, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto) {
        return mapper.map(dto, Student.class);
    }

}