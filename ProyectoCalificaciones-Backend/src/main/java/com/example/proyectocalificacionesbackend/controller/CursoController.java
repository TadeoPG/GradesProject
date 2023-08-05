package com.example.proyectocalificacionesbackend.controller;

import com.example.proyectocalificacionesbackend.dto.CursoDTO;
import com.example.proyectocalificacionesbackend.model.Curso;
import com.example.proyectocalificacionesbackend.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService service;

    @Qualifier("cursoMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> readAll() throws Exception {
        List<CursoDTO> lista = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lista, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Curso obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoDTO dto) throws Exception {
        dto.setEnabled(true);
        Curso obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody CursoDTO dto) throws Exception {
        dto.setIdCurso(id);
        Curso obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private CursoDTO convertToDto(Curso entidad) {
        return mapper.map(entidad, CursoDTO.class);
    }

    private Curso convertToEntity(CursoDTO dto) {
        return mapper.map(dto, Curso.class);
    }

}