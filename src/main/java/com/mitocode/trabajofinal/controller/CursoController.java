package com.mitocode.trabajofinal.controller;

import com.mitocode.trabajofinal.dto.CursoDTO;
import com.mitocode.trabajofinal.dto.GenericResponse;
import com.mitocode.trabajofinal.model.Curso;
import com.mitocode.trabajofinal.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> readAll() throws Exception{
        List<CursoDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CursoDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Curso obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList( convertToDto(obj))) );
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@Valid @RequestBody CursoDTO dto) throws Exception{
        Curso obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoDTO dto, @PathVariable("id") Integer id) throws Exception{
        Curso obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private CursoDTO convertToDto(Curso obj){
        return modelMapper.map(obj, CursoDTO.class);
    }

    private Curso convertToEntity(CursoDTO dto){
        return modelMapper.map(dto, Curso.class);
    }
}
