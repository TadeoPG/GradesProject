package com.example.proyectocalificacionesbackend.controller;

import com.example.proyectocalificacionesbackend.dto.AulaDTO;
import com.example.proyectocalificacionesbackend.model.Aula;
import com.example.proyectocalificacionesbackend.service.IAulaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final IAulaService service;

    @Qualifier("aulaMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AulaDTO>> readAll() throws Exception {
        List<AulaDTO> lista = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lista, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Aula obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<AulaDTO> create(@Valid @RequestBody AulaDTO dto) throws Exception {
        dto.setEnabled(true);
        Aula obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody AulaDTO dto) throws Exception {
        dto.setIdAula(id);
        Aula obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private AulaDTO convertToDto(Aula entidad) {
        return mapper.map(entidad, AulaDTO.class);
    }

    private Aula convertToEntity(AulaDTO dto) {
        return mapper.map(dto, Aula.class);
    }

}