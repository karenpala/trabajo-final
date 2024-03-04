package com.mitocode.trabajofinal.controller;

import com.mitocode.trabajofinal.dto.MatriculaDTO;
import com.mitocode.trabajofinal.dto.GenericResponse;
import com.mitocode.trabajofinal.model.Estudiante;
import com.mitocode.trabajofinal.model.Matricula;
import com.mitocode.trabajofinal.service.IMatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final IMatriculaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> readAll() throws Exception{

        List<MatriculaDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<MatriculaDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Matricula obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList( convertToDto(obj))) );
    }

    @GetMapping("/relacionCE")
    public ResponseEntity<Map<String, List<String>>> getRelacionCursoEstudiantes(){
        Map<String, List<String>> listaCE = service.mostrarCursoEtudiantes();
        return new ResponseEntity<>(listaCE, HttpStatus.OK);
    }

    @GetMapping("/relacionCECorto")
    public ResponseEntity<Map<String, List<String>>> getRelacionCursoEstudiantesCorto(){
        Map<String, List<String>> listaCEC = service.mostrarCursoEtudiantesCorto();
        return new ResponseEntity<>(listaCEC, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> save(@Valid @RequestBody MatriculaDTO dto) throws Exception{
        Matricula obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO dto, @PathVariable("id") Integer id) throws Exception{
        Matricula obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

/*    @GetMapping("/realcionCE")
    public ResponseEntity<Void> getRelacion(){
        service.mostrarCursoEtudiantes();
        return new ResponseEntity<>(HttpStatus.OK);
    }*/


    private MatriculaDTO convertToDto(Matricula obj){
        return modelMapper.map(obj, MatriculaDTO.class);
    }

    private Matricula convertToEntity(MatriculaDTO dto){
        return modelMapper.map(dto, Matricula.class);
    }
}
