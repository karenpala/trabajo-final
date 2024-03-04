package com.mitocode.trabajofinal.controller;

import com.mitocode.trabajofinal.dto.EstudianteDTO;
import com.mitocode.trabajofinal.dto.GenericResponse;
import com.mitocode.trabajofinal.model.Estudiante;
import com.mitocode.trabajofinal.service.IEstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> readAll() throws Exception{
        List<EstudianteDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<EstudianteDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList( convertToDto(obj))) );
    }

    @GetMapping("/ordenar")
    public ResponseEntity<List<EstudianteDTO>> ordenarDesc() throws Exception{
        List<EstudianteDTO> list = service.ordenarEdadDesc().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> save(@Valid @RequestBody EstudianteDTO dto) throws Exception{
        Estudiante obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody EstudianteDTO dto, @PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    private EstudianteDTO convertToDto(Estudiante obj){
        return modelMapper.map(obj, EstudianteDTO.class);
    }

    private Estudiante convertToEntity(EstudianteDTO dto){

        return modelMapper.map(dto, Estudiante.class);

    }

}
