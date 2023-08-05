package com.example.proyectocalificacionesbackend.controller;

import com.example.proyectocalificacionesbackend.dto.AlumnoDTO;
import com.example.proyectocalificacionesbackend.model.Alumno;
import com.example.proyectocalificacionesbackend.service.IAlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnoService service;

    @Qualifier("alumnoMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> readAll() throws Exception {
        List<AlumnoDTO> lista = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lista, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Alumno obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> create(@Valid @RequestBody AlumnoDTO dto) throws Exception {
        dto.setEnabled(true);
        Alumno obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody AlumnoDTO dto) throws Exception {
        dto.setIdAlumno(id);
        Alumno obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private AlumnoDTO convertToDto(Alumno entidad) {
        return mapper.map(entidad, AlumnoDTO.class);
    }

    private Alumno convertToEntity(AlumnoDTO dto) {
        return mapper.map(dto, Alumno.class);
    }

}