package com.example.proyectocalificacionesbackend.controller;

import com.example.proyectocalificacionesbackend.dto.CalificacionDTO;
import com.example.proyectocalificacionesbackend.model.Calificacion;
import com.example.proyectocalificacionesbackend.service.ICalificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final ICalificacionService service;

    @Qualifier("calificacionMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CalificacionDTO>> readAll() throws Exception {
        List<CalificacionDTO> lista = service.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lista, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Calificacion obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<CalificacionDTO> create(@Valid @RequestBody CalificacionDTO dto) throws Exception {
        dto.setEnabled(true);
        service.calcularPromedioYAprobacion(dto);
        Calificacion obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> update(
            @Valid @PathVariable("id") Integer id,
            @RequestBody CalificacionDTO dto) throws Exception {
        dto.setIdCalificacion(id);
        Calificacion obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private CalificacionDTO convertToDto(Calificacion entidad) {
        return mapper.map(entidad, CalificacionDTO.class);
    }

    private Calificacion convertToEntity(CalificacionDTO dto) {
        return mapper.map(dto, Calificacion.class);
    }

}