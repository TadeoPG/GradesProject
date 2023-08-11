package com.example.gradesprojectbackend.controller;

import com.example.gradesprojectbackend.dto.ClassroomDTO;
import com.example.gradesprojectbackend.model.Classroom;
import com.example.gradesprojectbackend.service.IClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final IClassroomService service;

    @Qualifier("classroomMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> readAll() throws Exception {
        List<ClassroomDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Classroom obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<ClassroomDTO> create(@Valid @RequestBody ClassroomDTO dto) throws Exception {
        dto.setEnabled(true);
        Classroom obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody ClassroomDTO dto) throws Exception {
        dto.setIdClassroom(id);
        Classroom obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private ClassroomDTO convertToDto(Classroom entity) {
        return mapper.map(entity, ClassroomDTO.class);
    }

    private Classroom convertToEntity(ClassroomDTO dto) {
        return mapper.map(dto, Classroom.class);
    }

}